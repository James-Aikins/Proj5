package app;
import static app.values.*;

public class Donut extends MenuItem{
    private String flavor;
    private final double PRICE = 1.39;
    public Donut(int quantity, String flavor){
        setQuantity(quantity);
        this.flavor = flavor;
        double price = quantity*PRICE;

        setPrice(price);
    }

    public String toString(){

        return(getQuantity() + " " + flavor);
    }

    public String getFlavor(){
        return flavor;
    }


}
