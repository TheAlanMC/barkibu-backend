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

import java.util.List;

@Service
public class SecurityBl {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public SecurityBl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public AuthResDto authenticate(AuthReqDto credentials) {
        AuthResDto result;
        // Verificamos que el usuario exista
        String currentPasswordInBCrypt = userDao.findPasswordByUserName(credentials.getUserName());
        if (currentPasswordInBCrypt != null) {
            // Verificamos que la contraseña sea correcta
            BCrypt.Result verifyResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), currentPasswordInBCrypt);
            if (verifyResult.verified) {
                // Obtenemos los roles del usuario
                List<Role> roles = roleDao.findRolesByUserName(credentials.getUserName());
                List<String> rolesAsString= roles.stream().map(Role::getRoleName).toList();
                String [] rolesAsArray = rolesAsString.toArray(new String[0]);
                // Creamos el token
                // FIXME: CHANGE EXPIRATION TIME TO 300
                Integer expirationTime = 3000000;
                result = generateTokenJwt(credentials.getUserName(),expirationTime, rolesAsArray);
            } else {
                throw new BarkibuException("SCTY-2000");
            }
        } else {
            throw new BarkibuException("SCTY-4000");
        }
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
