package bo.edu.ucb.barkibu.util;

public class ValidationUtil {
    public static boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
