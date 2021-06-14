package Papeleria;

import TablaHash.TablaHash;
import ClasesPrincipales.Producto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JESUS
 */
public class Papeleria {
    //Método Main
    public static void main(String[] args) {
        
        File articulos = new File("./ARTICULOS.txt");
        TablaHash tabla = new TablaHash(20);
        LinkedList<Producto> vendidos2020 = new LinkedList();
        Inicializador.rutinaInicial(articulos, tabla, vendidos2020);
        Aplicacion app = new Aplicacion(tabla, vendidos2020);
        app.correr();
        System.out.println("");
        Inicializador.rutinaFinal(articulos, tabla);
        System.out.println("");
    }

    
}
