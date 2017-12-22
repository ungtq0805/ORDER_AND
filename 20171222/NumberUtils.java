package net.poweregg.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class NumberUtils
{
  public static final String NUM_CHAR = "^[0-9.,-]+";
  
  public static boolean isNumber(String value, boolean isNegativePermit)
  {
    if (StringUtils.nullOrBlank(value)) {
      return true;
    }
    if (!value.replaceAll("[.-]", "").equals(value
      .replaceAll("[^0-9]", ""))) {
      return false;
    }
    int firstMinus = value.indexOf("-");
    if (isNegativePermit)
    {
      if (firstMinus > -1)
      {
        if (firstMinus > 0) {
          return false;
        }
        if (value.length() < 2) {
          return false;
        }
        if (value.lastIndexOf("-") > 0) {
          return false;
        }
      }
    }
    else if (firstMinus > -1) {
      return false;
    }
    int firstDot = value.indexOf(".");
    if (firstDot > -1)
    {
      if (firstDot == 0) {
        return false;
      }
      if (firstDot == value.length() - 1) {
        return false;
      }
      if (firstDot != value.lastIndexOf(".")) {
        return false;
      }
    }
    try
    {
      new BigDecimal(value);
    }
    catch (NumberFormatException e)
    {
      return false;
    }
    return true;
  }
  
  public static String editCommaValue(String aText)
  {
    if ((aText == null) || (aText.compareTo("") == 0)) {
      return "";
    }
    aText = aText.replace(",", "");
    boolean minusFlag = false;
    String sub = aText;
    if (aText.startsWith("-"))
    {
      minusFlag = true;
      
      sub = aText.substring(1);
    }
    while ((sub.startsWith("0")) && (sub.length() > 1)) {
      sub = sub.substring(1);
    }
    if (sub.indexOf('.') != -1) {
      for (int i = sub.indexOf('.') - 3; i > 0; i--)
      {
        sub = sub.substring(0, i) + "," + sub.substring(i);
        i -= 2;
      }
    } else {
      for (int i = sub.length() - 3; i > 0; i--)
      {
        sub = sub.substring(0, i) + "," + sub.substring(i);
        i -= 2;
      }
    }
    if (sub.endsWith(".")) {
      sub = sub.substring(0, sub.length() - 1);
    }
    if (sub.startsWith(".")) {
      sub = "0" + sub;
    }
    if (minusFlag) {
      sub = "-" + sub;
    }
    return sub;
  }
  
  public static boolean containsOnlyNumberChars(String value, String pattern)
  {
    if (StringUtils.nullOrBlank(pattern)) {
      pattern = "^[0-9.,-]+";
    }
    return Pattern.matches(pattern, value);
  }
  
  public static Long toLong(Object value)
  {
    if (null == value) {
      return null;
    }
    try
    {
      return Long.valueOf(value.toString());
    }
    catch (Exception e) {}
    return null;
  }
  
  public static long toLongValue(Object value)
  {
    if (null == value) {
      return 0L;
    }
    try
    {
      if (isBigDecimal(value)) {
        return ((BigDecimal)value).longValue();
      }
      return Long.valueOf(value.toString()).longValue();
    }
    catch (Exception e) {}
    return 0L;
  }
  
  public static Boolean toBoolean(Object value)
  {
    try
    {
      return Boolean.valueOf(value.toString());
    }
    catch (Exception e) {}
    return null;
  }
  
  public static int toIntValue(Object value)
  {
    try
    {
      if (isBigDecimal(value)) {
        return parseIntegerValue(value).intValue();
      }
      return Integer.valueOf(value.toString()).intValue();
    }
    catch (Exception e) {}
    return 0;
  }
  
  public static long parseLongValue(String number, int radix)
  {
    try
    {
      return Long.parseLong(number, radix);
    }
    catch (Exception e) {}
    return 0L;
  }
  
  public static Short toShort(String number)
  {
    try
    {
      return Short.valueOf(Short.parseShort(number));
    }
    catch (Exception ex) {}
    return null;
  }
  
  public static Integer toInteger(String number)
  {
    try
    {
      return Integer.valueOf(Integer.parseInt(number));
    }
    catch (Exception ex) {}
    return null;
  }
  
  public static Integer toInteger(String number, int defaultValue)
  {
    Integer temp = toInteger(number);
    if (temp == null) {
      return new Integer(defaultValue);
    }
    return temp;
  }
  
  public static Integer parseIntegerValue(Object obj)
  {
    if (isBigDecimal(obj))
    {
      BigDecimal bigDec = (BigDecimal)obj;
      return new Integer(bigDec.intValue());
    }
    return new Integer(0);
  }
  
  private static boolean isBigDecimal(Object obj)
  {
    if ((obj instanceof BigDecimal)) {
      return true;
    }
    return false;
  }
}
