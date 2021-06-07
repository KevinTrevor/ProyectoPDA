/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaHash;

import ClasesPrincipales.Producto;
import java.util.*;

/**
 *
 * @author Kevin Rojas
 */
public class TablaHash {
    LinkedList<Producto>[] tabla;
    int tamaño;
    
    public TablaHash(int max){
        tamaño = max;
        tabla = new LinkedList[max];
    }
    
    public int hash(String clave){
        int valorClave = 0;
        for(int i = 0; i < clave.length(); i++){
            valorClave = valorClave + Character.getNumericValue(clave.charAt(i));
        }
        return valorClave % tamaño;
    }
    
    public void ingresar(String clave, Producto valor){
        int indice = hash(clave);
        if(tabla[indice].isEmpty()){
            tabla[indice].addLast(valor);
        }
        else{
            int posicionProducto = tabla[indice].indexOf(valor);
            if(posicionProducto != -1){
                tabla[indice].get(posicionProducto).aumentarCantidad(valor);
            }
            else{
                tabla[indice].addLast(valor);
            }
        }
    }
    
    public Producto buscar(String clave) throws Exception{
        int indice = hash(clave);
        Producto buscado = null;
        if(tabla[indice].isEmpty()){
            throw new Exception("No hay elementos guardados en la posición determinada");
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
    
    public void eliminar(String clave){
    
    }
    
    public void modificar(){
        
    }
}
