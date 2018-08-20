package com.jifan.pssmis.model.bo.cdm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_DATA_DIC")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region =
// "cdm_data_dic_entity")
public class DataDicBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "DATA_CLASS_CODE", length = 50, nullable = false)
	private String dataClassCode; // 数据类别代码

	@Column(name = "DATA_CLASS_NAME", length = 50, nullable = false)
	private String dataClassName; // 数据类别名称

	@Column(name = "DATA_CODE_TYPE", length = 0, nullable = false)
	private int dataCodeType; // 数据代码编码规则

	@Column(name = "MEMO", length = 200)
	private String memo; // 备注

	@Transient
	private List<DataDicDtlBO> datadtlList = new ArrayList<DataDicDtlBO>();// 子表

	/**
	 * 保存之前初始化一些必要的數據
	 * 
	 * @param user
	 */
	public void beforeSaveDatadic(String user) {
		for (DataDicDtlBO dataDicDtlBO : this.getDatadtlList()) {
			dataDicDtlBO.setUpdateTime(new Date());
			dataDicDtlBO.setUpdateUser(user);
			dataDicDtlBO.setDataClassCode(this.getDataClassCode());
			if (dataDicDtlBO.getId() == null) {
				dataDicDtlBO.setCreateUser(user);
			}
		}
	}

	/**
	 * 增加子表记录
	 */
	public void addDatadicdtl() {
		DataDicDtlBO dataDicDtlBO = new DataDicDtlBO();
		dataDicDtlBO.setDataDicBO(this);
		dataDicDtlBO.setDataClassCode(this.getDataClassCode());
		this.datadtlList.add(dataDicDtlBO);
	}

	/**
	 * 移除子表记录
	 * 
	 * @param dataDicDtlBO
	 */
	public void removeDatadicdtl(DataDicDtlBO dataDicDtlBO) {
		this.datadtlList.remove(dataDicDtlBO);
	}

	public DataDicBO() {
		this.dataCodeType = -1;
	}

	public String getDataCodeTypeString() {
		if (dataCodeType == 0) {
			return "传统父子";
		}
		if (dataCodeType == 1) {
			return "十层";
		}
		if (dataCodeType == 2) {
			return "五层";
		}
		if (dataCodeType == 3) {
			return "四层";
		}
		if (dataCodeType == 4) {
			return "三层";
		}
		if (dataCodeType == 10) {
			return "枚举";
		}
		return null;

	}

	public boolean isEnum() {
		if (dataCodeType == 10) {
			return true;
		} else
			return false;
	}

	public boolean isTree() {
		if (this.isTraditionTree())
			return true;
		if (this.isLeaveTree())
			return true;
		return false;
	}

	public boolean isTraditionTree() {
		if (dataCodeType == 0) {
			return true;
		} else
			return false;
	}

	public boolean isLeaveTree() {
		if (dataCodeType == 1) {
			return true;
		}
		if (dataCodeType == 2) {
			return true;
		}
		if (dataCodeType == 3) {
			return true;
		}
		if (dataCodeType == 4) {
			return true;
		}
		return false;
	}

	public DataDicBO(String dataClassCode, String dataClassName, int dataCodeType, int isDeleted, int sequenceNo,
			String memo, String refID, String refNodeID) {
		this.dataClassCode = dataClassCode;
		this.dataClassName = dataClassName;
		this.dataCodeType = dataCodeType;
		this.memo = memo;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<DataDicDtlBO> getDatadtlList() {

		return datadtlList;
	}

	public void setDatadtlList(List<DataDicDtlBO> datadtlList) {
		this.datadtlList = datadtlList;
	}

}
