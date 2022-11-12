package bo.edu.ucb.barkibu.util;

import java.util.Date;

public class ValidationUtil {
    // Validamos si el formato del correo es correcto
    public static boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    // Validamos si la fecha es posterior a la fecha actual
    public static boolean isDateAfterToday(Date date) {
        Date today = new Date();
        return date.after(today);
    }
}
