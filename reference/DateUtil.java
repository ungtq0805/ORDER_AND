/*
 * Copyright(C) 2012 D-CIRCLE, INC. All rights reserved.
 * 
 * (1)このソフトウェアは、ディサークル株式会社に帰属する機密情報 であり開示を固く禁じます。
 * (2)この情報を使用するには、ディサークル株式会社とのライセンス 契約が必要となります。
 * 
 * This software is the confidential and proprietary information of
 * D-CIRCLE, INC. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with D-CIRCLE.
 */
package net.poweregg.exkeihi.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import net.poweregg.contexts.UserContext;
import net.poweregg.exkeihi.constant.RoundingModeConst;
import net.poweregg.util.DateUtils;

/********************************************************************************
 * Ex-Keihi エンブレムフレームワーク
 * 
 * 日付ユーティリティ 日付を操作する一般的なユーティリティ
 * 
 * 作成日 : 2012/07/25
 * 
 * @author phapnv
 * 
 ********************************************************************************/
public class DateUtil implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static final int MILLISEC_PER_DAY = 24 * 60 * 60 * 1000;

    /************************************************
	 *
	 ************************************************/
    public static final String MMDD="MMdd";
    public static final String YYYY_MM_STRING = "yyyy/MM";
    public static final int LENGTH_YYYYMMDD = 8;
    public static final String PATTERN_YYYYMMDD = "(((\\d\\d)(0[48]|[2468][048]|[13579][26])|([02468][048]|[13579][26])(00))(02)([012]\\d))|(\\d\\d([02468][1235679]|[13579][01345789])(02)([01]\\d|2[012345678]))|(\\d\\d\\d\\d((0[13578]|1[02])([012]\\d|3[01])|((0[46]|11)([012]\\d|30))))";
    public static final String MM_DD_DAY_IN_WEEK = "MM/dd(E)";
    
    /**#19358 cuongbd: nguyen nhan vi da xay ra loi logic, ket qua parse gia tri khong dung.
    (khi thuc hien 2 xu ly dong thoi, o man hinh importer MIUで社員手当登録とプロジェクト登録).
    Ex: 
    co program A,program B cung thuc thi (hoac thuc thi program A , sau do thuc thi program B)
    Thread A : goi method toYYYYMMDD("20150101") ->ket qua dung : Date('20150101') 
    Nhung ket qua hien tai :Date('20161030') -->NG. gia tri '20161030' nay la do doc cua program B.*/
    
    /*private static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat(
            "yyyyMMdd");

    private final static DateFormat yyyyMMddHHmmss = new SimpleDateFormat(
            "yyyyMMddHHmmss");*/

    
    public static String toDateWithoutDayInWeek(Date date){
        return formatDateString(date,DateUtils.DATE_PARTTERN.DATE_WITHOUT_DAY_IN_WEEK);
    }
    
    public static String formatDateString(Date date, String format)
    {
        if(date == null){
            return null;
        } else{
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }
    
    public static final Date toYYYYMM(Date date) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static final Date addDay(Date date, int slipDate) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, slipDate);
        return calendar.getTime();
    }

    public static final Date getNowYYYYMMDD() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now.getTime();
    }

    public static final Date toYYYYMMDDDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static final boolean isYYYYMMDD(String yyyyMMDD) {
        try {
            if (null == yyyyMMDD) {
                return false;
            }
            if (LENGTH_YYYYMMDD != yyyyMMDD.length()) {
                return false;
            }
            SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");//#19358 cuongbd
            YYYYMMDD.setLenient(false);
            YYYYMMDD.parse(yyyyMMDD);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (ParseException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static final String toYYYYMMDD(Object dateObject) {
        try {
        	SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");//#19358 cuongbd
            YYYYMMDD.setLenient(false);
            return YYYYMMDD.format(dateObject);
        } catch (Exception e) {
            return null;
        }
    }

    public static final Date toYYYYMMDD(String yyyyMMDD) {
        try {
        	SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");//#19358 cuongbd
            YYYYMMDD.setLenient(false);
            return YYYYMMDD.parse(yyyyMMDD);
        } catch (Exception e) {
            return null;// format of string is not correct
        }
    }

    public static final String yyyyMMddHHmmss(Date date) {
    	DateFormat yyyyMMddHHmmss = new SimpleDateFormat(
                "yyyyMMddHHmmss");//#19358 cuongbd
        return yyyyMMddHHmmss.format(date);
    }

    /************************************************
     * 日付を比較する date1がdate2より未来日の場合正の数 date1がdate2より過去日の場合は負の数
     * 同じ日の場合は0を返す 本メソッドは時分は比較対象にならない
     * 
     * @param date1
     *        日付
     * @param date2
     *        日付
     * @return 比較結果
     ************************************************/
    public static final int compareDate(Date date1, Date date2) {
        Date baseDate1 = toZeroHourDate(date1);
        Date baseDate2 = toZeroHourDate(date2);

        return baseDate1.compareTo(baseDate2);
    }

    /************************************************
     * @param date
     * @return
     ************************************************/
    public static final String formatDate(Date date) {
    	SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");//#19358 cuongbd
        String str = YYYYMMDD.format(date);
        return DataUtil.formatDate(str);
    }

    /************************************************
     * {@link Date}型の引数の時間を00:00にした日付を返す
     * 
     * @param convertDate
     *        変換する日付
     * @return 00:00分の日付
     ************************************************/
    public static final Date toZeroHourDate(Date convertDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertDate);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int date = calendar.get(5);
        calendar.clear();
        calendar.set(year, month, date, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 年月取得 システム年月をYYYYの文字列に編集する
     * 
     * @param date
     * @return 編集後年
     */
    public static String getYear(Date date) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                "yyyy");

        return formatter.format(date);

    }

    /**
     * 年月取得 システム月日をMMの文字列に編集する
     * 
     * @param date
     * @return 編集後月
     */
    public static String getMonth(Date date) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                "MM");
        return formatter.format(date);

    }

    /**
     * 年月取得 システム月日をDDの文字列に編集する
     * 
     * @param date
     * @return 編集後日
     */
    public static String getDay(Date date) {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
                "dd");
        return formatter.format(date);

    }

	 /**
     * calculate number day between aStartDate and aEndDate
     *
     * @param aStartDate start date
     * @param aEndDate end date
     * @param roundMode round mode
     * @param includeTime if true, include time
     *          otherwise, exclude time from date
     * @return aEndDate - aStartDate
     */
    public static long getDistanceOfDays(Date aStartDate, Date aEndDate, 
                                        String roundMode, boolean includeTime) {
        long numDay = 0;
        if (aStartDate == null || aEndDate == null) {
            throw new NullPointerException();
        }
        Date startDate = aStartDate;
        Date endDate = aEndDate;
        if(!includeTime) {
            startDate = DateUtils.convertNewDate(aStartDate);
            endDate = DateUtils.convertNewDate(aEndDate);
        }
        long ms = endDate.getTime() - startDate.getTime();
        if(RoundingModeConst.ROUND_DOWN.equals(roundMode)) {
            //Round down
            numDay = (long) Math.floor(ms / (MILLISEC_PER_DAY));
        } else if(RoundingModeConst.ROUND_UP.equals(roundMode)) {
            //Round up
            numDay = (long) Math.ceil(ms / (MILLISEC_PER_DAY));
        } else {
            //Round half up
            numDay = (long) Math.round(ms / (MILLISEC_PER_DAY));
        }
        return numDay;
    }
    
    /**
     * 【多国語対応】 Format Date by locale and pattern
     *
     * @param date Date
     * @param locale Locale
     * @param format format
     * @return String
     */
    public static String formatDateString(Date date, Locale locale,
            String format) {
        if (null == date) {
            return "";
        }

        SimpleDateFormat formatter = null;
        if(null == locale) {
            formatter = new SimpleDateFormat(format);
        } else {
            formatter = new SimpleDateFormat(format, locale);
        }
        return formatter.format(date);
    }
    public static String[] getLocalizedWeekNames() {
        ResourceBundle resource = ResourceBundle.getBundle("common-resource",
                UserContext.instance().getLocale());
        return resource.getString("common_l_weekNames").split(",");
    }
    
    /**
     * if paramDate=null return "NULL" ELSE return "to_date('value','yyyyMMdd')"
     * use NativeQuery to update value 
     * @param dateParam
     * @return
     * @author cuongbd
     */
    public static String toDateForUpdateNativeQuery(Date dateParam){
		StringBuffer sql = new StringBuffer();
		if (dateParam == null) {
			sql.append("NULL");
		}else{
			sql.append(" to_date('").append(DateUtil.formatDateString(dateParam,DateUtils.DATE_PARTTERN.YMD))
					.append("','" + DateUtils.DATE_PARTTERN.YMD + "')");
		}
		return sql.toString();
	}
}
