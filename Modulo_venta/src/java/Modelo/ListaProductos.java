/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author jhona
 */
public class ListaProductos {
    private ArrayList<Producto> lista=new ArrayList<Producto>();
    public void addProducto(Producto pro){
        lista.add(pro);
    }
    public ArrayList<Producto> getLista(){
        return lista;
    }
    public void setLista(ArrayList<Producto> lista){
        this.lista=lista;
    }
    public int size(){
        return lista.size();
    }
}
