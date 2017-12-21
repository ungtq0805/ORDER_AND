package ctwhs.order.dao;

import android.content.Context;

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
     * Constructor
     * @param context
     */
   public MstCommonDao(Context context) {
        super(context);
   }

   public void initDefault() throws java.sql.SQLException {
       TransactionManager.callInTransaction(connectionSource,
               new Callable<Void>() {
                   public Void call() throws Exception {
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
                               ConstConfigs.CONST_DEFAULT_COMPANY_DEL_FLG,
                               context.getString(R.string.mst_common_company_desc),
                               CalendarUtils.getNow(),
                               CalendarUtils.getNow());
                       mstCommonDao.create(mstCommonEnt);
                       return null;
                   }
               }
       );
   }
}
