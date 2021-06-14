package TablaHash;

import ClasesPrincipales.Producto;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author Kevin Rojas
 */
public class TablaHash {
    LinkedList<Producto>[] tabla;
    int tamaño;
    int cantidadElementos;
    
    /**
     * Crea una tabla hash con un espacio máximo. Esta tabla hash utiliza el 
     * método de 'Resto de la División'. Y para lidiar con las colisiones se usa
     * el método de 'Encadenamiento de Sinónimos', por lo tanto, se implementa
     * como un arreglo que contiene en cada posición una lista enlazada de
     * objetos tipo Producto.
     * @param max
     */
    public TablaHash(int max){
        tamaño = max;
        tabla = new LinkedList[max];
        cantidadElementos = 0;
    }
    
    /**
     * Retorna el tamaño máximo de la tabla
     * @return tamaño
     */
    public int getTamaño(){
        return tamaño;
    }
    
    /**
     * Retorna la tabla
     * @return tabla
     */
    public LinkedList<Producto>[] getTabla(){
        return tabla;
    }
    
    /**
     * Determina si la tabla está vacía
     * @return boolean
     */
    public boolean esVacio(){
        return cantidadElementos == 0;
    }
    
    /**
     * Convierte la clave a su valor numérico y luego aplica el 'Resto de la 
     * División' para conseguir la posición asignada al Producto
     * @param clave
     * @return int
     */
    public int hash(String clave){
        int valorClave = 0;
        for(int i = 0; i < clave.length(); i++){
            valorClave = valorClave + Character.getNumericValue(clave.charAt(i));
        }
        return valorClave % tamaño;
    }
    
    /**
     * Ingresa un Producto en la tabla
     * @param clave
     * @param
     */
    private void ingresar(String clave, Producto valor){
        int indice = hash(clave);
        if(tabla[indice] == null){
            tabla[indice] = new LinkedList();
            tabla[indice].addLast(valor);
            cantidadElementos++;
        }
        else{
            int posicionProducto = tabla[indice].indexOf(valor);
            if(posicionProducto != -1){
                tabla[indice].set(posicionProducto, valor);
                cantidadElementos++;
            }
            else{
                tabla[indice].addLast(valor);
                cantidadElementos++;
            }
        }
    }
    
    /**
     * SOBRECARGA DE MÉTODOS
     * Llama a la función anterior con el producto y su referencia, para 
     * asegurarse de que el proceso de añadir se realizará el proceso con la 
     * clave adecuada
     * @param prod
     */
    public void ingresar(Producto prod){
        ingresar(prod.getReferencia(), prod);
    }
    
    /**
     * Busca el producto en la tabla
     * @param clave
     * @return Producto
     */
    public Producto buscar(String clave)throws Exception{
        int indice = hash(clave);
        Producto buscado = null;
        if(tabla[indice] == null){
            throw new Exception("Producto no existente.");
        }
        else{
            for(Producto p : tabla[indice]){
                if(p.getReferencia().equals(clave)){
                    buscado = p;
                }
            }
        }
        return buscado;
    }
    
    /**
     * Determina si ya existe una Producto en la tabla que posea una clave dada
     * @param clave
     * @return boolean
     */
    public boolean existe(String clave) {
        int indice = hash(clave);
        if(tabla[indice] == null){
            return false;
        }
        else{
            for(Producto p : tabla[indice]){
                if(p.getReferencia().equals(clave)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Elimina el Producto de la tabla
     * @param clave
     * @throws Exception
     */
    public void eliminar(String clave) throws Exception{
        Producto eliminado = buscar(clave);
        if(eliminado == null){
            throw new Exception("Clave de producto no existe.");
        }
        else{
            eliminado.setReferencia("*");
            ingresar(clave, eliminado);
            cantidadElementos--;
        }
    }
    
    /**
     * SOBRECARGA DE MÉTODOS
     * Llama a la función anterior con el Producto y su referencia, para 
     * asegurarse de que el proceso de eliminación se realiza con la clave
     * adecuada
     * @param prod
     * @throws Exception
     */
    public void eliminar(Producto prod) throws Exception{
        eliminar(prod.getReferencia());
    }
    
    /*
    * Retornar un String representando el objeto
    */
    @Override
    public String toString(){
        for(int i = 0; i < tamaño; i++){
            for(Producto p : tabla[i]){
                if(p != null){
                    if(p.getCantidad() > 0 && !p.getReferencia().equals("*")){
                        System.out.println(p.toString()+"\n");
                    }
                }
            }
        }
        return "";
    }
}
