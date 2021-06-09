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
    
    /**
     *
     * @param max
     */
    public TablaHash(int max){
        tamaño = max;
        tabla = new LinkedList[max];
    }
    
    public int getTamaño(){
        return tamaño;
    }
    
    public LinkedList<Producto>[] getTabla(){
        return tabla;
    }
    
    /**
     *
     * @param clave
     * @return
     */
    public int hash(String clave){
        BigInteger b = new BigInteger(String.valueOf(tamaño));
        int valorClave = 0;
        for(int i = 0; i < clave.length(); i++){
            valorClave = valorClave + Character.getNumericValue(clave.charAt(i));
        }
        return valorClave % Integer.parseInt(b.nextProbablePrime().toString());
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
    
    public void ingresar(Producto prod){
        ingresar(prod.getReferencia(), prod);
    }
    
    /**
     *
     * @param clave
     * @return
     */
    public Producto buscar(String clave){
        int indice = hash(clave);
        Producto buscado = null;
        if(tabla[indice] == null){
            return null;
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
    
    
    public boolean existe(String clave){
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
        }
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
