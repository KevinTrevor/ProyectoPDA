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
    String referencia;
    String distribuidor;
    String nombreProducto;
    int cantidad;
    double precio;
    Fecha ultimaSalida;
    
    public Producto(String ref, String dis, String nom, int cant, double price, Fecha fe){
        referencia = ref;
        distribuidor = dis;
        nombreProducto = nom;
        cantidad = cant;
        precio = price;
        ultimaSalida = fe;
    }
    
    @Override
    public boolean equals(Object o){
        Producto valor = (Producto) o;
        return referencia.equals(valor.referencia);
    }
    
    public void aumentarCantidad(int aumento){
        cantidad = cantidad + aumento;
    }
    
    public void disminuirCantidad(int decremento){
        cantidad = cantidad - decremento;
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
    
    // Métodos Setter

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setUltimaSalida(Fecha ultimaSalida) {
        this.ultimaSalida = ultimaSalida;
    }
    
    
}
