/*
 * $Id: CalendarUtils.java 185 2008-07-23 04:12:23Z damnh $
 * ====================================================================
 * All Rights Copyright(C) NIKKO COMPANY 2008-
 */

package ctwhs.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * ���t�A�������Z�A�t�H�[�}�b�g���[�e�B���e�B
 * 
 */
public class CalendarUtils {
	/**
	 * �T�̊J�n�i���j���j
	 */
	private final static int START_WEEK = 2;
	/**
	 * �T�̏I���i���j���j
	 */
	private final static int END_WEEK = 8;
	/**
	 * �T�̓���
	 */
	private final static int DAYS_OF_WEEK = 7;

	/**
	 * 
	 * Private Function - Start
	 */
	private static int getYearOfString(String date) {
		return Integer.parseInt(date.substring(0, 4));
	}
	private static int getMonthOfString(String date) {
		return Integer.parseInt(date.substring(4, 6)) - 1;
	}
	private static int getDayOfString(String date) {
		if (null == date) {
			throw new NullPointerException();
		}
		if (!isNum(date)) {
			throw new IllegalArgumentException();
		}
		if (date.length() == 8) {
			return Integer.parseInt(date.substring(6, 8));
		}
		throw new IllegalArgumentException();
	}
	private static boolean isNum(String aDate) {
		char charData = ' ';

		if (null == aDate) {
			throw new NullPointerException();
		}
		if (aDate.length() == 0) {
			return false;
		}
		for (int i = 0; i < aDate.length(); i++) {
			charData = aDate.charAt(i);
			if (((charData < '0') || (charData > '9'))) {
				return false;
			}
		}
		return true;
	}
	private static Calendar _getCalendar(int year, int month, int day) {
		GregorianCalendar cl = new GregorianCalendar();
		cl.set(Calendar.YEAR, year);
		cl.set(Calendar.MONTH, month);
		cl.set(Calendar.DAY_OF_MONTH, day);
		return cl;
	}
	private static Calendar _getCalendarOfDate(String date) {
		return _getCalendar(getYearOfString(date), getMonthOfString(date),
				getDayOfString(date));

	}

	/**
	 * 
	 * Private function - End
	 */

	/**
	 * Get system Date
	 * 
	 * @return String
	 */
	public static Date getToday() {
		// get system Date
		// return new SimpleDateFormat("yyyyMMdd").format(new Date());
		GregorianCalendar cl = new GregorianCalendar();
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
		return cl.getTime();
	}

	/**
	 * Get system Time
	 * 
	 * @return String
	 */
	public static Date getNow() {
		// get system Time
		// return new SimpleDateFormat("HHmmss").format(new Date());
		return new Date();
	}

	/**
	 * Get system data or system time with format style
	 * 
	 * @param sFormat
	 *            yyyyMMdd HHmmss
	 * @return String
	 */
	public static String getNow(String sFormat) {
		// Get System Date or System Time By sFormat
		if (null == sFormat) {
			throw new NullPointerException();
		}
		if (sFormat.trim().equals("")) {
			return "";
		}
		return new SimpleDateFormat(sFormat).format(new Date());
	}

	/**
	 * Get Num Weeks of Month
	 * 
	 * @param aYearMonth
	 * @return int
	 */
	public static int getWeeks(String aYearMonth) {
		if (null == aYearMonth) {
			throw new NullPointerException();
		}
		aYearMonth = aYearMonth.trim();
		if (isValidMonth(aYearMonth)) {
			int dd = getMaxDayOfMonth(aYearMonth);
			aYearMonth = aYearMonth + String.valueOf(dd);
			Calendar cl = _getCalendarOfDate(aYearMonth);
			cl.setFirstDayOfWeek(Calendar.MONDAY);
			return cl.get(Calendar.WEEK_OF_MONTH);
		}
		throw new IllegalArgumentException();
	}
	/**
	 * Get Num Weeks of Day in Month
	 * 
	 * @param aDate
	 * @return int
	 */
	public static int getWeekNumber(Date aDate) {
		if (ConvertUtils.nullOrBlank(aDate)) {
			throw new NullPointerException();
		}

		String date = new SimpleDateFormat("yyyyMMdd").format(aDate);
		Calendar cl = _getCalendarOfDate(date);
		cl.setFirstDayOfWeek(Calendar.MONDAY);
		return cl.get(Calendar.WEEK_OF_MONTH);

	}

	/**
	 * Add Day
	 * 
	 * @param aDate
	 * @param aDays
	 * @return Date after Add aDate + aDays
	 */
	public static Date addDays(Date aDate, int aDays) {
		if (ConvertUtils.nullOrBlank(aDate)) {
			throw new NullPointerException();
		}

		GregorianCalendar cl = new GregorianCalendar();
		cl.setTime(aDate);
		cl.set(Calendar.DATE, cl.get(Calendar.DATE) + aDays);
		return cl.getTime();

	}

	/**
	 * Add Weeks
	 * 
	 * @param aDate
	 * @param aWeeks
	 * @return Date after Add aDate + aWeeks
	 */
	public static Date addWeeks(Date aDate, int aWeeks) {
		if (ConvertUtils.nullOrBlank(aDate)) {
			throw new NullPointerException();
		}

		GregorianCalendar cl = new GregorianCalendar();
		cl.setTime(aDate);
		cl.set(Calendar.DATE, cl.get(Calendar.DATE) + (aWeeks * 7));
		return cl.getTime();

	}
	/**
	 * Add Months
	 * 
	 * @param aDate
	 * @param aMonths
	 * @return Date after Add aDate + aMonths
	 */
	public static Date addMonths(Date aDate, int aMonths) {
		if (ConvertUtils.nullOrBlank(aDate)) {
			throw new NullPointerException();
		}
		GregorianCalendar cl = new GregorianCalendar();
		cl.setTime(aDate);

		int byyyy = cl.get(Calendar.YEAR) + 1900;
		int bmm = byyyy * 12 + cl.get(Calendar.MONTH) + 1;
		int dd = cl.get(Calendar.DATE);
		int mm = bmm + aMonths;
		int ayyyy = mm / 12;
		int amm = mm % 12;
		String yyyymm = new StringBuffer().append(ayyyy).toString();
		if (amm == 0) {
			ayyyy--;
			yyyymm = new StringBuffer().append(ayyyy).toString();
			yyyymm += "12";
			amm = 12;
		} else if (amm >= 10) {
			yyyymm += new StringBuffer().append(amm).toString();
		} else {
			yyyymm += "0" + new StringBuffer().append(amm).toString();
		}
		int Maxdd = getMaxDayOfMonth(yyyymm);
		if (dd > Maxdd) {
			cl.set(Calendar.DATE, (Maxdd));

		} else {
			cl.set(Calendar.DATE, (dd));
		}
		cl.set(Calendar.MONTH, (amm - 1));
		cl.set(Calendar.YEAR, (ayyyy - 1900));
		return cl.getTime();
	}
	/**
	 * Caculate duration day between two Date
	 * 
	 * @param aStartDate
	 * @param anEndDate
	 * @param isStartDateContained
	 * @param isEndDateContained
	 * @return Num day between Two Date
	 */
	public static int getDays(Date aStartDate, Date anEndDate,
			boolean isStartDateContained, boolean isEndDateContained) {
		int numDay = 0;
		if ((ConvertUtils.nullOrBlank(aStartDate))
				|| (ConvertUtils.nullOrBlank(anEndDate))) {
			throw new NullPointerException();
		}

		long ms = anEndDate.getTime() - aStartDate.getTime();
		numDay = (int) Math.round(ms / (24 * 60 * 60 * 1000));
		if (numDay < 0) {
			numDay = 0;
		} else if (numDay == 0) {
			if ((isStartDateContained == false)
					&& (isEndDateContained == false)) {
				numDay = 0;
			} else {
				numDay = 1;
			}
		} else {
			numDay++;
			if (isStartDateContained == false) {
				numDay--;
			}
			if (isEndDateContained == false) {
				numDay--;
			}
		}

		return numDay;
	}
	/**
	 * Caculate duration Month between two Date
	 * 
	 * @param aStartYearMonth
	 * @param anEndYearMonth
	 * @param isStartYearMonthContained
	 * @param isEndYearMonthContained
	 * @return Num Month between Two Date
	 */
	public static int getMonths(String aStartYearMonth, String anEndYearMonth,
			boolean isStartYearMonthContained, boolean isEndYearMonthContained) {
		if ((null == aStartYearMonth) || (null == anEndYearMonth)) {
			throw new NullPointerException();
		}
		int numMonth = 0;
		aStartYearMonth = aStartYearMonth.trim();
		anEndYearMonth = anEndYearMonth.trim();
		if ((aStartYearMonth.length() == 6) && (anEndYearMonth.length() == 6)) {
			if (isValidMonth(aStartYearMonth) == true
					&& isValidMonth(anEndYearMonth) == true) {
				int SumStartMonth = (getYearOfString(aStartYearMonth) * 12)
						+ (Integer.parseInt(aStartYearMonth.substring(4, 6)));
				int SumEndMonth = (getYearOfString(anEndYearMonth) * 12)
						+ (Integer.parseInt(anEndYearMonth.substring(4, 6)));
				numMonth = SumEndMonth - SumStartMonth;
				if (numMonth == 0) {
					if ((isStartYearMonthContained == false)
							&& (isEndYearMonthContained == false)) {
						numMonth = 0;
					} else {
						numMonth = 1;
					}
				} else if (numMonth < 0) {
					numMonth = 0;
				} else {
					numMonth++;
					if (isStartYearMonthContained == false) {
						numMonth--;
					}
					if (isEndYearMonthContained == false) {
						numMonth--;
					}
				}
			}
		}
		return numMonth;
	}
	/**
	 * 
	 * @param aYearMonth
	 * @return The Last Date of Month
	 */

	public static Date getLastDateOfMonth(String aYearMonth) {

		if (null == aYearMonth) {
			throw new NullPointerException();
		}
		aYearMonth = aYearMonth.trim();
		if (!isNum(aYearMonth)) {
			throw new IllegalArgumentException();
		}
		if (aYearMonth.length() == 6) {
			GregorianCalendar cl = new GregorianCalendar();
			cl.set(Calendar.DAY_OF_MONTH, 1);
			cl.set(Calendar.YEAR, getYearOfString(aYearMonth));
			cl.set(Calendar.MONTH, getMonthOfString(aYearMonth));
			cl.set(Calendar.DAY_OF_MONTH, cl
					.getActualMaximum(Calendar.DAY_OF_MONTH));
			cl.set(Calendar.HOUR_OF_DAY, 0);
			cl.set(Calendar.MINUTE, 0);
			cl.set(Calendar.SECOND, 0);
			cl.set(Calendar.MILLISECOND, 0);
			return cl.getTime();
		}
		throw new IllegalArgumentException();
	}
	/**
	 * 
	 * Get last date of month.
	 * @param aDate
	 * @return last date of month
	 * @author tranghv
	 */
	public static Date getLastDateOfMonth(Date aDate) {
		if (null == aDate) {
			throw new NullPointerException();
		}
		String sDate = ConvertUtils.toEscape(aDate,"yyyyMM");
		return getLastDateOfMonth(sDate);
	}
	
	/**
	 * Private function to Check Valid Date
	 * 
	 * @param aDate
	 *            yyyyMMdd, aDateFormat yyyyMMdd
	 * @return boolean
	 */
	private static boolean _isValidDate(String aDate, String aDateFormat)
			throws ParseException {
		if (null == aDate || null == aDateFormat) {
			throw new NullPointerException();
		}
		if ("" == aDate || "" == aDateFormat) {
			return false;
		}
		if (aDate.length() != aDateFormat.length()) {
			return false;
		}
		DateFormat df = new SimpleDateFormat(aDateFormat);
		return aDate.equals(df.format(df.parse(aDate)));
	}

	/**
	 * Check Valid Date
	 * 
	 * @param aDate
	 *            yyyyMMdd
	 * @param aDateFormat
	 *            yyyyMMdd
	 * @return boolean
	 */
	public static boolean isValidDate(String aDate, String aDateFormat) {
		boolean Valid = false;
		if ((null == aDate) || (null == aDateFormat)) {
			throw new NullPointerException();
		}
		try {
			Valid = _isValidDate(aDate, aDateFormat);
		} catch (ParseException e) {
			// Do nothing
			// e.printStackTrace();
			
		}
		return Valid;
	}
	/**
	 * Check Valid Month
	 * 
	 * @param aYearMonth
	 *            yyyyMM
	 * @return boolean
	 */
	public static boolean isValidMonth(String aYearMonth) {
		boolean Valid = false;
		if (null == aYearMonth) {
			throw new NullPointerException();
		}
		if (aYearMonth.length() == 0) {
			return Valid;
		}
		aYearMonth = aYearMonth.trim() + "01";
		try {
			Valid = _isValidDate(aYearMonth, "yyyyMMdd");
		} catch (ParseException e) {
			// e.printStackTrace();
			// Do Nothing
			
		}
		return Valid;
	}
	/**
	 * Check leap year
	 * 
	 * @param aYear
	 *            int
	 * @return boolean
	 */
	public static boolean isLeapYear(int aYear) {
		// Test parameter year is Leap Year
		if (aYear < 0) {
			throw new IllegalArgumentException();
		}
		GregorianCalendar cl = new GregorianCalendar();
		return cl.isLeapYear(aYear);
	}
	/**
	 * Check leap year
	 * 
	 * @param aYear
	 *            String
	 * @return boolean
	 */
	public static boolean isLeapYear(String aYear) {
		aYear = aYear.trim();
		if (null == aYear) {
			throw new NullPointerException();
		}
		int year = Integer.parseInt(aYear);
		if (year < 0) {
			throw new IllegalArgumentException();
		}
		GregorianCalendar cl = new GregorianCalendar();

		return cl.isLeapYear(year);
	}
	/**
	 * Get Name day of Date
	 * 
	 * @param aDate
	 * @return name of Date
	 */
	public static String getDayOfTheWeek(Date aDate) {

		if (ConvertUtils.nullOrBlank(aDate)) {
			throw new NullPointerException();
		}

		Calendar cl = new GregorianCalendar();
		cl.setTime(aDate);
		int dayOfWeek = cl.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
			case Calendar.SUNDAY :
				return "��";
			case Calendar.MONDAY :
				return "��";
			case Calendar.TUESDAY :
				return "��";
			case Calendar.WEDNESDAY :
				return "��";
			case Calendar.THURSDAY :
				return "��";
			case Calendar.FRIDAY :
				return "��";
			case Calendar.SATURDAY :
				return "�y";
			default :
				return " ";
		}

	}
	/**
	 * �w��N���iYYYYMM�j�̓������擾����B
	 * 
	 * @param anYearMonth
	 *            �N���iYYYYMM�j
	 * @return ���̌��̓���
	 */
	public static int getMaxDayOfMonth(String anYearMonth) {
		if (null == anYearMonth) {
			throw new NullPointerException();
		}
		if (anYearMonth.length() == 6) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.YEAR, Integer.parseInt(anYearMonth.substring(
					0, 4)));
			calendar.set(Calendar.MONTH, Integer.parseInt(anYearMonth
					.substring(4, 6)) - 1);
			int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			return maxDays;
		}
		throw new IllegalArgumentException();
	}

