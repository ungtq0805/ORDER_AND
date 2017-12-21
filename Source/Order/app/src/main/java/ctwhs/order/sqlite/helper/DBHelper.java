package ctwhs.order.sqlite.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;

import ctwhs.order.entity.OrderDetailEnt;
import ctwhs.order.entity.OrderEnt;
import ctwhs.order.entity.ProductEnt;
import ctwhs.order.entity.ProductTypeEnt;
import ctwhs.order.entity.TableEnt;
import ctwhs.order.entity.TableOrderEnt;

/**
 * Created by UNGTQ on 3/28/2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    // Fields

//    public static final String DB_NAME = "order_manager.db";
//    private static final int DB_VERSION = 1;
//
//    // Public methods
//
//    public DBHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//        getWritableDatabase();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
//        try {
//
//            // Create Table with given table name with columnName
//            TableUtils.createTable(cs, ProductTypeEnt.class);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {
//
//    }

    // name of the database file for your application -- change to something appropriate for your app
    public static final String DATABASE_NAME = "helloAndroid.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DBHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, ProductTypeEnt.class);
            TableUtils.createTable(connectionSource, ProductEnt.class);
            TableUtils.createTable(connectionSource, OrderDetailEnt.class);
            TableUtils.createTable(connectionSource, OrderEnt.class);
            TableUtils.createTable(connectionSource, TableOrderEnt.class);
            TableUtils.createTable(connectionSource, TableEnt.class);

        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

//        // here we try inserting data in the on-create as a test
//        RuntimeExceptionDao<ProductTypeEnt, Integer> dao = getSimpleDataDao();
//        long millis = System.currentTimeMillis();
//        // create some entries in the onCreate
//        ProductTypeEnt simple = new ProductTypeEnt();
//        dao.create(simple);
//        simple = new SimpleData(millis + 1);
//        dao.create(simple);
//        Log.i(DBHelper.class.getName(), "created new entries in onCreate: " + millis);
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DBHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, ProductTypeEnt.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
//        simpleDao = null;
//        simpleRuntimeDao = null;
    }

    /**
     * check whether database exist on the device?
     *
     * @return true if existed
     */
    private boolean checkExistDataBase() {
        try {
            String myPath = DATABASE_NAME;
            File fileDB = new File(myPath);

            if (fileDB.exists()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
