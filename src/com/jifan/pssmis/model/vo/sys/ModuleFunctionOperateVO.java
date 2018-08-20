package com.jifan.pssmis.model.vo.sys;

 

 

public class ModuleFunctionOperateVO {
	private static final long serialVersionUID = 1L;
 
 	private String moduleFunctionID	;//功能点ID
	
  	private String operateCode;//	操作代码
	
 	private String operateName;//	操作名称
	
 	private String iconUrl;//	图标

	public String getModuleFunctionID() {
		return moduleFunctionID;
	}

	public void setModuleFunctionID(String moduleFunctionID) {
		this.moduleFunctionID = moduleFunctionID;
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
 	
}
