package com.jifan.pssmis.model.vo.cdm;

import java.io.Serializable;

import com.jifan.pssmis.model.vo.base.BaseVO;

public class DataDicQueryVO extends BaseVO implements Serializable {
	private String dataClassCode; // 数据类别代码

	private String dataClassName; // 数据类别名称

	private int dataCodeType; // 数据代码编码规则

	private String isDeleted; // 删除标记(1表示删除)

	private int sequenceNo; // 序号

	private String memo; // 备注

	private String sysNodeID;

	public DataDicQueryVO(String dataClassCode, String sysNodeID) {
		super();
		this.dataClassCode = dataClassCode;
		this.sysNodeID = sysNodeID;
	}
	
	

	public DataDicQueryVO() {
		this.dataCodeType = -1;
	}

	public String getDataClassCode() {
		return dataClassCode;
	}

	public void setDataClassCode(String dataClassCode) {
		this.dataClassCode = dataClassCode;
	}

	public String getDataClassName() {
		return dataClassName;
	}

	public void setDataClassName(String dataClassName) {
		this.dataClassName = dataClassName;
	}

	public int getDataCodeType() {
		return dataCodeType;
	}

	public void setDataCodeType(int dataCodeType) {
		this.dataCodeType = dataCodeType;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
	}

}
