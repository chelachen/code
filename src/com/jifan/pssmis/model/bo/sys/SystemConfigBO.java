package com.jifan.pssmis.model.bo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_SYSTEM_CONFIG")
public class SystemConfigBO  extends AuditableBO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7690073741809805466L;
	
	@Column(name = "CONFIG_KEY", length = 50, nullable = false)
	private String configKey;//配置键
	
	@Column(name = "CONFIG_VALUE", columnDefinition="VARCHAR(1000)", nullable = true)
	private String configValue;//配置值
	
	@Column(name = "MODULE_CODE", length = 20, nullable = true)
	private String moduleCode;//所属模块
	
	@Column(name = "MEMO",  columnDefinition="VARCHAR(1000)", nullable = true)
	private String memo;//说明
	
	public SystemConfigBO(){
	}

	public SystemConfigBO(String configKey,String configValue,String moduleCode,String memo){
		this.configKey=configKey;
		this.configValue=configValue;
		this.moduleCode=moduleCode;
		this.memo=memo;
	}
	
	
	
	public SystemConfigBO(String configKey, String configValue, String moduleCode, String memo, String bgID) {		
		this.configKey = configKey;
		this.configValue = configValue;
		this.moduleCode = moduleCode;
		this.memo = memo;
	}

	/**
	 * 从参数对象中复制信息
	 * @param systemConfigBO
	 */
	public void copyFrom(SystemConfigBO systemConfigBO){
		this.configValue = systemConfigBO.configValue;
		this.moduleCode=systemConfigBO.moduleCode;
		this.memo = systemConfigBO.memo;
		this.updateUser=systemConfigBO.updateUser;
		this.updateTime=systemConfigBO.updateTime;
	}
	
	public SystemConfigBO copySystemConfigBO(String bgID) {
		SystemConfigBO systemConfigBO = new SystemConfigBO(this.configKey,
				this.configValue, this.moduleCode,this.memo,bgID);
		return systemConfigBO;
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


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


}
