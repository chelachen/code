package com.jifan.pssmis.model.bo.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_USER_GROUP_RIGHTS")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_user_group_rights_entity")
public class UserGroupRights{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7343634613781038701L;
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid") 
	@Column(name = "ID" , length=50)
	private String id;
	@ManyToOne
	@JoinColumn(name="USER_GROUP_ID",nullable = false)
	private UserGroupBO userGroupBO;
	@ManyToOne
	@JoinColumn(name="RIGHTS_ID",nullable = false)
	private RightsBO rightsBO;
	
    @Column(name = "ASSIGNABLE")
	private boolean assignable;
    

	@Transient
	private String rightID;
	
	@Transient
	private String userGroupID;
	
	
    public UserGroupRights(){}
    
    public UserGroupRights(UserGroupBO userGroupBO,RightsBO rightsBO,boolean assignable){
    	this.userGroupBO=userGroupBO;
    	this.rightsBO=rightsBO;
    	this.assignable=assignable;
    }
	public UserGroupBO getUserGroupBO() {
		return userGroupBO;
	}

	public void setUserGroupBO(UserGroupBO userGroupBO) {
		this.userGroupBO = userGroupBO;
	}

	public RightsBO getRightsBO() {
		return rightsBO;
	}

	public void setRightsBO(RightsBO rightsBO) {
		this.rightsBO = rightsBO;
	}

	public boolean isAssignable() {
		return assignable;
	}

	public void setAssignable(boolean assignable) {
		this.assignable = assignable;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRightID() {
		return rightID;
	}

	public void setRightID(String rightID) {
		this.rightID = rightID;
	}

	public String getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(String userGroupID) {
		this.userGroupID = userGroupID;
	}
	
	
}
