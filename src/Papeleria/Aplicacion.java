package Papeleria;

import ClasesPrincipales.Fecha;
import ClasesPrincipales.Producto;
import TablaHash.TablaHash;
import java.util.LinkedList;
import java.util.Scanner;

public class Aplicacion {

    TablaHash tabla;
    LinkedList<Producto> vendidos2020;

    public Aplicacion(TablaHash tab, LinkedList<Producto> v2020) {
        tabla = tab;
        vendidos2020 = v2020;
    }

    public void correr() {
        Menu.mostrarMenu(true);
        int seleccion = Menu.opcionMenu();
        boolean salir = false;
        while (!salir) {
            switch (seleccion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    try {
                        añadirProducto();
                        Menu.mostrarMenu(false);
                        seleccion = Menu.opcionMenu();
                    } catch (Exception e) {
                        System.out.println("No se pudo añadir producto.");
                    }
                    break;

                case 2:
                    consultarProducto();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 3:
                    venderProducto();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 4:
                    comprarProducto();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 5:
                    listarDisponibles();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 6:
                    listarVendidos2020();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 7:
                    modificarProducto();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

                case 8:
                    eliminarProducto();
                    Menu.mostrarMenu(false);
                    seleccion = Menu.opcionMenu();
                    break;

            }
        }

    }

    public void añadirProducto() throws Exception {
        Scanner scanner = new Scanner(System.in);

        String referencia = Entrada.pedirReferencia(scanner, tabla);
        String nombreDistribuidor = Entrada.pedirDistribuidora(scanner);
        String nombreProducto = Entrada.pedirNombreProducto(scanner);
        int existencia = Entrada.pedirExistencia(scanner);
        double precio = Entrada.pedirPrecio(scanner);
        Fecha fecha = Entrada.pedirFecha(scanner);
        
        Producto prod = new Producto(referencia, nombreDistribuidor, nombreProducto, existencia, precio, fecha);
        if (prod.getUltimaSalida().getAño() == 2020) {
            vendidos2020.add(prod);
        }
        tabla.ingresar(prod);
    }

    public void consultarProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);

        if (buscado != null) {
            System.out.println(buscado);
        } else {
            System.out.println("Producto inexistente.");
        }
    }

    public void venderProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);
        int cantidadAVender;
        boolean aceptado;

        if (buscado != null) {
            cantidadAVender = Entrada.inputI("Cantidad a vender (numero entero positivo): ");
            if (!Validador.validarPositivo(cantidadAVender)) {
                aceptado = false;
                while (!aceptado) {
                    cantidadAVender = Entrada.inputI("Cantidad a vender (numero entero positivo): ");
                    aceptado = Validador.validarPositivo(cantidadAVender);
                }
            }
            if (cantidadAVender <= buscado.getCantidad()) {
                Fecha fecha = Entrada.construirFecha("venta");
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
        Producto buscado = Entrada.obtenerProducto(tabla);
        int cantidadAComprar;
        boolean aceptado;

        if (buscado != null) {
            cantidadAComprar = Entrada.inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
            if (!Validador.validarPositivo(cantidadAComprar)) {
                aceptado = false;
                while (!aceptado) {
                    cantidadAComprar = Entrada.inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
                    aceptado = Validador.validarPositivo(cantidadAComprar);
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
        Producto buscado = Entrada.obtenerProducto(tabla);
        boolean aceptado;
        String entrada;
        Scanner scanner = new Scanner(System.in);
        if (buscado != null) {
            boolean salir = false;
            Menu.mostrarMenuModificacion(true, buscado);
            int seleccion = Menu.opcionMenuModificacion();
            while (!salir) {
                switch (seleccion) {
                    case 0:
                        salir = true;
                        break;

                    case 1:
                        String nuevoDistribuidor = Entrada.pedirDistribuidora(scanner);
                        buscado.setDistribuidor(nuevoDistribuidor);
                        Menu.mostrarMenuModificacion(false, buscado);
                        seleccion = Menu.opcionMenuModificacion();
                        break;

                    case 2:
                        String nuevoNombreProducto = Entrada.pedirNombreProducto(scanner);
                        buscado.setNombreProducto(nuevoNombreProducto);
                        Menu.mostrarMenuModificacion(false, buscado);
                        seleccion = Menu.opcionMenuModificacion();
                        break;

                    case 3:
                        int nuevaExistencia = Entrada.pedirExistencia(scanner);
                        buscado.setCantidad(nuevaExistencia);
                        Menu.mostrarMenuModificacion(false, buscado);
                        seleccion = Menu.opcionMenuModificacion();
                        break;

                    case 4:
                        double nuevoPrecio = Entrada.pedirPrecio(scanner);
                        buscado.setPrecio(nuevoPrecio);
                        Menu.mostrarMenuModificacion(false, buscado);
                        seleccion = Menu.opcionMenuModificacion();
                        break;

                    case 5:
                        Fecha fecha = Entrada.construirFecha("general");
                        buscado.setUltimaSalida(fecha);
                        Menu.mostrarMenuModificacion(false, buscado);
                        seleccion = Menu.opcionMenuModificacion();
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
        Producto buscado = Entrada.obtenerProducto(tabla);

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

}
