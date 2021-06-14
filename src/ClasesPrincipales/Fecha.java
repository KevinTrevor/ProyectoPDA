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
    private int dia;
    private int mes;
    private int año;
    
    /**
     * Crea un objeto fecha
     * @param d dia
     * @param m mes
     * @param y año
     */
    public Fecha(int d, int m, int y){
        dia = d;
        mes = m;
        año = y;
    }
    
    // Métodos Getter 

    /**
     * Devuelve el día del objeto
     * @return dia
     */
    
    public int getDia() {
        return dia;
    }
    
    /**
     * Devuelve el mes del objeto
     * @return mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * Devuelve el año del objeto
     * @return
     */
    public int getAño() {
        return año;
    }
    
    // Métodos Setter

    /**
     * Establece el día del objeto
     * @param d dia
     */

    public void setDia(int d) {
        this.dia = d;
    }

    /**
     * Establece el mes del objeto
     * @param m mes
     */
    public void setMes(int m) {
        this.mes = m;
    }

    /**
     * Establece el año del objeto
     * @param y año
     */
    public void setAño(int y) {
        this.año = y;
    }
    
    /*
    * Retornar un String representando el objeto
    */
    @Override
    public String toString(){
        return dia + "/" + mes + "/" + año;
    }
}
