package com.jifan.pssmis.testbase;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class SimpleTest extends BaseTest {

	@Test
	/**
	 * 测试迭代删除
	 */
	public void testListRemove() {
		List list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		assertEquals(10, list.size());
		Iterator ite = list.iterator();
		while (ite.hasNext()) {
			ite.next();
			ite.remove();
		}
		assertEquals(0, list.size());
		
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(date.toLocaleString());
		DateFormat df = new SimpleDateFormat("yyMMddhhmmssSSS");
		String str = df.format(date);
		System.out.println(str);
		
		System.out.println(date.toString());
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.CHINA);
		String strDate = formatter.format(date) ;
		System.out.println(strDate);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		
		System.out.println(strtodate.toString());
		
		String x = "/pages/bcmweb/xhtml/Register.xhtml";
		int i = x.lastIndexOf('/');
		StringBuilder sb=new StringBuilder();
		sb.append(x).insert( i,"/aa");
		
		System.out.println(sb);
		
		String act_time = "2011-12-04 08:41:04.0".substring(0, 19);
		
		
		System.out.println(act_time.length());

	}
}
