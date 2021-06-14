package Papeleria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Validador {
    
    /*
    * Esta clace estática se encarga de validar todos los datos introducidos
    * por el usuario.
    */

    
    /**
     * Valida que el dato sea un entero
     * @param entero
     * @return boolean
     */
    public static boolean validarEntero(String entero) {
        try {
            Integer.parseInt(entero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida que el dato sea un número decimal
     * @param decimal
     * @return boolean
     */
    public static boolean validarDecimal(String decimal) {
        try {
            Double.parseDouble(decimal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida que un número entero sea positivo
     * @param numero
     * @return boolean
     */
    public static boolean validarPositivo(int numero) {
        return numero >= 0;
    }

    /**
     * Valida que un número decimal (double) sea positivo
     * @param numero
     * @return
     */
    public static boolean validarPositivo(double numero) {
        return numero >= 0.0;
    }

    /**
     * Valida que la cadena introducida tenga el formato de fecha
     * @param date
     * @return boolean
     */
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
    
    /**
     * Valida que las fechas sean posibles. Es decr, que el día vaya de 1 a 31,
     * el mes de 1 a 12 y el año de 0 a 9999 (?
     * @param date
     * @return boolean
     */
    static boolean revisarFecha(String date) {
        String patron = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean bandera = false;
        if (date.matches(patron)) {
            bandera = true;
        }
        return bandera;
    }

}
