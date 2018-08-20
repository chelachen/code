package com.jifan.pssmis.foundation.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jifan.pssmis.bs.cdm.IDataDicBS;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.datadic.DataDicDtlTreeNode;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;

@Component
public class ClassifyCodingUtil {

	private static IDataDicDtlBS dataDicDtlBS;
	private static IDataDicBS dataDicBS ;

	public final static Map<Integer, Map<Integer, Map<String, Integer>>> levelCodingInfo = new HashMap<Integer, Map<Integer, Map<String, Integer>>>();
	public final static String TOLERANCE = "TOLERANCE";
	public final static String MARK = "MARK";
	public final static String BITLEN = "BITLEN";
	


	static {

		Integer[] bitLenArray = new Integer[] { 127, 256, 256, 256 };
		Integer[] toleranceArray = new Integer[] { 16777216, 65536, 256, 1 };
		Integer[] markArray = new Integer[] { 2130706432, 2147418112, 2147483392, 2147483647 };
		Map<Integer, Map<String, Integer>> level = new HashMap<Integer, Map<String, Integer>>();
		Map<String, Integer> levelMap = null;


		bitLenArray = new Integer[] { 2,10,10,10,10,10,10,10,10,10 };
		toleranceArray = new Integer[] { 1000000000,100000000,10000000,1000000,100000,10000,1000,100,10,1};
		level = new HashMap<Integer, Map<String, Integer>>();
		
		for (int i = 0; i < 10; i++) {
			levelMap = new HashMap<String, Integer>();
			levelMap.put("TOLERANCE", toleranceArray[i]);// 公差
			levelMap.put("BITLEN", bitLenArray[i]);
			level.put(i + 1, levelMap);
		}
		
		levelCodingInfo.put(1, level);
		
		bitLenArray = new Integer[] { 21, 100, 100, 100, 100 };
		toleranceArray = new Integer[] { 100000000, 1000000, 10000, 100, 1 };
		level = new HashMap<Integer, Map<String, Integer>>();

		for (int i = 0; i < 5; i++) {
			levelMap = new HashMap<String, Integer>();
			levelMap.put("TOLERANCE", toleranceArray[i]);// 公差
			levelMap.put("BITLEN", bitLenArray[i]);
			level.put(i + 1, levelMap);
		}
		levelCodingInfo.put(2, level);
		
		bitLenArray = new Integer[] { 2, 100, 1000, 1000};
		toleranceArray = new Integer[] { 1000000000, 1000000, 1000,1};
		level = new HashMap<Integer, Map<String, Integer>>();

		for (int i = 0; i < 4; i++) {
			levelMap = new HashMap<String, Integer>();
			levelMap.put("TOLERANCE", toleranceArray[i]);// 公差
			levelMap.put("BITLEN", bitLenArray[i]);
			level.put(i + 1, levelMap);
		}
		levelCodingInfo.put(3, level);
		
		bitLenArray = new Integer[] { 21, 10000, 10000};
		toleranceArray = new Integer[] { 100000000,10000,1};
		level = new HashMap<Integer, Map<String, Integer>>();
		
		for (int i = 0; i < 3; i++) {
			levelMap = new HashMap<String, Integer>();
			levelMap.put("TOLERANCE", toleranceArray[i]);// 公差
			levelMap.put("BITLEN", bitLenArray[i]);
			level.put(i + 1, levelMap);
		}
		levelCodingInfo.put(4, level);
	}

	/**
	 * 取分类编码
	 * 
	 * @param parentCode
	 * @param codeType
	 * @return
	 */
	public static Integer getCode(DataDicDtlBO parentDtlBO) {
		Integer result = null;
		if (parentDtlBO == null) {
			return 0;
		}
		DataDicBO dataDicBO=parentDtlBO.getDataDicBO();
		for (int i = 0; i < levelCodingInfo.get(dataDicBO.getDataCodeType()).get(
				parentDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.BITLEN); i++) {
			result = levelCodingInfo.get(dataDicBO.getDataCodeType()).get(
					parentDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.TOLERANCE)
					* (i + 1) + parentDtlBO.getDataCode();
			DataDicDtlBO temp = dataDicDtlBS.getDataDicDtlByDataCode(parentDtlBO.getDataClassCode(), result);
			if (temp == null) {
				break;
			}
		}

		return result;
	}
	
	public static  Integer getLeavel(Integer dateCodeType) {
		return levelCodingInfo.get(dateCodeType).size();
	}