//	/**
//	 * �w�肳�ꂽ���̏T�����擾���܂��B<br>
//	 * �J�n���F���j���A�I�����F���j��<br>
//	 *
//	 * @param date
//	 *            �T���擾����Ώۓ�
//	 * @param isConcatMonths
//	 *            ���܂����t���O
//	 * @return Week �T���<br>
//	 *         ex: date=20060930(�y�j���j and isConcatMonths=<br>
//	 *         -false->week.startDate=20060925(mon), week.endDate=20060930(sat),
//	 *         week.yearMonth=200609, week.weekNo=5<br>
//	 *         -true->week.startDate=20060925(mon), week.endDate=20061001(sun),
//	 *         week.yearMonth=200610, week.weekNo=1
//	 */
//	public static Week getWeek(Date date, boolean isConcatMonths) {
//		// �w�肳�ꂽ����null��������Anull��Ԃ�
//		if (date == null) {
//			return null;
//		}
//		// date���Calendar instance���擾
//		Calendar calen = Calendar.getInstance(Locale.JAPAN);
//		calen.setTime(date);
//
//		return getWeek(calen, isConcatMonths);
//	}
//
//	/**
//	 * �w�肳�ꂽ����n�T��̏T�����擾���܂��B<br>
//	 * �J�n���F���j���A�I�����F���j��<br>
//	 *
//	 * @param date
//	 *            �T�̎擾����Ώۓ�
//	 * @param isConcatMonths
//	 *            ���܂����t���O
//	 * @param weeks
//	 *            ���T�Ԍ�i�}�C�i�X�̎��͑O�j��
//	 * @return Week �W���
//	 */
//	public static Week getWeek(Date date, boolean isConcatMonths, int weeks) {
//		Week week = getWeek(date, isConcatMonths);
//		// �w�肳�ꂽ����null��������Anull��Ԃ�
//		if (week == null) {
//			return null;
//		}
//		if (weeks == 0) {// weeks=0�ꍇ�A�w�肳�ꂽ�̏T����Ԃ��B
//			return week;
//		}
//		// �T�Ԍ�i�O�j��weeks�Ɉړ�
//		updateWeek(week, isConcatMonths, weeks);
//		return week;
//	}
//
//	/**
//	 * �w�肵���N���̏T���̃��X�g���擾���܂��B<br>
//	 * �v�f�͏T�ԍ��̏����Ń\�[�g����܂��B
//	 *
//	 * @param date
//	 *            �T���擾����Ώۓ�
//	 * @param isConcatMonths
//	 *            ���܂����t���O
//	 * @return List �w�肵���N���̏T���̃��X�g (�v�f��jp.co.nikkocompany.air.util.Week)
//	 */
//	public static List getWeekList(Date date, boolean isConcatMonths) {
//		// �w�肳�ꂽ����null��������A�󃊃X�g��Ԃ�
//		if (date == null) {
//			return new ArrayList();
//		}
//		// date���Calendar instance���擾
//		Calendar calen = Calendar.getInstance(Locale.JAPAN);
//		calen.setTime(date);
//		// ���̏��ߏT�����擾�ׁ̈A���̏��ߓ����Z�b�g���܂��B
//		calen.set(Calendar.DAY_OF_MONTH, 1);
//
//		// �v�f�͏T�ԍ��̏����Ń\�[�g�ׁ̈A
//		SortedMap map = new TreeMap();
//		// ���̏��ߏT�����擾
//		Week week = getWeek(calen, isConcatMonths);
//		String yearMonth = getYearMonth(calen);
//		// �������ŏT���̑S�Ă��擾
//		while (week != null && yearMonth.equals(week.getYearMonth())) {
//			// Week�𐶐����Amap�Ƀv�b�g
//			map.put(week.getWeekNo() + "", new Week(week.getYearMonth(), week
//					.getWeekNo(), week.getStartDate(), week.getEndDate(), week
//					.isConcatMonths()));
//			// ���T�����擾
//			updateWeek(week, isConcatMonths, 1);
//		}
//		// �Ԃ�
//		return new ArrayList(map.values());
//	}
//
//	private static Week getWeek(Calendar calen, boolean isConcatMonths) {
//		if (calen == null) {
//			return null;
//		}
//		// �T�̗j�����擾
//		int dayOfWeek = calen.get(Calendar.DAY_OF_WEEK);
//		if (dayOfWeek == Calendar.SUNDAY) {
//			dayOfWeek = END_WEEK;
//		}
//		// �w�肳�ꂽ�T�̌��j������dayOfWeek�܂ł̊�
//		int mondayFromPeriod = dayOfWeek - START_WEEK;
//		// dayOfWeek����w�肳�ꂽ�T�̏T�̓��j���܂ł̊�
//		int sundayToPeriod = END_WEEK - dayOfWeek;
//
//		return getWeek(calen, isConcatMonths, mondayFromPeriod, sundayToPeriod);
//	}
//
//	private static Week getWeek(Calendar calen, boolean isConcatMonths,
//			int mondayFromPeriod, int sundayToPeriod) {
//		if (calen == null) {
//			return null;
//		}
//		// �T�̊J�n����Calendar instance�����
//		Calendar toMondayCalen = Calendar.getInstance(Locale.JAPAN);
//		toMondayCalen.setTime(calen.getTime());
//		int oldDay = calen.get(Calendar.DATE);
//		// ��T�̏ꍇ
//		if (isConcatMonths) {
//			// �T�̊J�n���i���j���j�Ɉړ�
//			toMondayCalen.add(Calendar.DATE, -mondayFromPeriod);
//			// �T�̏I�����i���j���j�Ɉړ�
//			calen.add(Calendar.DATE, sundayToPeriod);
//			// �Ԃ��A�T�̏I�������weekNo���擾
//			return new Week(getYearMonth(calen), getWeekNo(calen, true),
//					toMondayCalen.getTime(), calen.getTime(), isConcatMonths);
//		}
//
//		// �����Ȃ��T�̏ꍇ------start------
//		if (oldDay - mondayFromPeriod <= 0) {// toMondayCalen�͑O�̌��̏ꍇ�A���̏��߂ɂȂ�
//			toMondayCalen.set(Calendar.DAY_OF_MONTH, 1);
//		} else {// �������ŏT�̊J�n���i���j���j�Ɉړ�
//			toMondayCalen.add(Calendar.DATE, -mondayFromPeriod);
//		}
//		// �T�̏I�����̈�
//		int daysOfMonth = calen.getActualMaximum(Calendar.DAY_OF_MONTH);
//		if (oldDay + sundayToPeriod > daysOfMonth) {// sundayToPeriod�Ɉړ����āA���̌��ɂȂ�����A�w�肳�ꂽ���̖��ɂȂ�܂��B
//			sundayToPeriod = daysOfMonth - oldDay;
//		}
//		// ���j���Ɉړ����܂��B
//		calen.add(Calendar.DATE, sundayToPeriod);
//		// �Ԃ��A�T�̊J�n�����weekNo���擾
//		return new Week(getYearMonth(calen), getWeekNo(toMondayCalen, false),
//				toMondayCalen.getTime(), calen.getTime(), isConcatMonths);
//		// ��T����Ȃ��̏ꍇ------end------
//	}

	/**
	 * �N���iyyyyMM)���擾
	 * 
	 * @param calen
	 * @return String
	 */
	private static String getYearMonth(Calendar calen) {
		if (calen == null) {
			return "";
		}
		// �Ԃ��̌`��yyyyMM
		int month = calen.get(Calendar.MONTH) + 1;
		int year = calen.get(Calendar.YEAR);
		if (month <= 9) {// month<9�ꍇ�Ayyyy0M�ɂȂ�
			return year + "0" + month;
		}
		return year + "" + month;
	}

