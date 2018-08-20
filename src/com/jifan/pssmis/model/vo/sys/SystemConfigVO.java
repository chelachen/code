package com.jifan.pssmis.model.vo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.vo.base.BaseVO;

public class SystemConfigVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4196151838562415844L;

	private String configKey;// 配置键

	private String configValue;// 配置值

	private String moduleCode;// 所属模块
	
	private String bgID;  //商业组ID

	public SystemConfigVO() {

	}

	public SystemConfigVO(String confKey,String bgID) {
		this.configKey = confKey;
		this.bgID=bgID;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getBgID() {
		return bgID;
	}

	public void setBgID(String bgID) {
		this.bgID = bgID;
	}

}
