package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.RoleDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.entity.Role;
import bo.edu.ucb.barkibu.util.AuthUtil;
import bo.edu.ucb.barkibu.util.BarkibuException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static bo.edu.ucb.barkibu.util.ValidationUtil.isTimeBeforeNow;

@Service
public class SecurityBl {
    private final UserDao userDao;
    private final RoleDao roleDao;
//    static Integer expirationTime = 3000000;
    static Integer expirationTime = 60;


    public SecurityBl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public AuthResDto authenticate(AuthReqDto credentials) {
        AuthResDto result;
        // Verificamos que el usuario exista
        String currentPasswordInBCrypt = userDao.findPasswordByUserName(credentials.getUserName());
        if (currentPasswordInBCrypt == null) {
            throw new BarkibuException("SCTY-4000");
        }
        // Verificamos si el usuario esta bloqueado
        if(userDao.findFailedLoginTimeByUserName(credentials.getUserName()) != null) {
            if (!isTimeBeforeNow(userDao.findFailedLoginTimeByUserName(credentials.getUserName()))) {
                throw new BarkibuException("SCTY-3001");
            }
        }
        // Verificamos que la contraseña sea correcta
        BCrypt.Result verifyResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), currentPasswordInBCrypt);
        if (!verifyResult.verified) {
            // Si la contraseña es incorrecta, verificamos si es el primer intento fallido
            if (userDao.findFailedLoginAttemptsByUserName(credentials.getUserName()) == null) {
                // Si es el primer intento fallido, iniciamos el contador de intentos fallidos
                userDao.updateFailedLoginAttemptsByUserName(credentials.getUserName(), 1);
            }
            else {
                // Incrementamos el contador de intentos fallidos
                userDao.updateFailedLoginAttemptsByUserName(credentials.getUserName(), userDao.findFailedLoginAttemptsByUserName(credentials.getUserName()) + 1);
            }
            // Si la contraseña es incorrecta, verificamos si el usuario tiene 3 intentos fallidos
            if (userDao.findFailedLoginAttemptsByUserName(credentials.getUserName()) == 3) {
                // Si tiene 3 intentos fallidos, bloqueamos el usuario por 15 minutos
                userDao.updateFailedLoginTimeByUserName(credentials.getUserName(), new Date(new Date().getTime() + 15 * 60 * 1000));
                throw new BarkibuException("SCTY-3001");
            }
            throw new BarkibuException("SCTY-2000");
        }
        // Si la contraseña es correcta, limpiamos el contador de intentos fallidos y la fecha de bloqueo
        userDao.resetFailedLoginByUserName(credentials.getUserName());
        // Obtenemos los roles del usuario
        List<Role> roles = roleDao.findRolesByUserName(credentials.getUserName());
        List<String> rolesAsString= roles.stream().map(Role::getRoleName).toList();
        String [] rolesAsArray = rolesAsString.toArray(new String[0]);
        // Creamos el token
        result = generateTokenJwt(credentials.getUserName(),expirationTime, rolesAsArray);
        return result;
    }

    public AuthResDto verifyRefreshToken(String token) {
        AuthResDto result;
        if (!AuthUtil.isRefreshToken(token)) {
            throw new BarkibuException("SCTY-2001");
        }
        String userName = AuthUtil.getUserNameFromToken(token);
        List<Role> roles = roleDao.findRolesByUserName(userName);
        List<String> rolesAsString= roles.stream().map(Role::getRoleName).toList();
        String [] rolesAsArray = rolesAsString.toArray(new String[0]);
        result = generateTokenJwt(userName, expirationTime, rolesAsArray);
        return result;
    }

    private AuthResDto generateTokenJwt(String subject, Integer expirationTime, String [] roles) {
        AuthResDto result = new AuthResDto();
        try {
            Algorithm algorithm = Algorithm.HMAC256(AuthUtil.JWT_SECRET);
            String token = JWT.create()
                    .withIssuer("ucb")
                    .withSubject(subject)
                    .withArrayClaim("roles", roles)
                    .withClaim("refresh", false)
                    .withIssuedAt(java.util.Date.from(java.time.Instant.now()))
                    .withExpiresAt(java.util.Date.from(java.time.Instant.now().plusSeconds(expirationTime)))
                    .sign(algorithm);
            result.setToken(token);
            String refreshToken = JWT.create()
                    .withIssuer("ucb")
                    .withSubject(subject)
                    .withClaim("refresh", true)
                    .withIssuedAt(java.util.Date.from(java.time.Instant.now()))
                    .withExpiresAt(java.util.Date.from(java.time.Instant.now().plusSeconds(expirationTime * 2)))
                    .sign(algorithm);
            result.setRefreshToken(refreshToken);
        } catch (JWTCreationException exception) {
            throw new BarkibuException("SCTY-5000");
        }
        return result;
    }
}