//	/**
//	 * �T�ԍ����擾
//	 *
//	 * @param calen
//	 *            ��T��������T�̏I����calen�ŁA�����Ȃ��T�̏ꍇ�A�T�̊J�n��calen�ł��B
//	 * @param isConcatMonths
//	 *            ���܂����t���O
//	 * @return int �T�ԍ�
//	 */
//	private static int getWeekNo(Calendar calen, boolean isConcatMonths) {
//		if (calen == null) {
//			return 0;
//		}
//		int day = calen.get(Calendar.DATE);
//		int div = day / DAYS_OF_WEEK;
//		int mod = day % DAYS_OF_WEEK;
//		if (mod > 0) {
//			div += 1;
//		}
//		if (isConcatMonths) {// �w�肳�ꂽ�����A��T�̐����擾�B
//			return div;
//		}
//
//		// �w�肳�ꂽ�����A�����Ȃ��T�̐����擾�B
//		if (day == 1) {// �T�̊J�n���͌��̏��߂�������AweekNo=1
//			return 1;
//		}
//		Calendar temp = Calendar.getInstance();
//		temp.setTime(calen.getTime());
//		temp.set(Calendar.DAY_OF_MONTH, 1);
//		if (temp.get(Calendar.DAY_OF_WEEK) == 2) {// ���̏��߂͌��j����������AweekNo�Ƃ���div��Ԃ�
//			return div;
//		}
//		// �ȊO�FweekNo�Ƃ���div+1��Ԃ�
//		return div + 1;
//	}

