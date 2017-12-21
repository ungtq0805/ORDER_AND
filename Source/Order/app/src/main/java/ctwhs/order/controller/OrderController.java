package ctwhs.order.controller;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class OrderController {
    private static OrderController instance = null;
    protected OrderController() {
        // Exists only to defeat instantiation.
    }
    public static OrderController getInstance() {
        if(instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
}
