package bo.edu.ucb.barkibu.bl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bo.edu.ucb.barkibu.dao.RoleDao;
import bo.edu.ucb.barkibu.dao.UserDao;
import bo.edu.ucb.barkibu.dto.AuthReqDto;
import bo.edu.ucb.barkibu.dto.AuthResDto;
import bo.edu.ucb.barkibu.dto.UserDto;
import bo.edu.ucb.barkibu.entity.Role;
import bo.edu.ucb.barkibu.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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
    public UserDto getUserByPk (Integer userId) {
        User user = userDao.findByPrimaryKey(userId);
        UserDto userDto = new UserDto(user.getUserId(), user.getCityId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserName(), user.getPhotoPath(), user.getDescription());
        return userDto;
    }

    public AuthResDto authenticate(AuthReqDto credentials) {
        AuthResDto result = new AuthResDto();
        String currentPasswordInBCrypt = userDao.findByUserName(credentials.getUserName());
        if (currentPasswordInBCrypt != null) {
            BCrypt.Result verifyResult = BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), currentPasswordInBCrypt);
            if (verifyResult.verified) {
                System.out.println("Password is correct");
                List<Role> roles = roleDao.findRolesByUserName(credentials.getUserName());
                List<String> rolesAsString= roles.stream().map(Role::getRoleName).toList();
                String [] rolesAsArray = rolesAsString.toArray(new String[0]);
                result = generateTokenJwt(credentials.getUserName(),300, rolesAsArray);
                System.out.println(result);
            } else {
                //TODO: FIX THROWING EXCEPTION
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            //TODO: FIX THROWING EXCEPTION
            throw new RuntimeException("User not found");
        }
        return result;
    }

    private AuthResDto generateTokenJwt(String subject, int expirationTime, String [] roles) {
        AuthResDto result = new AuthResDto();
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
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
            throw new RuntimeException("Error generating token", exception);
        }
        return result;
    }
}
