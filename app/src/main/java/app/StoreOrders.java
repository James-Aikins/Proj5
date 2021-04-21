package app;


import java.io.Serializable;
import java.util.ArrayList;

public class StoreOrders implements Customizable, Serializable {

    private ArrayList<Order> orderList;

    public StoreOrders(){
        orderList = new ArrayList<Order>();
    }

    @Override
    public boolean add(Object obj){
        if(obj instanceof Order){
            orderList.add((Order)obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order && orderList.contains((Order) obj)){
            orderList.remove((Order) obj);
            return true;
        }
        return false;
    }

    public ArrayList<Order> getList(){
        return orderList;
    }
}
