package ctwhs.order.model;

import android.content.Context;

import java.sql.SQLException;

import ctwhs.order.dao.MstCommonDao;
import ctwhs.order.entity.MstCommonEnt;
import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 12/21/2017.
 */
public class CommonModel {
    private static CommonModel instance = null;

    protected CommonModel() {
        // Exists only to defeat instantiation.
    }
    public static CommonModel getInstance() {
        if(instance == null) {
            instance = new CommonModel();
        }
        return instance;
    }

    /**
     * init default value for mst common dao
     * @param context
     */
    public void initDefault(Context context) {
        MstCommonDao mstCommonDao = MstCommonDao.getInstance(context);
        try {
            mstCommonDao.initDefault();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * check exist DB ORM LITEQ
     * @param context
     * @return
     */
    public boolean isExistDb(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);

        if (dbHelper.getWritableDatabase() != null) {
            return true;
        }

        return false;
    }
}
