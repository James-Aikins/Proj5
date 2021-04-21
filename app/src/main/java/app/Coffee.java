package app;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static app.values.*;

public class Coffee extends MenuItem implements Customizable, Serializable {

    //cream, sugar, whipped cream
    private Boolean[] addOns;
    private String size;

    public Coffee(){
        addOns = new Boolean[]{false,false,false};
    }

    public Coffee(String size,Boolean[] addOns,int quantity){
        this.addOns = addOns;
        this.size=size;
        setQuantity(quantity);
    }


    @Override
    public double getPrice(){
        double total;
        if(size.equals("Short")){
            total=shortPrice;
        }
        else if(size.equals("Tall")){
            total=tallPrice;
        }
        else if(size.equals("Grande")){
            total = grandePrice;
        }
        else{
            total=ventiPrice;
        }

        for(int i = 0;i<ADDONS_NUM;i++){

            if(addOns[i]){
                total+=ADD_ON_PRICE;
            }
        }
        return total;
    }

    public boolean add(Object index){
        addOns[(int)index] = true;
        return true;
    }

    public boolean remove(Object index){
        addOns[(int)index] = false;
        return true;
    }

    public String toString(){
        String addOnStr[] = {"Cream","Sugar","Whipped Cream"};
        String str = "";
        str+= getQuantity() + " ";
        str+= (size + " ");

        for(int i=0; i<addOns.length; i++){
            if(addOns[i]){
                str += (addOnStr[i] + " ");
            }
        }

        return str;
    }

    public void setSize(String size){
        this.size = size;
    }
    public String getSize(){
        return size;
    }



    public boolean equalAddOns(Coffee c){
        for(int i=0; i<ADDONS_NUM;i++){
            if(addOns[i] != c.addOns[i]){
                return false;
            }
        }
        return true;
    }
    public boolean getAddon(int pos){
        return addOns[pos];
    }

   public void swapAddOn(int add){
        addOns[add] = !addOns[add];
   }



}
