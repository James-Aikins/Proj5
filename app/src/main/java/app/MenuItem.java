package app;

import java.io.Serializable;

/**
 * parent class of donut and coffee with getter and setter methods
 * @author Michael Radoian, James Aikens
 */
public class MenuItem implements Serializable {
    private double price;
    private int quantity;

    /**
     * default constructor of menu Item
     */
    public MenuItem(){
    }

    /**
     * menu item constructor that sets a price of a menu item
     * @param price menu price
     */
    public MenuItem(double price){
        this.price = price;
    }

    /**
     * gets the price of a menuItem
     * @return returns the price
     */
    public double getPrice(){
        return price;
    }

    /**
     * sets the price of a menu Item
     * @param price desired price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * sets the quantity of a menu item
     * @param quantity desired quantity
     */
    public void setQuantity(int quantity){this.quantity = quantity; }


    /**
     * gets the quantity of a menuItem
     * @return quantity of item
     */
    public int getQuantity(){return quantity;}
}
