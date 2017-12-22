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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Pattern;

import net.poweregg.util.StringUtils;

import java.util.List;
import java.util.ArrayList;

import javax.faces.model.DataModel;


/********************************************************************************
 * Ex-KeiHi
 * エンブレムフレームワーク
 *
 * データを編集するユーティリティクラス
 *
 * 作成日 : 2012/07/26
 * @author phapnv
 *
 ********************************************************************************/
public class DataUtil implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //public static final String KATAKANA_PATTERN = "^[0-9A-Zｦｱ-ﾝ\\-\\.\\sﾞﾟ()/+]+$";
    public static final String CHAR_NUM_ENGLISH = "[\\p{ASCII}&&[^'\",]]";
    public static final String KATAKANA_PATTERN = "^[0-9A-Zｦｱ-ﾝﾞﾟ\\\\｢｣/()\\-.\\s]+$";
    public static final String KATAKANA_PATTERN_NEW = "^[0-9A-Zｦｱ-ﾝﾞﾟ\\\\｢｣/()\\-.,\\s]+$"; //#24765
    public static final int MAX_SQL_QUALIFICATION_SIZE = 1000;


	/************************************************
	 * @param aObject チェック対象のオブジェクト
	 * @return nullもしくは0バイトの文字列であれば true
	 *************************************************/
	public static boolean isEmpty(Object aObject) {
		if (aObject == null)
			return true;
		String str = aObject.toString(); //JavaにはtoStringがあるのです
		if (str.length() == 0)
			return true;
		return false;
	}

	/************************************************
	 * @param aObject
	 * @return Boolean
	 *************************************************/
	public static Boolean toBoolean(Object aObject) {
		if (aObject == null) {
			return null;
		} else {
			String target = toString(aObject);

			if (target.equals("1")){
				return new Boolean(true);
			} else if (target.equals("0")){
				return new Boolean(false);
			}
			return Boolean.valueOf(target);
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Short値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return Short値
	 **************************************************/
	public static Short toShort(Object aObject) {
		try {
			return new Short(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Short値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return Short値
	 **************************************************/
	public static Short toShortZero(Object aObject) {
		try {
			if (aObject == null) {
				return new Short("0");
			}
			return new Short(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Integer値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Long値
	 *************************************************/
	public static Integer toInteger(Object aObject) {
		try {
			return new Integer(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Integer値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Long値
	 *************************************************/
	public static Integer toIntegerZero(Object aObject) {
		try {
			if (aObject == null) {
				return new Integer("0");
			}
			return new Integer(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Long値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Long値
	 *************************************************/
	public static Long toLong(Object aObject) {
		try {
			return new Long(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Long値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Long値
	 *************************************************/
	public static Long toLongZero(Object aObject) {
		try {
			if (aObject == null) {
				return new Long("0");
			}
			return new Long(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Float値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Float値
	 ************************************************/
	public static Float toFloat(Object aObject) {
		try {
			return new Float(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Float値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject 変換対象のオブジェクト
	 * @return Float値
	 ************************************************/
	public static Float toFloatZero(Object aObject) {
		try {
			if (aObject == null) {
				return new Float("0");
			}
			return new Float(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Double値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return
	 ************************************************/
	public static Double toDouble(Object aObject) {
		try {
			return new Double(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * Double値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return
	 ************************************************/
	public static Double toDoubleZero(Object aObject) {
		try {
			if (aObject == null) {
				return new Double("0");
			}
			return new Double(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * @param anObject
	 * @return オブジェクトがnullであればnullを返す
	 *************************************************/
	public static String toString(Object anObject) {
		if (anObject == null) {
			return null;
		}
		String str = anObject.toString();
		if (str.compareTo("") == 0) {
			return null;
		} else {
			return str;
		}
	}

	/************************************************
	 * @param anObject
	 * @return オブジェクトがnullであればnullを返す
	 *************************************************/
	public static String toStringBlank(Object anObject) {
		String str = toString(anObject);
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}


	/************************************************
	 * toStringBlankした値をエンコードします<BR>
	 * null 場合は 空値 を復帰します。<BR>
	 * @param anObject オブジェクト
	 * @return String型の文字列
	 *************************************************/
	public static String toStringEncode(Object anObject) {
		//String str = toStringBlank(anObject);
		//return PEStringUtilities.parseHtmlString(str);
		//TODO 互換作成必要 2009.01.11
		throw new UnsupportedOperationException();
	}

	/************************************************
	 *
	 * @param anObject
	 * @return YYYYMMDDに変換した値
	 *************************************************/
	public static String toYYYYMMDD(Object anObject) {
		String param = toString(anObject);
		try {
			return param.replaceAll("/", "");

		} catch (Exception e) {
			return null;
		}
	}

	/************************************************
	 * Objectを日付文字列に変換します<BR>
	 * 但し、null又は(length≠6 And length≠8)の場合はnullを復帰します。<BR>
	 * <UL>
	 * <LI>length=6：YYYY/MM
	 * <LI>length=8：YYYY/MM/DD
	 * </UL>
	 * @param anObject オブジェクト
	 * @return 日付文字列（YYYY/MM Or YYYY/MM/DD）
	 *************************************************/
	public static String formatDate(Object anObject) {

		String str = null;
		if (anObject instanceof java.util.Date) {
			return DateUtil.formatDate((java.util.Date) anObject);
		} else {
			str = toString(anObject);
		}

		if (str == null || (str.length() != 6 && str.length() != 8)) {
			return null;
		} else {
			if (str.length() == 6) {
				return (str.substring(0, 4) + "/" + str.substring(4, 6));
			} else {
				return (str.substring(0, 4) + "/" + str.substring(4, 6) + "/" + str.substring(6, 8));
			}
		}
	}

	/************************************************
	 * Objectを日付文字列に変換します<BR>
	 * 但し、null又は (length≠6 And length≠8) の場合は 空値 を復帰します。<BR>
	 * <UL>
	 * <LI>length=6：YYYY/MM
	 * <LI>length=8：YYYY/MM/DD
	 * </UL>
	 * @param anObject オブジェクト
	 * @return 日付文字列（YYYY/MM Or YYYY/MM/DD）
	 *************************************************/
	public static String formatDateBlank(Object anObject) {
		String str = formatDate(anObject);
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/************************************************
	 * Objectを日付文字列に変換します<BR>
	 * 但し、nullの場合は空値を復帰します。<BR>
	 *  (length≠6 And length≠8) の場合は 引数をStringに変換します。<BR>
	 * @param anObject
	 * @return
	 ************************************************/
	public static String formatDateNullBlank(Object anObject) {
		if (anObject == null) {
			return "";
		}
		String ret = toStringBlank(anObject);
		if (ret.length() == 6 || ret.length() == 4) {
			ret = formatDateBlank(anObject);
		}
		return ret;
	}

	/************************************************
	 * YYYYMMDD形式の書式化された現在日次を取得する
	 * @return　YYYYMMDD
	 *************************************************/
	public static String getNowDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}

	/************************************************
	 * Object（数値）を指定された形式にﾌｫｰﾏｯﾄします<BR>
	 * 但し、null、空値 又は 数値 として認識できない場合は null を復帰します。<BR>
	 * @param anObj オブジェクト
	 * @param aFormat フォーマット形式
	 * @return ﾌｫｰﾏｯﾄ文字列
	 *************************************************/
	public static String formatNumber(Object anObj, String aFormat) {
/*		if (anObj == null) {
			return null;
		} else {
			String str = anObj.toString();
			if (str.compareTo("") == 0) {
				return null;
			} else {
				try {
					Double num = new Double(str);
					double dd = num.doubleValue();
					DecimalFormat fmt = new DecimalFormat(aFormat);
					return PEStringUtilities.parseHtmlString(fmt.format(dd));
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		*/
		//TODO:要互換作成 2009.01.11
		throw new UnsupportedOperationException();
	}

	/************************************************
	 * 小数点以下桁をあわせて四捨五入する
	 *
	 * @param value Double値
	 * @param scale 小数点以下桁 (例 123.456 scale=3)
	 * @return 四捨五入された文字列表現
	 ************************************************/
	public static String formatDouble(Double value, int scale) {
		if (value == null) {
			return null;
		} else {
			BigDecimal bd = new BigDecimal(value.doubleValue());
			double newValue = bd.setScale(scale, BigDecimal.ROUND_CEILING).doubleValue();	//四捨五入
			return Double.toString(newValue);
		}
	}

	/************************************************
	 * Booleanを文字列に変換する
	 *
	 * @param value 対応する値
	 * @return 1or0
	 *************************************************/
	public static final String formatBoolean(Boolean value) {
		return formatBoolean(value.booleanValue());
	}

	/************************************************
	 * Booleanを文字列に変換する
	 *
	 * @param value 対応する値
	 * @return 1or0
	 *************************************************/
	public static final String formatBoolean(boolean value) {
		if (value) {
			return "1";
		} else {
			return "0";
		}
	}

	/************************************************
	 * Object（数値）を指定された形式にﾌｫｰﾏｯﾄします<BR>
	 * 但し、null、空値 又は 数値 として認識できない場合は 空値 を復帰します。<BR>
	 * @param anObject オブジェクト
	 * @param aFormat フォーマット形式
	 * @return ﾌｫｰﾏｯﾄ文字列
	 *************************************************/
	public static String formatNumberBlank(Object anObject, String aFormat) {
		String str = formatNumber(anObject, aFormat);
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	/************************************************
	 * 金額を編集する
	 * 渡された値を "####
	 *
	 * @param yen
	 * @return 1,234,567,890形式の書式
	 *************************************************/
	public static String formatYen(long yen) {
		DecimalFormat df  = new DecimalFormat("#,###");
		return df.format(yen);
	}

	/************************************************
	 * 金額を編集する
	 *
	 * @param yen
	 * @param prefix
	 * @return 1,234,567,890形式にprefixが付いた書式
	 *************************************************/
	public static String formatYen(long yen, String prefix) {
		String s = formatYen(yen) + prefix;
		return s;
	}

	/************************************************
	 * 金額を編集する
	 *
	 * @param yen
	 * @param prefix
	 * @return 1,234,567,890形式にprefixが付いた書式
	 *************************************************/
	public static String formatYen(Long yen, String prefix) {
		if (yen != null) {
			return formatYen(yen.longValue(), prefix);
		}
		return null;
	}

	/************************************************
	 *
	 * @param param
	 * @return エスケーピングされたHTML
	 *************************************************/
	public static String getEscapeHTML(String param) {
    	String ret = param.replaceAll("\n", "</BR>");
    	ret = ret.replaceAll(" ", "&nbsp;");
    	return ret;
    }

	/************************************************
	 *
	 * @author EMBELM)t.torige
	 * @param anObj1
	 * @param anObj2
	 * @return 一致すればtrue
	 *************************************************/
	public static boolean isEqual(Object anObj1, Object anObj2) {
		if (anObj1.getClass().isPrimitive() && anObj2.getClass().isPrimitive()) {
			return (anObj1 == anObj2);
		} else if (anObj1 instanceof Boolean && anObj2 instanceof Boolean) {
			return (((Boolean)anObj1).booleanValue() == ((Boolean) anObj2).booleanValue());
		} else if (anObj1 instanceof Byte && anObj2 instanceof Byte) {
			return (((Byte)anObj1).byteValue() == ((Byte) anObj2).byteValue());
		} else if (anObj1 instanceof Character && anObj2 instanceof Character) {
			return (((Character)anObj1).charValue() == ((Character) anObj2).charValue());
		} else if (anObj1 instanceof Short && anObj2 instanceof Short) {
			return (((Short)anObj1).shortValue() == ((Short) anObj2).shortValue());
		} else if (anObj1 instanceof Integer && anObj2 instanceof Integer) {
			return (((Integer)anObj1).intValue() == ((Integer) anObj2).intValue());
		} else if (anObj1 instanceof Long && anObj2 instanceof Long) {
			return (((Long)anObj1).longValue() == ((Long) anObj2).longValue());
		} else if (anObj1 instanceof Double && anObj2 instanceof Double) {
			return (((Double)anObj1).doubleValue() == ((Double) anObj2).doubleValue());
		} else if (anObj1 instanceof Float && anObj2 instanceof Float) {
			return (((Float)anObj1).floatValue() == ((Float) anObj2).floatValue());
		} else {
	        String str1 = toString(anObj1);
	        String str2 = toString(anObj2);
	        if(str1 == null && str2 == null) {
	            return true;
	        }
	        if(str1 == null) {
	            return false;
	        }
	        if(str2 == null) {
	            return false;
	        }
	        return str1.equals(str2);
		}
    }

	/************************************************
	 * 文字列に半角数値(0-9)以外ない場合true</br>
	 * それ以外が含まれている場合falseを返却する
	 *
	 * @param str 対象文字列
	 * @return 全て半角数値の場合true
	 * @since 2.0.2
	 ************************************************/
	public static boolean isHalfNum(String str) {
		if (str == null) {
			return false;
		}
		final String HALFNUM = "[0-9]+";
		return Pattern.matches(HALFNUM, str);
	}

	/************************************************
	 * 文字列に半角カナのみ含まれている場合はtrue<br/>
	 * それ以外が含まれている場合はfalseを返却する<br/>
	 * @param str 対象文字列
	 * @return 全て半角カナの場合true
	 * @since 2.0.2
	 ************************************************/
	public static boolean isHalfKana(String str) {
		if (str == null) {
			return false;
		}
		final String HALFKANA = "[ｦｱ-ﾝ\\sﾞﾟ]+";
		return Pattern.matches(HALFKANA, str);
	}

	/************************************************
	 * 文字列に半角英数のみ含まれている場合はtrue<br/>
	 * それ以外が含まれている場合はfalseを返却する<br/>
	 * @param str 対象文字列
	 * @return 全て半角英数の場合true
	 * @since 2.0.2
	 ************************************************/
	public static boolean isHalfAlfNum(String str) {
		if (str == null) {
			return false;
		}
		final String HALFALFNUM = "[a-zA-Z0-9]+";
		return Pattern.matches(HALFALFNUM, str);
	}

	public static boolean isCharAndNumEng(String str){
	    if (str == null) {
          return true;
	    }
        String replaceText = str.replaceAll(CHAR_NUM_ENGLISH, "");
        if (!StringUtils.nullOrBlank(replaceText)) {
            return false;
        }
        return true;
	}

	/************************************************
	 * 文字列が日付形式(YYYYMMDD)または(YYYY/MM/DD)
	 * であればtrue,そうでなければfalseを返却する<br/>
	 * 厳密にチェックをする場合はDateUtil#toDateでの返却値
	 * がnullかどうかでチェックする
	 * @param str 対象文字列
	 * @return
	 * @since 2.0.2
	 ************************************************/
	public static boolean isStrDate(String str) {
		if (str == null) {
			return false;
		}
		final String DATESTR = "\\d{8}";
		final String DATESTRSLASH = "\\d{4}\\/\\d{2}\\/\\d{2}";

		if ((str.length() == 8 && Pattern.matches(DATESTR, str))
				|| (str.length() == 10 && Pattern.matches(DATESTRSLASH, str))) {
			return true;
		} else {
			return false;
		}
	}

	/************************************************
	 * 「カタカナ文字」の使用可能文字列であればtrue、そうでなければfalse
	 * を返却する<br/>
	 * 使用可能文字列とは<br/>
	 * 0から9の半角英数
	 * AからZの半角アルファベット(小文字を除く)
	 * ｱからﾝ(ｬなどの小さい文字を除く)の半角カナ
	 * "."(ピリオド) "/"(スラッシュ) "(" ")"半角括弧
	 *
	 * @param strVal 入力文字
	 * @return 使用可能な場合はtrue、そうでなければfalse
	 ************************************************/
	public static boolean isKatakana(String strVal) {
		if (StringUtils.nullOrBlank(strVal)) {
			return false;
		}
		return Pattern.matches(KATAKANA_PATTERN, strVal);
	}

	/************************************************
	 * {@link PEStringUtilities}がCp943に変更した文字列を
	 * UNICODEに戻す
	 *
	 * @param original 変換された文字列
	 * @return 元の文字列
	 *************************************************/
	public static String reverseCp943CtoUnicode(String original) {
		try {
	        char array[] = original.toCharArray();
	        int length = array.length;
	        for(int i = 0; length > i; i++) {
	        	array[i] = reverseCp943CtoUnicode(array[i]);
	        }
	        return new String(array);
		} catch (Exception e) {
			return "";
		}
	}

	/*************************************************
	 * {@link PEStringUtilities#parseHtmlString(String)}
	 * で変換された文字列を元の文字列に変換し直す
	 *
	 * @param original 変換された文字
	 * @return 元の文字列
	 *************************************************/
	public static String reverseHtmlString(String original) {
    	String ret = original.replaceAll("&amp;", "&");
    	ret = ret.replaceAll("&quot;", "");
    	ret = ret.replaceAll("&lt;", "<");
    	ret = ret.replaceAll("&gt;", ">");
    	ret = ret.replaceAll("&#39;",  "\\");
    	return ret;
	}

	/************************************************
	 * {@link PEStringUtilities}がCp943に変更した文字列を
	 * UNICODEに戻す
	 *
	 * @param original 変換された文字
	 * @return 元の文字
	 *************************************************/
	public static char reverseCp943CtoUnicode(char original) {
        switch(original)
        {
        case  '\u2014':
            original = 8213;
            break;

        case '\u301C':
            original = 65374;
            break;

        case '\u2016':
            original = 8741;
            break;

        case '\u2026':
            original = 8943;
            break;

        case '\u2212':
            original = 65293;
            break;

        case '\uFFE0':
            original = 162;
            break;

        case  '\uFFE1':
            original = 163;
            break;
        case '\uFFE2':
            original = 172;
            break;
        }
        return original;
	}

	/************************************************
	 * 渡された長さで0埋めフォーマットを行う
	 *
	 * @param target 変換対象
	 * @param length 長さ
	 * @return 0埋めされた文字列
	 *************************************************/
	public static String zeroFormat(Long target, int length) {
		final String ZERO = "0";
		// target=NULLの場合は""を返す
		if (target == null) {
			return "";
		}
		StringBuffer format = new StringBuffer();
		for (; length > format.length(); format.append(ZERO)) {
		}
		DecimalFormat df = new DecimalFormat(format.toString());
		return df.format(target.longValue());
	}

	public static String zeroFormat(String target, int length) {
        final String ZERO = "0";
        // target=NULLの場合は""を返す
        if (target == null) {
            return "";
        }
        StringBuffer format = new StringBuffer();
        for (; length > format.length(); format.append(ZERO)) {
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        return df.format(target);
    }

	/************************************************
	 * 全角半角も含んだtrimを行う
	 *
	 * @param target 対象の文字列
	 * @return trim後の文字列
	 ************************************************/
	public static final String trimJ(String target) {
		char c;
	    int head = 0;
	    int length = target.length();
	    int tail = length - 1; //最後の文字を指す

	    while (head < length
	        && ((c = target.charAt(head)) <= ' ' || c == '　')){
	      ++head;
	    }
	    while (tail > head
	        && ((c = target.charAt(tail)) <= ' ' || c == '　')){
	        --tail;
	    }
	    return target.substring(head, tail + 1);
	}

	/*******************************************************************************
	 * Objectを指定されたバイトでカットします<BR>
	 *
	 * @return	String			カットされた文字列
	 * @param	anObject		オブジェクト
	 * @param	aByteLength		カットするByte数
	 * @throws	Exception		例外記述
	 *******************************************************************************/
	public static String substringByte(String anObject, int aByteLength) throws Exception {
		StringBuffer newStr = new StringBuffer();
		try {
			if (anObject == null) {
				return "";
			}
			byte[] byteStr = anObject.getBytes("Cp943C");
			if (byteStr.length <= aByteLength) {
				newStr.append(anObject.toString());
			} else {
				int count = 0;
				for (int i = 0; i < anObject.length(); ++i) {
					String charStr = anObject.substring(i, i + 1);
					byte[] byteChar = charStr.getBytes();
					if (byteChar.length + count > aByteLength) {
						break;
					}
					count += byteChar.length;
					newStr.append(charStr);
				}
			}
		} catch (Exception e) {
			return "";
		}
		return newStr.toString();
	}

	/*******************************************************************************
	 * Objectを指定されたバイトでカットします<BR>
	 *
	 * @return	String			カットされた文字列
	 * @param	anObject		オブジェクト
	 * @param	aByteLength		カットするByte数
	 * @param	anCharset
	 * @throws	Exception		例外記述
	 *******************************************************************************/
	public static String substringByte(String anObject, int aByteLength,
			String anCharset) throws Exception {
		StringBuffer newStr = new StringBuffer();
		try {
			if (anObject == null) {
				return "";
			}
			byte[] byteStr = anObject.getBytes(anCharset);
			if (byteStr.length <= aByteLength) {
				newStr.append(anObject.toString());
			} else {
				int count = 0;
				for (int i = 0; i < anObject.length(); ++i) {
					String charStr = anObject.substring(i, i + 1);
					byte[] byteChar = charStr.getBytes(anCharset);
					if (byteChar.length + count > aByteLength) {
						break;
					}
					count += byteChar.length;
					newStr.append(charStr);
				}
			}
		} catch (Exception e) {
			return "";
		}
		return newStr.toString();
	}

	/************************************************
	 * Date比較
	 ************************************************/
	public static int compareDate(Date aDate1, Date aDate2) {
		if (aDate1 == null && aDate2 == null) {
			return 0;
		} else if (aDate1 == null) {
			return -1;
		} else if (aDate2 == null) {
			return 1;
		}
		Date date1 = convertNewDate(aDate1);
		Date date2 = convertNewDate(aDate2);
		return date1.compareTo(date2);
	 }
	 /************************************************
	  * convertNewDate
	  ************************************************/
	 public static Date convertNewDate(Date receiveDate){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(receiveDate);
	    int year = calendar.get(1);
	    int month = calendar.get(2);
	    int date = calendar.get(5);
	    calendar.clear();
	    calendar.set(year, month, date, 0, 0, 0);
	    return calendar.getTime();
	 }

	  /************************************************
      * Check collection is null or empty
      *
      * @param collection collection
      * @return true if collection is null or empty
      *         otherwise, return false
      ************************************************/
	 public static boolean isCollectionNullOrEmpty(Collection<?> collection) {
	     return (collection == null || collection.isEmpty());
	 }

	 /**
	  * Get number of scale number of BigDecimal
	  *
	  * Ex: getNumberOfDecimalPlaces(new BigDecimal("0.001") = 3
	  *     getNumberOfDecimalPlaces(new BigDecimal("0.01")  = 2
	  *     getNumberOfDecimalPlaces(new BigDecimal("0.1")   = 1
	  *     getNumberOfDecimalPlaces(new BigDecimal("1.000") = 0
	  *     getNumberOfDecimalPlaces(new BigDecimal("1.0")   = 0
	  *     getNumberOfDecimalPlaces(new BigDecimal("1")     = 0
	  *     getNumberOfDecimalPlaces(new BigDecimal("1.1")   = 1
	  *     getNumberOfDecimalPlaces(new BigDecimal("10.01") = 2
	  *
	  * @param bigDecimal BigDecimal number
	  * @return number of scale number
	  */
	 public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	     if(null == bigDecimal) {
	         return 0;
	     }
	     String plainStr = bigDecimal.stripTrailingZeros().toPlainString();
	     int index = plainStr.indexOf(".");
	     return index < 0 ? 0 : plainStr.length() - index - 1;
	 }

	 /**
	  * Check index is out of bound collection
	  *
	  * @param collection Collection
	  * @param index index
	  * @return true if :
	  *              1. collection is null or empty
	  *              or
	  *              2. index < 0 or index >= collection.size()
	  *         otherwise, return false
	  */
	 public static boolean isIndexOutOfBound(Collection<?> collection, int index) {
	     if(isCollectionNullOrEmpty(collection)) {
	         return true;
	     }
	     return index < 0 || index >= collection.size();
	 }

	  /************************************************
      * Check array is null or empty
      *
      * @param arr array
      * @return true if array is null or empty
      *         otherwise, return false
      ************************************************/
	 public static boolean isArrayNullOrEmpty(Object[] arr) {
	     return arr == null || arr.length == 0;
	 }

	  /**
      * Check index is out of bound array
      *
      * @param arr array
      * @param index index
      * @return true if :
      *              1. array is null or empty
      *              or
      *              2. index < 0 or index >= arr.length
      *         otherwise, return false
      */
	 public static boolean isIndexOutOfBound(Object[] arr, int index) {
	     if(isArrayNullOrEmpty(arr)) {
	         return true;
	     }
	     return index < 0 || index >= arr.length;
	 }
	/**
	 *  Check obj1 and obj2 is equal
	 * @param obj1
	 * @param obj2
	 * @return true : obj1 is equal to obj2
	 *         false: obj1 is not equal to obj2
	 */
    public static boolean equals(Object obj1, Object obj2) {
        if (null == obj1 && null == obj2) {
            return true;
        }
        return StringUtils.toEmpty(obj1).equals(StringUtils.toEmpty(obj2));
    }

    /************************************************
	 * 使用可能文字列とは<br/>
	 * 半角数字、半角大文字アルファベット、半角カナ(小文字は除く)、
	　* 半角濁点、半角半濁点、半角記号8種類(\｢｣/()-.)、半角スペース
	 * @param strVal 入力文字
	 * @return 使用可能な場合はtrue、そうでなければfalse
	 ************************************************/
	public static boolean isValidCharacter(String pattern, String strVal) {
		if (StringUtils.nullOrBlank(strVal)) {
			return false;
		}
		return Pattern.matches(pattern, strVal);
	}
	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * BigDecimal値に変換する。
	 * 渡されたオブジェクトがNULLの時0を返す。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return
	 ************************************************/
	public static BigDecimal toBigDecimalZero(Object aObject) {
		try {
			if (aObject == null) {
				return new BigDecimal("0");
			}
			return new BigDecimal(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}
	/************************************************
	 * 渡されたオブジェクトのtoString()で書式化された文字列を
	 * BigDecimal値に変換する。
	 * もしNumberFormatException等のエラーが発生した場合
	 * などはnullを返す
	 *
	 * @param aObject
	 * @return
	 ************************************************/
	public static BigDecimal toBigDecimal(Object aObject) {
		try {
			return new BigDecimal(aObject.toString());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Divide list by the specified size
	 *
	 * @param lst list
	 * @param size the size
	 *
	 * @return Collection of sub list by the specified size
	 */
    public static List<List<Long>> subList(List<Long> lst, int size) {
    	List<List<Long>> results = new ArrayList<List<Long>>();

    	int maxSizeInInQualification = size;

    	if(maxSizeInInQualification <= 0) {
    		maxSizeInInQualification = MAX_SQL_QUALIFICATION_SIZE;
    	}
    	if(!isCollectionNullOrEmpty(lst)) {
    		int lstSize = lst.size();

	    	for (int i = 0; i < lstSize; i += maxSizeInInQualification) {
	    		int to;

	    		if (lstSize < i + maxSizeInInQualification) {
	    			to = lstSize;
	    		} else {
	    			to = i + maxSizeInInQualification;
	    		}
	    		results.add(lst.subList(i, to));
	    	}
    	}
    	return results;
    }


	/**
	 * Divide array by the specified size
	 *
	 * @param arr array
	 * @param size the size
	 *
	 * @return Array of sub array by the specified size
	 */
    public static Long[][] subArray(Long[] arr, int size) {
    	//List<List<Long>> results = new ArrayList<List<Long>>();
    	Long[][] results = null;

    	if(isArrayNullOrEmpty(arr)) {
    		results = new Long[0][];
    	} else {
    		List<Long> lst = Arrays.asList(arr);

    		List<List<Long>> subLst = subList(lst, size);
    		results = new Long[subLst.size()][];
    		int i = 0;

    		for(List<Long> sub : subLst) {
    			int subSize = sub.size();
    			Long[] subArr = new Long[sub.size()];

    			for(int j = 0; j < subSize; j++) {
    				subArr[j] = sub.get(j);
    			}

    			results[i++] = subArr;
    		}
    	}

    	return results;
    }

	/**
     * This method will convert a collection to string<br/>
     * Ex: s = {a, b, c} . delimiter = ";"<br/>
     * Result : a;b;c<br/>
     * @param lst the list
     * @param delimiter the delimiter
     * @return joined list in string
     */
    public static String joinList(List<Long> lst, String delimiter) {

        if(isCollectionNullOrEmpty(lst)){
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        Iterator<Long> iter = lst.iterator();

        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

	/**
     * This method will convert an array to string<br/>
     * Ex: s = {a, b, c} . delimiter = ";"<br/>
     * Result : a;b;c<br/>
     * @param lst the list
     * @param delimiter the delimiter
     * @return joined array in string
     */
    public static String joinArray(Long[] arr, String delimiter) {

        if(isArrayNullOrEmpty(arr)){
            return "";
        }

        List<Long> lst = Arrays.asList(arr);

        return joinList(lst, delimiter);
    }

	/**
     * This method will convert a collection to string<br/>
     * Ex: s = {a, b, c} . delimiter = ";"<br/>
     * Result : a;b;c<br/>
     * @param lst the list
     * @param delimiter the delimiter
     * @return joined list in string
     */
    public static String joinStrList(List<String> lst, String delimiter) {

        if(isCollectionNullOrEmpty(lst)){
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = lst.iterator();

        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

	 /**
	  * Check index is out of bound data model
	  *
	  * @param model DataModel
	  * @param index index
	  * @return true if :
	  *              1. DataModel is null or empty
	  *              or
	  *              2. index < 0 or index >= DataModel.getRowCont()
	  *         otherwise, return false
	  */
	 public static boolean isIndexOutOfBound(DataModel model, int index) {
		 boolean result = false;

	     if(null != model) {
	    	 if(index < 0 || index >= model.getRowCount())
	         result = true;
	     }
	     return result;
	 }

	   /**
      * Convert to SQL string value ('<value>')
      *
      * @param value
      * @return SQL string value
      */
     public static String toSqlString(String value) {
         StringBuffer buff = new StringBuffer();

         buff.append("'");

         if(StringUtils.isValidString(value)) {
             buff.append(value);
         }

         buff.append("'");

         return buff.toString();
     }

     /**
      * Convert string list to SQL string list
      *
      * @param lst string list
      * @return SQL string list
      */
     public static void toSqlStringLst(List<String> lst) {
         if(!DataUtil.isCollectionNullOrEmpty(lst)) {
             int lstSize = lst.size();

             for(int i = 0; i < lstSize; i++) {
                 lst.set(i, toSqlString(lst.get(i)));
             }
         }
     }
     
     /**
      * convert to SQL string value ('<value>')
      * 例：
      * value = empty,return null
      * value != empty,return ('<value>')
      * @param value
      * @return
      */
     public static String adjustSqlStringEmptyToNull(String value) {
     	if(StringUtils.nullOrBlank(value)){
     		return null;
     	}else{
     		return toSqlString(value);
     	}
     }
}
