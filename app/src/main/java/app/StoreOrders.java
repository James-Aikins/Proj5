package app;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Store order list that keeps track of every order in a observable list
 * @author James Aikens, Michael Radoian
 */
public class StoreOrders implements Customizable, Serializable {

    private ArrayList<Order> orderList;

    /**
     * default constructor that sets the orderlist to blank
     */
    public StoreOrders(){
        orderList = new ArrayList<Order>();
    }

    /**
     * adds an obj to the store order
     * @param obj desired obj to add
     * @return true if obj is a Order and false otherwise
     */
    @Override
    public boolean add(Object obj){
        if(obj instanceof Order){
            orderList.add((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * removes an object from the list
     * @param obj desired object to remove
     * @return true if obj is and order and in the list
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order && orderList.contains((Order) obj)){
            orderList.remove((Order) obj);
            return true;
        }
        return false;
    }

    /**
     * gets the store order list of orders
     * @return arrayList order list
     */
    public ArrayList<Order> getList(){
        return orderList;
    }
}
