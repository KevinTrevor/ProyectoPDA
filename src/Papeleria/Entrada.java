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
    
    public static int inputI(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        String seleccion = scanner.nextLine();
        if (Validador.validarEntero(seleccion)) {
            System.out.println("");
            return Integer.parseInt(seleccion);
        } else {
            boolean aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.print(mensaje);
                aceptado = Validador.validarEntero(seleccion);
            }
            System.out.println("");
            return Integer.parseInt(seleccion);
        }
    }

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
                System.out.print(mensaje);
                aceptado = Validador.validarDecimal(seleccion);
            }
            System.out.println("");
            return Double.parseDouble(seleccion);
        }
    }

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
                System.out.print("Articulo existente. Introducir referencia (10 caracteres max: ");
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
    
    public static int pedirExistencia(Scanner scanner){
        int existencia;
        boolean aceptado;
        existencia = inputI("Existencia (numero entero positivo): ");
        if (!Validador.validarPositivo(existencia)) {
            aceptado = false;
            while (!aceptado) {
                existencia = inputI("Existencia (numero entero positivo): ");
                aceptado = Validador.validarPositivo(existencia);
            }
        }
        return existencia;
    }
    
    public static double pedirPrecio(Scanner scanner){
        double precio;
        boolean aceptado;
        precio = inputD("Precio (numero decimal): ");
        if (!Validador.validarPositivo(precio)) {
            aceptado = false;
            while (!aceptado) {
                precio = inputD("Precio (numero decimal): ");
                aceptado = Validador.validarPositivo(precio);
            }
        }
        return precio;
    }
    
    public static Fecha construirFecha(String modo) {
        Fecha retornable;

        Scanner sc = new Scanner(System.in);
        boolean aceptado;

        if (modo.equals("general")) {
            System.out.println("Introducir fecha (dd/mm/yyyy): ");
        } else if (modo.equals("venta")) {
            System.out.println("Introducir fecha de venta (dd/mm/yyyy): ");
        }
        String fecha = sc.next();

        if (Validador.validarFecha(fecha)) {
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        } else {
            aceptado = false;
            while (!aceptado) {
                System.out.println("Introducir fecha (dd/mm/yyyy): ");
                fecha = sc.next();
                aceptado = Validador.validarFecha(fecha);
            }
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        }
        return retornable;
    }
    
    public static Fecha pedirFecha(Scanner scanner){
        String entrada;
        boolean aceptado;
        Fecha fecha;
        System.out.println("");
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
