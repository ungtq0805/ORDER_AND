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
    public static final Boolean CONST_DEFAULT_DEL_FLG = Boolean.FALSE;

    /**
     * DEFAULT ORDER INCREASE
     */
    public static final String CONST_DEFAULT_ORDER_INCREASE_COMMON_NO = "00000002";
    public static final String CONST_DEFAULT_ORDER_INCREASE_CLASS_NO = "ORDER_INCREASE";
    public static final String CONST_DEFAULT_ORDER_INCREASE_CLASS_NAME = "ORDER INCREASMENT";
    public static final String CONST_DEFAULT_ORDER_INCREASE_DATA_1 = "000000000";
    public static final String CONST_DEFAULT_ORDER_INCREASE_DATA_2 = "1";

    /**
     * DEFAULT LOGIN INFO
     */
    public static final String CONST_DEFAULT_LOGIN_INFO_COMMON_NO = "00000003";
    public static final String CONST_DEFAULT_LOGIN_INFO_CLASS_NO = "LOGIN_DEFAULT";
    public static final String CONST_DEFAULT_LOGIN_INFO_CLASS_NAME = "LOGIN INFO";
    public static final String CONST_DEFAULT_LOGIN_INFO_DATA_1 = "admin";
    public static final String CONST_DEFAULT_LOGIN_INFO_DATA_2 = "123456";

}
