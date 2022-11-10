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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityBl {
    private UserDao userDao;
    private RoleDao roleDao;

    public SecurityBl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }
    //public UserDto getUserByPk (Integer userId) {
    //    User user = userDao.findByPrimaryKey(userId);
    //    UserDto userDto = new UserDto(user.getUserId(), user.getCityId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserName(), user.getPhotoPath(), user.getDescription());
    //    return userDto;
    //}

    public AuthResDto authenticate(AuthReqDto credentials) {
        AuthResDto result;
        String currentPasswordInBCrypt = userDao.findPasswordByUserName(credentials.getUserName());
        if (currentPasswordInBCrypt != null) {
            BCrypt.Result verifyResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), currentPasswordInBCrypt);
            if (verifyResult.verified) {
                List<Role> roles = roleDao.findRolesByUserName(credentials.getUserName());
                List<String> rolesAsString= roles.stream().map(Role::getRoleName).toList();
                String [] rolesAsArray = rolesAsString.toArray(new String[0]);
                result = generateTokenJwt(credentials.getUserName(),300, rolesAsArray);
            } else {
                throw new BarkibuException("Invalid credentials", "SCTY-2000", HttpStatus.UNAUTHORIZED);
            }
        } else {
            //TODO: SECURITY SHOW THAT USER DOES NOT EXIST?
            throw new BarkibuException("User not found", "SCTY-3000", HttpStatus.NOT_FOUND);
        }
        return result;
    }

    private AuthResDto generateTokenJwt(String subject, int expirationTime, String [] roles) {
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
            //TODO: INCLUDE GROUP?
        } catch (JWTCreationException exception) {
            throw new BarkibuException("Error generating token", "SCTY-4000", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
