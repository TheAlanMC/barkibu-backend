package bo.edu.ucb.barkibu.util;

import java.util.Date;

public class ValidationUtil {
    // Verificamos si el formato del correo es correcto
    public static boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Verificamos si la fecha es posterior a la fecha actual
    public static boolean isTimeAfterNow(Date date) {
        Date now = new Date();
        return date.after(now);
    }
    // Verificamos si la fecha es anterior a la fecha actual
    public static boolean isTimeBeforeNow(Date date) {
        Date now = new Date();
        return date.before(now);
    }

    public static boolean userNameHasBlankSpaces(String userName) {
        return userName.contains(" ");
    }
}
