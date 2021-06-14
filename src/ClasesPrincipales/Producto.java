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
    
    /**
     * Crea un objeto producto
     * @param ref Referencia
     * @param dis Distribuidora
     * @param nom Nombre del Producto
     * @param cant Cantidad en existencia del Producto
     * @param price Precio del Producto
     * @param fe Fecha de última venta
     */
    public Producto(String ref, String dis, String nom, int cant, double price, Fecha fe){
        referencia = ref;
        distribuidor = dis;
        nombreProducto = nom;
        cantidad = cant;
        precio = price;
        ultimaSalida = fe;
    }
    
    /*
     * Determinar si dos productos comparten la misma referencia
     * @param obj
     */
    @Override
    public boolean equals(Object obj){
        Producto valor = (Producto) obj;
        return referencia.equals(valor.referencia);
    }
    
    /**
     * Aumenta la existencia del producto
     * @param aumento
     */
    public void aumentarCantidad(int aumento){
        cantidad = cantidad + aumento;
    }
    
    /**
     * Disminuye la existencia del producto
     * @param decremento
     */
    public void disminuirCantidad(int decremento){
        cantidad = cantidad - decremento;
    }
    
    /*
    * Retornar un String representando el objeto
    */
    @Override
    public String toString(){
        return ("Referencia del producto: "+referencia+
                "\nMarca del producto: "+distribuidor+
                "\nNombre del producto: "+nombreProducto +
                "\nCantidad del producto: "+cantidad);
    }
    
    
    
    // Métodos Getter 

    /**
     * Retornar la referencia del Producto
     * @return referencia
     */

    public String getReferencia() {
        return referencia;
    }

    /**
     * Retorna el distribuidor del Producto
     * @return distribuidor
     */
    public String getDistribuidor() {
        return distribuidor;
    }

    /**
     * Retornar el nombre del Producto
     * @return nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
      Retorna la cantidad en existencia del producto
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Retornar el precio del producto
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Retornar la fecha de última venta de este Producto
     * @return ultimaSalida
     */
    public Fecha getUltimaSalida() {
        return ultimaSalida;
    }
    
    /*
    * Retornar un String representando el objeto, pero de una manera más 
    * agradable para el usuario y apta para el listado de productos
    */
    public String getStringFormateado(){
        return "Ref: "+referencia+" || Dis: "+distribuidor+" || Nombre: "+nombreProducto+" || Cant: "+cantidad+" || Precio: "+precio+" || Ult: "+ultimaSalida;
    }
    
    // Métodos Setter

    /**
     * Establece la referencia de este producto
     * @param ref
     */

    public void setReferencia(String ref) {
        this.referencia = ref;
    }

    /**
     * Establece el distribuidor de este producto
     * @param dis
     */
    public void setDistribuidor(String dis) {
        this.distribuidor = dis;
    }

    /**
     * Establece el nombre de este Producto
     * @param nom
     */
    public void setNombreProducto(String nom) {
        this.nombreProducto = nom;
    }

    /**
     * Establece la cantidad en existencia de este Producto
     * @param cant
     */
    public void setCantidad(int cant) {
        this.cantidad = cant;
    }

    /**
     * Establece el precio de este producto
     * @param price
     */
    public void setPrecio(double price) {
        this.precio = price;
    }

    /**
     * Establece la fecha de la última venta
     * @param f
     */
    public void setUltimaSalida(Fecha f) {
        this.ultimaSalida = f;
    }
}
