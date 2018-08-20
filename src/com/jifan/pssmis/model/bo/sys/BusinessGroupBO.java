package com.jifan.pssmis.model.bo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_Business_Group")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_business_group_entity")
public class BusinessGroupBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "BG_CODE", length = 20, nullable = false)
	private String bgCode; // 商业组代码

	@Column(name = "BG_NAME", length = 50, nullable = false)
	private String bgName; // 商业组名称
	
	@Column(name = "LOGO_IMG", length = 50)
	private String logoImg; //logo图片名称
	
	@Column(name = "MEMO", columnDefinition="VARCHAR(1000)" )
	private String memo;  //描述
	
	@Column(name = "ALIAS_NAME", length = 50, nullable = true)
	private String aliasName; // 商业组别名
	
	@Column(name = "ENG_NAME", length = 50, nullable = true)
	private String engName; // 英文名称
	
    
    @Column(name = "LINKMAN" , length=100)
    private String linkman; //联系人
    
    @Column(name = "MOBILE" , length=100)
    private String mobile; //联系电话
    
    @Column(name = "ADDRESS" , length=100)
    private String address; //地址
    
    @Column(name = "FAX" , length=100)
    private String fax; //传真
    @Column(name = "EMAIL" , length=100)
    private String email; //电  邮
	
	public BusinessGroupBO() {
	}

	public BusinessGroupBO(String bgCode, String bgName) {
		this.bgCode = bgCode;
		this.bgName = bgName;
	}
	
	public void addBusinessGroupBO(String bgCode, String bgName,String memo){
		this.bgCode = bgCode;
		this.bgName = bgName;
		this.memo = memo;
	}
	

	public String getBgCode() {
		return bgCode;
	}

	public void setBgCode(String bgCode) {
		this.bgCode = bgCode;
	}

	public String getBgName() {
		return bgName;
	}

	public void setBgName(String bgName) {
		this.bgName = bgName;
	}


	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