//	/**
//	 * week��艽�T�O�i��j�̏T���X�V
//	 *
//	 * @param week
//	 * @param isConcatMonths
//	 *            ���܂����t���O
//	 * @param num
//	 *            ���T�� �i>0:��A<0:�O�j
//	 */
//	private static void updateWeek(Week week, boolean isConcatMonths, int num) {
//		if (week == null) {
//			return;
//		}
//		if (isConcatMonths) {// ��T���
//			// �T�̊J�n��
//			Date temp = addDays(week.getStartDate(), num * 7);
//			week.setStartDate(temp);
//			// �T�̏I����
//			temp = addDays(week.getEndDate(), num * 7);
//			week.setEndDate(temp);
//			// �N��
//			Calendar calen = Calendar.getInstance();
//			calen.setTime(week.getEndDate());
//			week.setYearMonth(getYearMonth(calen));
//			// �T�ԍ�
//			week.setWeekNo(getWeekNo(calen, isConcatMonths));
//			return;
//		}
//
//		// �����Ȃ��T���
//		if (num > 0) {// week���num�Ɉړ�
//			int i = num;
//			while (i > 0) {
//				updateWeek(week, true);
//				i--;
//			}
//		} else {// week�O��num�Ɉړ�
//			int i = num;
//			while (i < 0) {
//				updateWeek(week, false);
//				i++;
//			}
//		}
//	}

