package ctwhs.order.model;

import android.content.Context;

import ctwhs.order.dao.MstCommonDao;
import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class DBModel {
    private static DBModel instance = null;

    protected DBModel() {
        // Exists only to defeat instantiation.
    }

    public static DBModel getInstance() {
        if(instance == null) {
            instance = new DBModel();
        }
        return instance;
    }

    public void initDbDefaultIfNotExists(Context context) {
        CommonModel commonModel = CommonModel.getInstance();
        commonModel.initDefault(context);
    }
}
