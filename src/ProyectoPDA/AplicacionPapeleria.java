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
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AplicacionPapeleria {

    TablaHash tabla;
    LinkedList<Producto> vendidos2020;

    public AplicacionPapeleria(TablaHash tab, LinkedList<Producto> v2020) {
        tabla = tab;
        vendidos2020 = v2020;
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

    public int opcionMenuModificacion() {
        int seleccion = inputI("Seleccion: ");
        if (seleccion < 0 || seleccion > 5) {
            boolean aceptado = false;
            while (!aceptado) {
                seleccion = inputI("Seleccion ");
                aceptado = (seleccion >= 0) && (seleccion <= 5);
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
                    try {
                        añadirProducto();
                        mostrarMenu(false);
                        seleccion = opcionMenu();
                    } catch (Exception e) {
                        System.out.println("No se pudo añadir producto.");
                    }
                    break;

                case 2:
                    consultarProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 3:
                    venderProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 4:
                    comprarProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 5:
                    listarDisponibles();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 6:
                    listarVendidos2020();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 7:
                    modificarProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

                case 8:
                    eliminarProducto();
                    mostrarMenu(false);
                    seleccion = opcionMenu();
                    break;

            }
        }

    }

    public void añadirProducto() throws Exception {
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
        Producto prod = new Producto(referencia, nombreDistribuidor, nombreProducto, existencia, precio, fecha);
        if (prod.getUltimaSalida().getAño() == 2020) {
            vendidos2020.add(prod);
        }
        tabla.ingresar(prod);
    }

    public void consultarProducto() {
        Producto buscado = obtenerProducto();

        if (buscado != null) {
            System.out.println(buscado);
        } else {
            System.out.println("Producto inexistente.");
        }
    }

    public void venderProducto() {
        Producto buscado = obtenerProducto();
        int cantidadAVender;
        boolean aceptado;

        if (buscado != null) {
            cantidadAVender = inputI("Cantidad a vender (numero entero positivo): ");
            if (!validarPositivo(cantidadAVender)) {
                aceptado = false;
                while (!aceptado) {
                    cantidadAVender = inputI("Cantidad a vender (numero entero positivo): ");
                    aceptado = validarPositivo(cantidadAVender);
                }
            }
            if (cantidadAVender <= buscado.getCantidad()) {
                Fecha fecha = construirFecha("venta");
                buscado.setUltimaSalida(fecha);
                buscado.setCantidad(buscado.getCantidad() - cantidadAVender);
            } else {
                System.out.println("Cantidad a vender sobrepasa existencia actual del producto.");
            }
        } else {
            System.out.println("Producto inexistente");
        }
    }

    public void comprarProducto() {
        Producto buscado = obtenerProducto();
        int cantidadAComprar;
        boolean aceptado;

        if (buscado != null) {
            cantidadAComprar = inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
            if (!validarPositivo(cantidadAComprar)) {
                aceptado = false;
                while (!aceptado) {
                    cantidadAComprar = inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
                    aceptado = validarPositivo(cantidadAComprar);
                }
            }
            buscado.setCantidad(buscado.getCantidad() + cantidadAComprar);
        } else {
            System.out.println("Producto inexistente");
        }
    }

    public void listarDisponibles() {
        if (tabla.esVacio()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("PRODUCTOS DISPONIBLES");
            System.out.println("REF. DIST. NOM. EXIST. PREC. FECH.");
            int n = tabla.getTamaño();
            LinkedList<Producto>[] tablaInterna = tabla.getTabla();
            for (int i = 0; i < n; i++) {
                if (tablaInterna[i] != null) {
                    for (Producto e : tablaInterna[i]) {
                        if (!e.getReferencia().equals("*")) {
                            System.out.println(e.getStringFormateado());
                        }
                    }
                }
            }
        }
    }

    public void listarVendidos2020() {
        if (vendidos2020.isEmpty()) {
            System.out.println("No se han vendido productos en 2020.");
        } else {
            System.out.println("PRODUCTOS VENDIDOS EN 2020");
            System.out.println("REF. DIST. NOM. EXIST. PREC. FECH.");
            for (Producto e : vendidos2020) {
                System.out.println(e.getStringFormateado());
            }
        }
    }

    public void modificarProducto() {
        Producto buscado = obtenerProducto();
        boolean aceptado;
        String entrada;
        Scanner scanner = new Scanner(System.in);

        if (buscado != null) {
            boolean salir = false;
            mostrarMenuModificacion(true, buscado);
            int seleccion = opcionMenuModificacion();
            while (!salir) {
                switch (seleccion) {
                    case 0:
                        salir = true;
                        break;

                    case 1:
                        String nuevoDistribuidor;
                        System.out.println("");
                        System.out.print("Nombre de la distribuidora (25 caracteres max.): ");
                        entrada = scanner.nextLine();
                        if (entrada.length() > 25) {
                            entrada = entrada.substring(0, 24);
                            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
                        }
                        nuevoDistribuidor = entrada;
                        buscado.setDistribuidor(nuevoDistribuidor);
                        mostrarMenuModificacion(false, buscado);
                        seleccion = opcionMenuModificacion();
                        break;

                    case 2:
                        String nuevoNombreProducto;
                        System.out.println("");
                        System.out.print("Nombre de producto (30 caracteres max.): ");
                        entrada = scanner.nextLine();
                        if (entrada.length() > 30) {
                            entrada = entrada.substring(0, 29);
                            System.out.println("Limite de caracteres excedido. Nombre aceptado: " + entrada);
                        }
                        nuevoNombreProducto = entrada;
                        buscado.setNombreProducto(nuevoNombreProducto);
                        mostrarMenuModificacion(false, buscado);
                        seleccion = opcionMenuModificacion();
                        break;

                    case 3:
                        int nuevaExistencia;
                        nuevaExistencia = inputI("Existencia (numero entero positivo): ");
                        if (!validarPositivo(nuevaExistencia)) {
                            aceptado = false;
                            while (!aceptado) {
                                nuevaExistencia = inputI("Existencia (numero entero positivo): ");
                                aceptado = validarPositivo(nuevaExistencia);
                            }
                        }
                        buscado.setCantidad(nuevaExistencia);
                        mostrarMenuModificacion(false, buscado);
                        seleccion = opcionMenuModificacion();
                        break;

                    case 4:
                        double nuevoPrecio;
                        nuevoPrecio = inputD("Precio (numero decimal): ");
                        if (!validarPositivo(nuevoPrecio)) {
                            aceptado = false;
                            while (!aceptado) {
                                nuevoPrecio = inputD("Precio (numero decimal): ");
                                aceptado = validarPositivo(nuevoPrecio);
                            }
                        }
                        buscado.setPrecio(nuevoPrecio);
                        mostrarMenuModificacion(false, buscado);
                        seleccion = opcionMenuModificacion();
                        break;

                    case 5:
                        Fecha fecha = construirFecha("general");
                        buscado.setUltimaSalida(fecha);
                        mostrarMenuModificacion(false, buscado);
                        seleccion = opcionMenuModificacion();
                        break;
                }
            }
            if ((buscado.getUltimaSalida().getAño() == 2020) && !vendidos2020.contains(buscado)) {
                vendidos2020.add(buscado);
            } else if ((buscado.getUltimaSalida().getAño() != 2020) && vendidos2020.contains(buscado)) {
                vendidos2020.remove(buscado);
            }

        } else {
            System.out.println("Producto inexistente");
        }
    }

    public void eliminarProducto() {
        Producto buscado = obtenerProducto();

        if (buscado != null) {
            try {
                tabla.eliminar(buscado);
                if (buscado.getUltimaSalida().getAño() == 2020) {
                    vendidos2020.remove(buscado);
                }
            } catch (Exception ex) {
                System.out.println("No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("Producto inexistente.");
        }
    }

    /* Funciones de menu. */
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

    public void mostrarMenuModificacion(boolean imprimirCabecera, Producto prod) {
        if (imprimirCabecera) {
            System.out.println("MODIFICANDO PRODUCTO: " + prod.getNombreProducto() + " (" + prod.getReferencia() + ")");
        }
        System.out.println("");
        System.out.println("1. Modificar nombre del distribuidor");
        System.out.println("2. Modificar nombre del producto");
        System.out.println("3. Modificar existencia");
        System.out.println("4. Modificar precio");
        System.out.println("5. Modificar fecha de ultima salida/venta");
        System.out.println("0. Salir");
        System.out.println("");
    }

    /* Funciones de revisión. */
    static boolean revisarFecha(String date) {
        String patron = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean bandera = false;
        if (date.matches(patron)) {
            bandera = true;
        }
        return bandera;
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

    public Fecha construirFecha(String modo) {
        Fecha retornable;

        Scanner sc = new Scanner(System.in);
        boolean aceptado;

        if (modo.equals("general")) {
            System.out.println("Introducir fecha (dd/mm/yyyy): ");
        } else if (modo.equals("venta")) {
            System.out.println("Introducir fecha de venta (dd/mm/yyyy): ");
        }
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
        System.out.print(mensaje);
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
        System.out.print(mensaje);
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

    public Producto obtenerProducto() {
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
