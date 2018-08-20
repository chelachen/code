package com.jifan.pssmis.foundation.util;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author jifan
 *
 */
public class WebUtil {

	public static final int StreamFlushBufferSzie = 100;
	static Class class$com$jifan$pssmis$foundation$util$WebUtil; /*
																 * synthetic
																 * field
																 */

	public WebUtil() {
	}

	public static ByteArrayOutputStream castToBAOStream(Object obj,
			int bufferSize) {
		Log logger;
		ByteArrayOutputStream baos;
		FileInputStream fis;
//		logger = LogFactory
//				.getLog(class$cn$ccb$foundation$web$common$WebUtil != null ? class$cn$ccb$foundation$web$common$WebUtil
//						: (class$cn$ccb$foundation$web$common$WebUtil = class$("cn.ccb.foundation.web.common.WebUtil")));
		baos = new ByteArrayOutputStream();
		if (obj.getClass().isInstance(baos))
			return (ByteArrayOutputStream) obj;
		fis = (FileInputStream) obj;
		BufferedInputStream bis = new BufferedInputStream(fis);
		baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		int i = 0;
		try {
			do {
				int ch;
				if ((ch = bis.read()) == -1)
					break;
				bos.write(ch);
				if (i++ == bufferSize) {
					bos.flush();
					i = 0;
				}
			} while (true);

			bos.flush();
			bis.close();
			return baos;
		} catch (IOException e) {
			// ClassCastException e;
			// e;
			// logger.info("Stream object not a ByteArrayOutputStream or a
			// FileInputStream:" + e.getCause() + e.getMessage());
			// return null;
			// e;
			// logger.info("baos is null:" + e.getCause() + e.getMessage());
			return null;
		}
	}

	public static ByteArrayOutputStream castToBAOStream(Object obj) {
		return castToBAOStream(obj, 100);
	}

	public static void pushMember(Object obj, String targetPath, Object value) {
		ArrayList path = createPath(targetPath);
		Object fatherMember = getFatherMember(obj, path);
		setTargetMember(fatherMember, path, value);
	}

	private static Object getFatherMember(Object obj, ArrayList path) {
		Class nul[] = new Class[0];
		Object tempObj = obj;
		try {
			for (int i = 0; i < path.size() - 1; i++)
				tempObj = tempObj.getClass().getMethod(
						parseToGetMethod((String) path.get(i)), nul).invoke(
						tempObj, nul);

		} catch (Exception e) {
			throw new RuntimeException(
					"the data table property:replace fileld path not exit.");
		}
		return tempObj;
	}

	private static String getTargetMember(Object father, ArrayList path) {
		Class nul[] = new Class[0];
		try {
			return (String) father.getClass().getMethod(
					parseToGetMethod((String) path.get(path.size() - 1)), nul)
					.invoke(father, nul);
		} catch (Exception e) {
			throw new RuntimeException(
					"the data table property:replace fileld path not exit or member is not a String type.");
		}

	}

	private static void setTargetMember(Object father, ArrayList path,
			Object newValue) {
		Class targetClass = null;
		String field = (String) path.get(path.size() - 1);
		targetClass = newValue.getClass();
		Class paramsformat[] = new Class[1];
		paramsformat[0] = targetClass;
		Object params[] = new Object[1];
		params[0] = newValue;
		try {
			father.getClass().getMethod(parseToSetMethod(field), paramsformat)
					.invoke(father, params);
		} catch (Exception e) {
			throw new RuntimeException(
					"replace fileld path not exit. or the new value not fit to the decleared member.");
		}
	}

	private static String parseToGetMethod(String memberName) {
		return "get" + memberName.substring(0, 1).toUpperCase()
				+ memberName.substring(1);
	}

	private static String parseToSetMethod(String memberName) {
		return "set" + memberName.substring(0, 1).toUpperCase()
				+ memberName.substring(1);
	}

	private static ArrayList createPath(String paths) {
		int ASCII_DOT = 46;
		int start = 0;
		int stop = 0;
		int length = paths.length();
		String temp = null;
		ArrayList path = new ArrayList();
		do {
			if (start > length - 1)
				break;
			temp = null;
			start = paths.indexOf(46, stop);
			if (start != -1)
				temp = paths.substring(stop, start);
			else
				temp = paths.substring(stop);
			stop = start + 1;
			path.add(temp);
		} while (start != -1);
		return path;
	}

	static Class class$(String x0) {
		try {
			return Class.forName(x0);
		} catch (ClassNotFoundException e) {
			throw new NoClassDefFoundError(e.getMessage());
		}

	}
}
