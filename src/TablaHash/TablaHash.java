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
    
    /**
     *
     * @param max
     */
    public TablaHash(int max){
        tamaño = max;
        tabla = new LinkedList[max];
    }
    
    /**
     *
     * @param clave
     * @return
     */
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
    public void ingresar(String clave, Producto valor){
        int indice = hash(clave);
        if(tabla[indice].isEmpty()){
            tabla[indice].addLast(valor);
        }
        else{
            int posicionProducto = tabla[indice].indexOf(valor);
            if(posicionProducto != -1){
                tabla[indice].set(posicionProducto, valor);
            }
            else{
                tabla[indice].addLast(valor);
            }
        }
    }
    
    /**
     *
     * @param clave
     * @return
     * @throws Exception
     */
    public Producto buscar(String clave) throws Exception{
        int indice = hash(clave);
        Producto buscado = null;
        if(tabla[indice].isEmpty()){
            throw new Exception("No hay elementos guardados en la posición determinada.");
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
        }
    }
}
