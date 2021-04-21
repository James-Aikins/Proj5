package app;
import java.io.Serializable;

import static app.values.*;
/**
 * Coffee Object class which is used to keep track of the addOns
 * and size of each coffee
 * @author Michael Radoian, James Aikens
 */
public class Coffee extends MenuItem implements Customizable, Serializable {

    //cream, sugar, whipped cream
    private Boolean[] addOns;
    private String size;

    /**
     * default coffee constructor sets all addOns to false
     */
    public Coffee(){
        addOns = new Boolean[]{false,false,false};
    }


    /**
     * Coffee constructor that assigns the size,addOns, and quantity of a Coffee
     * @param size size of short,tall, grande, venti
     * @param addOns boolean array for if cream, sugar, whipped cream
     * @param quantity number of coffees
     */
    public Coffee(String size,Boolean[] addOns,int quantity){
        this.addOns = addOns;
        this.size = size;
        setQuantity(quantity);
    }


    /**
     * returns the price of the coffee based on add ons and size
     * @return the price
     */
    @Override
    public double getPrice(){
        double total;
        if(size.equals("Short")){
            total = SHORT_PRICE;
        }
        else if(size.equals("Tall")){
            total = TALL_PRICE;
        }
        else if(size.equals("Grande")){
            total = GRANDE_PRICE;
        }
        else{
            total = VENTI_PRICE;
        }

        for(int i = 0;i < ADDONS_NUM; i++){

            if(addOns[i]){
                total += ADD_ON_PRICE;
            }
        }
        return total;
    }

    /**
     * adds a add on to a coffee
     * @param index which add to add
     * @return true
     */
    public boolean add(Object index){
        addOns[(int)index] = true;
        return true;
    }

    /**
     * removes a add on
     * @param index which add on to remove
     * @return true
     */
    public boolean remove(Object index){
        addOns[(int)index] = false;
        return true;
    }

    /**
     * converts a coffee to a String in the format
     *  "quantity size add ons"
     * @return Coffee String
     */
    public String toString(){
        String addOnStr[] = {"Cream","Sugar","Whipped Cream"};
        String str = "";
        str += getQuantity() + " ";
        str += (size + " ");

        for(int i = 0; i < addOns.length; i++){
            if(addOns[i]){
                str += (addOnStr[i] + " ");
            }
        }

        return str;
    }

    /**
     * returns the Coffee size
     * @return the size of coffee
     */
    public String getSize(){
        return size;
    }

    /**
     * checks if two coffees have the same add ons
     * @param c coffee to compare too
     * @return true if equal false otherwise
     */
    public boolean equalAddOns(Coffee c){
        for(int i = 0; i < ADDONS_NUM; i++){
            if(addOns[i] != c.addOns[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * returns if the coffee has a certain add on
     * @param pos the position of the add on
     * @return true if it has it false otherwise
     */
    public boolean getAddon(int pos){
        return addOns[pos];
    }

    /**
     * inverses an add on
     * @param add the position to inverse
     */
   public void swapAddOn(int add){
        addOns[add] = !addOns[add];
   }



}
