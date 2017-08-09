package com.atwhere.p2p.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

public class StringUtil {
	public static final NumberFormat CURRENCY_FORMAT = NumberFormat
			.getCurrencyInstance();

	// public static final NumberFormat PERCENT_FORMAT =
	// NumberFormat.getPercentInstance();
	// public static final SimpleDateFormat YYYYMMDD = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static final SimpleDateFormat YYYYMMDD_HHMMSS = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// public static final SimpleDateFormat YYYYMMDD_HHMMSS_FILE = new
	// SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

	/**
	 * 判断某字符串是否为空(null和""和 "  "都为空)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean empty(String str) {
		return str == null || str.trim().equals("")
				|| str.trim().equals("null");
	}

	/**
	 * 获取随机字符串
	 * 
	 * @param length
	 *            ：要获取的字符串的长度
	 * @return 返回随机字符串
	 * 
	 *         随机字符串有数字和字母组成，特殊字符可自定义
	 */
	public static String getRandomStr(int length) {
		StringBuffer buffer = new StringBuffer(
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 根据左右下标截取字符串 并且左闭右开
	 * 
	 * @param str初始字符串
	 * @param startindex开始位置从0开始
	 *            （包含此位置）
	 * @param endindex结束位置
	 *            （不包含此位置）
	 * @return 新字符串
	 */
	public static String subString(String str, int startindex, int endindex) {
		return str.substring(startindex, endindex);
	}

	/**
	 * 连接两个字符串并返回
	 * 
	 * @param str1字符串1
	 * @param str2字符串2
	 * @return
	 */
	public static String conCat(String str1, String str2) {
		return str1.concat(str2); // str1不能为null
	}

	/**
	 * 根据左下标截取字符串
	 * 
	 * @param str初始字符串
	 * @param beginindex下标开始位置
	 *            到末尾
	 * @return
	 */
	public static String subString(String str, int beginindex) {
		return str.substring(beginindex);
	}

	/**
	 * 替换字符串中的指定字符
	 * 
	 * @param str初始字符串
	 * @param original指定字符
	 *            （要替换的字符）
	 * @param replacement替换字符
	 *            （替换成的）
	 * @return
	 */
	public String rePlace(String str, char original, char replacement) {
		return str.replace(original, replacement);
	}

	/**
	 * 替换字符串
	 * 
	 * @since 1.1
	 * @param strXhc
	 *            需要进行替换的字符串
	 * @param oldStr
	 *            源字符串
	 * @param newStr
	 *            替换后的字符串
	 * @return 替换后对应的字符串
	 */
	public static String replace(String strXhc, String oldStr, String newStr) {
		String ret = strXhc;
		if (ret != null && oldStr != null && newStr != null) {
			ret = strXhc.replaceAll(oldStr, newStr);
		}
		return ret;
	}

	/**
	 * 替换字符串，修复java.lang.String类的replaceAll方法时第一参数是字符串常量正则时(如："address".
	 * replaceAll("dd","$");)的抛出异常：java.lang.StringIndexOutOfBoundsException:
	 * String index out of range: 1的问题。
	 * 
	 * @since 1.2
	 * @param strXhc
	 *            需要进行替换的字符串
	 * @param oldStr
	 *            源字符串
	 * @param newStr
	 *            替换后的字符串
	 * @return 替换后对应的字符串
	 */
	public static String replaceAll(String strXhc, String oldStr, String newStr) {
		int i = -1;
		while ((i = strXhc.indexOf(oldStr)) != -1) {
			strXhc = new StringBuffer(strXhc.substring(0, i)).append(newStr)
					.append(strXhc.substring(i + oldStr.length())).toString();
		}
		return strXhc;
	}

	/**
	 * 模拟一个trim方法，去除字符串两端的空格。（利用str.substring(start, end)取不是空格的字符串即可）
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		// 将String转换成char数组
		char[] cs = str.toCharArray();
		int start = 0;
		int end = cs.length;
		while (start < end && cs[start] == ' ') {
			start++;
		}
		while (start < end && cs[end - 1] == ' ') {
			end--;
		}
		String newStr = str.substring(start, end);
		return newStr;
	}

	/**
	 * 将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String turn(String str, int start, int end) {
		char[] cs = str.toCharArray();
		for (int i = start, j = end; i < j; i++, j--) {
			char t = cs[i];
			cs[i] = cs[j];
			cs[j] = t;
		}
		String newStr = new String(cs);
		return newStr;
	}

	/**
	 * 获取一个字符串在另一个字符串中出现的次数。 比如：获取“ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
	 * 
	 * @param str1 源字符串 如： “abkkcadkabkebfkabkskab”
	 * @param str2 目标字符串 如：“ab”
	 */
	public static int times(String str1, String str2) {
		int total = 0;
		int j = 0;
		while (str1.indexOf(str2, j) != -1) {
			total++;
			j = str1.indexOf(str2, j) + 1;

		}
		return total;
	}
	
	
	 /**
	  * 获取两个字符串中最大相同子串。比如： str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
	  * 提示：将短的那个串进行长度依次递减的子串与较长的串比较。
	  * @param str1
	  * @param str2
	  * @return
	  */
	public static String maxStr(String str1, String str2) {
		String strMax = str1.length() > str2.length() ? str1 : str2;
		String strMin = str1.length() > str2.length() ? str2 : str1;
		for (int i = 0; i < strMin.length(); i++) {
			for (int j = 0, h = strMin.length() - 1 - i; j < i + 1; j++, h++) {
				String maxStr = strMin.substring(j, h);
				if (strMax.contains(maxStr)) {
					return maxStr;
				}
			}
		}
		return null;
	}
	
	/** 
	 *对字符串中字符进行自然顺序排序。
	 * 提示： 1）字符串变成字符数组。 2）对数组排序，选择，冒泡，Arrays.sort();3）将排序后的数组变成字符串。
	 * @param str
	 * @return
	 */
	public static String sort(String str) {
		char[] ch = str.toCharArray();
		Arrays.sort(ch);
		String newStr = String.copyValueOf(ch);
		return newStr;
	}

	/**
	 * 将字符串转换成HTML格式的字符串
	 * 
	 * @since 1.1
	 * @param str
	 *            需要进行转换的字符串
	 * @return 转换后的字符串
	 */
	public static String strToHtml(String str) {
		String html = str;
		if (str == null || str.length() == 0) {
			return "";
		} else {
			html = replace(html, "&", "&amp;");
			html = replace(html, "<", "&lt;");
			html = replace(html, ">", "&gt;");
			html = replace(html, "\r\n", "\n");
			html = replace(html, "\n", "<br>\n");
			html = replace(html, "\"", "&quot;");
			html = replace(html, " ", "&nbsp;");
			return html;
		}
	}

	/**
	 * 将HTML格式的字符串转换成常规显示的字符串
	 * 
	 * @since 1.1
	 * @param str
	 *            需要进行转换的字符串
	 * @return 转换后的字符串
	 */
	public static String htmlToStr(String str) {
		String text = str;
		if (str == null || str.length() == 0) {
			return "";
		} else {
			text = replace(text, "&amp;", "&");
			text = replace(text, "&lt;", "<");
			text = replace(text, "&gt;", ">");
			text = replace(text, "<br>\n", "\n");
			text = replace(text, "<br>", "\n");
			text = replace(text, "&quot;", "\"");
			text = replace(text, "&nbsp;", " ");
			return text;
		}
	}

	/**
	 * 将一字符串数组以某特定的字符串作为分隔来变成字符串
	 * 
	 * @since 1.0
	 * @param strs
	 *            字符串数组
	 * @param token
	 *            分隔字符串
	 * @return 以token为分隔的字符串
	 */
	public static String arrToStr(String[] strs, String token) {
		if (strs == null)
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (i != 0)
				sb.append(token);
			sb.append(strs[i]);
		}
		return sb.toString();
	}

	/**
	 * 将一字符串以某特定的字符串作为分隔来变成字符串数组 特殊字符开头
	 * 
	 * @since 1.0
	 * @param str
	 *            需要拆分的字符串("@12@34@56")
	 * @param token
	 *            分隔字符串("@")
	 * @return 以token为分隔的拆分开的字符串数组
	 */
	public static String[] split(String str, String token) {
		if (str != null && str.startsWith(token)) {
			String temp = str.substring(1, str.length());
			return temp.split(token);
		}
		return str.split(token);
	}

	/**
	 * 将数值型字符串转换成Integer型
	 * 
	 * @since 1.0
	 * @param str
	 *            需要转换的字符型字符串
	 * @param ret
	 *            转换失败时返回的值
	 * @return 成功则返回转换后的Integer型值；失败则返回ret
	 */
	public static Integer string2Integer(String str, Integer ret) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return ret;
		}
	}

	/**
	 * 将数值型转换成字符串
	 * 
	 * @since 1.0
	 * @param it
	 *            需要转换的Integer型值
	 * @param ret
	 *            转换失败的返回值
	 * @return 成功则返回转换后的字符串；失败则返回ret
	 */
	public static String integer2String(Integer it, String ret) {
		try {
			return Integer.toString(it);
		} catch (NumberFormatException e) {
			return ret;
		}
	}

	/**
	 * 比较两字符串大小(ASCII码顺序)
	 * 
	 * @since 1.1
	 * @param str1
	 *            参与比较的字符串1
	 * @param str2
	 *            参与比较的字符串2
	 * @return str1>str2:1;str1<str2:-1;str1=str2:0
	 */
	public static int compare(String str1, String str2) {//
		if (str1.equals(str2)) {
			return 0;
		}
		int str1Length = str1.length();
		int str2Length = str2.length();
		int length = 0;
		if (str1Length > str2Length) {
			length = str2Length;
		} else {
			length = str1Length;
		}
		for (int i = 0; i < length; i++) {
			if (str1.charAt(i) > str2.charAt(i)) {
				return 1;
			}
		}
		return -1;
	}

	/**
	 * 将字符串的首字母改为大写
	 * 
	 * @since 1.2
	 * @param str
	 *            需要改写的字符串
	 * @return 改写后的字符串
	 */
	public static String firstToUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 将字符串的首字母改为小写
	 * 
	 * @since 1.2
	 * @param str
	 *            需要改写的字符串
	 * @return 改写后的字符串
	 */
	public static String firstToLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 解析分号和等于号分割的字符串，转成HashMap对象
	 * 
	 * @param str
	 *            源串，示例："Ver=1.0;xmlns:esg=urn::2011"
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> str2HashMap(String str) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (str == null || str.trim().equals(""))
			return map;

		String[] parts = str.trim().split(";");
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			String[] item = part.split("=");
			map.put(item[0], item[1]);
		}
		return map;
	}

	/**
	 * 将HashMap对象 转化为分号和等于号分割的字符串
	 * 
	 * @param map
	 * @return 示例："Ver=1.0;xmlns:esg=urn::2011"
	 */
	public static String hashMap2Str(HashMap<String, String> map) {
		String str = "";
		LinkedList<String> list = new LinkedList<String>(map.keySet());
		Collections.sort(list);
		for (String key : list) {
			if (str.equals("")) {
				str = key + "=" + map.get(key);
			} else {
				str += ";" + key + "=" + map.get(key);
			}
		}
		return str;
	}

	/**
	 * utf8字符串的长度
	 * 
	 * @param str
	 * @return 出错时返回-1
	 */
	public static int uLength(String str) {
		try {
			String b = new String(str.getBytes("UTF-8"), "ISO8859_1");
			return b.length();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 解决中文参数转换
	 * 
	 * @param str
	 * @return
	 */
	public static String setEncode(String str) {
		String reStr = "";
		try {
			reStr = new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 提供精确的加法运算
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 * 
	 */
	public static float add(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).floatValue();
	}

	/**
	 * 版本号比较大小 版本号形式: 0.1.2.14
	 * 
	 * @param newVersion
	 * @param oldVersion
	 * @return
	 */
	public static boolean compareVersion(String newVersion, String oldVersion) {
		boolean flag = false;
		if (!"null".equals(newVersion) && !"null".equals(oldVersion)) {
			String[] str1 = newVersion.split("\\.");
			String[] str2 = oldVersion.split("\\.");
			int size = str1.length;
			for (int i = 0; i < size; i++) {
				int temp1 = Integer.parseInt(str1[i]);
				int temp2 = Integer.parseInt(str2[i]);

				if (temp1 == temp2)
					continue;
				else {
					flag = temp1 > temp2 ? true : false;
					break;
				}
			}
		} else
			flag = false;
		return flag;

	}

	/**
	 * 将字符串转化为Clob对象
	 * 
	 * @param str
	 * @return
	 */
	public static Clob strToClob(String str) {
		try {
			return new SerialClob(str.toCharArray());
		} catch (SerialException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将string对象转换为Clob对象,Blob处理方式与此相同
	 * 
	 * @param str
	 * @param lob
	 */
	public static Clob oracleStr2Clob(String str, Clob lob) throws Exception {
		Method methodToInvoke = lob.getClass().getMethod(
				"getCharacterOutputStream", (Class[]) null);
		Writer writer = (Writer) methodToInvoke.invoke(lob, (Object[]) null);
		writer.write(str);
		writer.close();
		return lob;
	}

	/**
	 * 将Clob对象转换为String对象
	 * 
	 * @param Clob
	 * @return String
	 */
	public static String ClobToString(Clob clob) throws SQLException,
			IOException {
		String reString = "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}

	@SuppressWarnings("unchecked")
	public static Object createOracleLob(Connection conn, String lobClassName)
			throws Exception {
		@SuppressWarnings("rawtypes")
		Class lobClass = conn.getClass().getClassLoader()
				.loadClass(lobClassName);
		final Integer DURATION_SESSION = new Integer(lobClass.getField(
				"DURATION_SESSION").getInt(null));
		final Integer MODE_READWRITE = new Integer(lobClass.getField(
				"MODE_READWRITE").getInt(null));
		Method createTemporary = lobClass.getMethod("createTemporary",
				new Class[] { Connection.class, boolean.class, int.class });
		Object lob = createTemporary.invoke(null, new Object[] { conn, false,
				DURATION_SESSION });
		Method open = lobClass.getMethod("open", new Class[] { int.class });
		open.invoke(lob, new Object[] { MODE_READWRITE });
		return lob;
	}

	/**
	 * 判断一个字符串是否为整形数字
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isNumeric(String str) {
		// str = str.trim();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 * 
	 *         第二位是3,4,5,7,8的才能通过验证，可自定义
	 */
	public static boolean isMobile(String str) {
		// str = str.trim();
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 验证字符串合法性
	 * 
	 * @since 1.0
	 * @param str
	 *            需要验证的字符串
	 * @param test
	 *            非法字符串（如："~!#$%^&*()',;:?"）
	 * @return true:非法;false:合法
	 */
	public static boolean check(String str, String test) {
		if (str == null || str.equals("")) // 为空或null是也非法
			return true;
		boolean flag = false;
		for (int i = 0; i < test.length(); i++) {
			if (str.indexOf(test.charAt(i)) != -1) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 替换字符串中指定位置字符
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param n
	 * @return
	 */
	public static String replaceStr(String str, int n) {
		String s1 = "", s2 = "";
		if (!empty(str)) {
			if (n > 4) {
				s1 = subString(str, 0, n - 1) + "*********";
				s2 = subString(str, str.length() - 3, str.length()); // n+8
			} else {
				s1 = subString(str, 0, n - 1) + "****";
				s2 = subString(str, str.length() - 4, str.length()); // n+3
			}
		}
		return conCat(s1, s2);
	}
}
