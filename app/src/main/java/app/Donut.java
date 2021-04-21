package app;

import java.io.Serializable;
import static app.values.*;

/**
 * Donut class that keeps track of the flavor of donuts
 * @author Michael Radoian, James Aikens
 */
public class Donut extends MenuItem implements Serializable {
    private String flavor;


    /**
     * Donut Constructor that sets the type, quantity, and flavor
     * of a donut object
     * @param quantity number of that donut
     * @param flavor desired flavor
     */
    public Donut(int quantity, String flavor){
        setQuantity(quantity);
        this.flavor = flavor;
        double price = quantity * PRICE;

        setPrice(price);
    }
    /**
     * converts a donut to a String format of "quantity flavor type"
     * @return Donut string
     */
    public String toString(){

        return(getQuantity() + " " + flavor);
    }

    /**
     * returns the flavor of current donut
     * @return flavor of donut
     */
    public String getFlavor(){
        return flavor;
    }


}
