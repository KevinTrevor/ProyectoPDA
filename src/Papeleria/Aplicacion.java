package Papeleria;

import ClasesPrincipales.Fecha;
import ClasesPrincipales.Producto;
import TablaHash.TablaHash;
import java.util.LinkedList;
import java.util.Scanner;

public class Aplicacion {

    TablaHash tabla;
    LinkedList<Producto> vendidos2020;

    /*
    * Esta clase se encarga de la lógica principal del proyecto
    */
    
    /**
     * Crea un objeto de tipo Aplicación
     * @param tab
     * @param v2020
     */
    public Aplicacion(TablaHash tab, LinkedList<Producto> v2020) {
        tabla = tab;
        vendidos2020 = v2020;
    }

    /**
     * Es el método que corre el programa principal. Usa los menús, las entradas
     * y las demás clases.
     */
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

    /**
     * Añade un producto al programa. Lee todo los datos del mismo y luego llama
     * a la tabla hash
     * @throws Exception
     */
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

    /**
     * Muestra los datos de un producto específico. Para eso primero obtiene su
     * posición.
     */
    public void consultarProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);

        if (buscado != null) {
            System.out.println(buscado.getStringFormateado());
        } else {
            System.out.println("ERROR: Producto inexistente.");
        }
    }

    /**
     * Vende un producto. Para esto primero busca su posición y luego disminuye
     * su cantidad en existencia
     */
    public void venderProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);
        int cantidadAVender;
        boolean aceptado;

        if (buscado != null) {
            cantidadAVender = Entrada.inputI("Cantidad a vender (numero entero positivo): ");
            if (!Validador.validarPositivo(cantidadAVender)) {
                aceptado = false;
                while (!aceptado) {
                    System.out.println("ERROR: Debe introducir una cantidad válida");
                    cantidadAVender = Entrada.inputI("Cantidad a vender (numero entero positivo): ");
                    aceptado = Validador.validarPositivo(cantidadAVender);
                }
            }
            if (cantidadAVender <= buscado.getCantidad()) {
                Fecha fecha = Entrada.construirFecha("venta");
                buscado.setUltimaSalida(fecha);
                buscado.setCantidad(buscado.getCantidad() - cantidadAVender);
                System.out.println("Producto Vendido");
            } else {
                System.out.println("ERROR: Cantidad a vender sobrepasa existencia actual del producto.");
            }
        } else {
            System.out.println("ERROR: Producto inexistente");
        }
    }

    /**
     * Compra un producto al proveedor. Para esto primero busca su posición y 
     * luego aumenta su cantidad en existencia.
     */
    public void comprarProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);
        int cantidadAComprar;
        boolean aceptado;

        if (buscado != null) {
            cantidadAComprar = Entrada.inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
            if (!Validador.validarPositivo(cantidadAComprar)) {
                aceptado = false;
                while (!aceptado) {
                    System.out.println("ERROR: Debe introducir una cantidad válida");
                    cantidadAComprar = Entrada.inputI("Cantidad a comprar a proveedor (numero entero positivo): ");
                    aceptado = Validador.validarPositivo(cantidadAComprar);
                }
            }
            buscado.setCantidad(buscado.getCantidad() + cantidadAComprar);
            System.out.println("Producto Comprado");
        } else {
            System.out.println("ERROR: Producto inexistente");
        }
    }

    /**
     * Muestra los datos de todos los productos disponibles.
     */
    public void listarDisponibles() {
        if (tabla.esVacio()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("PRODUCTOS DISPONIBLES");
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

    /**
     * Muestra los datos de los productos vendidos por última vez en 2020.
     */
    public void listarVendidos2020() {
        if (vendidos2020.isEmpty()) {
            System.out.println("No se han vendido productos en 2020.");
        } else {
            System.out.println("PRODUCTOS VENDIDOS EN 2020");
            for (Producto e : vendidos2020) {
                System.out.println(e.getStringFormateado());
            }
        }
    }

    /**
     * Modifica los datos de un producto. Para esto primero busca su posición y
     * luego se vale de otras clases para interactuar con el usuario y actualizar
     * los datos.
     */
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
            System.out.println("ERROR: Producto inexistente");
        }
    }

    /**
     * Elimina un producto específico. Para esto primero busca su posición en la
     * tabla hash
     */
    public void eliminarProducto() {
        Producto buscado = Entrada.obtenerProducto(tabla);

        if (buscado != null) {
            try {
                tabla.eliminar(buscado);
                System.out.println("Producto Eliminado");
                if (buscado.getUltimaSalida().getAño() == 2020) {
                    vendidos2020.remove(buscado);
                }
            } catch (Exception ex) {
                System.out.println("ERRO: No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("ERROR: Producto inexistente.");
        }
    }

}
