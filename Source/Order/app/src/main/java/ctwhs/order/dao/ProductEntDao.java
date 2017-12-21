package ctwhs.order.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.table.TableUtils;

import java.util.List;
import java.util.concurrent.Callable;

import ctwhs.order.entity.ProductEnt;
import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 3/28/2017.
 */

public class ProductEntDao extends DBHelper {
    // the DAO object we use to access the SimpleData table
    private Dao<ProductEnt, Integer> simpleDao = null;
    private RuntimeExceptionDao<ProductEnt, Integer> simpleRuntimeDao = null;

    private Context context;

    /**
     * Constructor
     * @param context
     */
   public ProductEntDao(Context context) {
        super(context);
    }

    /**
     * add new product - foods
     * @param entity
     * @throws java.sql.SQLException
     */
    public void addProduct(final ProductEnt entity) throws java.sql.SQLException {
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

    /**
     * update product
     * @param entity
     * @throws java.sql.SQLException
     */
    public void updProduct(final ProductEnt entity) throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        TransactionManager.callInTransaction(connectionSource,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        simpleDao.update(entity);
                        return null;
                    }
                }
        );
    }

    public void delProductEnt(final ProductEnt entity) throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        TransactionManager.callInTransaction(connectionSource,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        simpleDao.delete(entity);
                        return null;
                    }
                }
        );
    }

    public List<ProductEnt> queryAll() throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        return simpleDao.queryForAll();
    }

    /**
     * get Product entity by Id
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public ProductEnt getProductEntById(int id) throws java.sql.SQLException {
        if(simpleDao == null){
            getDao();
        }
        return simpleDao.queryForId(id);
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<ProductEnt, Integer> getDao() throws SQLException, java.sql.SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(ProductEnt.class);
        }
        return simpleDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<ProductEnt, Integer> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(ProductEnt.class);
        }
        return simpleRuntimeDao;
    }

    /**
     * drop table
     * @throws java.sql.SQLException
     */
    public void dropTableProdEnt() throws java.sql.SQLException {
        TableUtils.dropTable(connectionSource, ProductEnt.class, false);
    }

    /**
     * create table
     * @throws java.sql.SQLException
     */
    public void createTableTypeEnt() throws java.sql.SQLException {
        TableUtils.createTable(connectionSource, ProductEnt.class);
    }
}
