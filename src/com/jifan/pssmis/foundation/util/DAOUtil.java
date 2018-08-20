package com.jifan.pssmis.foundation.util;

import java.io.Serializable;
import java.util.List;

/**
 * DAO处理工具类
 * @author jifan
 *
 */
public class DAOUtil {
	public static Object[] getObjectsByList(List list) {
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null)
				objects[i] = "";
			else
				objects[i] = list.get(i);
		}
		return objects;
	}
	
}
