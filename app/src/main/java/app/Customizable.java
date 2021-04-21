package app;

/**
 * Customizable interface for the order lists for easy adding and removing
 * from a class
 * @author James Aikins, Michael Radoian
 */
public interface Customizable {
    boolean add(Object obj);
    boolean remove(Object obj);
}
