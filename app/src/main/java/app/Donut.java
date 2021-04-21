package app;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static app.values.*;

public class Donut extends MenuItem implements Parcelable, Serializable {
    private String flavor;
    private final double PRICE = 1.39;

    public Donut(int quantity, String flavor){
        setQuantity(quantity);
        this.flavor = flavor;
        double price = quantity*PRICE;

        setPrice(price);
    }
    public Donut(Parcel in){
        setQuantity(in.readInt());
        flavor = in.readString();
        setPrice(getQuantity()*PRICE);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Donut createFromParcel(Parcel in){
            return new Donut(in);
        }
        public Donut[] newArray(int size){
            return new Donut[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(getQuantity());
        dest.writeString(flavor);
        dest.writeDouble(getPrice());
    }
    public String toString(){

        return(getQuantity() + " " + flavor);
    }

    public String getFlavor(){
        return flavor;
    }


}
