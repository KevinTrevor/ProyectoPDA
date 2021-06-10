package TablaHash;

import ClasesPrincipales.*;
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
     *
     * @param max
     */
    public TablaHash(int max){
        tamaño = max;
        tabla = new LinkedList[max];
        cantidadElementos = 0;
    }
    
    public int getTamaño(){
        return tamaño;
    }
    
    public LinkedList<Producto>[] getTabla(){
        return tabla;
    }
    
    public boolean esVacio(){
        return cantidadElementos == 0;
    }
    
    public int hash(String clave){
        int valorClave = 0;
        for(int i = 0; i < clave.length(); i++){
            valorClave = valorClave + Character.getNumericValue(clave.charAt(i));
        }
        return valorClave % tamaño;
    }
    
    /**
     *
     * @param clave
     * @param valor
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
    
    public void ingresar(Producto prod){
        ingresar(prod.getReferencia(), prod);
    }
    
    /**
     *
     * @param clave
     * @return
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
     *
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
    
    public void eliminar(Producto prod) throws Exception{
        eliminar(prod.getReferencia());
    }
    
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
