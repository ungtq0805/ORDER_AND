package ctwhs.order.constant;

import ctwhs.order.sqlite.helper.DBHelper;

/**
 * Created by UNGTQ on 6/4/2017.
 */

public class ConstConfigs {
    /**
     * File name configurations
     */
    public static final String CONFIGURATIONS_XML = "Configurations.xml";

    /**
     * server name tag info
     */
    public static final String CONFIGURATIONS_XML_SERVER_NAME_TAG = "server_name";

    /**
     * server port info
     */
    public static final String CONFIGURATIONS_XML_SERVER_PORT_TAG = "server_port";

    /**
     * DEFAULT DB PATH
     */
    public static final String CONST_DEFAULT_DB_PATH = "/data/data/ctwhs.order/databases/" + DBHelper.DATABASE_NAME;

    /**
     * DEFAULT COMPANY NAME
     */
    public static final String CONST_DEFAULT_COMPANY_COMMON_NO = "00000001";
    public static final String CONST_DEFAULT_COMPANY_CLASS_NO = "CORP_INFO";
    public static final String CONST_DEFAULT_COMPANY_CLASS_NAME = "CTWHS SOFTWARE";
    public static final String CONST_DEFAULT_COMPANY_DATA_1 = "ungtq0805@gmail.com";
    public static final Boolean CONST_DEFAULT_COMPANY_DEL_FLG = Boolean.FALSE;
}