//	/**
//	 * week����O���ォ�̏T�����X�V�i�����Ȃ��T�j
//	 *
//	 * @param week
//	 * @param dir
//	 *            (true:��,false:�O�j
//	 */
//	private static void updateWeek(Week week, boolean dir) {
//		if (week == null) {
//			return;
//		}
//		// week�i�����Ȃ��T�j����O�i��j�̏T�Ɉړ�
//		Calendar calen = Calendar.getInstance();
//		if (dir) {// ��
//			updateWeekNext(week, calen);
//		} else {// �O
//			updateWeekPrevious(week, calen);
//		}
//		// �N��
//		calen.setTime(week.getStartDate());
//		week.setYearMonth(getYearMonth(calen));
//		// �T�ԍ�
//		week.setWeekNo(getWeekNo(calen, false));
//	}
//
//	/**
//	 * week�����̏T�̊J�n���A�I���т��X�V�i�����Ȃ��T�j
//	 *
//	 * @param week
//	 * @param calen
//	 */
//	private static void updateWeekNext(Week week, Calendar calen) {
//		if (week == null || calen == null) {
//			return;
//		}
//		calen.setTime(week.getEndDate());
//		calen.add(Calendar.DATE, 1);
//		// �T�̊J�n�т��Z�b�g
//		week.setStartDate(calen.getTime());
//		// �j�����擾
//		int dayOfWeek = calen.get(Calendar.DAY_OF_WEEK);
//		if (dayOfWeek == Calendar.SUNDAY) {
//			dayOfWeek = END_WEEK;
//		}
//		// �T�̏I�������擾
//		int toSundayPeriod = END_WEEK - dayOfWeek;
//		if (calen.get(Calendar.DATE) + toSundayPeriod > calen
//				.getActualMaximum(Calendar.DAY_OF_MONTH)) {// �T���͎��̌��̏ꍇ�A
//			// �O�̌����Ɉړ�
//			toSundayPeriod = calen.getActualMaximum(Calendar.DAY_OF_MONTH)
//					- calen.get(Calendar.DATE);
//		}
//		calen.add(Calendar.DATE, toSundayPeriod);
//		// �T�̏I�������Z�b�g
//		week.setEndDate(calen.getTime());
//	}

