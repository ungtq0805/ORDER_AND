package ctwhs.order.dao;

import android.content.Context;
import android.database.SQLException;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.util.concurrent.Callable;

import ctwhs.common.utils.CalendarUtils;
import ctwhs.order.R;
import ctwhs.order.constant.ConstConfigs;
import ctwhs.order.entity.MstCommonEnt;
import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 3/28/2017.
 */

public class MstCommonDao extends DBHelper {
    // the DAO object we use to access the SimpleData table
    private Dao<MstCommonEnt, Integer> mstCommonDao = null;

    private Context context;
    private static MstCommonDao instance = null;

    /**
     * get instance model
     * @param context
     * @return
     */
    public static MstCommonDao getInstance(Context context) {
        if(instance == null) {
            instance = new MstCommonDao(context);
        }

        return instance;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<MstCommonEnt, Integer> getDao() throws SQLException, java.sql.SQLException {
        if (mstCommonDao == null) {
            mstCommonDao = getDao(MstCommonEnt.class);
        }
        return mstCommonDao;
    }


    /**
     * Constructor
     * @param context
     */
   public MstCommonDao(Context context) {
        super(context);
        this.context = context;
   }

   public void initDefault() throws java.sql.SQLException {
       mstCommonDao = getDao();

       TransactionManager.callInTransaction(connectionSource,
               new Callable<Void>() {
                   public Void call() throws Exception {

                       try {
                           //create master company info
                           createMstCompanyInfo();

                           //create master order increase
                           createMstOrderIncrease();

                           //create master login info
                           createMstLoginInfo();
                       } catch(Exception ex) {
                           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                       return null;
                   }

                   /**
                    * create master company info
                    */
                   private void createMstCompanyInfo() {
                       MstCommonEnt mstCommonEnt = new MstCommonEnt();
                       mstCommonEnt.setValues(ConstConfigs.CONST_DEFAULT_COMPANY_COMMON_NO,
                               ConstConfigs.CONST_DEFAULT_COMPANY_CLASS_NO,
                               ConstConfigs.CONST_DEFAULT_COMPANY_CLASS_NAME,
                               ConstConfigs.CONST_DEFAULT_COMPANY_DATA_1,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               ConstConfigs.CONST_DEFAULT_DEL_FLG,
                               context.getResources().getString(R.string.mst_common_company_desc),
                               CalendarUtils.getNow(),
                               CalendarUtils.getNow());
                       try {
                           mstCommonDao.create(mstCommonEnt);
                       } catch(Exception ex) {
                           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }

                   /**
                    * create master order increase
                    */
                   private void createMstOrderIncrease() {
                       MstCommonEnt mstCommonEnt = new MstCommonEnt();
                       mstCommonEnt.setValues(ConstConfigs.CONST_DEFAULT_ORDER_INCREASE_COMMON_NO,
                               ConstConfigs.CONST_DEFAULT_ORDER_INCREASE_CLASS_NO,
                               ConstConfigs.CONST_DEFAULT_ORDER_INCREASE_CLASS_NAME,
                               ConstConfigs.CONST_DEFAULT_ORDER_INCREASE_DATA_1,
                               ConstConfigs.CONST_DEFAULT_ORDER_INCREASE_DATA_2,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               ConstConfigs.CONST_DEFAULT_DEL_FLG,
                               null,
                               CalendarUtils.getNow(),
                               CalendarUtils.getNow());
                       try {
                           mstCommonDao.create(mstCommonEnt);
                       } catch(Exception ex) {
                           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }

                   /**
                    * create master LOGIN INFO
                    */
                   private void createMstLoginInfo() {
                       MstCommonEnt mstCommonEnt = new MstCommonEnt();
                       mstCommonEnt.setValues(ConstConfigs.CONST_DEFAULT_LOGIN_INFO_COMMON_NO,
                               ConstConfigs.CONST_DEFAULT_LOGIN_INFO_CLASS_NO,
                               ConstConfigs.CONST_DEFAULT_LOGIN_INFO_CLASS_NAME,
                               ConstConfigs.CONST_DEFAULT_LOGIN_INFO_DATA_1,
                               ConstConfigs.CONST_DEFAULT_LOGIN_INFO_DATA_2,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null,
                               ConstConfigs.CONST_DEFAULT_DEL_FLG,
                               null,
                               CalendarUtils.getNow(),
                               CalendarUtils.getNow());
                       try {
                           mstCommonDao.create(mstCommonEnt);
                       } catch(Exception ex) {
                           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               }
       );

       Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
   }


}
