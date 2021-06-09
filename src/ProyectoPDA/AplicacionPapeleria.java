/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoPDA;

import ClasesPrincipales.*;
import TablaHash.TablaHash;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AplicacionPapeleria {

    TablaHash tabla;

    public AplicacionPapeleria(TablaHash tab) {
        tabla = tab;
    }

    public int opcionMenu() {
        int seleccion = inputI("Seleccion: ");
        if (seleccion < 0 || seleccion > 9) {
            boolean aceptado = false;
            while (!aceptado) {
                seleccion = inputI("Seleccion ");
                aceptado = (seleccion >= 0) && (seleccion <= 10);
            }
        }
        return seleccion;
    }

    public void correr() {
        mostrarMenu(true);
        int seleccion = opcionMenu();
        boolean salir = false;
        while (!salir) {
            switch (seleccion) {
                case 0:
                    salir = true;
                    break;

                case 1:
                    añadirProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;
            }
        }

    }

    public void añadirProducto() {
        Scanner scanner = new Scanner(System.in);
        String entrada;
        boolean aceptado;

        String referencia;
        String nombreDistribuidor;
        String nombreProducto;
        int existencia;
        double precio;

        Fecha fecha;

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

        referencia = entrada;

        System.out.println("");
        System.out.print("Nombre de la distribuidora (25 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 25) {
            entrada = entrada.substring(0, 24);
            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
        }
        nombreDistribuidor = entrada;

        System.out.println("");
        System.out.print("Nombre de producto (30 caracteres max.): ");
        entrada = scanner.nextLine();
        if (entrada.length() > 30) {
            entrada = entrada.substring(0, 29);
            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
        }
        nombreProducto = entrada;

        existencia = inputI("Existencia (numero entero positivo): ");
        if (!validarPositivo(existencia)) {
            aceptado = false;
            while (!aceptado) {
                existencia = inputI("Existencia (numero entero positivo): ");
                aceptado = validarPositivo(existencia);
            }
        }

        precio = inputD("Precio (numero decimal): ");
        if (!validarPositivo(precio)) {
            aceptado = false;
            while (!aceptado) {
                precio = inputD("Precio (numero decimal): ");
                aceptado = validarPositivo(precio);
            }
        }

        System.out.println("");
        System.out.print("¿Incluir fecha de venta? (s/n): ");
        entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("n")) {
            if (entrada.equalsIgnoreCase("s")) {
                fecha = construirFecha();
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
                fecha = construirFecha();
            } else {
                fecha = new Fecha(0, 0, 0);
            }
        }
        Producto prod = new Producto(referencia, nombreDistribuidor, nombreProducto, existencia, precio, fecha);
        tabla.ingresar(prod);
    }

    public Fecha construirFecha() {
        Fecha retornable;

        Scanner sc = new Scanner(System.in);
        boolean aceptado;

        System.out.println("Introducir fecha (dd/mm/yyyy): ");
        String fecha = sc.next();

        if (validarFecha(fecha)) {
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        } else {
            aceptado = false;
            while (!aceptado) {
                System.out.println("Introducir fecha (dd/mm/yyyy): ");
                fecha = sc.next();
                aceptado = validarFecha(fecha);
            }
            String[] argumentos = fecha.split("/");
            retornable = new Fecha(Integer.parseInt(argumentos[0]), Integer.parseInt(argumentos[1]), Integer.parseInt(argumentos[2]));
        }
        return retornable;
    }

    private boolean validarFecha(String date) {
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

    public void mostrarMenu(boolean imprimirCabecera) {
        if (imprimirCabecera) {
            System.out.println("SISTEMA DE INVENTARIO DE ARTICULOS DE PAPELERÍA");
            System.out.println("            LA PRINCIPAL ESTACIÓN              ");
        }
        System.out.println("");
        System.out.println("1. Agregar artículo");
        System.out.println("2. Consultar artículo");
        System.out.println("3. Vender artículo");
        System.out.println("4. Comprar artículo a proveedor");
        System.out.println("5. Listar artículos disponibles");
        System.out.println("6. Ver listado de artículos vendidos en 2020");
        System.out.println("7. Modificar datos de un artículo");
        System.out.println("8. Eliminar artículo del sistema");
        System.out.println("0. Salir");
        System.out.println("");
    }

    public boolean validarEntero(String entero) {
        try {
            Integer.parseInt(entero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarDecimal(String decimal) {
        try {
            Double.parseDouble(decimal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarPositivo(int numero) {
        return numero >= 0;
    }

    public boolean validarPositivo(double numero) {
        return numero >= 0.0;
    }

    public int inputI(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selección: ");
        String seleccion = scanner.nextLine();
        if (validarEntero(seleccion)) {
            System.out.println("");
            return Integer.parseInt(seleccion);
        } else {
            boolean aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.print(mensaje);
                aceptado = validarEntero(seleccion);
            }
            System.out.println("");
            return Integer.parseInt(seleccion);
        }
    }

    public double inputD(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selección: ");
        String seleccion = scanner.nextLine();
        if (validarDecimal(seleccion)) {
            System.out.println("");
            return Double.parseDouble(seleccion);
        } else {
            boolean aceptado = false;
            while (!aceptado) {
                System.out.println("");
                System.out.print(mensaje);
                aceptado = validarDecimal(seleccion);
            }
            System.out.println("");
            return Double.parseDouble(seleccion);
        }
    }
}
