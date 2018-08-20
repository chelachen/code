/*
 * Id: SecurityUtil.java
 * Type Name: cn.ccb.cclbm.foundation.common.SecurityUtil
 * Create Date: 2005-4-12
 * Author: robert.luo,zhangfeng/zh.ccb@ccb.cn
 * 
 *
 * Project: CCLBM
 *
 *
 *
 */

package com.jifan.pssmis.foundation.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jifan.pssmis.foundation.log.SysLogger;

/**
 * Utiltiy class for encryption/decryption, encode/decode
 * 专用工具类,负责字符串的加/解密,编码/解码工作
 * 
 * @author jifan
 * 
 */
public class SecurityUtil {

	public static final String ALGORITHM_SHA = "SHA-1";

	public static final String ALGORITHM_MD5 = "MD5";

	public static void main(String[] args) {
		System.out.println(encryptMD5("123456"));
	}

	public static String encryptSha(String str) {
		try {
			MessageDigest hasher = MessageDigest.getInstance("SHA-1");
			byte[] digest = hasher.digest(str.toString().getBytes());
			return ByteToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String ByteToHexString(byte[] data) {
		StringBuilder str = new StringBuilder();
		for (byte b : data) {
			String hv = Integer.toHexString(b & 0xFF);
			if (hv.length() < 2)
				str.append("0");
			str.append(hv);
		}
		return str.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param plainText
	 *            要加密的字符串
	 * @return 加密后的串
	 */
	public static String encryptMD5(String plainText) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance(SecurityUtil.ALGORITHM_MD5);
			md.update(plainText.getBytes());
			// result = new BigInteger(1, md.digest()).toString(16);
			//			
			// //如果为31位时，前面加上一个0
			// if ((result.length() % 2) != 0) {
			// result = "0" + result;
			// }

			byte[] digest = md.digest();
			StringBuffer md5 = new StringBuffer();
			for (int i = 0; i < digest.length; i++) {
				md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));
				md5.append(Character.forDigit((digest[i] & 0xF), 16));
			}
			result = md5.toString();

		} catch (NoSuchAlgorithmException e) {
			SysLogger.getLog(SecurityUtil.class).equals(e);
		}
		return result;
	}

	/**
	 * MD5编码
	 * 
	 * @param origin
	 *            原始字符串
	 * @return 经过MD5加密之后的结果
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			// resultString =
			// byteArrayToHexString(md.digest(resultString.getBytes()));//原文件内容，可能原因是：win2003时系统缺省编码为GBK，win7为utf-8
			resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));// 正确的写法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	/*
	 * parm_string是用户传进来的密码，parm_seed是用户传进来的用户名 由 parm_string 和 parm_seed生成
	 * temp_obj_string 如果参数为null 则返回 null 高总写的算法
	 */
	public static String f_string_translator(String parm_string, String parm_seed) {
		if ((parm_string == null) || (parm_seed == null)) {
			return null;
		}
		if (parm_string.length() > 16) {
			return null;
		}
		char ary_string[] = new char[17];// 定义17个数组是为了与delphi对应
		String tmpAryString = parm_string.toUpperCase();// 把传进来的‘密码’参数转换为大写
		int len = tmpAryString.length();
		String s = "";
		if (len <= 16) {
			int n = 16 / len;
			for (int i = 0; i < n; i++) {
				s = s + tmpAryString;
			}
			s = s + tmpAryString.substring(0, (16 - n * len));// 如果传进来的parm_string参数长度不足16位把它填充为16位
		}
		char c[] = s.toCharArray();// 把字符串s转换成char类型

		for (int i = 1; i <= 16; i++) {
			ary_string[i] = c[i - 1];// 把c[]赋值给对应的ary_string[]
		}
		char ary_seed[] = new char[17];
		String tmpAryStringseed = parm_seed.toUpperCase();// 把传进来的‘用户名’参数转换为大写
		int lenseed = tmpAryStringseed.length();
		String s1 = "";
		if (lenseed <= 16) {
			int n = 16 / lenseed;
			for (int i = 0; i < n; i++) {
				s1 = s1 + tmpAryStringseed;
			}
			s1 = s1 + tmpAryStringseed.substring(0, (16 - n * lenseed));// 如果传进来的parm_seed参数长度不足16位把它填充为16位
		}
		char c1[] = s1.toCharArray();// 把字符串s1转换成char类型

		for (int i = 1; i <= 16; i++) {
			ary_seed[i] = c1[i - 1];// 把c1[]赋值给对应的ary_seed[]
		}

		String temp_obj_string = "";

		long ary_sn[] = new long[17];// 定义17个是为了与Delphi对应
		ary_seed[0] = '0';
		ary_string[0] = '0';
		ary_sn[1] = ary_string[8] * ary_seed[1];// 把ary_string[]与ary_seed[]的ascii码相乘赋值给ary_sn[]
		ary_sn[2] = ary_string[5] * ary_seed[2];
		ary_sn[3] = ary_string[6] * ary_seed[3];
		ary_sn[4] = ary_string[7] * ary_seed[4];

		ary_sn[5] = ary_string[1] + ary_seed[5];// 把ary_string[]与ary_seed[]的ascii码相加赋值给ary_sn[]
		ary_sn[6] = ary_string[4] + ary_seed[6];
		ary_sn[7] = ary_string[3] + ary_seed[7];
		ary_sn[8] = ary_string[2] + ary_seed[8];

		ary_sn[9] = ary_string[15] * ary_seed[9];// 把ary_string[]与ary_seed[]的ascii码相乘赋值给ary_sn[]
		ary_sn[10] = ary_string[13] * ary_seed[10];
		ary_sn[11] = ary_string[16] * ary_seed[11];
		ary_sn[12] = ary_string[14] * ary_seed[12];

		ary_sn[13] = ary_string[11] + ary_seed[13];// 把ary_string[]与ary_seed[]的ascii码相加赋值给ary_sn[]
		ary_sn[14] = ary_string[10] + ary_seed[14];
		ary_sn[15] = ary_string[12] + ary_seed[15];
		ary_sn[16] = ary_string[9] + ary_seed[16];

		int dd;
		char tc;
		int i;
		StringBuilder st = new StringBuilder(16);
		for (i = 1; i <= 16; i++) {
			dd = (int) (ary_sn[i] % 41);// 把ary_sn[]取模赋值给dd
			if (dd < 15) {
				tc = (char) (((dd + 5) % 8) + 50);
			} else {
				tc = (char) (dd + 50);
			}
			st.insert(0, tc);// 遍历ary_sn[]增加到st
		}
		temp_obj_string = st.toString();// 把st转换为string赋值给temp_obj_string

		return temp_obj_string;

	}

	private static final int[] abcde = { 0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0 };
	// 摘要数据存储数组
	private static int[] digestInt = new int[5];
	// 计算过程中的临时数据存储数组
	private static int[] tmpData = new int[80];

	// 计算sha-1摘要
	private static int process_input_bytes(byte[] bytedata) {
		// 初试化常量
		System.arraycopy(abcde, 0, digestInt, 0, abcde.length);
		// 格式化输入字节数组，补10及长度数据
		byte[] newbyte = byteArrayFormatData(bytedata);
		// 获取数据摘要计算的数据单元个数
		int MCount = newbyte.length / 64;
		// 循环对每个数据单元进行摘要计算
		for (int pos = 0; pos < MCount; pos++) {
			// 将每个单元的数据转换成16个整型数据，并保存到tmpData的前16个数组元素中
			for (int j = 0; j < 16; j++) {
				tmpData[j] = byteArrayToInt(newbyte, (pos * 64) + (j * 4));
			}
			// 摘要计算函数
			encrypt();
		}
		return 20;
	}

	// 格式化输入字节数组格式
	private static byte[] byteArrayFormatData(byte[] bytedata) {
		// 补0数量
		int zeros = 0;
		// 补位后总位数
		int size = 0;
		// 原始数据长度
		int n = bytedata.length;
		// 模64后的剩余位数
		int m = n % 64;
		// 计算添加0的个数以及添加10后的总长度
		if (m < 56) {
			zeros = 55 - m;
			size = n - m + 64;
		} else if (m == 56) {
			zeros = 63;
			size = n + 8 + 64;
		} else {
			zeros = 63 - m + 56;
			size = (n + 64) - m + 64;
		}
		// 补位后生成的新数组内容
		byte[] newbyte = new byte[size];
		// 复制数组的前面部分
		System.arraycopy(bytedata, 0, newbyte, 0, n);
		// 获得数组Append数据元素的位置
		int l = n;
		// 补1操作
		newbyte[l++] = (byte) 0x80;
		// 补0操作
		for (int i = 0; i < zeros; i++) {
			newbyte[l++] = (byte) 0x00;
		}
		// 计算数据长度，补数据长度位共8字节，长整型
		long N = (long) n * 8;
		byte h8 = (byte) (N & 0xFF);
		byte h7 = (byte) ((N >> 8) & 0xFF);
		byte h6 = (byte) ((N >> 16) & 0xFF);
		byte h5 = (byte) ((N >> 24) & 0xFF);
		byte h4 = (byte) ((N >> 32) & 0xFF);
		byte h3 = (byte) ((N >> 40) & 0xFF);
		byte h2 = (byte) ((N >> 48) & 0xFF);
		byte h1 = (byte) (N >> 56);
		newbyte[l++] = h1;
		newbyte[l++] = h2;
		newbyte[l++] = h3;
		newbyte[l++] = h4;
		newbyte[l++] = h5;
		newbyte[l++] = h6;
		newbyte[l++] = h7;
		newbyte[l++] = h8;
		return newbyte;
	}

	private static int f1(int x, int y, int z) {
		return (x & y) | (~x & z);
	}

	private static int f2(int x, int y, int z) {
		return x ^ y ^ z;
	}

	private static int f3(int x, int y, int z) {
		return (x & y) | (x & z) | (y & z);
	}

	private static int f4(int x, int y) {
		return (x << y) | x >>> (32 - y);
	} // 单元摘要计算函数

	private static void encrypt() {
		for (int i = 16; i <= 79; i++) {
			tmpData[i] = f4(tmpData[i - 3] ^ tmpData[i - 8] ^ tmpData[i - 14] ^ tmpData[i - 16], 1);
		}
		int[] tmpabcde = new int[5];
		for (int i1 = 0; i1 < tmpabcde.length; i1++) {
			tmpabcde[i1] = digestInt[i1];
		}
		for (int j = 0; j <= 19; j++) {
			int tmp = f4(tmpabcde[0], 5) + f1(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] + tmpData[j]
					+ 0x5a827999;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}
		for (int k = 20; k <= 39; k++) {
			int tmp = f4(tmpabcde[0], 5) + f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] + tmpData[k]
					+ 0x6ed9eba1;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}
		for (int l = 40; l <= 59; l++) {
			int tmp = f4(tmpabcde[0], 5) + f3(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] + tmpData[l]
					+ 0x8f1bbcdc;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}
		for (int m = 60; m <= 79; m++) {
			int tmp = f4(tmpabcde[0], 5) + f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] + tmpData[m]
					+ 0xca62c1d6;
			tmpabcde[4] = tmpabcde[3];
			tmpabcde[3] = tmpabcde[2];
			tmpabcde[2] = f4(tmpabcde[1], 30);
			tmpabcde[1] = tmpabcde[0];
			tmpabcde[0] = tmp;
		}
		for (int i2 = 0; i2 < tmpabcde.length; i2++) {
			digestInt[i2] = digestInt[i2] + tmpabcde[i2];
		}
		for (int n = 0; n < tmpData.length; n++) {
			tmpData[n] = 0;
		}
	}

	// 4字节数组转换为整数
	private static int byteArrayToInt(byte[] bytedata, int i) {
		return ((bytedata[i] & 0xff) << 24) | ((bytedata[i + 1] & 0xff) << 16) | ((bytedata[i + 2] & 0xff) << 8)
				| (bytedata[i + 3] & 0xff);
	}

	// 整数转换为4字节数组
	private static void intToByteArray(int intValue, byte[] byteData, int i) {
		byteData[i] = (byte) (intValue >>> 24);
		byteData[i + 1] = (byte) (intValue >>> 16);
		byteData[i + 2] = (byte) (intValue >>> 8);
		byteData[i + 3] = (byte) intValue;
	}

	// 将字节转换为十六进制字符串
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	// 将字节数组转换为十六进制字符串
	private static String byteArrayToHexString(byte[] bytearray) {
		String strDigest = "";
		for (int i = 0; i < bytearray.length; i++) {
			strDigest += byteToHexString(bytearray[i]);
		}
		return strDigest;
	}

	// 计算sha-1摘要，返回相应的字节数组
	public static byte[] getDigestOfBytes(byte[] byteData) {
		process_input_bytes(byteData);
		byte[] digest = new byte[20];
		for (int i = 0; i < digestInt.length; i++) {
			intToByteArray(digestInt[i], digest, i * 4);
		}
		return digest;
	}

	// 计算sha-1摘要，返回相应的十六进制字符串
	public static String getDigestOfString(byte[] byteData) {
		return byteArrayToHexString(getDigestOfBytes(byteData));
	}

	public static String encryptSHA1(String str) {
		return getDigestOfString(str.getBytes());
	}

}
