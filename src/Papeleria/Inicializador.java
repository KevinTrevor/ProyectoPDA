package Papeleria;

import ClasesPrincipales.Fecha;
import ClasesPrincipales.Producto;
import TablaHash.TablaHash;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author JESUS
 */
public class Inicializador {

    public static void rutinaInicial(File articulos, TablaHash tabla, LinkedList<Producto> vendidos2020) {
        try {
            articulos.createNewFile();
        } catch (IOException ex) {
            System.out.println("No se pudo crear archivo.");
        }

        if (articulos.length() > 0) {
            try {
                cargarInventario(articulos, tabla, vendidos2020);
            } catch (FileNotFoundException ex) {
                System.out.println("No se encontro el archivo");
            }
        }
    }

    public static void cargarInventario(File archivo, TablaHash tabla, LinkedList<Producto> vendidos2020) throws FileNotFoundException {
        Scanner escaner = new Scanner(archivo);
        while (escaner.hasNextLine()) {
            String[] parametros = escaner.nextLine().split(" ");
            Producto producto = parsearProducto(parametros);
            tabla.ingresar(producto);
            if (producto.getUltimaSalida().getAño() == 2020) {
                vendidos2020.add(producto);
            }
        }
    }

    public static Producto parsearProducto(String[] parametros) {
        Producto productoTemporal;
        String referencia = parametros[0];
        String nombreDistribuidora = parametros[1].replace('_', ' ');
        String nombreProducto = parametros[2].replace('_', ' ');
        int existencia = Integer.parseInt(parametros[3]);
        double precio = Double.parseDouble(parametros[4]);
        Fecha fecha = parsearFecha(parametros[5].split("/"));
        productoTemporal = new Producto(referencia, nombreDistribuidora, nombreProducto, existencia, precio, fecha);
        return productoTemporal;
    }

    public static Fecha parsearFecha(String[] fechaString) {
        Fecha fechaTemporal;
        fechaTemporal = new Fecha(Integer.parseInt(fechaString[0]), Integer.parseInt(fechaString[1]), Integer.parseInt(fechaString[2]));
        return fechaTemporal;
    }

    public static void rutinaFinal(File articulos, TablaHash tabla) {
        try {
            FileWriter fw = new FileWriter(articulos);
            try {
                articulos.createNewFile();
            } catch (IOException ex) {
                System.out.println("No se pudo crear archivo.");
            }
            actualizarArchivo(articulos, fw, tabla);
        } catch (IOException ex) {
            System.out.println("No se pudo leer el archivo.");
        }

    }

    public static void actualizarArchivo(File nuevoArchivo, FileWriter fw, TablaHash tabla) {
        int n = tabla.getTamaño();
        LinkedList<Producto>[] tablaInterna = tabla.getTabla();

        for (int i = 0; i < n; i++) {
            if (tablaInterna[i] != null) {
                for (Producto e : tablaInterna[i]) {
                    if (!e.getReferencia().equals("*")) {
                        String producto = codificarProducto(e);
                        try {
                            fw.write(producto);
                        } catch (IOException ex) {
                            System.out.println("No se pudo escribir en el archivo");
                        }
                    }
                }
            }
        }

        try {
            fw.close();
        } catch (IOException ex) {
            System.out.println("No se pudo cerrar el archivo.");
        }
    }

    public static String codificarProducto(Producto prod) {
        String referencia = prod.getReferencia();
        String nombreDistribuidor = prod.getDistribuidor().replace(' ', '_');
        String nombreProducto = prod.getNombreProducto().replace(' ', '_');
        int existencia = prod.getCantidad();
        double precio = prod.getPrecio();
        String fecha = prod.getUltimaSalida().toString();
        String productoCodificado = referencia + " " + nombreDistribuidor + " " + nombreProducto + " " + existencia + " " + precio + " " + fecha + "\n";
        return productoCodificado;
    }
}
