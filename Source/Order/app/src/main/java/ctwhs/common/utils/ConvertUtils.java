/*
 * $Id: ConvertUtils.java 185 2008-07-23 04:12:23Z damnh $
 * All Rights Copyright(C) NIKKO COMPANY 2008-
 */

package ctwhs.common.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * An <b>experimental </b> class with some standard conversion utilities. Needs
 * more proof of concept and unit testing.
 * 
 * @author Ted Husted
 * @author OK State DEQ
 * @author WXXI Public Broadcasting Council
 * @version $Revision: 185 $ $Date: 2008-07-23 11:12:23 +0700 (水, 23 7 2008) $
 */
public class ConvertUtils {
	/**
	 * This is an all-static utility class. A private constructor prevents
	 * inadvertent instantiation.
	 */
	private ConvertUtils() {
	}

	// ---------------------------------------------------- Text Separators
	/**
	 * An empty string.
	 */
	public static String STRING_EMPTY = "";

	/**
	 * An single-space string.
	 */
	public static final String STRING_SPACE = " ";

	/**
	 * Space character.
	 */
	public static final char SPACE = ' ';

	/**
	 * Comma character.
	 */
	public static final char COMMA = ',';

	/**
	 * Single-quotation character.
	 */
	public static final char SINGLE_QUOTATION = '\'';

	/**
	 * Double-quotation character.
	 */
	public static final char DOUBLE_QUOTATION = '"';

	/**
	 * Horizontal tab character.
	 */

	public static final char HORIZONTAL_TABULATION = '\t'; // (aka u0009)

	/**
	 * Line feed character.
	 */
	public static final char LINE_FEED = 10; // (aka u000A);

	/**
	 * Vertical tab character.
	 */
	public static final char VERTICAL_TABULATION = '\u000B';

	/**
	 * Form feed character.
	 */
	public static final char FORM_FEED = '\u000C';

	/**
	 * Carriage return character.
	 */
	public static final char CARRIAGE_RETURN = 13; // (aka u000D)

	/**
	 * File separator character.
	 */
	public static final char FILE_SEPARATOR = '\u001C';

	/**
	 * Group separator character.
	 */
	public static final char GROUP_SEPARATOR = '\u001D';

	/**
	 * Record separator character.
	 */
	public static final char RECORD_SEPARATOR = '\u001E';

	/**
	 * Unit separator character.
	 */
	public static final char UNIT_SEPARATOR = '\u001F';

	/**
	 * TODO Describe the JavaDoc comment!
	 */
	public static final String CRLF = new String(new char[]{CARRIAGE_RETURN,
			LINE_FEED});

	/**
	 * Array of line separator characters.
	 * http://java.sun.com/j2se/1.3/docs/api/java/lang/Character.html#isWhitespace(char)
	 */
	public static final char[] SEPARATORS = {HORIZONTAL_TABULATION, LINE_FEED,
			VERTICAL_TABULATION, FORM_FEED, CARRIAGE_RETURN, FILE_SEPARATOR,
			GROUP_SEPARATOR, RECORD_SEPARATOR, UNIT_SEPARATOR};

	// --------------------------------------------------------- Tokenizers

	/**
	 * Return array of tokens, using the result of <code>getTokeSep()</code>
	 * as the separator. Blanks are trimmed from tokens.
	 * 
	 * @param tokens
	 *            The string to tokenize into an array
	 * @param separator
	 * @return String[]
	 */
	public static String[] tokensToArray(String tokens, String separator) {
		if (null==tokens){
			return null;
		}
		StringTokenizer tokenizer = new StringTokenizer(tokens, separator);
		int i = 0;
		String[] array = new String[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if ((token == null) || (token.length() == 0))
				continue;
			array[i++] = token;
		}
		return array;
	} // end tokensToArray

