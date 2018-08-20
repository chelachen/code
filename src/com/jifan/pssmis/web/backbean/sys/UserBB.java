package com.jifan.pssmis.web.backbean.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import com.jifan.pssmis.bs.sys.IUserBS;
import com.jifan.pssmis.bs.sys.IUserGroupBS;
import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.paging.PagedListDataModel;
import com.jifan.pssmis.foundation.paging.Paginator;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.PingYinUtil;
import com.jifan.pssmis.foundation.util.SecurityUtil;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.UserVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "userBB")
public class UserBB extends BaseBean {

	private static final long serialVersionUID = 1L;

	private UserBO currentBO = new UserBO();
	
	private UserBO loginBO = new UserBO();

	private UserVO param = new UserVO();

	private List<UserBO> list = new ArrayList<UserBO>();

	private List<SelectItem> sexList = new ArrayList<SelectItem>();

	private List<SelectItem> typeList = new ArrayList<SelectItem>();

	private List resultList = new ArrayList();

	private String newPassWord;
	
	private TreeNode groupTree = new DefaultTreeNode("Root", null);
	private TreeNode[] selectedUserRole;//选中的角色节点
	
	//用户组与用户关联维护
	private List<UserGroupBO> usersExistsList = new ArrayList<UserGroupBO>();
	private List<UserGroupBO> usersNotExistList = new ArrayList<UserGroupBO>();
	private DualListModel<UserGroupBO> userModel = new DualListModel<UserGroupBO>(usersNotExistList, usersExistsList);

	@ManagedProperty(name = "userBS", value = "#{userBS}")
	private IUserBS userBS;
	@ManagedProperty(name = "userGroupBS", value = "#{userGroupBS}")
	private IUserGroupBS userGroupBS;

	@PostConstruct
	public void init() {
		// typeList = DataCodeUtil.getUserTypeList();
	}

	public UserBB() {

	}

	public void editCurUser() {
		try {
			this.loginBO = this.userBS.get(this.getUserId());
			this.newPassWord="";
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void setUserBO(UserBO userBO) {

	}

	public void autoCode() {
		if (CommonUtil.isNotEmpty(this.currentBO.getUserName()))
			this.currentBO.setUserCode(PingYinUtil.getFirstSpell(this.currentBO.getUserName()).toLowerCase());
		else
			PubShowMessage.showInfo("请先填写姓名后再试！");
	}

	public UserVO getParam() {
		return param;
	}

	public void setParam(UserVO param) {
		this.param = param;
	}

	public PagedListDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(PagedListDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public Paginator getPager() {
		return pager;
	}

	@SuppressWarnings("static-access")
	public void save() {
		try {
			this.setUserAndDate(currentBO);
			if (CommonUtil.isEmpty(this.currentBO.getId())) {
				this.currentBO.setPassWord(SecurityUtil.encryptMD5(this.currentBO.getPassWord()));
				this.userBS.saveNotExist(this.currentBO);
			} else {
				if (CommonUtil.isNotEmpty(newPassWord))
					this.currentBO.setPassWord(SecurityUtil.encryptMD5(newPassWord));
				this.userBS.updateNotExist(this.currentBO);
			}
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void updateUserInfo() {
		try {
			this.setUserAndDate(loginBO);
			if (CommonUtil.isNotEmpty(newPassWord))
				this.loginBO.setPassWord(SecurityUtil.encryptMD5(newPassWord));
			this.userBS.update(this.loginBO);
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void add() {
		this.currentBO = new UserBO();
		currentBO.setIsEnabled(DataCodeContants.SYS_USER_STATUS_ONE);
	}

	public void delete() {
		try {
			this.userBS.delete(this.currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	@SuppressWarnings("static-access")
	public void updateUser() {

		if (currentBO != null) {
			currentBO.setUpdateTime(this.getSysDate());
			currentBO.setUpdateUser(this.getSysUserCode());
			currentBO.setPassWord(new SecurityUtil().encryptMD5(currentBO.getPassWord()));
			this.userBS.update(currentBO);
		}
	}

	public void deleteUser() {

		try {
			this.userBS.delete(currentBO);
			this.list.remove(this.currentBO);
			PubShowMessage.showInfo(PubShowMessage.DELETE);
			this.getDataModel().refresh();
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void findByPager() {
		try {
			this.list = this.userBS.findUserByParam(param);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}
	
	public void getUserRole(){
		List<UserGroupBO> list = this.userBS.loadUserGroupOfUser(this.currentBO.getId());
		this.groupTree=DataCodeUtil.getUserGroupTree(list);
		this.checkSelectRole(this.groupTree,list);
		

	}
	
	private void checkSelectRole(TreeNode treeNode,List<UserGroupBO> selectNodes){
		UserGroupBO ug=null;
		for(TreeNode tn:treeNode.getChildren()){
		    ug= (UserGroupBO)tn.getData();
			if(ug!=null && selectNodes.size()>0){
				for(UserGroupBO sl :selectNodes ){
					if(sl.getId().equals(ug.getId())){
						tn.setSelected(true);
						break;
					}
				}
			}
			if(!tn.isLeaf())
				this.checkSelectRole(tn,selectNodes);
		}
	}
	
	public void saveUserRole(){
		try {
			List<UserGroupBO> selects = new ArrayList<UserGroupBO>();
			UserGroupBO ug =null;
			for(int i=0;i<this.selectedUserRole.length;i++){
				ug=(UserGroupBO)this.selectedUserRole[i].getData();
//				if(ug.isPosition())
					selects.add(ug);
			}
			this.userGroupBS.saveUserRoles(this.currentBO.getId(), selects);
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public List<SelectItem> getSexList() {
		return sexList;
	}

	public void setSexList(List<SelectItem> sexList) {
		this.sexList = sexList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public IUserBS getUserBS() {
		return userBS;
	}

	public void setUserBS(IUserBS userBS) {
		this.userBS = userBS;
	}

	public List<UserBO> getList() {
		return list;
	}

	public void setList(List<UserBO> list) {
		this.list = list;
	}

	public UserBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(UserBO currentBO) {
		this.currentBO = currentBO;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public List<SelectItem> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<SelectItem> typeList) {
		this.typeList = typeList;
	}

	public UserBO getLoginBO() {
		return loginBO;
	}

	public void setLoginBO(UserBO loginBO) {
		this.loginBO = loginBO;
	}

	public TreeNode getGroupTree() {
		return groupTree;
	}

	public void setGroupTree(TreeNode groupTree) {
		this.groupTree = groupTree;
	}

	public TreeNode[] getSelectedUserRole() {
		return selectedUserRole;
	}

	public void setSelectedUserRole(TreeNode[] selectedUserRole) {
		this.selectedUserRole = selectedUserRole;
	}

	public List<UserGroupBO> getUsersExistsList() {
		return usersExistsList;
	}

	public void setUsersExistsList(List<UserGroupBO> usersExistsList) {
		this.usersExistsList = usersExistsList;
	}

	public List<UserGroupBO> getUsersNotExistList() {
		return usersNotExistList;
	}

	public void setUsersNotExistList(List<UserGroupBO> usersNotExistList) {
		this.usersNotExistList = usersNotExistList;
	}

	public DualListModel<UserGroupBO> getUserModel() {
		return userModel;
	}

	public void setUserModel(DualListModel<UserGroupBO> userModel) {
		this.userModel = userModel;
	}

	public IUserGroupBS getUserGroupBS() {
		return userGroupBS;
	}

	public void setUserGroupBS(IUserGroupBS userGroupBS) {
		this.userGroupBS = userGroupBS;
	}

}