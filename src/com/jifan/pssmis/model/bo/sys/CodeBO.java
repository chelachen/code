package com.jifan.pssmis.model.bo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_CODE")
public class CodeBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "CLASS_NAME", length = 255)
	private String className; // 类名
	@Column(name = "TABLE_NAME", length = 255)
	private String tableName; // 表

	@Column(name = "BAG_NAME", length = 255)
	private String bagName; // 包名

	@Column(name = "MD_NAME", length = 255)
	private String mdName; // 模块简拼

	@Column(name = "NUM", length = 11)
	private int num; // 序号

	@Column(name = "memo", length = 255)
	private String memo; // 说明

	public CodeBO() {
	}

	public CodeBO(String className, String bagName, int num, String content) {
		this.className = className;
		this.bagName = bagName;
		this.num = num;
		this.memo = content;
		this.id="";
	}

	public String getClassName() {
		return className;
	}
	
	public String getClassNameLow() {
		String strb = className.substring(0,1);
		String stre = className.substring(1,className.length());
		return strb.toLowerCase()+stre;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTableName() {
		return tableName;
	}
	
	public String changeFullTableName() {
		if(CommonUtil.isEmpty(this.tableName)){
			String newStr="";
			int i = 0;  
	        while(i < this.className.length()){  
	            char chr = this.className.charAt(i);  
	            //如果字符是大写且不是首字母
	            if(Character.isUpperCase(chr) && i !=0){  
	            	newStr=newStr+"_"+chr;
	            }else
	            	newStr=newStr+chr;
	            i++;
	        }
			this.tableName=this.getMdName().toUpperCase()+"_"+newStr.toUpperCase();
			
		}
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMdName() {
		return mdName;
	}

	public void setMdName(String mdName) {
		this.mdName = mdName;
	}

}
