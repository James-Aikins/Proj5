package app;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * order class the keeps track of all the
 * menu items in the current order
 * @author James Aikins, Michael Radoian
 */
public class Order implements Customizable, Serializable {

    private int orderNumber;
    private static int nextNum = 1;
    private ArrayList<MenuItem> itemList;

    /**
     * defualt order contructor that sets
     * the order to a blank list
     */
    public Order() {
        itemList = new ArrayList<>();
        setOrderNumber();
    }

    /**
     * adds a menu item to the order list
     * @param obj object to add to the list
     * @return if obj is a menu item true and false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            itemList.add((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * removes a obj from the order list
     * @param obj desired removed object
     * @return true if the obj is a menu item and in the
     * list and false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem && itemList.contains((MenuItem) obj)) {
            itemList.remove((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * returns the total price of the order
     * @return total price
     */
    public double totalPrice() {
        double tempPrice = 0;
        for(MenuItem m: itemList){
            tempPrice += (m.getPrice()*m.getQuantity());
        }
        return tempPrice;
    }

    /**
     * returns the list of menu items in the list
     * @return arraylist of menu items
     */
    public ArrayList<MenuItem> getList() {
        return itemList;
    }

    /**
     * converts the order number as a string
     * @return order number string
     */
    public String toString() {
        return Integer.toString(orderNumber);
    }


    /**
     * sets the order to the next num available number the
     * increments by 1 each order
     */
    public void setOrderNumber() {
        orderNumber = nextNum;
        nextNum++;
    }
    public int getOrderNumber(){
        return orderNumber;
    }

    /**
     * checks if an order is empty
     * @return true if empty false otherwise
     */
    public boolean isEmpty(){
        if(itemList.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * clears the order list
     */
    public void clear(){
        itemList.clear();
    }
}


