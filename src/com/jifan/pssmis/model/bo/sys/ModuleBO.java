package com.jifan.pssmis.model.bo.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@SuppressWarnings("unchecked")
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_MODULE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_module_entity")
public class ModuleBO extends AuditableBO implements Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5648849429831583647L;

	@Column(name = "MODULE_CODE", length = 20, nullable = false)
	private String moduleCode;

	@Column(name = "MODULE_NAME", length = 20, nullable = false)
	private String moduleName;

	@Column(name = "MODULE_SRC", length = 100, nullable = false)
	private String moduleSrc;

	@Column(name = "MODULE_NO", nullable = false)
	private Integer moduleNo = 0;

	@Column(name = "ICON_URL", length = 100, nullable = true)
	private String iconUrl;

	@Transient
	private String iconUrlBig;// 大图标地址

	@Transient
	private String iconUrlMin;// 小图标地址

	@OneToMany(mappedBy = "moduleBO", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<ModuleFunctionBO> moduleFunctions = new ArrayList<ModuleFunctionBO>();

	public ModuleBO CopyModuleBO(String sysNodeID) {
		ModuleBO moduleBO = new ModuleBO(this.moduleCode, this.moduleName, this.moduleSrc, this.moduleNo);
		moduleBO.createTime = new Date();
		moduleBO.updateTime = new Date();
		moduleBO.iconUrl = this.iconUrl;
		for (ModuleFunctionBO bo : this.moduleFunctions) {
			moduleBO.getModuleFunctions().add(bo.CopyModuleFunctionBO(moduleBO));
		}
		return moduleBO;
	}

	public ModuleBO CopyModuleBOWithoutDtl(String sysNodeID) {
		ModuleBO moduleBO = new ModuleBO(this.moduleCode, this.moduleName, this.moduleSrc, this.moduleNo);
		moduleBO.createTime = new Date();
		moduleBO.updateTime = new Date();
		moduleBO.iconUrl = this.iconUrl;
		return moduleBO;
	}

	public ModuleBO UpdateModuleBO(ModuleBO moduleBO) {
		this.moduleCode = moduleBO.moduleCode;
		this.moduleName = moduleBO.moduleName;
		this.moduleSrc = moduleBO.moduleSrc;
		this.moduleNo = moduleBO.moduleNo;
		this.iconUrl = moduleBO.iconUrl;
		this.updateTime = new Date();
		this.moduleFunctions.clear();
		for (ModuleFunctionBO bo : moduleBO.moduleFunctions) {
			this.moduleFunctions.add(bo.CopyModuleFunctionBO(this));
		}
		return moduleBO;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleSrc() {
		return moduleSrc;
	}

	public void setModuleSrc(String moduleSrc) {
		this.moduleSrc = moduleSrc;
	}

	public ModuleBO() {
		super();
	}

	public ModuleBO(String moduleCode, String moduleName, String moduleSrc, Integer moduleNo) {
		super();
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.moduleSrc = moduleSrc;
		this.moduleNo = moduleNo;
	}

	/*
	 * 添加功能点到该模块 chencl 2012-02-20
	 */
	public void addModuleFunctions() {
		ModuleFunctionBO moduleFunctionBOnew = new ModuleFunctionBO();
		moduleFunctionBOnew.setModuleBO(this);
		moduleFunctionBOnew.setFunctionType(0);
		this.getModuleFunctions().add(moduleFunctionBOnew);
	}

	public Integer getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
	}

	public List<ModuleFunctionBO> getModuleFunctions() {
		return moduleFunctions;
	}

	public void setModuleFunctions(List<ModuleFunctionBO> moduleFunctions) {
		this.moduleFunctions = moduleFunctions;
	}

	@Override
	public int compareTo(Object o) {
		return this.hashCode() - o.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moduleNo == null) ? 0 : moduleNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ModuleBO other = (ModuleBO) obj;
		if (moduleNo == null) {
			if (other.moduleNo != null)
				return false;
		} else if (!moduleNo.equals(other.moduleNo))
			return false;
		return true;
	}

	public String getIconUrl() {
		if (this.iconUrl == null || this.iconUrl.equals(""))
			iconUrl = "sys.png";
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconUrlBig() {
		iconUrlBig = "images:funcIcon/" + this.getIconUrl();
		return iconUrlBig;
	}

	public void setIconUrlBig(String iconUrlBig) {
		this.iconUrlBig = iconUrlBig;
	}

	public String getIconUrlMin() {
		iconUrlMin = "images:funcIconMin/" + this.getIconUrl();
		return iconUrlMin;
	}

	public void setIconUrlMin(String iconUrlMin) {
		this.iconUrlMin = iconUrlMin;
	}

}
