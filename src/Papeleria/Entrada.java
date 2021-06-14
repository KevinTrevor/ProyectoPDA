package Papeleria;

import ClasesPrincipales.Fecha;
import ClasesPrincipales.Producto;
import TablaHash.TablaHash;
import java.util.Scanner;

/**
 *
 * @author JESUS
 */
public class Entrada {
    
    /*
    * Esta clase se encarga leer todos los datos introducidos por el usuario.
    * Trabaja en conjunto con la clase 'Validador'
    */

    /**
     * Realiza la entrada de un número entero
     * @param mensaje
     * @return
     */    
    public static int inputI(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print(mensaje);
        String seleccion = scanner.nextLine();
        if (Validador.validarEntero(seleccion)) {
            System.out.println("");
            return Integer.parseInt(seleccion);
        } else {
            boolean aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.println("ERROR: Debe introducir un numero entero positivo");
                System.out.print(mensaje);
                seleccion = scanner.nextLine();
                aceptado = Validador.validarEntero(seleccion);
            }
            System.out.println("");
            return Integer.parseInt(seleccion);
        }
    }

    /**
     * Realiza la entrada de un número decimal (double)
     * @param mensaje
     * @return
     */
    public static double inputD(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        String seleccion = scanner.nextLine();
        if (Validador.validarDecimal(seleccion)) {
            System.out.println("");
            return Double.parseDouble(seleccion);
        } else {
            boolean aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.println("ERROR: Debe introducir un número decimal");
                System.out.print(mensaje);
                seleccion = scanner.nextLine();
                aceptado = Validador.validarDecimal(seleccion);
            }
            System.out.println("");
            return Double.parseDouble(seleccion);
        }
    }

    /**
     * Realiza la entrada de la referencia de un producot. En caso de que la 
     * cadena introducida supere el límite de caracteres, la corta. También 
     * evita que se introduzca una referencia ya existente
     * @param scanner
     * @param tabla
     * @return
     */
    public static String pedirReferencia(Scanner scanner, TablaHash tabla){
        String entrada;
        boolean aceptado;
        System.out.println("");
        System.out.print("Referencia del artículo (10 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 10) {
            entrada = entrada.substring(0, 9);
            System.out.println("Limite de caracteres excedido. Referencia aceptada: " + entrada);
        }
        if (tabla.existe(entrada)) {
            aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.print("ERROR: Articulo ya existente. Introducir referencia (10 caracteres max: ");
                entrada = scanner.nextLine();
                if (entrada.length() > 10) {
                    entrada = entrada.substring(0, 9);
                    System.out.println("Limite de caracteres excedido. Referencia aceptada: " + entrada);
                }
                aceptado = (!tabla.existe(entrada));
            }
        }
        return entrada;
    }
    
    /**
     * Realiza la entrada de la Distribuidora. En caso de que la cadena 
     * introducida supere el límite de caracteres, la corta.
     * @param scanner
     * @return
     */
    public static String pedirDistribuidora(Scanner scanner){
        String entrada;
        boolean aceptado;
        System.out.println("");
        System.out.print("Nombre de la distribuidora (25 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 25) {
            entrada = entrada.substring(0, 24);
            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
        }
        return entrada;
    }
    
    /**
     * Realiza la entrada de la Distribuidora. En caso de que la cadena 
     * introducida supere el límite de caracteres, la corta.
     * @param scanner
     * @return
     */
    public static String pedirNombreProducto(Scanner scanner){
        String entrada;
        boolean aceptado;
        System.out.println("");
        System.out.print("Nombre de producto (30 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 30) {
            entrada = entrada.substring(0, 29);
            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
        }
        return entrada;
    }
    
    /**
     * Realiza la entrada de la cantidad en existencia de un producto
     * @param scanner
     * @return
     */
    public static int pedirExistencia(Scanner scanner){
        int existencia;
        boolean aceptado;
        System.out.println();
        existencia = inputI("Existencia (numero entero positivo): ");
        if (!Validador.validarPositivo(existencia)) {
            aceptado = false;
            while (!aceptado) {
                System.out.println("ERROR: Debe introducir una número válido");
                existencia = inputI("Existencia (numero entero positivo): ");
                aceptado = Validador.validarPositivo(existencia);
            }
        }
        return existencia;
    }
    
    /**
     * Realiza la entrada del precio de un producto.
     * @param scanner
     * @return
     */
    public static double pedirPrecio(Scanner scanner){
        double precio;
        boolean aceptado;
        precio = inputD("Precio (numero decimal): ");
        if (!Validador.validarPositivo(precio)) {
            aceptado = false;
            while (!aceptado) {
                System.out.println("ERROR: Debe introducir un número válido");
                precio = inputD("Precio (numero decimal): ");
                aceptado = Validador.validarPositivo(precio);
            }
        }
        return precio;
    }
    
    /**
     * Lee una fecha en forma de cadena y crea un objeto a partir de la misma.
     * @param modo
     * @return
     */
    public static Fecha construirFecha(String modo) {
        Fecha retornable;

        Scanner sc = new Scanner(System.in);
        boolean aceptado;

        if (modo.equals("general")) {
            System.out.print("Introducir fecha (dd/mm/yyyy): ");
        } else if (modo.equals("venta")) {
            System.out.print("Introducir fecha de venta (dd/mm/yyyy): ");
        }
        String fecha = sc.next();

        if (Validador.validarFecha(fecha)) {
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        } else {
            aceptado = false;
            while (!aceptado) {
                System.out.print("ERROR: Debe introducir una cadena de tipo (dd/mm/yyyy): ");
                fecha = sc.next();
                aceptado = Validador.validarFecha(fecha);
            }
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        }
        return retornable;
    }
    
    /**
     * Pregunta si el usuario desea introducir una fecha. En caso positivo,
     * llama al método anterior para leerla y convertirla en un objeto. En caso
     * negativo, no hace nada.
     * @param scanner
     * @return
     */
    public static Fecha pedirFecha(Scanner scanner){
        String entrada;
        boolean aceptado;
        Fecha fecha;
        System.out.print("¿Incluir fecha de ultima venta? (s/n): ");
        entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("n")) {
            if (entrada.equalsIgnoreCase("s")) {
                fecha = construirFecha("venta");
            } else {
                fecha = new Fecha(0, 0, 0);
            }
        } else {
            aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.print("¿Incluir fecha de ultima venta? (s/n): ");
                aceptado = (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("n"));
            }
            if (entrada.equalsIgnoreCase("s")) {
                fecha = construirFecha("venta");
            } else {
                fecha = new Fecha(0, 0, 0);
            }
        }
        return fecha;
    }
    
    /**
     * Realiza la entrada de la referencia de un artícula y utiliza la función
     * hash para conseguir su posición.
     * @param tabla
     * @return
     */
    public static Producto obtenerProducto(TablaHash tabla) {
        String entrada;
        Scanner scanner = new Scanner(System.in);

        System.out.println("");
        System.out.print("Referencia del artículo (10 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 10) {
            entrada = entrada.substring(0, 9);
            System.out.println("Limite de caracteres excedido. Referencia aceptada: " + entrada);
        }

        if (entrada == "*") {
            return null;
        }

        try {
            Producto buscado = tabla.buscar(entrada);
            return buscado;
        } catch (Exception e) {
            return null;
        }
    }

}
