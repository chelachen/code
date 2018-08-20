package com.jifan.pssmis.model.bo.sys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_MODULE_FUNCTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_module_function_entity")
public class ModuleFunctionBO extends AuditableBO  implements Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5967828760432635637L;

	
	@Column(name = "FUNCTION_CODE" ,length=100 ,nullable = false)
	private String functionCode;
	
	@Column(name = "FUNCTION_NAME" ,length=100 ,nullable = false)
	private String functionName;
	
	@Column(name = "FUNCTION_SRC" ,length=100 ,nullable = false)
	private String functionSrc;
	
	@Column(name = "FUNCTION_BEAN" ,length=100 ,nullable = false)
	private String functionBean; //功能点后台 2011-07-04
	
	@Column(name = "FUNCTION_NO" ,nullable = false)
	private Integer functionNo = 0;
	
	@Column(name = "ICON_URL" ,length=100, nullable = true)
	private String iconUrl;
	
	
	@Column(name = "FUNCTION_TYPE" )
	private Integer functionType = 0;

	@ManyToOne
	@JoinColumn(name = "MODULE_ID")
	private ModuleBO moduleBO;
	
	@Column(name = "MEMO" , columnDefinition="VARCHAR(500)")
	private String memo; //备注
	
	@Transient
	private boolean show= false;
	
	@Transient
	private String iconUrlBig;// 大图标地址

	@Transient
	private String iconUrlMin;// 小图标地址
	
	@OneToMany
	@JoinColumns(value={@JoinColumn(name="MODULE_FUNCTION_ID",referencedColumnName="ID")})
	private Set<ModuleFunctionOperateBO> moduleFunctionOperates = new HashSet<ModuleFunctionOperateBO>();

	public String getFunctionBean() {
		return functionBean;
	}

	public void setFunctionBean(String functionBean) {
		this.functionBean = functionBean;
	}

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

	public String getFunctionSrc() {
		return functionSrc;
	}

	public void setFunctionSrc(String functionSrc) {
		this.functionSrc = functionSrc;
	}

	public ModuleFunctionBO() {
		super();
	}
    public ModuleFunctionBO CopyModuleFunctionBO(ModuleBO moduleBO){
     ModuleFunctionBO moduleFunctionBO=new ModuleFunctionBO( this.moduleBO,this.functionCode,this.functionName,this.functionSrc,this.functionNo);
     moduleFunctionBO.createTime = new Date();
     moduleFunctionBO.updateTime = new Date();
     moduleFunctionBO.functionBean=this.functionBean;
     moduleFunctionBO.iconUrl=this.iconUrl;
     moduleFunctionBO.functionType = this.functionType;
     moduleFunctionBO.memo=this.memo;
     moduleFunctionBO.setModuleBO(moduleBO);
     for(ModuleFunctionOperateBO moduleFunctionOperateBO:this.getModuleFunctionOperates()){
    	 moduleFunctionBO.getModuleFunctionOperates().add(moduleFunctionOperateBO.copyModuleFunctionOperateBO(null));
     }
     return moduleFunctionBO;
    }
    public ModuleFunctionBO CopyModuleFunctionBOWithOutDtl(ModuleBO moduleBO){
        ModuleFunctionBO moduleFunctionBO=new ModuleFunctionBO( this.moduleBO,this.functionCode,this.functionName,this.functionSrc,this.functionNo);
        moduleFunctionBO.createTime = new Date();
        moduleFunctionBO.updateTime = new Date();
        moduleFunctionBO.functionBean=this.functionBean;
        moduleFunctionBO.iconUrl=this.iconUrl;
        moduleFunctionBO.functionType = this.functionType;
        moduleFunctionBO.memo=this.memo;
        moduleFunctionBO.setModuleBO(moduleBO);
        return moduleFunctionBO;
       }
    public ModuleFunctionBO UpdateModuleFunctionBO(ModuleFunctionBO moduleFunctionBO){
		this.moduleBO = moduleFunctionBO.moduleBO;
		this.functionCode = moduleFunctionBO.functionCode;
		this.functionName = moduleFunctionBO.functionName;
		this.functionSrc = moduleFunctionBO.functionSrc;
		this.functionNo = moduleFunctionBO.functionNo;
		this.iconUrl=moduleFunctionBO.iconUrl;
		this.functionType = moduleFunctionBO.functionType;
		this.memo=moduleFunctionBO.memo;
        moduleFunctionBO.updateTime = new Date();
        
        return moduleFunctionBO;
       }
	public ModuleFunctionBO( ModuleBO moduleBO, String functionCode,
			String functionName, String functionSrc,Integer functionNo) {
		super();
		this.moduleBO = moduleBO;
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.functionSrc = functionSrc;
		this.functionNo = functionNo;
	}

	public Integer getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(Integer functionNo) {
		this.functionNo = functionNo;
	}

	public ModuleBO getModuleBO() {
		return moduleBO;
	}

	public void setModuleBO(ModuleBO moduleBO) {
		this.moduleBO = moduleBO;
	}

	@Override
	public int compareTo(Object o) {
		return this.hashCode() - o.hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((functionNo == null) ? 0 : functionNo.hashCode());

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
		final ModuleFunctionBO other = (ModuleFunctionBO) obj;
		if (functionCode == null) {
			if (other.functionCode != null)
				return false;
		} else if (!functionCode.equals(other.functionCode))
			return false;
//		if (functionName == null) {
//			if (other.functionName != null)
//				return false;
//		} else if (!functionName.equals(other.functionName))
//			return false;
		if (functionNo == null) {
			if (other.functionNo != null)
				return false;
		} else if (!functionNo.equals(other.functionNo))
			return false;
		if (functionSrc == null) {
			if (other.functionSrc != null)
				return false;
		} else if (!functionSrc.equals(other.functionSrc))
			return false;
		if (moduleBO == null) {
			if (other.moduleBO != null)
				return false;
		} else if (!moduleBO.equals(other.moduleBO))
			return false;
		return true;
	}

	public String getIconUrl() {
		if (this.iconUrl == null || this.iconUrl.equals(""))
			iconUrl = this.getModuleBO().getIconUrl();
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		
		this.iconUrl = iconUrl;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
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

	public Set<ModuleFunctionOperateBO> getModuleFunctionOperates() {
		return moduleFunctionOperates;
	}

	public void setModuleFunctionOperates(
			Set<ModuleFunctionOperateBO> moduleFunctionOperates) {
		this.moduleFunctionOperates = moduleFunctionOperates;
	}

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}



}
