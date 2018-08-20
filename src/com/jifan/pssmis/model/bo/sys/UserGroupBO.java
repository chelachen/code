package com.jifan.pssmis.model.bo.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_USER_GROUP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_user_group_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserGroupBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;
	@Column(name = "GROUP_CODE", length = 20)
	private String groupCode; // 组代码

	@Column(name = "GROUP_NAME", length = 50)
	private String groupName; // 组名称

	@Column(name = "PARENT_GROUP_ID", length = 36)
	private String parentGroup; // 父组

	@Column(name = "TYPE", length = 0)
	private Integer type; // 类型 1-部门，2=职位

	@Column(name = "REMARK", length = 255)
	private String remark; // 说明

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_GROUP_USER", joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"))
	private Set<UserBO> usersList = new HashSet<UserBO>();

	@OneToMany(mappedBy = "userGroupBO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserGroupRights> userGroupRightsList = new HashSet<UserGroupRights>();
	
	@Transient
	private List<UserGroupBO> allSubUserGroupList = new ArrayList<UserGroupBO>();

	public UserGroupBO() {
	}
	
	public UserGroupBO(String groupCode,String groupName) {
		this.groupCode=groupCode;
		this.groupName=groupName;
	}
	
	public boolean isPosition(){
		if (DataCodeContants.USER_GROUP_TYPE_TWO.equals(this.getType()))
			return true;
		return false;
	}
	

	// 获取当前组的子组
	public List<UserGroupBO> getSubUserGroupList(List<UserGroupBO> list) {
		List<UserGroupBO> supList = new ArrayList<UserGroupBO>();
		for (UserGroupBO userGroupBO : list) {
			if (this.groupCode.equals(userGroupBO.getParentGroup()))
				supList.add(userGroupBO);
		}
		return supList;
	}
	

	// 获得所以父组
	public List<UserGroupBO> getParentUserGroupList(List<UserGroupBO> list) {
		List<UserGroupBO> parentList = new ArrayList<UserGroupBO>();
		for (UserGroupBO userGroupBO : list) {
			if (null == userGroupBO.getParentGroup() || "".equals(userGroupBO.getParentGroup()))
				parentList.add(userGroupBO);
		}
		return parentList;
	}

	// 在集合中找自己本身，目的是让数据属于相同session
	public UserGroupBO getUserGroupBOBySelf(List<UserGroupBO> list) {
		for (UserGroupBO bo : list) {
			if (bo.getId().equalsIgnoreCase(this.id))
				return bo;
		}
		return null;
	}

	// 获得集合中所有可能的父组
	public List<UserGroupBO> getParentUserGroupBO(List<UserGroupBO> allList, List<UserGroupBO> list) {
		HashMap<String, String> allSub = new HashMap<String, String>();
		for (UserGroupBO userGroupBO : list) {
			for (UserGroupBO bo : userGroupBO.getAllSubUserGroupList(allList)) {
				allSub.put(bo.getGroupCode(), null);
			}
		}
		Iterator<UserGroupBO> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (allSub.containsKey(iterator.next().getGroupCode())) {
				iterator.remove();
			}

		}
		return list;
	}

	public List<UserGroupBO> getAllSubUserGroupList(List<UserGroupBO> list) {
		this.allSubUserGroupList.clear();
		this.combinAllSubUserGroupList(this, list);
		return this.allSubUserGroupList;

	}

	private void combinAllSubUserGroupList(UserGroupBO userGroupBO, List<UserGroupBO> list) {
		for (UserGroupBO bo : userGroupBO.getSubUserGroupList(list)) {
			this.allSubUserGroupList.add(bo);
			this.combinAllSubUserGroupList(bo, list);
		}
	}

	public UserGroupBO(String groupCode, String groupName, String parentGroup, String remark) {
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.parentGroup = parentGroup;
		this.remark = remark;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getTypeName() {
		return DataCodeUtil.getUserGroupTypeName(this.type);
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<UserBO> getUsersList() {
		return usersList;
	}

	public void setUsersList(Set<UserBO> usersList) {
		this.usersList = usersList;
	}

	public Set<UserGroupRights> getUserGroupRightsList() {
		return userGroupRightsList;
	}

	public void setUserGroupRightsList(Set<UserGroupRights> userGroupRightsList) {
		this.userGroupRightsList = userGroupRightsList;
	}

	public List<UserGroupBO> getAllSubUserGroupList() {
		return allSubUserGroupList;
	}

	public void setAllSubUserGroupList(List<UserGroupBO> allSubUserGroupList) {
		this.allSubUserGroupList = allSubUserGroupList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
