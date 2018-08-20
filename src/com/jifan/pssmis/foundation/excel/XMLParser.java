package com.jifan.pssmis.foundation.excel;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.message.SysMessage;

/**
 * xml转换处理类
 * @author jifan
 *
 */
public class XMLParser {
	private static Log log = LogFactory.getLog(XMLParser.class);
	private static final HashMap NODES_MAP = new HashMap(); // 文档树的所有节点，以ID作为key
	private static final boolean reload = true; // 控制是否每次改动后都会重新加载,true代表重新加载

	/**
	 * 
	 * 功能描述：
	 * 
	 * @param file
	 * @throws DocumentException
	 * @throws BizException
	 */
	public static void load(String file) throws DocumentException, BizException {
		if (reload) {
			InputStream in = XMLParser.class.getClassLoader()
					.getResourceAsStream(file);
			if (in != null) {
				loadFile(in);
			} else {
				log.error("文件路径错误：" + file);
				throw new BizException(new SysMessage("文件路径错误：" + file));
			}
		} else {
			if (NODES_MAP.size() == 0) {
				InputStream in = XMLParser.class.getClassLoader()
						.getResourceAsStream(file);
				if (in != null) {
					loadFile(in);
				} else {
					log.error("文件路径错误：" + file);
					throw new BizException(new SysMessage("文件路径错误：" + file));
				}
			}
		}

	}

	/**
	 * 
	 * 功能描述：根据节点ID获取元素对象
	 * 
	 * @param tagId
	 * @return
	 */
	public static Element getNodeById(String tagId) {
		return (Element) NODES_MAP.get(tagId);
	}

	/**
	 * 
	 * 功能描述：根据输入流加载
	 * 
	 * @param in
	 */
	private static void loadFile(InputStream in) throws DocumentException,
			BizException {
		NODES_MAP.clear();
		SAXReader sa = new SAXReader();
		// DTD验证
		sa.setValidation(true);
		try {
			Document doc = sa.read(in);
			Element root = doc.getRootElement();
			java.util.Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element em = (Element) it.next();
				if (em.attribute("id") == null) {
					throw new BizException(new SysMessage("请为Eexel 模板："
							+ em.getName() + " 定义ID! "));
				}
				NODES_MAP.put(em.attributeValue("id"), em);
			}
		} catch (DocumentException e) {
			log.error(e);
			throw e;
		}
	}
}
