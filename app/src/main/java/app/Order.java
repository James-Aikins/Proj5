package app;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Customizable, Parcelable {

    private int orderNumber;
    private static int nextNum = 1;
    private double totalPrice;
    private ArrayList<MenuItem> itemList;

    public Order() {
        itemList = new ArrayList<MenuItem>();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Order createFromParcel(Parcel in){
            return new Order();
        }
        public Order[] newArray(int size){
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            itemList.add((MenuItem) obj);
            totalPrice += ((MenuItem) obj).getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem && itemList.contains((MenuItem) obj)) {
            itemList.remove((MenuItem) obj);
            totalPrice -= ((MenuItem) obj).getPrice();
            return true;
        }
        return false;
    }

    public double totalPrice() {
        return totalPrice;
    }


    public ArrayList<MenuItem> getList() {
        return itemList;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(orderNumber);
        dest.writeDouble(totalPrice);
        dest.writeValue(itemList);
    }
    public String toString() {
        return Integer.toString(orderNumber);
    }

    public void setOrderNumber() {
        orderNumber = nextNum;
        nextNum++;
    }

    public void setPrice(double price) {
        totalPrice = price;
    }
}