	/**
	 * Return list of tokens, using the result of <code>getTokeSep()</code> as
	 * the separator. Blanks are trimmed from tokens.
	 * 
	 * @param tokens
	 *            The string to tokenize into an array
	 * @param separator
	 * @return List
	 */
	public static List tokensToList(String tokens, String separator) {
		if (null==tokens){
			return new ArrayList();
		}
		StringTokenizer tokenizer = new StringTokenizer(tokens, separator);
		List list = new ArrayList(tokenizer.countTokens());
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			if ((token == null) || (token.length() == 0))
				continue;
			list.add(token);
		}
		return list;
	} // end tokensToList
	/**
	 * Return list of tokens, using the result of <code>getTokeSep()</code> as
	 * the separator. Blanks are trimmed from tokens.
	 * 
	 * @param tokens
	 *            The string to tokenize into an array
	 * @param separator
	 * @param kind
	 * @return List
	 */
	public static List tokensToList(String tokens, String separator, char kind) {
		if (null==tokens){
			return new ArrayList();
		}
		StringTokenizer tokenizer = new StringTokenizer(tokens, separator);
		List list = new ArrayList(tokenizer.countTokens());
		while (tokenizer.hasMoreTokens()) {
			switch (kind) {
			case 'B': // BigDecimal
				break;
			case 'D': // Double
				break;
				
			case 'L': // Long	
				Long tokenL = new Long(tokenizer.nextToken().trim());
				if (tokenL == null) {
					continue;
				}
				list.add(tokenL);
				break;

			default: // String
				String token = tokenizer.nextToken().trim();
				if ((token == null) || (token.length() == 0)) {
					continue;
				}
				list.add(token);
				break;
			}
		}
		return list;
	} // end tokensToList

	/**
	 * Return a <code>String</code> object which separated by
	 * <code>separator</code>
	 * 
	 * @param list
	 *            list of elements
	 * @param separator
	 * @return String
	 */
	public static String listToTokens(List list, String separator) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; list != null && !list.isEmpty() && i < list.size(); i++) {
			if (i != 0) {
				buff.append(separator + list.get(i).toString());
			} else {
				buff.append(list.get(i).toString());
			}
		}
		return buff.toString();
	}

	// ------------------------------------------------------- Text Methods
	/**
	 * Returns true if null or trims to an empty string.
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean nullOrBlank(String s) {
		return ((null == s) || (STRING_EMPTY.equals(s.trim())));
	}
	
	/**
	 * Returns true if not null or not trims to an empty string.
	 * @param s
	 * @return boolean
	 */
	public static boolean notNullOrBlank(String s) {
		return !((null == s) || (STRING_EMPTY.equals(s.trim())));
	}

	/**
	 * Returns true if null or zero.
	 * 
	 * @param key
	 * @return boolean
	 */
	public static boolean nullOrZero(Number key) {
		return ((null == key) || (0 == key.doubleValue()));
	}

	/**
	 * Returns true if null, trims to an empty string, or to "0".
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean nullOrBlankOrZero(String s) {
		if (null == s)
			return true;
		String _s = s.trim();
		return ((STRING_EMPTY.equals(_s)) || (STRING_ZERO.equals(_s)));
	}

	/**
	 * Return a trimmed or empty string (but not null).
	 * 
	 * @param string
	 * @return String
	 */
	public static String toTrimOrEmpty(String string) {
		if (null == string)
			return STRING_EMPTY;
		return string.trim();
	}

	/**
	 * Return an empty string or original string.
	 * 
	 * @param anObject
	 * @return String
	 */
	public static String toEmpty(Object anObject) {
		if (null == anObject || null == anObject.toString()) {
			return STRING_EMPTY;
		}
		return anObject.toString();
	}

	/**
	 * Return a trimmed or null.
	 * 
	 * @param string
	 * @return String
	 */
	public static String toTrimOrNull(String string) {
		if (null == string)
			return null;
		return string.trim();
	}

	/**
	 * Return an null or original string.
	 * 
	 * @param anObject
	 * @return String
	 */
	public static String toNull(Object anObject) {
		if (nullOrBlank(anObject)) {
			return null;
		}
		return anObject.toString();
	}

	/**
	 * Returns a trimmed uppercase string or null.
	 * 
	 * @param string
	 * @return String
	 */
	public static String toUpperOrNull(String string) {
		if (null != string)
			return string.toUpperCase().trim();
		return null;
	}

	/**
	 * Returns a trimmed uppercase string or empty string.
	 * 
	 * @param string
	 * @return String
	 */
	public static String toUpperOrEmpty(String string) {
		if (null != string)
			return string.toUpperCase().trim();
		return STRING_EMPTY;
	}

	/**
	 * Returns a trimmed lowercase string or null.
	 * 
	 * @param string
	 * @return String
	 */
	public static String toLowerOrNull(String string) {
		if (null != string)
			return string.toLowerCase().trim();
		return null;
	}

	/**
	 * Returns a trimmed lowercase string or empty string.
	 * 
	 * @param string
	 * @return String
	 */
	public static String toLowerOrEmpty(String string) {
		if (null != string)
			return string.toLowerCase().trim();
		return STRING_EMPTY;
	}

	/**
	 * The token that signifies the begnning of a query string ["?"].
	 */
	public static String QS_START = "?";

	/**
	 * The token that delimits two or more attributes of a query string
	 * ["&amp;"].
	 */
	public static String QS_DELIM = "&amp;";

	/**
	 * The token that seperates an attribute name and value.
	 */
	public static String QS_SEP = "=";

	/**
	 * Appends name=value parameter.
	 * 
	 * @param path
	 * @param name
	 * @param value
	 * @return String
	 */
	public static String addParam(String path, String name, String value) {
		StringBuffer uri = new StringBuffer(path);
		boolean isQuery = (path.indexOf(QS_START) >= 0);
		if (isQuery)
			uri.append(QS_DELIM);
		else
			uri.append(QS_START);
		uri.append(name);
		uri.append(QS_SEP);
		uri.append(value);
		return uri.toString();
	}

	/**
	 * Appends name=value parameters to path from Map.
	 * 
	 * @param path
	 * @param parameters
	 * @return String
	 */
	public static String addParams(String path, Map parameters) {
		if (null == path)
			path = new String();
		if ((null == parameters) || (parameters.isEmpty()))
			return path;
		StringBuffer uri = new StringBuffer(path);
		boolean isQuery = (path.indexOf(QS_START) >= 0);
		if (isQuery)
			uri.append(QS_DELIM);
		else
			uri.append(QS_START);
		Set entries = parameters.entrySet();
		for (Iterator i = entries.iterator(); i.hasNext();) {
			Entry e = (Entry) i.next();
			uri.append(e.getKey());
			uri.append(QS_SEP);
			uri.append(e.getValue());
		}
		return uri.toString();
	}

	/**
	 * Returns parameters as a series of hidden HTML fields.
	 * 
	 * @param parameters
	 * @return String
	 */
	public static String renderHiddenFields(Map parameters) {
		if ((null == parameters) || (parameters.isEmpty()))
			return new String();
		StringBuffer html = new StringBuffer();
		Set entries = parameters.entrySet();
		for (Iterator i = entries.iterator(); i.hasNext();) {
			html.append("<input type='hidden' name='");
			Entry e = (Entry) i.next();
			html.append(e.getKey());
			html.append("' value='");
			html.append(e.getValue());
			html.append("' />");
		}
		return html.toString();
	}

	/**
	 * Replace line returns with spaces.
	 * 
	 * @param string
	 * @return String
	 */
	/*
	 * //�Q�Ƃ��Ȃ� private static String stripLn(String string) { // :FIXME: Better
	 * way to buffer the interim strings? for (int i = 0; i < SEPARATORS.length;
	 * i++) { string = string.replace(SEPARATORS[i], SPACE); } return string; }
	 */
	// ----------------------------------------------------- Numeric Values
	/**
	 * An Double 0.
	 */
	public static Double DOUBLE_ZERO = new Double(0);

	/**
	 * An Double 1.
	 */
	public static Double DOUBLE_ONE = new Double((double) 1.0);

	/**
	 * An Long 0.
	 */
	public static Long LONG_ZERO = new Long(0);

	/**
	 * An Long 1.
	 */
	public static Long LONG_ONE = new Long(1);
	
	/**
	 * An Long 100.
	 */
	public static Long LONG_ONE_HUND = new Long(100);

	/**
	 * An Integer 0.
	 */
	public static Integer INTEGER_ZERO = new Integer(0);

	/**
	 * An Integer 1.
	 */
	public static Integer INTEGER_ONE = new Integer(1);

	/**
	 * A Short 0.
	 */
	public static Short SHORT_ZERO = new Short((short) 0);

	/**
	 * A Short 1.
	 */
	public static Short SHORT_ONE = new Short((short) 1);

	/**
	 * A String 0.
	 */
	public static String STRING_ZERO = "0";

	/**
	 * A String 1.
	 */
	public static String STRING_ONE = "1";
	
	/**
	 * A BigDecimal 0.
	 */
	public static BigDecimal BIGDECIMAL_ZERO = new BigDecimal("0");

	// ---------------------------------------------------- Numeric Methods
	/**
	 * Return String with of digits only (0..9).
	 * http://java.sun.com/j2se/1.4/docs/api/java/lang/Character.html
	 * 
	 * @param s
	 * @return String
	 */
	public static String getDigits(String s) {
		if (s == null)
			return null;
		int n = s.length();
		StringBuffer sb = new StringBuffer(n);
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c))
				sb.append(c);
		}
		return (sb.toString());
	}

	/**
	 * Returns number formatted for default or given locale.
	 * 
	 * @param value
	 * @param locale
	 * @return String
	 */
	public static String getNumber(Number value, Locale locale) {
		if (locale == null)
			return appendDec(value,(NumberFormat.getInstance().format(value)));
		return appendDec(value,(NumberFormat.getInstance(locale).format(value)));
	}

	/**
	 * Returns percent formatted for default or given locale.
	 * 
	 * @param value
	 * @param locale
	 * @return String
	 */
	public static String getPercent(Number value, Locale locale) {
		if (locale == null)
			return (NumberFormat.getPercentInstance().format(value));
		return (NumberFormat.getPercentInstance(locale).format(value));
	}

	/**
	 * Returns whether the last digit of numeric string is a parity check on the
	 * others per the "primes of nines" method. Reference:
	 * http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
	 * 
	 * @param number -
	 *            Number to check. Must be all digits and not null.
	 * @return boolean
	 */
	public static boolean luhnCheck(String number) {
		int no_digit = number.length();
		int oddoeven = no_digit & 1;
		long sum = 0;
		for (int count = 0; count < no_digit; count++) {
			int digit = Integer.parseInt(String.valueOf(number.charAt(count)));
			if (((count & 1) ^ oddoeven) == 0) { // not
				digit *= 2;
				if (digit > 9)
					digit -= 9;
			}
			sum += digit;
		}
		if (sum == 0)
			return false;
		if (sum % 10 == 0)
			return true;
		return false;
	}

	/**
	 * Returns number with the appropriate digit appended so that is passes a
	 * "luhnCheck".
	 * 
	 * @param number -
	 *            Number to process. Must be all digits and not null.
	 * @return String
	 */
	public static String addLuhnDigit(String number) {
		// I don't actually understand the alogorithm
		// so we just use brute force to find the digit.
		char[] digits = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		int c = number.length();
		StringBuffer tryNumber = new StringBuffer(number + digits[0]);
		int i;
		for (i = 0; i < 10; i++) {
			tryNumber.setCharAt(c, digits[i]);
			if (luhnCheck(tryNumber.toString()))
				break;
		}
		return tryNumber.toString();
	}

	// ------------------------------------------------------------ Decimal
	/**
	 * Default decimal pattern.
	 * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
	 */
	public static String DECIMAL_PATTERN = "###,###.##";

	/**
	 * Symbols that can be used in a decimal pattern.
	 * 
	 * @param locale
	 * @return DecimalFormatSymbols
	 */
	public static DecimalFormatSymbols getGenericDecimal(Locale locale) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		symbols.setGroupingSeparator('`'); // :FIXME: Want apostrophe here
		return symbols;
	}

	/**
	 * Return decimal number formatted for default or given locale.
	 * 
	 * @param value
	 * @param locale
	 * @return String
	 */
	public static String getDecimal(Number value, Locale locale) {
		if (value == null) {
			return null;
		}
		if (locale == null)
			return appendDec(value,(NumberFormat.getInstance().format(value)));
		return appendDec(value,(NumberFormat.getInstance(locale).format(value)));
	}

	/**
	 * Return decimal number formatted for default or given locale using given
	 * pattern.
	 * 
	 * @param value
	 * @param locale
	 * @param pattern
	 * @return String
	 */
	public static String getDecimal(Number value, Locale locale, String pattern) {
		if (value == null) {
			return null;
		}
		NumberFormat formatter;
		if (locale == null)
			formatter = new DecimalFormat(pattern);
		else {
			formatter = NumberFormat.getNumberInstance(locale);
			DecimalFormat df = (DecimalFormat) formatter;
			df.applyPattern(pattern);
			return df.format(value);
		}
		return (formatter.format(value));
	}
	/**
	 * 
	 * get the String has been formated, have check value null
	 * 
	 * @param value
	 * @param locale
	 * @param pattern
	 * @return String ojbect
	 * @author tranghv
	 */
	public static String getDecimalCheckNull(Number value, Locale locale,
											 String pattern) {
		if (null == value) {
			return getDecimal(new BigDecimal("0"), locale, pattern);
		}
		return getDecimal(value, locale, pattern);
	}
	/**
	 * TODO Describe the JavaDoc comment!
	 * 
	 * @param value
	 * @param locale
	 * @param pattern
	 * @return Number
	 */
	public static Number getNumber(String value, Locale locale, String pattern) {
		NumberFormat formatter;
		if (value != null && !value.equals("")) {
			try {
				if (locale == null)
					formatter = new DecimalFormat(pattern);
				else {
					formatter = NumberFormat.getNumberInstance(locale);
					DecimalFormat df = (DecimalFormat) formatter;
					return df.parse(value);
				}
				return (formatter.parse(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Return decimal for default locale using standard pattern.
	 * 
	 * @param value
	 * @return String
	 */
	public static String getDecimal(Number value) {
		if (null != value) {
			return appendDec(value,getDecimal(value, (Locale) null, DECIMAL_PATTERN));
		}
		return "";
	}

	// ----------------------------------------------------------- Currency
	/**
	 * Standard currency pattern.
	 * http://java.sun.com/docs/books/tutorial/i18n/format/numberpattern.html
	 */
	public static String CURRENCY_PATTERN = "\\" + DECIMAL_PATTERN;

	/**
	 * Return currency for default locale using standard pattern.
	 * 
	 * @param value
	 * @return String
	 */
	public static String getCurrency(Number value) {
		return getDecimal(value, null, CURRENCY_PATTERN);
	}

	// --------------------------------------------------------------- Date
	/**
	 * Default style for dates and times.
	 */
	public static int DEFAULT = DateFormat.DEFAULT;

	/**
	 * Short style for dates and times.
	 */
	public static int SHORT = DateFormat.SHORT;

	/**
	 * Medium style for dates and times.
	 */
	public static int MEDIUM = DateFormat.MEDIUM;

	/**
	 * Long style for dates and times.
	 */
	public static int LONG = DateFormat.LONG;

	/**
	 * Full style for dates and times.
	 */
	public static int FULL = DateFormat.FULL;

	/**
	 * Default lenient setting for getDate.
	 */
	private static boolean LENIENT_DATE = false;

	/**
	 * A "default" date format.
	 */
	public static String ESCAPE_DATE_PATTERN = "yyyy.MM.dd";

	/**
	 * Simple date format
	 */
	public static String SIMPLE_DATE_PATTERN = "yy.MM.dd";

	/**
	 * Convert String to Date using given format. Returns null if conversion
	 * fails. Set lenient=false to disallow dates like 2001-9-32.
	 * http://java.sun.com/j2se/1.4/docs/api/java/text/SimpleDateFormat.html
	 * 
	 * @param dateDisplay
	 * @param format
	 * @param lenient
	 * @return Date
	 */
	public static Date getDate(String dateDisplay, String format,
							   boolean lenient) {
		if (dateDisplay == null) {
			return null;
		}
		DateFormat df = null;
		try {
			if (format == null) {
				df = new SimpleDateFormat();
			} else {
				df = new SimpleDateFormat(format);
			}
			// setLenient avoids allowing dates like 9/32/2001
			// which would otherwise parse to 10/2/2001
			df.setLenient(false);
			return df.parse(dateDisplay);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Convert String to Date using given format. Returns null if conversion
	 * fails. Uses "strict" coNversion (lenient=false).
	 * 
	 * @param dateDisplay
	 * @param format
	 * @return Date
	 */
	public static Date getDate(String dateDisplay, String format) {
		return getDate(dateDisplay, format, LENIENT_DATE);
	}

	/**
	 * Convert String to Date using a medium (weekday day month year) format.
	 * Returns null if conversion fails. Uses "strict" coNversion
	 * (lenient=false).<br>
	 * default format: �hyy/MM/dd hh:mm�h
	 * 
	 * @author Hal Deadman
	 * @param dateDisplay
	 * @return Date
	 */
	public static Date getDate(String dateDisplay) {
		return getDate(dateDisplay, null, LENIENT_DATE);
	}

	/**
	 * 
	 * Return Date value using a String. Null or conversion error returns null.<br>
	 * default format: �hyy/MM/dd hh:mm�h
	 * 
	 * @param string
	 * @return Date
	 */
	public static Date toDate(String string) {
		if (string == null) {
			return null;
		}
		try {
			return getDate(string);
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * Convert date to String for given locale in given style. A null locale
	 * will return the default locale.
	 * 
	 * @param date
	 * @param locale
	 * @param style
	 * @return String
	 */
	public static String getDate(Date date, Locale locale, int style) {
		if (locale == null)
			return (DateFormat.getDateInstance(style).format(date));
		return (DateFormat.getDateInstance(style, locale).format(date));
	}

	/**
	 * Convert date to String for default locale in given style. A null locale
	 * will return the default locale.
	 * 
	 * @param date
	 * @param style
	 * @return String
	 */
	public static String getDate(Date date, int style) {
		return getDate(date, (Locale) null, style);
	}

	/**
	 * Convert date to String for default locale in DEFAULT style. A null locale
	 * will return the default locale.
	 * 
	 * @param date
	 * @return String
	 */
	public static String getDate(Date date) {
		return getDate(date, (Locale) null, DEFAULT);
	}

	/**
	 * Return String value representing Date. Null returns null.
	 * 
	 * @param date
	 * @return String
	 */
	public static String toString(Date date) {
		if (date == null) {
			return null;
		}
		return getDate(date);
	}

	/**
	 * Return String value representing Date. Null returns null.
	 * 
	 * @param date
	 * @return String
	 */
	public static String toEscape(Date date) {
		return toEscape(date, ESCAPE_DATE_PATTERN);
	}
	/**
	 * Return String value representing Date. Null returns null.
	 * 
	 * @param date
	 * @return String
	 */
	public static String toSimpleEscape(Date date) {
		return toEscape(date, SIMPLE_DATE_PATTERN);
	}

	/**
	 * Return String value representing Date. Null returns null.
	 * 
	 * @param aDate
	 * @param aPattern
	 *            pattern of date
	 * @return String
	 */
	public static String toEscape(Date aDate, String aPattern) {
		if (aDate == null)
			return null;
		DateFormat df = null;
		try {
			df = new SimpleDateFormat(aPattern);
		} catch (Throwable t) {
			return null;
		}
		df.setLenient(false);
		return df.format(aDate);
	}

	// ---------------------------------------------------------- Timestamp
	/**
	 * Date separator ["."].
	 */
	public static final String DATE_SEPARATOR = ".";

	/**
	 * Time separator [":"].
	 */
	public static final String TIME_SEPARATOR = ":";

	/**
	 * Date Time separator [" "].
	 */
	public static final String DATE_TIME_SEPARATOR = STRING_SPACE;

	/**
	 * String to prepend to time [HH:MM:SS.d] to create a Timestamp escape
	 * string ["0002.11.30"].
	 */
	public static final String TIMESTAMP_DATE_ZERO = "0002.11.30";

	/**
	 * String to append to date [YEAR-MM-DD] to create a Timestamp escape string ["
	 * 00:00:00.0"]. Note: includes leading space.
	 */
	public static final String TIMESTAMP_TIME_ZERO = " 00:00:00.0";

	/**
	 * Escape string representing "November 30, 0002 00:00:00".
	 */
	public static String ZERO_TIMESTAMP_DISPLAY = "2-11-30 00:00:00";

	/**
	 * Timestamp representing ""November 30, 0002 00:00:00".
	 */
	public static Timestamp ZERO_TIMESTAMP = new Timestamp(
			(long) 00000000000000);

	/**
	 * Escape string to create Timestamp representing "January 1, 1970
	 * 00:00:00".
	 */
	public static String NULL_TIMESTAMP_DISPLAY = "1970-01-01 00:00:00";

	/**
	 * @deprecated Use NULL_TIMESTAMP_DISPLAY.
	 */
	public static String NULL_TIMESTAMP_TEXT = "NULL_TIMESTAMP_DISPLAY";

	/**
	 * Value needed to create Timestamp representing "January 1, 1970 00:00:00".
	 * From the documentation, you would think this would be Timestamp(0), but
	 * empirical tests show it to be Timestamp(18000000).
	 */
	public static long NULL_TIME = (long) 18000000;

	/**
	 * Timestamp representing "January 1, 1970 00:00:00".
	 */
	public static Timestamp NULL_TIMESTAMP = new Timestamp(NULL_TIME);

	/**
	 * Timestamp representing "December 31, 2029, 23:59:59.9"
	 */
	public static Timestamp MAX_TIMESTAMP = Timestamp
			.valueOf("2029-12-31 23:59:59.999");

	/**
	 * Return the String representing "January 1, 1970 00:00:00".
	 * 
	 * @return String
	 */
	public static String getTimestampDisplayNull() {
		return NULL_TIMESTAMP_DISPLAY;
	}

	/**
	 * Return the String representing the current timestamp;
	 * 
	 * @return String
	 */
	public static String getTimestampDisplay() {
		return getTimestamp(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * @deprecated Use getTimestampDisplay.
	 * @return String
	 */
	public static String getTimestampText() {
		return getTimestampDisplay();
	}

	/**
	 * Return null if timestamp is null or equals "January 1, 1970 00:00:00".
	 * 
	 * @param timestamp
	 * @return boolean
	 */
	public static boolean isNull(Timestamp timestamp) {
		return ((timestamp == null) || (timestamp.getTime() == NULL_TIME));
	}

	/**
	 * Factory method to return timestamp initialized to current system time.
	 * For timestamp as a String in the default format, use
	 * <code>getTimestamp().toString()</code>.
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Convert timestamp to String for given locale in given style. A null
	 * locale will return the default locale.
	 * 
	 * @param timestamp
	 * @param locale
	 * @param style
	 * @return String
	 */
	public static String getTimestamp(Timestamp timestamp, Locale locale,
									  int style) {
		Date date = (Date) timestamp;
		if (locale == null)
			return (DateFormat.getDateTimeInstance(style, style).format(date));
		return (DateFormat.getDateTimeInstance(style, style, locale)
				.format(date));
	}

	/**
	 * Convert date to String for default locale in given style. A null locale
	 * will return the default locale.
	 * 
	 * @param timestamp
	 * @param style
	 * @return String
	 */
	public static String getTimestamp(Timestamp timestamp, int style) {
		return getTimestamp(timestamp, (Locale) null, style);
	}

	/**
	 * Convert date to String for default locale in DEFAULT style. A null locale
	 * will return the default locale.
	 * 
	 * @param timestamp
	 * @return String
	 */
	public static String getTimestamp(Timestamp timestamp) {
		return getTimestamp(timestamp, (Locale) null, DEFAULT);
	}

	/**
	 * Return Timestamp value using a String. Null or conversion error returns
	 * null.
	 * 
	 * @param string
	 *            representing Timestamp
	 * @return Timestamp
	 */
	public static Timestamp toTimestamp(String string) {
		if (string == null) {
			return null;
		}
		try {
			return Timestamp.valueOf(string);
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * Return a Timestamp based on the parameters. Any nulls or conversion error
	 * returns null.
	 * 
	 * @param year
	 *            The year
	 * @param month
	 *            The month
	 * @param day
	 *            The day
	 * @return Timestamp for year-month-day
	 */
	public static Timestamp toTimestamp(String year, String month, String day) {
		if ((null == year) || (null == month) || (null == day))
			return null;
		StringBuffer sb = new StringBuffer();
		// YEAR-MM-DD 00:00:00.0
		sb.append(year);
		sb.append(DATE_SEPARATOR);
		sb.append(month);
		sb.append(DATE_SEPARATOR);
		sb.append(day);
		sb.append(TIMESTAMP_TIME_ZERO);
		return toTimestamp(sb.toString());
	}

	/**
	 * Return String value representing Timestamp. Null returns null.
	 * 
	 * @param timestamp
	 * @return String
	 */
	public static String toString(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toString();
	}
	
	/**
	 * Return String value representing Object. Null returns null.
	 * 
	 * @param obj
	 * @return String
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	// ----------------------------------------------------------- Internet
	/**
	 * Return the integer value of an IP address in dotted octet form, like that
	 * returned by request.getRemotehost). :FIXME: Needs to be implemented; just
	 * returns zero.
	 * 
	 * @param ipAddress
	 * @return Integer
	 */
	public static Integer ipNode(String ipAddress) {
		return INTEGER_ZERO;
	}

//	// -- MITANI Framework Original --//
//	/**
//	 * Bean����String�^�̑S�Ẵv���p�e�B�l�������I�ɒu�������܂��B <BR>
//	 * �Ⴆ�΁ABean�̑S�Ă�null�l���󕶎��ɒu��������ꍇ�Ȃǂ� �g�p�ł��܂��B
//	 *
//	 * @param aBean
//	 *            �u�������Ώۂ�Bean
//	 * @param anOrg
//	 *            �u�������O�̒l
//	 * @param aDest
//	 *            �u��������̒l
//	 */
//	public static void replacePropertyValues(Object aBean, String anOrg,
//			String aDest) {
//		// �v���p�e�B�}�b�v���擾
//		Map map = null;
//		try {
//			map = BeanUtils.describe(aBean);
//		} catch (Exception ex) {
//			return;
//		}
//		// �}�b�v�̊e�v���p�e�B�ɂ��āA�u�������O��
//		// �l�����������̂𒊏o���A�v���p�e�B�l��u��������
//		Iterator iterator = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			String value = (String) map.get(key);
//			boolean hit = false;
//			if (anOrg == null) {
//				if (value == null) {
//					hit = true;
//				}
//			} else {
//				if (anOrg.equals(value)) {
//					hit = true;
//				}
//			}
//			if (hit) {
//				try {
//					BeanUtils.setProperty(aBean, key, aDest);
//				} catch (Exception ex) {
//					// skip
//				}
//			}
//		}
//	}

//	/**
//	 * 2��Bean���r���A�������O�̃v���p�e�B�̒l�� ������瑼���֏㏑���R�s�[���܂��B <BR>
//	 * 2��Bean�͓����N���X�ł���K�v�͂���܂���B <BR>
//	 * null�l�̓R�s�[����܂���B ���Ȃ킿�AaSrc���ł͏㏑���������v���p�e�B������ null�ȊO�̒l���Z�b�g���AaTarget�̃v���p�e�B�l��
//	 * �ύX�ł��܂��B
//	 *
//	 * @param aTarget
//	 *            �㏑�������Bean �iaSrc�̃v���p�e�B�l��aTarget�ɃR�s�[���܂��j
//	 * @param aSrc
//	 *            �㏑������Bean �iaSrc�̃v���p�e�B�l��aTarget�ɃR�s�[���܂��j
//	 */
//	public static void overWriteValues(Object aTarget, Object aSrc) {
//		Map map = null;
//		try {
//			map = BeanUtils.describe(aSrc);
//		} catch (Exception ex) {
//			return;
//		}
//		Iterator iterator = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			String value = (String) map.get(key);
//			if (value != null) {
//				try {
//					BeanUtils.setProperty(aTarget, key, value);
//				} catch (Exception ex) {
//					// skip
//				}
//			}
//		}
//	}

//	// private static boolean registeredNullableConverter = false;
//
//	/**
//	 * org.apache.commons.beanutils.ConvertUtils���g�p���郉�b�p�[�N���X�p
//	 * �R���o�[�^�ɑ΂��A�f�t�H���g�l��null�ɐݒ肵�܂��B�ΏۂƂȂ�N���X�� �ȉ��̒ʂ�ł��B
//	 * <ul>
//	 * <li>BigDecimal</li>
//	 * <li>BigInteger</li>
//	 * <li>Boolean</li>
//	 * <li>Byte</li>
//	 * <li>Character</li>
//	 * <li>Class</li>
//	 * <li>Double</li>
//	 * <li>Float</li>
//	 * <li>Integer</li>
//	 * <li>Long</li>
//	 * <li>Short</li>
//	 * <li>Date</li>
//	 * <li>Time</li>
//	 * <li>Timestamp</li>
//	 * </ul>
//	 *
//	 */
//	public static void registerNullableConverter() {
//
//		// if (registeredNullableConverter) {
//		// return;
//		// }
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new BigDecimalConverter(null), BigDecimal.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new BigIntegerConverter(null), BigInteger.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new BooleanConverter(null), Boolean.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new ByteConverter(
//				null), Byte.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new CharacterConverter(null), Character.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new ClassConverter(
//				null), Class.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new DoubleConverter(
//				null), Double.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new FloatConverter(
//				null), Float.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new IntegerConverter(null), Integer.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new LongConverter(
//				null), Long.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new ShortConverter(
//				null), Short.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new SqlDateConverter(null), Date.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new SqlTimeConverter(null), Time.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new SqlTimestampConverter(null), Timestamp.class);
//		org.apache.commons.beanutils.ConvertUtils.register(new DateConverter(
//				null), Date.class);
//		org.apache.commons.beanutils.ConvertUtils.register(
//				new StringConverter(), String.class);
//		// registeredNullableConverter = true;
//	}

	/**
	 * return true if anObj is null object or blank string
	 * 
	 * @param anObj
	 * @return boolean
	 */
	public static boolean nullOrBlank(Object anObj) {
		return ((null == anObj) || (anObj.toString() != null && STRING_EMPTY
				.equals(anObj.toString().trim())));
	}

	/**
	 * return true if anObj =0 or blank
	 * 
	 * @param anObj
	 * @return boolean
	 */
	public static boolean blankOrZero(Object anObj) {
		boolean ret = false;
		if (anObj == null) {
			ret = true;
		} else {
			if (anObj instanceof Number) {
				ret = nullOrZero((Number) anObj);
			} else if (anObj instanceof String) {
				ret = nullOrBlank(anObj);
			}
		}
		return ret;
	}
	/**
	 * Add prefix character to aStr
	 * 
	 * @param aStr
	 * @param aPrefix
	 * @param aLength
	 * @return string with prefix string
	 */
	public static String addPrefixToString(String aStr, char aPrefix,
										   int aLength) {
		String ret = "";
		if (null == aStr || aStr.length() >= aLength) {
			return aStr;
		}
		ret = aStr;
		for (int i = aStr.length(); i < aLength; i++) {
			ret = aPrefix + ret;
		}
		return ret;
	}
	/**
	 * Convert to <code>BigDecimal</code> object from <code>aStr</code>
	 * 
	 * @param aStr
	 *            value
	 * @return BigDecimal object
	 */
	public static BigDecimal toBigDecimal(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		return new BigDecimal(aStr.trim());
	}
	
	/**
	 * toBoolean
	 * 
	 * @param aStr
	 * @return
	 */
	public static Boolean toBoolean(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		if ("1".equals(aStr)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * toFlagString
	 * 
	 * @param aBoolean
	 * @return
	 */
	public static String toFlagString(Boolean aBoolean) {
		if (nullOrBlank(aBoolean)) {
			return STRING_EMPTY;
		}
		if (aBoolean) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * Convert to <code>Short</code> object from <code>aStr</code>
	 * 
	 * @param aStr
	 *            value
	 * @return Short object
	 */
	public static Short toShort(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		return new Short(aStr);
	}
	/**
	 * Convert to <code>Long</code> object from <code>aStr</code>
	 * 
	 * @param aStr
	 *            value
	 * @return Long object
	 */
	public static Long toLong(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		aStr = aStr.trim().replaceAll(",", "");
		return new Long(aStr);
	}
	/**
	 * Convert to <code>Double</code> object from <code>aStr</code>
	 * 
	 * @param aStr
	 *            value
	 * @return Double object
	 */
	public static Double toDouble(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		return new Double(aStr);
	}
	/**
	 * Convert to <code>Integer</code> object from <code>aStr</code>
	 * 
	 * @param aStr
	 *            value
	 * @return Short object
	 */
	public static Integer toInt(String aStr) {
		if (nullOrBlank(aStr)) {
			return null;
		}
		return new Integer(aStr);
	}
	/**
	 * Convert <code>aNumber</code> to <code>Double</code>.<br>
	 * Return zero if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Double
	 */
	public static Double toDoubleZero(Number aNumber) {
		if (null == aNumber) {
			return DOUBLE_ZERO;
		}
		return new Double(aNumber.doubleValue());
	}
	/**
	 * Convert <code>aNumber</code> to <code>Long</code>.<br>
	 * Return zero if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Long
	 */
	public static Long toLongZero(Number aNumber) {
		if (null == aNumber) {
			return LONG_ZERO;
		}
		return new Long(aNumber.longValue());
	}
	
	/**
	 * Convert <code>aNumber</code> to <code>Long</code>.<br>
	 * Return null if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Long
	 */
	public static Long toLong(Number aNumber) {
		if (null == aNumber) {
			return null;
		}
		return new Long(aNumber.longValue());
	}

	/**
	 * Convert <code>aNumber</code> to <code>Integer</code>.<br>
	 * Return zero if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Integer
	 */
	public static Integer toIntZero(Number aNumber) {
		if (null == aNumber) {
			return INTEGER_ZERO;
		}
		return new Integer(aNumber.intValue());
	}
	
	/**
	 * Convert <code>aNumber</code> to <code>Integer</code>.<br>
	 * Return null if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Integer
	 */
	public static Integer toInt(Number aNumber) {
		if (null == aNumber) {
			return null;
		}
		return new Integer(aNumber.intValue());
	}
	/**
	 * Convert <code>aNumber</code> to <code>Short</code>.<br>
	 * Return zero if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return Short
	 */
	public static Short toShortZero(Number aNumber) {
		if (null == aNumber) {
			return SHORT_ZERO;
		}
		return new Short(aNumber.shortValue());
	}
	/**
	 * Convert <code>aNumber</code> to <code>BigDecimal</code>.<br>
	 * Return zero if <code>aNumber</code> is null.
	 * 
	 * @param aNumber
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimalZero(Number aNumber) {
		if (null == aNumber) {
			return new BigDecimal("0");
		}
		//-->20070628: not use doubleValue to get exactly decimal part (decimal part: .xxxxxxxxx) 
		//return new BigDecimal(aNumber.doubleValue());
		return new BigDecimal(aNumber.toString());
	}

//	/**
//	 * Compare two object <br>
//	 * obj1=null or obj2=null -> NullPointerExeption<br>
//	 *
//	 * @param obj1
//	 * @param obj2
//	 * @return int <br>
//	 *         0: obj1==obj2, <0: obj1 < obj2, >0: obj1 > obj2
//	 */
//	public static int compare(Object obj1, Object obj2) {
//		ParamValidator.notNull(obj1, "obj1");
//		ParamValidator.notNull(obj2, "obj2");
//
//		return compareWithNull(obj1, obj2);
//	}
	
//	/**
//	 * Compare the LENGTH of object before compare value
//	 * Compare two object <br>
//	 * obj1=null or obj2=null -> NullPointerExeption<br>
//	 *
//	 * @param obj1
//	 * @param obj2
//	 * @return int <br>
//	 *         0: obj1==obj2, <0: obj1 < obj2, >0: obj1 > obj2
//	 */
//	public static int compareWLen(Object obj1, Object obj2) {
//		ParamValidator.notNull(obj1, "obj1");
//		ParamValidator.notNull(obj2, "obj2");
//		// first: compare length
//		if (obj1.toString().length() > obj2.toString().length()) {
//			return 1;
//		}
//		if (obj1.toString().length() < obj2.toString().length()) {
//			return -1;
//		}
//		// second: when same length, -> compare value
//		return compareWithNull(obj1, obj2);
//	}
	
	/**
	 * Compare two object (Date, Long, String, Bigdecimal,....and not array type)<br>
	 * when want to compare two Arrays type (long[],...,Object[]), use CompareToBuild class<br>
	 * obj1 = null (or blank,"  " string) and obj2 = null (or blank,"  " string) -> equals (return 0)<br>
	 * obj1 = null, obj2 != null -> return <0<br>
	 * obj1 != null, obj2 = null -> return >0<br>
	 * String type: not compare length, return String.compare(obj1,obj2)<br>
	 * .....
	 * 
	 * @param obj1 (not Array)
	 * @param obj2 (not Array)
	 * @return int <br>
	 *         0: obj1==obj2, <0: obj1 < obj2, >0: obj1 > obj2
	 */
	public static int compareWithNull(Object obj1, Object obj2) {
		if (nullOrBlank(obj1)) {
			if (nullOrBlank(obj2)) {// the same (obj1=null, obj2=null)
				return 0;
			}
			return -1;// obj1 = null, obj2 != null
		}
		if (nullOrBlank(obj2)) {// obj1 != null, obj2=null
			return 1;
		}

		// -->-----------obj1 !=null, obj2 != null----------	
		if (!obj1.getClass().isAssignableFrom(obj2.getClass())
				&& !obj2.getClass().isAssignableFrom(obj1.getClass())) {
			throw new RuntimeException(
					"can not compare two object with difference type class.");
		}	
		try {
			return ((Comparable)obj1).compareTo(obj2);
		}catch (ClassCastException cE) {
			//Jdk1.4 some Type class not comparable
			return obj1.toString().compareTo(obj2.toString());
		} catch (Exception e) {
			//other exception -> compare with String type
			return obj1.toString().compareTo(obj2.toString());
		}
	}
	
	/**
	 * append all string num after dec (.) of orgNum to formatedStr (begin dec
	 * (.) )
	 * 
	 * @param orgNum
	 * @param formatedStr
	 * @return String
	 */
	private static String appendDec(Number orgNum, String formatedStr) {
		if (nullOrBlank(formatedStr) || orgNum == null) {
			return formatedStr;
		}
		String orgStr = orgNum.toString();
		if (orgStr.indexOf(".") < 0 || formatedStr.indexOf(".") < 0) {
			return formatedStr;
		}
		
		String tmp1 = orgStr.substring(orgStr.indexOf(".")+1);
		String tmp2 = formatedStr.substring(0, formatedStr.indexOf(".")+1);
		
		return tmp2.concat(tmp1);
	}
	
	/**
	 * From src (Object) append pref at prefix of src until to length length<br>
	 * if src = null -> return blank<br>
	 * 
	 * @param src
	 * @param length
	 * @param pref
	 * @param zeroToBlank
	 *            true: src=0 -> return blank<br>
	 *            false: srt=0 -> return 0 string with length
	 * @return String
	 */
	public static String appePrefWithLength(Object src, int length, char pref,
											boolean zeroToBlank) {
		if (nullOrBlank(src)) {
			return "";
		}
		try {
			if (zeroToBlank && new BigDecimal(src.toString()).intValue() == 0) {
				return "";
			}
		} catch (Exception e) {
			;// not exception
		}
		return addPrefixToString(src.toString(), pref, length);
	}
	
	/**
	 * ������Null�ł����Ă��A����\��equal���\�b�h
	 * 
	 * @param anObject1
	 * @param anObject2
	 * @return boolean
	 */
	public static boolean isEqual(Object anObject1, Object anObject2) {
		String str1 = toString(anObject1);
		String str2 = toString(anObject2);
		if (str1 == null && str2 == null) {
			return true;
		}
		if (str1 == null || str2 == null) {
			return false;
		}
		//-- ���̏ꍇ
		if (str1.equals(str2)) {
			return true;
		}
		//-- ���̏ꍇ
		return false;
	}
}