//	/**
//	 * week����O�̏T�̊J�n���A�I���т��X�V�i�����Ȃ��T�j
//	 *
//	 * @param week
//	 * @param calen
//	 */
//	private static void updateWeekPrevious(Week week, Calendar calen) {
//		if (week == null || calen == null) {
//			return;
//		}
//		calen.setTime(week.getStartDate());
//		calen.add(Calendar.DATE, -1);
//		// �T�̏I�������Z�b�g
//		week.setEndDate(calen.getTime());
//		// �j�����擾
//		int dayOfWeek = calen.get(Calendar.DAY_OF_WEEK);
//		if (dayOfWeek == Calendar.SUNDAY) {
//			dayOfWeek = END_WEEK;
//		}
//		// �T�̊J�n�����擾
//		int toMondayPeriod = dayOfWeek - START_WEEK;
//		if (calen.get(Calendar.DATE) - toMondayPeriod <= 0) {
//			// �T�̊J�n���͑O�̌��̏ꍇ�A���̌��n�߂Ɉړ�
//			calen.set(Calendar.DAY_OF_MONTH, 1);
//		} else {
//			calen.add(Calendar.DATE, -toMondayPeriod);
//		}
//		// �T�̊J�n�����Z�b�g
//		week.setStartDate(calen.getTime());
//	}
	/**
	 * 
	 * get first of month
	 * 
	 * @param aYm :
	 *            year month
	 * @return the first of date
	 * @author AIT-Damnh
	 */
	public static Date getFirstOfMonth(String aYm) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").parse(aYm
					.substring(0, 4)
					+ "/" + aYm.substring(4, 6) + "/01 00:00:00.000");
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
	/**
	 * 
	 * add month
	 * 
	 * @param aYm
	 * @param aMonth :
	 *            so thang phai cong/tru
	 * @return ym : year month
	 * @author AIT-Damnh
	 */
	public static String addMonth(String aYm, int aMonth) {
		Date date = getFirstOfMonth(aYm);
		if (date == null)
			return null;
		return ConvertUtils.toEscape(CalendarUtils.addMonths(date, aMonth),
				"yyyyMM");
	}

	/**
	 * @param date
	 * @return yyyyMM
	 */
	public static String getYm(Date date) {
		if (null!=date){
			return new SimpleDateFormat("yyyyMM").format(date);
		}else{
			return "";
		}
	}
	/**
	 * compare year month
	 * 
	 * @param ym1
	 * @param ym2
	 * @return 0, if ym1=ym2,<br>
	 *         <0 if ym1<ym2,<br>
	 *         >0 if ym1>ym2
	 */
	public static int compareYm(String ym1, String ym2) {
		Date d1 = getFirstOfMonth(ym1);
		Date d2 = getFirstOfMonth(ym2);
		return d1.compareTo(d2);
	}

	/**
	 * Get begin of date (time 00:00:00)
	 * @param date
	 * @return date+time
	 */
	public static Date getStartDateTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		// set start hour, minus, second of day
		calen.set(Calendar.HOUR_OF_DAY, 0);
		calen.set(Calendar.MINUTE, 0);
		calen.set(Calendar.SECOND, 0);

		return calen.getTime();
	}
	
	/**
	 * Get end of date (time: 23:59:59)
	 * @param date
	 * @return date+time
	 */
	public static Date getEndDateTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calen = Calendar.getInstance();
		calen.setTime(date);
		// set end hour, minus, second of day
		calen.set(Calendar.HOUR_OF_DAY, 23);
		calen.set(Calendar.MINUTE, 59);
		calen.set(Calendar.SECOND, 59);

		return calen.getTime();
	}
}