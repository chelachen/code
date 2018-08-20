package com.jifan.pssmis.model.bo.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_USER")
public class UserBO extends AuditableBO {

	private static final long serialVersionUID = 7343634613781038701L;

	@Column(name = "USER_CODE", length = 20, nullable = false)
	private String userCode;

	@Column(name = "USER_NAME", length = 20)
	private String userName;

	@Column(name = "PASSWORD", length = 50, nullable = false)
	private String passWord;

	@Column(name = "SEX")
	private Integer sex;

	@Column(name = "MOBILE", length = 20)
	private String mobile;

	@Column(name = "EMAIL", length = 100, nullable = true)
	private String email;

	@Column(name = "TEL", length = 20, nullable = true)
	private String tel;

	@Column(name = "REMARK", length = 255, nullable = true)
	private String remark;

	@Column(name = "IS_ENABLED", nullable = false)
	private Integer isEnabled;
	
	@Column(name = "JOB_NUM", length = 255,  nullable = true)
	private String jobNum;
	
	@Column(name = "TYPE")
	private Integer type=1; //用户类型：1-普通用户，2-管理员，暂不开启使用
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_GROUP_USER", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "id"))
	private Set<UserGroupBO> groupsList = new HashSet<UserGroupBO>();

	public UserBO(String userCode, String userName,  String passWord, Integer sex, Integer isEnabled) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.isEnabled = isEnabled;
	}

	/**
	 * 获取用户的所有权限Map(权限类型,Map(资源信息,权限))
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getUserAllRights(List<UserGroupBO> userGroupList) {
		Map mapFunc = new HashMap();// 功能权限Map
		Map mapFlow = new HashMap();// 流程权限Map
		Map mapData = new HashMap();// 流程权限Map
		Map mapUser = new HashMap();// 用户权限Map

		// 用户组权限部分
		this.addGroupRights(mapFunc, mapFlow,mapData,userGroupList, new ArrayList<UserGroupBO>(this.getGroupsList()));

		mapUser.put(SysContants.RIGHTS_TYPE_FUNC, mapFunc);
		mapUser.put(SysContants.RIGHTS_TYPE_DATA, mapData);		
		return mapUser;
	}

	/**
	 * 递归取得用户所在组及其所在组的子组的所有权限信息
	 * @param mapFunc
	 * @param mapFlow
	 * @param userGroupList
	 */
	@SuppressWarnings("unchecked")
	private void addGroupRights(Map mapFunc, Map mapFlow,Map mapData,
			List<UserGroupBO> groupListAll, List<UserGroupBO> userGroupList) {
		for (UserGroupBO UserGroupBO : userGroupList) {
			Set<UserGroupRights> userGroupRightsList = UserGroupBO
					.getUserGroupRightsList();
			for (UserGroupRights userGroupRights : userGroupRightsList) {
				this.addRightToMap(mapFunc, mapFlow,mapData,userGroupRights
						.getRightsBO());
			}
			this.addGroupRights(mapFunc, mapFlow, mapData,groupListAll,UserGroupBO.getSubUserGroupList(groupListAll));
		}
	}

	/**
	 * 把权限信息存入对应的Map
	 * @param mapFunc
	 * @param mapFlow
	 * @param rightsBO
	 */
	@SuppressWarnings("unchecked")
	private void addRightToMap(Map mapFunc, Map mapFlow,Map mapData,RightsBO rightsBO) {
		if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_FUNC) {
			if (!mapFunc.containsKey(rightsBO.getSourceInfo()))
				mapFunc.put(rightsBO.getSourceInfo(), rightsBO);
		} else if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_FLOW) {
			if (!mapFlow.containsKey(rightsBO.getSourceInfo()))
				mapFlow.put(rightsBO.getSourceInfo(), rightsBO);
		}
		 else if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_DATA) {
				if (!mapData.containsKey(rightsBO.getSourceInfo()))
					mapData.put(rightsBO.getSourceInfo(), rightsBO);
		}
	}

	/**
	 * 获取该用户可进行二次分配的权限Map(权限代码,权限)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getUserAssignRights() {
		Map mapUser = new HashMap();// 用户权限Map

		return mapUser;
	}


	
	public void addUser(String userCode,String userName, String moblie,String password,String email,String tpassID){
		this.userCode = userCode;
		this.userName = userName;
		this.mobile = moblie;
		this.passWord = password;
		this.email = email;
	}


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserBO() {
		super();
	}
	public UserBO CopyUserBO(String sysNodeID){
		UserBO userBO=new UserBO(this.userCode,this.userName,this.passWord,
				this.sex,this.mobile,this.email,this.tel,this.remark,this.isEnabled,sysNodeID);
		return userBO;
	}
	public UserBO(String userCode, String userName, String passWord,
			Integer sex, String mobile, String email, String tel,
			String remark, Integer isEnabled, String sysNodeID) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.mobile = mobile;
		this.email = email;
		this.tel = tel;
		this.remark = remark;
		this.isEnabled = isEnabled;
	}



	public Set<UserGroupBO> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(Set<UserGroupBO> groupsList) {
		this.groupsList = groupsList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}


}
