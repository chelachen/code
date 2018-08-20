package com.jifan.pssmis.model.bo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@SuppressWarnings("unchecked")
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_MODULE_FUNCTION_OPERATE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_module_function_operate_entity")
public class ModuleFunctionOperateBO extends AuditableBO  implements Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "MODULE_FUNCTION_ID" ,length=36 ,nullable = false)
	private String moduleFunctionID	;//功能点ID
	
	@Column(name = "OPERATE_CODE" ,length=20 ,nullable = false)
 	private String operateCode;//	操作代码
	
	@Column(name = "OPERATE_NAME" ,length=20 ,nullable = false)
	private String operateName;//	操作名称
	
	@Column(name = "ICON_URL" ,length=100 ,nullable = true)
	private String iconUrl;//	图标
	@Transient
	private ModuleFunctionBO moduleFunctionBO;
	
	
	
	public ModuleFunctionOperateBO copyModuleFunctionOperateBO(ModuleFunctionBO moduleFunctionBO) {
		ModuleFunctionOperateBO tempBO=new ModuleFunctionOperateBO();
		tempBO.operateCode = operateCode;
		tempBO.operateName = operateName;
		tempBO.iconUrl = iconUrl;
		this.moduleFunctionBO= moduleFunctionBO;
		return tempBO;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.hashCode() - o.hashCode();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
	public String getOperateCode() {
		return operateCode;
	}
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getModuleFunctionID() {
		return moduleFunctionID;
	}
	public void setModuleFunctionID(String moduleFunctionID) {
		this.moduleFunctionID = moduleFunctionID;
	}
	public ModuleFunctionBO getModuleFunctionBO() {
		return moduleFunctionBO;
	}
	public void setModuleFunctionBO(ModuleFunctionBO moduleFunctionBO) {
		this.moduleFunctionBO = moduleFunctionBO;
	}

}
