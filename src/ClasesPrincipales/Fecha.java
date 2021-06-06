/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesPrincipales;

/**
 *
 * @author Kevin Rojas
 */
public class Fecha {
    int dia;
    int mes;
    int año;
    
    public Fecha(int d, int m, int y){
        dia = d;
        mes = m;
        año = y;
    }
    
    // Métodos Getter 
    
    public int getDia() {
        return dia;
    }
    
    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }
    
    
}
