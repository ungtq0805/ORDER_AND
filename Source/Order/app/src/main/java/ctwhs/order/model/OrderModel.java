package ctwhs.order.model;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class OrderModel {
    private static OrderModel instance = null;
    protected OrderModel() {
        // Exists only to defeat instantiation.
    }
    public static OrderModel getInstance() {
        if(instance == null) {
            instance = new OrderModel();
        }
        return instance;
    }
}
