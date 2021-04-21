package app;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private double price;
    private int quantity;

    public MenuItem(){
    }

    public MenuItem(double price){
        this.price=price;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){this.quantity = quantity; }

    public int getQuantity(){return quantity;}
}
