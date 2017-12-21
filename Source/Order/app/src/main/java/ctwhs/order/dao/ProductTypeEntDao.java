package ctwhs.order.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.table.TableUtils;

import java.util.List;
import java.util.concurrent.Callable;

import ctwhs.order.entity.ProductTypeEnt;
import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 3/28/2017.
 */

public class ProductTypeEntDao extends DBHelper {
    // the DAO object we use to access the SimpleData table
    private Dao<ProductTypeEnt, Integer> simpleDao = null;
    private RuntimeExceptionDao<ProductTypeEnt, Integer> simpleRuntimeDao = null;

    private Context context;

    /**
     * Constructor
     * @param context
     */
   public ProductTypeEntDao(Context context) {
        super(context);
    }

    /**
     * add new product - foods
     * @param entity
     * @throws java.sql.SQLException
     */
    public void addProductType(final ProductTypeEnt entity) throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        TransactionManager.callInTransaction(connectionSource,
            new Callable<Void>() {
                public Void call() throws Exception {
                    simpleDao.create(entity);
                    return null;
                }
            }
        );
    }

    public List<ProductTypeEnt> queryAll() throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        return simpleDao.queryForAll();
    }

    public ProductTypeEnt getProductTypeEntById(int id) throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        return simpleDao.queryForId(id);
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<ProductTypeEnt, Integer> getDao() throws SQLException, java.sql.SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(ProductTypeEnt.class);
        }
        return simpleDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<ProductTypeEnt, Integer> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(ProductTypeEnt.class);
        }
        return simpleRuntimeDao;
    }

    /**
     * drop table
     * @throws java.sql.SQLException
     */
    public void dropTableProdTypeEnt() throws java.sql.SQLException {
        TableUtils.dropTable(connectionSource, ProductTypeEnt.class, false);
    }

    /**
     * create table
     * @throws java.sql.SQLException
     */
    public void createTableProdTypeEnt() throws java.sql.SQLException {
        TableUtils.createTable(connectionSource, ProductTypeEnt.class);
    }
}
