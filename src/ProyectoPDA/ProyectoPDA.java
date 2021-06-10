package ProyectoPDA;

import ClasesPrincipales.*;
import TablaHash.*;
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
public class ProyectoPDA {

    public static void main(String[] args) {
        //TO-DO: Estos metodos pueden ir en su propia clase.
        
        File articulos = new File("./ARTICULOS.txt");
        TablaHash tabla = new TablaHash(20);
        LinkedList<Producto> vendidos2020 = new LinkedList();
        Inicializadores.rutinaInicial(articulos, tabla, vendidos2020);
        AplicacionPapeleria app = new AplicacionPapeleria(tabla, vendidos2020);
        app.correr();
        System.out.println("");
        Inicializadores.rutinaFinal(articulos, tabla);
        System.out.println("");
    }

    
}
