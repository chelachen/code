package com.jifan.pssmis.foundation.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;

/**
 * 
 * Collection Helper
 * @author getdown
 *
 */
public class CollectionHelper {
	
	/**
	 * 对集合进行排序(根据集合里面的对象的单个属性)
	 * @param list 需要排序的集合
	 * @param property 需要对集合里面排序的属性名
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list, String property) {
		Comparator cmp = ComparableComparator.getInstance();
		//允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);
		
		Collections.sort(list, new BeanComparator(property,cmp));
	}
	
	/**
	 * 对集合进行排序(根据集合里面的对象的单个属性，升降序)
	 * @param list 需要排序的集合
	 * @param property 需要对集合里面排序的属性名
	 * @param polarity 降序true，升序false
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list, String property, boolean polarity) {
		Comparator cmp = ComparableComparator.getInstance();
		//允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		if (polarity) {
			Collections.sort(list, new ReverseComparator(new BeanComparator(property, cmp)));
		} else {			
			Collections.sort(list, new BeanComparator(property, cmp));
		}
	}
	
	/**
	 * 对集合进行排序(根据集合里面的值，升降序)
	 * @param list 需要排序的集合
	 * @param polarity 降序true，升序false
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list, boolean polarity) {
		Comparator cmp = ComparableComparator.getInstance();
		//允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		if (polarity) {
			//取反
			Collections.sort(list, new ReverseComparator(cmp));
		} else {			
			Collections.sort(list, cmp);
		}
	}
	
	/**
	 * 对集合进行排序（根据集合里面的对象的多个属性）
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param properties
	 *            需要对集合里面排序的对象的属性的集合， 定义成ArrayList，ArrayList里面的顺序就是排序的先后顺序
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list, List<String> properties) {
		
		Comparator cmp = ComparableComparator.getInstance();
		//允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);
		
		ArrayList<BeanComparator> sortFields = new ArrayList<BeanComparator>();
		
		for (String property : properties) {
			sortFields.add(new BeanComparator(property, cmp));
		}
		
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}
	
	/**
	 * 对集合进行排序（根据集合里面的对象的多个属性,升降序）
	 * 
	 * @param list
	 *            需要排序的集合
	 * @param map
	 *            需要对集合里面排序的对象的属性的集合，定义成LinkedHashMap里面的顺序就是排序的先后顺序,降序true，升序false
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list, Map<String, Boolean> map) {

		Comparator cmp = ComparableComparator.getInstance();
		// 允许值为null
		cmp = ComparatorUtils.nullLowComparator(cmp);

		ArrayList sortFields = new ArrayList();
		for (String property : map.keySet()) {
			if (map.get(property)) {
				sortFields.add(new ReverseComparator(new BeanComparator(
						property, cmp)));
			} else {
				sortFields.add(new BeanComparator(property, cmp));
			}
		}
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}
	
	/**
	 * 对集合进行排序（根据集合里面的对象的多个属性）
	 * @param objects 需要排序的集合
	 * @param params 对象的属性，优先级高的属性放在前
	 */
	public static void sortList(List objects, String[] params) {
		List<String> properties = new ArrayList<String>();
		for (int i=0; i<params.length; i++ ){
			properties.add(params[i]);
		}
		sortList(objects, properties);
	}
	
	/**
	 * 将一个Set集合转化成List集合
	 */
	@SuppressWarnings("unchecked")
	public static List toList(Set objects) {
		List result = new ArrayList();
		for (Object object : objects) {
			result.add(object);
		}
		return result;
	}
	
	/**
	 * 将数组转化成List集合
	 */
	@SuppressWarnings("unchecked")
	public static List toList(Object[] objects) {
		List result = new ArrayList();
		for (Object object : objects) {
			result.add(object);
		}
		return result;
	}

	/**
	 * 将一个List集合转化成Set集合
	 */
	@SuppressWarnings("unchecked")
	public static Set toSet(List objects) {
		Set result = new HashSet();
		for (Object object : objects) {
			result.add(object);
		}
		return result;
	}
	
	/**
	 * 将数组转化成Set集合
	 */
	@SuppressWarnings("unchecked")
	public static Set toSet(Object[] objects) {
		Set result = new HashSet();
		for (Object object : objects) {
			result.add(object);
		}
		return result;
	}

}
