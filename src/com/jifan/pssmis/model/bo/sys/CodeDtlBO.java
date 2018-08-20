package com.jifan.pssmis.model.bo.sys;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_CODE_DTL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_code_dtl_entity")
public class CodeDtlBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "FIELD_NAME", length = 255)
	private String fieldName; // 字段名

	@Column(name = "SQL_NAME", length = 255)
	private String sqlName; // 数据库字段名

	@Column(name = "FIELD_TYPE", length = 255)
	private String fieldType; // 类型

	@Column(name = "CODE_ID", length = 36)
	private String codeID; // 类ID

	@Column(name = "NUM", length = 11)
	private int num; // 序号

	@Column(name = "LENGTH", length = 11)
	private String length; // 长度

	@Column(name = "CAN_NULL", length = 11)
	private String canNull; // 是否可空

	@Column(name = "DEFAULT_VALUE", length = 36)
	private String defaultValue; // 默认值

	@Column(name = "MEMO", length = 36)
	private String memo; // 说明

	public CodeDtlBO() {
	}

	public CodeDtlBO(String codeID, String fieldName, String sqlName,
			String fieldType, int num, String length, String canNull,
			String defaultValue) {
		this.fieldName = fieldName;
		this.sqlName = sqlName;
		this.fieldType = fieldType;
		this.codeID = codeID;
		this.num = num;
		this.length = length;
		this.canNull = canNull;
		this.defaultValue = defaultValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldNameLow() {
		String strb = fieldName.substring(0,1);
		String stre = fieldName.substring(1,fieldName.length());
		return strb.toLowerCase()+stre;
	}
	
	public void changeFildNameToSqlName(){
		if(CommonUtil.isEmpty(this.sqlName)){
			String newStr="";
			int i = 0;  
	        while(i < this.fieldName.length()){  
	            char chr = this.fieldName.charAt(i);  
	            //如果字符是大写且不是首字母
	            if(Character.isUpperCase(chr) && i !=0){  
	            	newStr=newStr+"_"+chr;
	            }else
	            	newStr=newStr+chr;
	            i++;
	        }
	        this.sqlName= newStr.toUpperCase();
		}
	    
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getCodeID() {
		return codeID;
	}

	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCanNull() {
		return canNull;
	}

	public void setCanNull(String canNull) {
		this.canNull = canNull;
	}

}
