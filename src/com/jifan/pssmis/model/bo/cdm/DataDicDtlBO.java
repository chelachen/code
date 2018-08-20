package com.jifan.pssmis.model.bo.cdm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_DATA_DIC_DTL")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region =
// "cdm_data_dic_dtl_entity")
public class DataDicDtlBO extends AuditableBO implements Comparable {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "DATA_CLASS_CODE", length = 50, nullable = false)
	private String dataClassCode; // 数据类别代码

	@Column(name = "PARENT_DATA_CODE")
	private Integer parentDataCode; // 父商代码

	@Column(name = "DATA_CODE", nullable = false)
	private Integer dataCode; // 属性代码

	@Column(name = "COR_DATA_CODE", length = 50)
	private String corDataCode; // 属性公司编码

	@Column(name = "DATA_NAME", length = 50, nullable = false)
	private String dataName; // 属性名称

	@Column(name = "SEQUENCE_NO", length = 20, nullable = false)
	private int sequenceNo; // 序号

	@Column(name = "MEMO", length = 500)
	private String memo; // 备注


	@Column(name = "DATA_LEVEL", nullable = false)
	private int dataLevel; // 当前层级
	
    @ManyToOne
	@JoinColumn(name = "DATA_DIC_ID", nullable = false)
	private DataDicBO dataDicBO;
	
	@Transient
	private boolean choiced;


	public DataDicDtlBO(){
	}

	
	public void initData(Integer dataCode,Integer parentDataCode,String dataName,DataDicBO dataDicBO){
		this.dataCode=dataCode;
		this.corDataCode=dataCode.toString();
		this.sequenceNo=dataCode;
		this.parentDataCode=parentDataCode;
		this.dataDicBO=dataDicBO;
		this.dataName=dataName;
		this.dataClassCode=dataDicBO.getDataClassCode();
	}

	public DataDicDtlBO(String dataClassCode, Integer parentDataCode, Integer dataCode, String corDataCode,
			String dataName, int sequenceNo, String memo) {
		this.dataClassCode = dataClassCode;
		this.parentDataCode = parentDataCode;
		this.dataCode = dataCode;
		this.corDataCode = corDataCode;
		this.dataName = dataName;
		this.sequenceNo = sequenceNo;
		this.memo = memo;

	}

	public DataDicDtlBO getDataDicDtlBOByParentCode(Integer parentcode, List<DataDicDtlBO> dList) {
		for (DataDicDtlBO dataDicDtlBO : dList) {
			if (parentcode.intValue() == dataDicDtlBO.getDataCode().intValue()) {
				return dataDicDtlBO;
			}
		}
		return null;
	}
	


	public String getDataClassCode() {
		return dataClassCode;
	}

	public void setDataClassCode(String dataClassCode) {
		this.dataClassCode = dataClassCode;
	}

	public Integer getParentDataCode() {
		return parentDataCode;
	}

	public void setParentDataCode(Integer parentDataCode) {
		this.parentDataCode = parentDataCode;
	}

	public Integer getDataCode() {
		return dataCode;
	}

	public void setDataCode(Integer dataCode) {
		this.dataCode = dataCode;
	}

	public String getCorDataCode() {
		return corDataCode;
	}

	public void setCorDataCode(String corDataCode) {
		this.corDataCode = corDataCode;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
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



	public int getDataLevel() {
		return dataLevel;
	}

	public void setDataLevel(int dataLevel) {
		this.dataLevel = dataLevel;
	}



	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.hashCode() - o.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataClassCode == null) ? 0 : dataClassCode.hashCode());
		result = prime * result + dataCode;
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
		final DataDicDtlBO other = (DataDicDtlBO) obj;
		if (corDataCode == null) {
			if (other.corDataCode != null)
				return false;
		} else if (!corDataCode.equals(other.corDataCode))
			return false;
		if (dataClassCode == null) {
			if (other.dataClassCode != null)
				return false;
		} else if (!dataClassCode.equals(other.dataClassCode))
			return false;
		if (dataCode == null) {
			if (other.dataCode != null)
				return false;
		} else if (!dataCode.equals(other.dataCode))
			return false;
		if (dataLevel != other.dataLevel)
			return false;
		if (dataName == null) {
			if (other.dataName != null)
				return false;
		} else if (!dataName.equals(other.dataName))
			return false;
		if (sequenceNo != other.sequenceNo)
			return false;
		return true;
	}


	public boolean isChoiced() {
		return choiced;
	}


	public void setChoiced(boolean choiced) {
		this.choiced = choiced;
	}


	public DataDicBO getDataDicBO() {
		return dataDicBO;
	}


	public void setDataDicBO(DataDicBO dataDicBO) {
		this.dataDicBO = dataDicBO;
	}



}
