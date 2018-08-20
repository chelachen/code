package com.jifan.pssmis.model.vo.sys;

import com.jifan.pssmis.model.vo.base.BaseVO;

public class ModuleVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8510735261899422622L;

	private String moduleCode;
	
	private String moduleName;
	
	private String sysNodeID;


	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
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

	public ModuleVO() {
		super();
	}

	public ModuleVO(String moduleCode, String moduleName) {
		super();
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
	}
	
	
	
}
