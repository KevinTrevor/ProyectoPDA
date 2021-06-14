package Papeleria;

import ClasesPrincipales.Producto;

public class Menu {
    /*
    * Esta clase se encarga de los menús con los que el usuario interactua
    */

    /**
     * Muestra el menú principal con todas las opciones requeridas del proyecto
     * @param imprimirCabecera
     */
    public static void mostrarMenu(boolean imprimirCabecera) {
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
        System.out.println("0. Salir del Programa");
        System.out.println("");
    }

    /**
     * Muestra el menú de modificación de un Producto. Tiene una opción para 
     * cada atributo del mismo para que el usuario pueda elegir cual modificar
     * @param imprimirCabecera
     * @param prod
     */
    public static void mostrarMenuModificacion(boolean imprimirCabecera, Producto prod) {
        if (imprimirCabecera) {
            System.out.println("MODIFICANDO PRODUCTO: " + prod.getNombreProducto() + " (" + prod.getReferencia() + ")");
        }
        System.out.println("");
        System.out.println("1. Modificar nombre del distribuidor");
        System.out.println("2. Modificar nombre del producto");
        System.out.println("3. Modificar existencia");
        System.out.println("4. Modificar precio");
        System.out.println("5. Modificar fecha de ultima salida/venta");
        System.out.println("0. Salir del Menu de Modificacion");
        System.out.println("");
    }
    
    /**
     * Lee la opción elegida por el usuario en el menú principal. Asegurándose
     * de que esté dentro del rango [0-9]
     * @return int
     */
    public static int opcionMenu() {
        int seleccion = Entrada.inputI("Seleccion: ");
        if (seleccion < 0 || seleccion > 9) {
            boolean aceptado = false;
            while (!aceptado) {
                seleccion = Entrada.inputI("Seleccion ");
                aceptado = (seleccion >= 0) && (seleccion <= 10);
            }
        }
        return seleccion;
    }

    /**
     * Lee la opción elegida por el usuario en el menú de modificación. 
     * Asegurándose de que esté dentro del rango [0-5]
     * @return
     */
    public static int opcionMenuModificacion() {
        int seleccion = Entrada.inputI("Seleccion: ");
        if (seleccion < 0 || seleccion > 5) {
            boolean aceptado = false;
            while (!aceptado) {
                seleccion = Entrada.inputI("Seleccion ");
                aceptado = (seleccion >= 0) && (seleccion <= 5);
            }
        }
        return seleccion;
    }

}
