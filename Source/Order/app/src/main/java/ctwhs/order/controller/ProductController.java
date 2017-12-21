package ctwhs.order.controller;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class ProductController {
    private static ProductController instance = null;
    protected ProductController() {
        // Exists only to defeat instantiation.
    }
    public static ProductController getInstance() {
        if(instance == null) {
            instance = new ProductController();
        }
        return instance;
    }
}
