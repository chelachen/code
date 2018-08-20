package com.jifan.pssmis.foundation.excel;

/**
 * 报表模板类
 * @author jifan
 *
 */
public class ReportTemplate {
	private String config = "/CBMS_TAB01.xml";		//模板文件
	private String templateId;		//模板ID
	
	//构造器
	public ReportTemplate(String templateId) {
		this.templateId = templateId;
	}
	//构造器
	public ReportTemplate(String config,String templateId) {
		this.config = config;
		this.templateId = templateId;
	}
	/**
	 * @return Returns the config.
	 */
	public String getConfig() {
		return config;
	}
	/**
	 * @param config The config to set.
	 */
	public void setConfig(String config) {
		this.config = config;
	}
	/**
	 * @return Returns the templateId.
	 */
	public String getTemplateId() {
		return templateId;
	}
	/**
	 * @param templateId The templateId to set.
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
}
