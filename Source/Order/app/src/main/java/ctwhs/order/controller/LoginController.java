package ctwhs.order.controller;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class LoginController {
    private static LoginController instance = null;
    protected LoginController() {
        // Exists only to defeat instantiation.
    }
    public static LoginController getInstance() {
        if(instance == null) {
            instance = new LoginController();
        }
        return instance;
    }
}
