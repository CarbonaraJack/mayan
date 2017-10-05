/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

//import bean.itemBean;

import java.util.ArrayList;


/**
 *
 * @author Michela
 */
public class listaItems {
    private ArrayList<itemBean> items;
    private int index = 0;
    
    /*public void setItems(ArrayList<itemBean> items){
        this.items.addAll(items);
    }*/
    
    public void setItems(itemBean item){
        this.index = this.index + 1;
        this.items.add(item);
    }
    
    public ArrayList<itemBean> getItems(){
        return items;
    }
    
    public int getIndex(){
        return index;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