	/**
	 * 取某一层分类的最大范围
	 * 
	 * @param dateCodeType
	 * @param level
	 * @return
	 */
	public static Integer getRange(Integer dateCodeType, Integer level) {
		Integer tolerance = 0;
		Integer bitlen = 0;
		Map<Integer, Map<String, Integer>> map = levelCodingInfo.get(dateCodeType);
		if (map != null) {
			Map<String, Integer> map2 = map.get(level);
			if (map2 != null) {
				tolerance = map2.get(ClassifyCodingUtil.TOLERANCE);
				bitlen = map2.get(ClassifyCodingUtil.BITLEN);
			}
		}
		return tolerance * bitlen;
	}

	/**
	 * O(N) 算法构造树
	 * 
	 * @param dataDicDtlBO
	 *            树根
	 * @return
	 */
	public static DataDicDtlTreeNode createTree(String dataClassCode, Integer dataCode, String sysNodeId) {
		if (null == dataClassCode) {
			return null;
		}
		if (null == dataCode) {
			dataCode = 0;
		}

		DataDicDtlQueryVO dicQueryVO = new DataDicDtlQueryVO();
		dicQueryVO.setDataClassCode(dataClassCode);
		dicQueryVO.setSysNodeID(sysNodeId);
		dicQueryVO.setDataCode(dataCode);
		List<DataDicDtlBO> list = dataDicDtlBS.getSubDataDicDtlBOList(dicQueryVO);
		DataDicDtlTreeNode rootTreeNode = null;
		DataDicDtlTreeNode lastTreeNode = null;
		DataDicDtlTreeNode currentTreeNode = null;
		DataDicDtlTreeNode parentTreeNode = null;
		Integer last_level = 0;
		if (null != list) {
			for (DataDicDtlBO dataDicDtlBO2 : list) {
				currentTreeNode = new DataDicDtlTreeNode();
				currentTreeNode.setNodeName(dataDicDtlBO2.getDataName());
				currentTreeNode.setDataDicDtlBO(dataDicDtlBO2);
				currentTreeNode.setDataCode(dataDicDtlBO2.getDataCode());
				currentTreeNode.setDataDicDtlTreeNode(null);
				DataDicBO dataDicBO=dataDicDtlBO2.getDataDicBO();
				if (dataDicDtlBO2.getDataCode() >= 0
						&& dataDicDtlBO2.getDataCode() < ClassifyCodingUtil.getRange(dataDicBO
								.getDataCodeType(), dataDicDtlBO2.getDataLevel() + 1)) {// 根结�?
					rootTreeNode = currentTreeNode;
					parentTreeNode = currentTreeNode;
				} else if (dataDicDtlBO2.getDataLevel() == last_level + 1) {// 儿子
					currentTreeNode.setDataDicDtlTreeNode(lastTreeNode);
					lastTreeNode.getChildrenList().add(currentTreeNode);
				} else if (dataDicDtlBO2.getDataLevel() == last_level) {// 兄弟
					parentTreeNode = lastTreeNode.getDataDicDtlTreeNode();
					currentTreeNode.setDataDicDtlTreeNode(parentTreeNode);
					parentTreeNode.getChildrenList().add(currentTreeNode);
				} else if (dataDicDtlBO2.getDataLevel() < last_level) {// 表示是上级节�?
					parentTreeNode = lastTreeNode;
					for (int i = 0; i <= last_level - dataDicDtlBO2.getDataLevel(); i++) {// (有可能是小一级，也有可能小很多级)
						parentTreeNode = parentTreeNode.getDataDicDtlTreeNode();
					}
					currentTreeNode.setDataDicDtlTreeNode(parentTreeNode);
					parentTreeNode.getChildrenList().add(currentTreeNode);
				} else {
					SysLogger.debug(DataDicDtlTreeNode.class, "构造树出错");
				}
				last_level = dataDicDtlBO2.getDataLevel();
				lastTreeNode = currentTreeNode;
			}
		} else {
			rootTreeNode = null;
		}
		return rootTreeNode;

	}

	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()));
		System.out.println(2212433 / 10000 * 10000);
	}



	public static IDataDicDtlBS getDataDicDtlBS() {
		return dataDicDtlBS;
	}

	@Autowired(required = true)
	public static void setDataDicDtlBS(IDataDicDtlBS dataDicDtlBS) {
		ClassifyCodingUtil.dataDicDtlBS = dataDicDtlBS;
	}

	public static IDataDicBS getDataDicBS() {
		return dataDicBS;
	}

	@Autowired(required = true)
	public static void setDataDicBS(IDataDicBS dataDicBS) {
		ClassifyCodingUtil.dataDicBS = dataDicBS;
	}
}
