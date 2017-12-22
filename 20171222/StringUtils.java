package net.poweregg.util;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class StringUtils
{
  private static final char[] ORIGINAL = { '&', '"', '<', '>', '\'' };
  private static final String[] CHANGE = { "&amp;", "&quot;", "&lt;", "&gt;", "&#39;" };
  private static final String ESC_CHAR = "\\";
  private static final String DEFAULT_DELIMITER = ",";
  public static final String UTF8_BOM = "?";
  public static final char CR = '\r';
  public static final char LF = '\n';
  public static final String EMPTY = "";
  public static final String BLANK = " ";
  private static final String UTF_8 = "UTF-8";
  
  public static boolean isEmpty(Object obj)
  {
    if (nullOrBlank(obj)) {
      return true;
    }
    String s = obj.toString();
    s = s.replaceAll("[ 　]", "");
    return nullOrBlank(s);
  }
  
  public static boolean nullOrBlank(Object obj)
  {
    if (obj == null) {
      return true;
    }
    if ("".equals(obj.toString())) {
      return true;
    }
    return false;
  }
  
  public static synchronized boolean isValidString(String target)
  {
    return (target != null) && (target.length() > 0);
  }
  
  public static String addEmpty(Object obj)
  {
    if (obj == null) {
      return "";
    }
    return obj.toString();
  }
  
  public static String formatNumber(Long number)
  {
    String result = "";
    String st = number.toString();
    int j = 0;
    for (int i = st.length() - 1; i > 0; i--)
    {
      result = st.charAt(i) + result;
      j++;
      if (j == 3)
      {
        j = 0;
        result = "," + result;
      }
    }
    result = st.charAt(0) + result;
    return result;
  }
  
  public static String formatDate(Date aDate)
  {
    String result = "";
    if (aDate != null)
    {
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(aDate);
      String year = String.valueOf(calendar.get(1));
      String month = String.valueOf(calendar.get(2) + 1);
      String day = String.valueOf(calendar.get(5));
      if (Integer.valueOf(month).intValue() < 10) {
        month = "0" + month;
      }
      if (Integer.valueOf(day).intValue() < 10) {
        day = "0" + day;
      }
      result = year + "/" + month + "/" + day;
    }
    return result;
  }
  
  public static String formatTime(String time)
  {
    if (nullOrBlank(time)) {
      return "";
    }
    if (time.length() != 4) {
      return time;
    }
    return time.substring(0, 2) + ":" + time.substring(2, 4);
  }
  
  public static String toEmpty(Object obj)
  {
    if (obj == null) {
      return "";
    }
    return obj.toString();
  }
  
  public static String formatDateSubject(Date aDate)
  {
    String result = "";
    if (aDate != null)
    {
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(aDate);
      String month = String.valueOf(calendar.get(2) + 1);
      String day = String.valueOf(calendar.get(5));
      if (Integer.valueOf(month).intValue() < 10) {
        month = "0" + month;
      }
      if (Integer.valueOf(day).intValue() < 10) {
        day = "0" + day;
      }
      result = month + "月" + day + "日";
    }
    return result;
  }
  
  public static String formatYearMonth(Date aDate)
  {
    String result = "";
    if (aDate != null)
    {
      Calendar calendar = new GregorianCalendar();
      calendar.setTime(aDate);
      String year = String.valueOf(calendar.get(1));
      String month = String.valueOf(calendar.get(2) + 1);
      if (Integer.valueOf(month).intValue() < 10) {
        month = "0" + month;
      }
      result = year + "/" + month;
    }
    return result;
  }
  
  public static String addZero(Object value, int num)
  {
    if (nullOrBlank(value)) {
      return "";
    }
    String ret = value.toString();
    for (int i = ret.length(); i < num; i++) {
      ret = "0" + ret;
    }
    return ret;
  }
  
  public static String formatNumber(Object aOriginal, String aFormat)
  {
    String originalStr = new String();
    try
    {
      originalStr = aOriginal.toString();
      




      return new DecimalFormat(aFormat).format(new BigDecimal(originalStr));
    }
    catch (NullPointerException npe)
    {
      return new String();
    }
    catch (NumberFormatException nfe)
    {
      return originalStr;
    }
    catch (Exception e) {}
    return originalStr;
  }
  
  public static String changeToCp943C(String original)
  {
    try
    {
      char[] tArray = original.toCharArray();
      int arrLength = tArray.length;
      for (int cnt = 0; arrLength > cnt; cnt++) {
        tArray[cnt] = changeToCp943C(tArray[cnt]);
      }
      return new String(tArray);
    }
    catch (NullPointerException e) {}
    return "";
  }
  
  public static char changeToCp943C(char original)
  {
    switch (original)
    {
    case '―': 
      original = '?'; break;
    case '～': 
      original = '?'; break;
    case '∥': 
      original = '?'; break;
    case '?': 
      original = '…'; break;
    case '－': 
      original = '?'; break;
    case '￠': 
      original = 65504; break;
    case '￡': 
      original = 65505; break;
    case '￢': 
      original = 65506;
    }
    return original;
  }
  
  public static String convertToWindows31J(String original)
  {
    try
    {
      char[] tArray = original.toCharArray();
      int arrLength = tArray.length;
      for (int cnt = 0; arrLength > cnt; cnt++) {
        tArray[cnt] = changeToWindows31J(tArray[cnt]);
      }
      return new String(tArray);
    }
    catch (NullPointerException e) {}
    return "";
  }
  
  public static char changeToWindows31J(char original)
  {
    switch (original)
    {
    case '―': 
      original = '―'; break;
    case '～': 
      original = 65374; break;
    case '∥': 
      original = '∥'; break;
    case '?': 
      original = '…'; break;
    case '－': 
      original = 65293; break;
    case '?': 
      original = 65293; break;
    case '￠': 
      original = 65504; break;
    case '￡': 
      original = 65505; break;
    case '￢': 
      original = 65506;
    }
    return original;
  }
  
  public static int getByteCount(String value)
  {
    if (nullOrBlank(value)) {
      return 0;
    }
    int byteCount = 0;
    for (int i = 0; i < value.length(); i++)
    {
      String charAt = value.substring(i, i + 1);
      try
      {
        byteCount += charAt.getBytes("SJIS").length;
      }
      catch (UnsupportedEncodingException e) {}
    }
    return byteCount;
  }
  
  public static int getByteCountUTF8(String value)
  {
    if (nullOrBlank(value)) {
      return 0;
    }
    int byteCount = 0;
    for (int i = 0; i < value.length(); i++)
    {
      String charAt = value.substring(i, i + 1);
      try
      {
        byteCount += charAt.getBytes("UTF-8").length;
      }
      catch (UnsupportedEncodingException e) {}
    }
    return byteCount;
  }
  
  public static String changeEncoding(String orginal, String encoding)
  {
    if (orginal == null) {
      return null;
    }
    try
    {
      StringBuffer retStr = new StringBuffer();
      URLCodec encoder = new URLCodec(encoding);
      for (int i = 0; i < orginal.length(); i++)
      {
        String s = encoder.encode(orginal.substring(i, i + 1));
        retStr.append(s);
      }
      return retStr.toString();
    }
    catch (EncoderException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static String changeToUTF8(String orginal)
  {
    if (orginal == null) {
      return "";
    }
    try
    {
      byte[] bytes = orginal.getBytes("SJIS");
      return new String(bytes, "UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public static Long convertToLong(String obj)
  {
    if (nullOrBlank(obj)) {
      return new Long(0L);
    }
    try
    {
      return Long.valueOf(obj);
    }
    catch (Exception e) {}
    return new Long(0L);
  }
  
  public static ArrayList<String> separateValueString(String aStr, String aSeprator)
  {
    ArrayList<String> list = new ArrayList();
    for (;;)
    {
      int isp = 0;
      String w;
      for (;;)
      {
        int ifnd = aStr.indexOf(aSeprator, isp);
        if (ifnd < 0)
        {
          int ipos = ifnd;
          break;
        }
        if (ifnd != 0)
        {
          String w = aStr.substring(ifnd - 1, ifnd);
          if (w.compareTo("\\") == 0)
          {
            aStr = aStr.substring(0, ifnd - 1) + aSeprator + aStr.substring(ifnd + 1);
            isp = ifnd;
          }
          else
          {
            int ipos = ifnd;
            break;
          }
        }
        else
        {
          int ipos = ifnd;
          break;
        }
      }
      int ipos;
      if (ipos < 0) {
        break;
      }
      String w;
      String w;
      if (ipos != 0) {
        w = aStr.substring(0, ipos);
      } else {
        w = "";
      }
      if (aStr.length() > ipos + 1) {
        aStr = aStr.substring(ipos + 1);
      } else {
        aStr = "";
      }
      list.add(w);
    }
    list.add(aStr);
    return list;
  }
  
  public static synchronized String formUrl(String baseUrl)
  {
    if (baseUrl.startsWith("/")) {
      return baseUrl;
    }
    if ((baseUrl.startsWith("http://")) || (baseUrl.startsWith("https://"))) {
      return baseUrl;
    }
    return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/" + baseUrl;
  }
  
  public static String[] splitCsvValueWithQuot(String csvLine, String delimiter)
  {
    return splitCsvValue(csvLine, delimiter, true);
  }
  
  public static String[] splitCsvValueWithQuot(String csvLine)
  {
    return splitCsvValue(csvLine, ",", true);
  }
  
  public static String[] splitCsvValueNoQuot(String csvLine, String delimiter)
  {
    return splitCsvValue(csvLine, delimiter, false);
  }
  
  public static String[] splitCsvValueNoQuot(String csvLine)
  {
    return splitCsvValue(csvLine, ",", false);
  }
  
  private static String[] splitCsvValue(String csvLine, String delimiter, boolean hasQuot)
  {
    String restSt = csvLine;
    String element = "";
    List<String> valueList = new ArrayList();
    int fNo = -1;
    if (csvLine == null) {
      return new String[0];
    }
    do
    {
      boolean endFlg = false;
      if (restSt.equals(""))
      {
        element = "";
        valueList.add(element);
        fNo = -1;
      }
      else
      {
        if (hasQuot)
        {
          String sCh = restSt.substring(0, 1);
          if ((sCh.compareTo("'") == 0) || (sCh.compareTo("\"") == 0))
          {
            int eChNo = restSt.indexOf(sCh, 1);
            if (eChNo != -1)
            {
              element = restSt.substring(1, eChNo);
              
              int sepNo = restSt.indexOf(delimiter, eChNo + 1);
              if (sepNo != -1)
              {
                element = element + restSt.substring(eChNo + 1, sepNo);
                
                valueList.add(element);
                
                restSt = restSt.substring(sepNo + 1);
                fNo = 0;
                endFlg = true;
              }
              else
              {
                element = element + restSt.substring(eChNo + 1);
                
                valueList.add(element);
                
                restSt = "";
                fNo = -1;
                endFlg = true;
              }
            }
          }
        }
        if (!endFlg)
        {
          fNo = restSt.indexOf(delimiter);
          if (fNo != 0)
          {
            if (fNo != -1)
            {
              element = restSt.substring(0, fNo);
              if (hasQuot) {
                element = removeQuotation(element);
              }
              valueList.add(element);
              restSt = restSt.substring(fNo + 1);
            }
            else
            {
              element = restSt;
              if (hasQuot) {
                element = removeQuotation(element);
              }
              valueList.add(element);
            }
          }
          else
          {
            element = "";
            valueList.add(element);
            restSt = restSt.substring(1);
          }
        }
      }
    } while (fNo != -1);
    return (String[])valueList.toArray(new String[valueList.size()]);
  }
  
  private static String removeQuotation(String element)
  {
    if (element.length() == 1) {
      return element;
    }
    String headChar = element.substring(0, 1);
    String endChar = element.substring(element.length() - 1, element.length());
    if (((headChar.equals("'")) || (headChar.equals("\""))) && 
      (headChar.equals(endChar))) {
      element = element.substring(1, element.length() - 1);
    }
    return element;
  }
  
  public static String parseHtmlString(String original)
  {
    try
    {
      if (nullOrBlank(original)) {
        return "";
      }
      StringBuffer result = new StringBuffer(original.length());
      
      char[] cArray = original.toCharArray();
      int arrLength = cArray.length;
      
      int orgLength = ORIGINAL.length;
      for (int cnt = 0; arrLength > cnt; cnt++)
      {
        char workChar = cArray[cnt];
        
        boolean found = false;
        for (int cnt1 = 0; orgLength > cnt1; cnt1++) {
          if (workChar == ORIGINAL[cnt1])
          {
            result.append(CHANGE[cnt1]);
            found = true;
            break;
          }
        }
        if (!found) {
          result.append(workChar);
        }
      }
      return result.toString();
    }
    catch (NullPointerException e) {}
    return "";
  }
  
  public static String removeControlChar(String inText)
  {
    if (inText == null) {
      return null;
    }
    String regExpStr = "[\\00-\\x08\\x0b-\\x0c\\x0e-\\x1f\\x7f]";
    return inText.replaceAll(regExpStr, "");
  }
  
  public static String trimBlank(String inText)
  {
    if (nullOrBlank(inText)) {
      return "";
    }
    return inText.trim();
  }
  
  public static boolean nullBlankSpace(String text)
  {
    if (text == null) {
      return true;
    }
    return nullOrBlank(text.replaceAll(" ", "").replaceAll("　", ""));
  }
  
  public static String replaceAll(String value, String regex, String replacement)
  {
    value = toEmpty(value);
    regex = toEmpty(regex);
    replacement = toEmpty(replacement);
    
    return value.replaceAll(regex, replacement).trim();
  }
  
  public static String convertHiragana2Katakana(String hiragana)
  {
    StringBuffer katakana = new StringBuffer(hiragana);
    for (int i = 0; i < katakana.length(); i++)
    {
      char c = katakana.charAt(i);
      if ((c >= 'ぁ') && (c <= 'ん')) {
        katakana.setCharAt(i, (char)(c - 'ぁ' + 12449));
      }
    }
    return katakana.toString();
  }
  
  public static String parseXMLString(String oldMessage)
  {
    String textNode = "";
    String oldTextNode = "";
    oldMessage = toEmpty(oldMessage);
    

    DOMParser parser = new DOMParser();
    try
    {
      parser.parse(new InputSource(new StringReader(oldMessage)));
      textNode = getTextNodes(parser.getDocument(), oldMessage);
      oldTextNode = new String(textNode);
      textNode = parseHtmlString(textNode);
    }
    catch (Exception e)
    {
      return oldTextNode;
    }
    textNode = oldMessage.replace(oldTextNode, textNode);
    
    return textNode;
  }
  
  private static boolean isNewLineNode(Node node)
  {
    if ((node != null) && 
      (node.getNodeName().equalsIgnoreCase("br"))) {
      return true;
    }
    return false;
  }
  
  private static boolean isBlockNode(Node node)
  {
    Node targetNode = node.getPreviousSibling() != null ? node.getPreviousSibling() : node.getParentNode();
    if ((targetNode != null) && (
      (targetNode.getNodeName().equalsIgnoreCase("div")) || 
      (targetNode.getNodeName().equalsIgnoreCase("dd")) || 
      (targetNode.getNodeName().equalsIgnoreCase("dt")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h1")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h2")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h3")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h4")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h5")) || 
      (targetNode.getNodeName().equalsIgnoreCase("h6")) || 
      (targetNode.getNodeName().equalsIgnoreCase("li")) || 
      (targetNode.getNodeName().equalsIgnoreCase("p")) || 
      (targetNode.getNodeName().equalsIgnoreCase("td")) || 
      (targetNode.getNodeName().equalsIgnoreCase("th")))) {
      return true;
    }
    return false;
  }
  
  private static boolean isIgnoreNode(Node node)
  {
    if ((node.getNodeName().equalsIgnoreCase("style")) || 
      (node.getNodeName().equalsIgnoreCase("script"))) {
      return true;
    }
    return false;
  }
  
  private static String getTextNodes(Node node, String message)
  {
    StringBuffer resultString = new StringBuffer();
    for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
      if (child.getNodeType() == 3)
      {
        if ((isBlockNode(child)) || 
          (isNewLineNode(child))) {
          resultString.append("\r\n");
        }
        if ((!child.getNodeValue().equals("&nbsp;")) && 
          (!child.getNodeValue().equals(" ")) && 
          (!child.getNodeValue().equals("\t")) && 
          (!child.getNodeValue().equals("\n")) && 
          (!child.getNodeValue().getBytes().equals(Integer.valueOf(160))) && 
          (!child.getNodeValue().getBytes().equals(Integer.valueOf(63))) && (
          (child.getNodeValue().getBytes().length != 1) || (child.getNodeValue().getBytes()[0] != 63))) {
          resultString.append(child.getNodeValue());
        }
      }
      else if (!isIgnoreNode(child))
      {
        resultString.append(getTextNodes(child, message));
      }
    }
    return resultString.toString();
  }
  
  public static final String escapeHTML(String s)
  {
    StringBuffer sb = new StringBuffer();
    int n = s.length();
    for (int i = 0; i < n; i++)
    {
      char c = s.charAt(i);
      switch (c)
      {
      case '<': 
        sb.append("&lt;"); break;
      case '>': 
        sb.append("&gt;"); break;
      case '&': 
        sb.append("&amp;"); break;
      case '"': 
        sb.append("&quot;"); break;
      case 'a': 
        sb.append("&agrave;"); break;
      case 'A': 
        sb.append("&Agrave;"); break;
      case 'a': 
        sb.append("&acirc;"); break;
      case 'A': 
        sb.append("&Acirc;"); break;
      case 'a': 
        sb.append("&auml;"); break;
      case 'A': 
        sb.append("&Auml;"); break;
      case 'a': 
        sb.append("&aring;"); break;
      case 'A': 
        sb.append("&Aring;"); break;
      case 'a': 
        sb.append("&aelig;"); break;
      case 'A': 
        sb.append("&AElig;"); break;
      case 'c': 
        sb.append("&ccedil;"); break;
      case 'C': 
        sb.append("&Ccedil;"); break;
      case 'e': 
        sb.append("&eacute;"); break;
      case 'E': 
        sb.append("&Eacute;"); break;
      case 'e': 
        sb.append("&egrave;"); break;
      case 'E': 
        sb.append("&Egrave;"); break;
      case 'e': 
        sb.append("&ecirc;"); break;
      case 'E': 
        sb.append("&Ecirc;"); break;
      case 'e': 
        sb.append("&euml;"); break;
      case 'E': 
        sb.append("&Euml;"); break;
      case 'i': 
        sb.append("&iuml;"); break;
      case 'I': 
        sb.append("&Iuml;"); break;
      case 'o': 
        sb.append("&ocirc;"); break;
      case 'O': 
        sb.append("&Ocirc;"); break;
      case 'o': 
        sb.append("&ouml;"); break;
      case 'O': 
        sb.append("&Ouml;"); break;
      case 'o': 
        sb.append("&oslash;"); break;
      case 'O': 
        sb.append("&Oslash;"); break;
      case 's': 
        sb.append("&szlig;"); break;
      case 'u': 
        sb.append("&ugrave;"); break;
      case 'U': 
        sb.append("&Ugrave;"); break;
      case 'u': 
        sb.append("&ucirc;"); break;
      case 'U': 
        sb.append("&Ucirc;"); break;
      case 'u': 
        sb.append("&uuml;"); break;
      case 'U': 
        sb.append("&Uuml;"); break;
      case 'R': 
        sb.append("&reg;"); break;
      case 'c': 
        sb.append("&copy;"); break;
      case '?': 
        sb.append("&euro;"); break;
      default: 
        sb.append(c);
      }
    }
    return sb.toString();
  }
  
  public static String truncate(String target, int length)
  {
    CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
    if (length >= encoder.maxBytesPerChar() * target.length()) {
      return target;
    }
    CharBuffer buffer = CharBuffer.wrap(new char[Math.min(target.length(), length)]);
    target.getChars(0, Math.min(target.length(), buffer.length()), buffer.array(), 0);
    if (length >= encoder.maxBytesPerChar() * buffer.limit()) {
      return buffer.toString();
    }
    ByteBuffer byteBuffer = ByteBuffer.allocate(length);
    encoder.reset();
    
    CoderResult result = buffer.hasRemaining() ? encoder.encode(buffer, byteBuffer, true) : CoderResult.UNDERFLOW;
    if (result.isUnderflow()) {
      result = encoder.flush(byteBuffer);
    }
    return buffer.flip().toString();
  }
  
  public static String join(Collection<String> s, String delimiter)
  {
    if ((s == null) || (s.size() == 0)) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    Iterator<String> iter = s.iterator();
    while (iter.hasNext())
    {
      buffer.append((String)iter.next());
      if (iter.hasNext()) {
        buffer.append(delimiter);
      }
    }
    return buffer.toString();
  }
  
  public static String join(String[] array, String separator)
  {
    if ((array == null) || (array.length == 0)) {
      return "";
    }
    StringBuffer result = new StringBuffer();
    result.append(array[0]);
    for (int i = 1; i < array.length; i++)
    {
      result.append(separator);
      result.append(array[i]);
    }
    return result.toString();
  }
  
  public static String chomp(String str)
  {
    if (isEmpty(str)) {
      return str;
    }
    if (str.length() == 1)
    {
      char ch = str.charAt(0);
      if ((ch == '\r') || (ch == '\n')) {
        return "";
      }
      return str;
    }
    int lastIdx = str.length() - 1;
    char last = str.charAt(lastIdx);
    if (last == '\n')
    {
      if (str.charAt(lastIdx - 1) == '\r') {
        lastIdx--;
      }
    }
    else if (last != '\r') {
      lastIdx++;
    }
    return str.substring(0, lastIdx);
  }
  
  public static String serialize(Collection<List<String>> values)
  {
    if ((values == null) || (values.size() == 0)) {
      return "[[]]";
    }
    StringBuffer buff = new StringBuffer();
    buff.append("[");
    Iterator<List<String>> iter = values.iterator();
    
    int count = 0;
    while (iter.hasNext())
    {
      if (count > 0) {
        buff.append(",");
      }
      buff.append(serialize((List)iter.next()));
      count++;
    }
    buff.append("]");
    
    return buff.toString();
  }
  
  public static String serialize(List<String> params)
  {
    return "[" + join(params, ",") + "]";
  }
  
  public static String removeUTF8BOM(String s)
  {
    if (s.startsWith("?")) {
      s = s.substring(1);
    }
    return s;
  }
  
  public static String substringAfter(String str, String separator)
  {
    if ((nullOrBlank(str)) || (nullOrBlank(separator))) {
      return str;
    }
    int pos = str.indexOf(separator);
    if (pos == -1) {
      return "";
    }
    return str.substring(pos + separator.length());
  }
  
  public static String substringBeforeLast(String str, String separator)
  {
    if ((nullOrBlank(str)) || (nullOrBlank(separator))) {
      return str;
    }
    int pos = str.lastIndexOf(separator);
    if (pos == -1) {
      return str;
    }
    return str.substring(0, pos);
  }
  
  public static String substringBefore(String str, String separator)
  {
    if ((nullOrBlank(str)) || (nullOrBlank(separator))) {
      return str;
    }
    int pos = str.indexOf(separator);
    if (pos == -1) {
      return str;
    }
    return str.substring(0, pos);
  }
  
  public static int countByte(String value, String encode)
  {
    if (nullOrBlank(value)) {
      return 0;
    }
    int cnt = 0;
    try
    {
      cnt = value.getBytes(encode).length;
    }
    catch (UnsupportedEncodingException ex) {}
    return cnt;
  }
  
  public static boolean isMailAddress(String maiAddress)
  {
    String literalMailAddress = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-_@!#$%&*+/=?`{|}";
    boolean validate = false;
    if (("".equals(maiAddress)) || (maiAddress == null)) {
      return true;
    }
    int count = 0;
    for (int idex = 0; idex < maiAddress.length() - 2; idex++) {
      if ("@".equals(maiAddress.substring(idex, idex + 1))) {
        count++;
      }
    }
    if (count != 1) {
      return false;
    }
    int index = maiAddress.indexOf(".");
    if ((index < 1) || (index >= maiAddress.length() - 1)) {
      return false;
    }
    index = maiAddress.indexOf("_");
    if ((index >= 0) && ((index < 1) || (index >= maiAddress.length() - 1))) {
      return false;
    }
    index = maiAddress.indexOf("-");
    if ((index >= 0) && ((index < 1) || (index >= maiAddress.length() - 1))) {
      return false;
    }
    for (int idex = 0; idex < maiAddress.length(); idex++)
    {
      String literal = maiAddress.substring(idex, idex + 1);
      if (literalMailAddress.indexOf(literal) < 0)
      {
        validate = false;
        break;
      }
      validate = true;
    }
    return validate;
  }
  
  public static boolean isMailServer(String maiServer)
  {
    String literalMailAddress = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-";
    boolean validate = false;
    if (("".equals(maiServer)) || (maiServer == null)) {
      return true;
    }
    for (int idex = 0; idex < maiServer.length(); idex++)
    {
      String literal = maiServer.substring(idex, idex + 1);
      if (literalMailAddress.indexOf(literal) < 0)
      {
        validate = false;
        break;
      }
      validate = true;
    }
    return validate;
  }
  
  public static String trimLeft(String string)
  {
    int stringLength = string.length();
    for (int i = 0; i < stringLength; i++) {
      if (!isEmpty(Character.valueOf(string.charAt(i)))) {
        break;
      }
    }
    if (i == 0) {
      return string;
    }
    return string.substring(i);
  }
  
  public static String trimRight(String string)
  {
    int lastChar = string.length() - 1;
    for (int i = lastChar; i >= 0; i--) {
      if (!isEmpty(Character.valueOf(string.charAt(i)))) {
        break;
      }
    }
    if (i < lastChar) {
      return string.substring(0, i + 1);
    }
    return string;
  }
  
  public static String trim(String string)
  {
    return trimLeft(trimRight(string));
  }
  
  public static String parseAndToRichText(String content)
  {
    content = addEmpty(content);
    content = parseHtmlString(content);
    content = PEStringUtilities.toRichText(content);
    return content;
  }
  
  public static boolean isValidUrl(String url)
  {
    if (isValidString(url))
    {
      if (url.length() <= 1) {
        return false;
      }
      if ("/".equals(url.substring(0, 1)))
      {
        if ("/".equals(url.substring(1, 2))) {
          return false;
        }
      }
      else if ((!url.matches("(?i)^https?://.+$")) && 
        (!url.matches("(?i)^\\\\{2}.+$")) && 
        (!url.matches("(?i)^[a-z]:.+$"))) {
        return false;
      }
    }
    return true;
  }
  
  public static int getMaxRichTextSize()
  {
    FacesContext context = FacesContext.getCurrentInstance();
    ServletContext servletContext = (ServletContext)context.getExternalContext().getContext();
    Long maxSize = null;
    if (servletContext.getAttribute("maxRichTextSize") != null) {
      maxSize = (Long)servletContext.getAttribute("maxRichTextSize");
    }
    int max_bytes = 0;
    if (maxSize != null) {
      max_bytes = maxSize.intValue();
    }
    return max_bytes;
  }
  
  public static String cutstringByte(String src, int byteLength, String encoding)
  {
    if (nullOrBlank(src)) {
      return "";
    }
    String result = src;
    if (countByte(src, encoding) > byteLength)
    {
      int bytes = 0;
      int len = src.length();
      for (int index = 0; index < len; index++) {
        try
        {
          bytes += String.valueOf(src.charAt(index)).getBytes(encoding).length;
          if (bytes > byteLength)
          {
            result = src.substring(0, index);
            break;
          }
        }
        catch (UnsupportedEncodingException e) {}
      }
    }
    return result;
  }
}
