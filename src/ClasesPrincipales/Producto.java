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
public class Producto {
    private String referencia;
    private String distribuidor;
    private String nombreProducto;
    private int cantidad;
    private double precio;
    private Fecha ultimaSalida;
    
    public Producto(String ref, String dis, String nom, int cant, double price, Fecha fe){
        referencia = ref;
        distribuidor = dis;
        nombreProducto = nom;
        cantidad = cant;
        precio = price;
        ultimaSalida = fe;
    }
    
    @Override
    public boolean equals(Object obj){
        Producto valor = (Producto) obj;
        return referencia.equals(valor.referencia);
    }
    
    public void aumentarCantidad(int aumento){
        cantidad = cantidad + aumento;
    }
    
    public void disminuirCantidad(int decremento){
        cantidad = cantidad - decremento;
    }
    
    @Override
    public String toString(){
        return ("Referencia del producto: "+referencia+
                "\nMarca del producto: "+distribuidor+
                "\nNombre del producto: "+nombreProducto +
                "\nCantidad del producto: "+cantidad);
    }
     // Métodos Getter 

    public String getReferencia() {
        return referencia;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public Fecha getUltimaSalida() {
        return ultimaSalida;
    }
    
    public String getStringFormateado(){
        return referencia+" "+distribuidor+" "+nombreProducto+" "+cantidad+" "+precio+" "+ultimaSalida;
    }
    
    // Métodos Setter

    public void setReferencia(String ref) {
        this.referencia = ref;
    }

    public void setDistribuidor(String dis) {
        this.distribuidor = dis;
    }

    public void setNombreProducto(String nom) {
        this.nombreProducto = nom;
    }

    public void setCantidad(int cant) {
        this.cantidad = cant;
    }

    public void setPrecio(double price) {
        this.precio = price;
    }

    public void setUltimaSalida(Fecha f) {
        this.ultimaSalida = f;
    }
}
