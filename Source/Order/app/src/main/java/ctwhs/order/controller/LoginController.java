package ctwhs.order.controller;

import android.content.Context;

import java.io.File;

import ctwhs.order.constant.ConstConfigs;
import ctwhs.order.model.DBModel;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class LoginController {
    private static LoginController instance = null;
    private static DBModel dbModel;
    private static Context context;

    protected LoginController(final Context context) {
        // Exists only to defeat instantiation.
        this.context = context;
    }

    public static LoginController getInstance(final Context context) {
        if(instance == null) {
            instance = new LoginController(context);
            dbModel = DBModel.getInstance();
        }
        return instance;
    }

    /**
     * check DB and create
     */
    public void onCheckDbAndCreate() {
        /*if (!dbModel.isExistDb(context)) {
            dbModel.initDbDefaultIfNotExists(context);
        }*/
        File fileDb = context.getDatabasePath(ConstConfigs.CONST_DEFAULT_DB_PATH);
        if (!fileDb.exists()) {
            dbModel.initDbDefaultIfNotExists(context);
        }
    }
}
