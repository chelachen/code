package com.jifan.pssmis.model.vo.sys;

import com.jifan.pssmis.model.vo.base.BaseVO;

public class ModuleFunctionVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2637444332720593182L;

	private String functionCode;
	
	private String functionName;
	
	private String functionBean;
	
	private String sysNodeID;

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public ModuleFunctionVO() {
		super();
	}

	public ModuleFunctionVO(String functionCode, String functionName) {
		super();
		this.functionCode = functionCode;
		this.functionName = functionName;
	}

	public String getFunctionBean() {
		return functionBean;
	}

	public void setFunctionBean(String functionBean) {
		this.functionBean = functionBean;
	}

	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
	}
	
}
