package Papeleria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Validador {
    
    public static boolean validarEntero(String entero) {
        try {
            Integer.parseInt(entero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarDecimal(String decimal) {
        try {
            Double.parseDouble(decimal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarPositivo(int numero) {
        return numero >= 0;
    }

    public static boolean validarPositivo(double numero) {
        return numero >= 0.0;
    }

    public static boolean validarFecha(String date) {
        boolean logrado = false;
        if (revisarFecha(date)) {
            DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            try {
                formatoFecha.parse(date);
                logrado = true;
            } catch (Exception e) {
                logrado = false;
            }
        }
        return logrado;
    }
    
    static boolean revisarFecha(String date) {
        String patron = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean bandera = false;
        if (date.matches(patron)) {
            bandera = true;
        }
        return bandera;
    }

}
