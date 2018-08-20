package com.jifan.pssmis.web.backbean.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

import com.jifan.pssmis.bs.sys.IRightsBS;
import com.jifan.pssmis.bs.sys.IUserGroupBS;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.RightsVO;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;
import com.jifan.pssmis.model.vo.sys.UserVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "userGroupBB")
public class UserGroupBB extends BaseBean {

	private static final long serialVersionUID = 1L;

	private UserGroupBO currentBO = new UserGroupBO();

	private UserGroupQueryVO param = new UserGroupQueryVO();

	private List<UserGroupBO> list = new ArrayList<UserGroupBO>();

	private List<SelectItem> typeList = new ArrayList<SelectItem>();

	@ManagedProperty(name = "userGroupBS", value = "#{userGroupBS}")
	private IUserGroupBS userGroupBS;
	private TreeNode selectedData;
	private TreeNode groupTree = new DefaultTreeNode("Root", null);
	private TreeNode funcRightsTree = new DefaultTreeNode("Root", null);//功能权限树
	private TreeNode[] selectedFuncRights;//选中的权限节点

	//用户组与用户关联维护
	private List<UserVO> usersExistsList = new ArrayList<UserVO>();
	private List<UserVO> usersNotExistList = new ArrayList<UserVO>();
	private DualListModel<UserVO> userModel = new DualListModel<UserVO>(usersNotExistList, usersExistsList);
	
	//用户组与功能权限关联维护
	@ManagedProperty(name = "rightsBS", value = "#{rightsBS}")
	private IRightsBS rightsBS;
	private List<RightsVO> funcRightsExistsList = new ArrayList<RightsVO>();
	private List<RightsVO> funcRightsNotExistList = new ArrayList<RightsVO>();
	private DualListModel<RightsVO> funcRightsModel = new DualListModel<RightsVO>(funcRightsNotExistList, funcRightsExistsList);


	@PostConstruct
	public void init() {
		typeList = DataCodeUtil.getUserGroupTypeList();
		this.initGroupTree();
	}

	public UserGroupBB() {

	}

	public void update() {
		try {
			if (this.selectedData != null)
				this.currentBO = (UserGroupBO) this.selectedData.getData();
			else {
				PubShowMessage.showInfo("请选中一个父节点！以便添加。");
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void add() {
		try {
			this.currentBO = new UserGroupBO();
			if (this.selectedData != null) {
				UserGroupBO parentGroup = (UserGroupBO) this.selectedData.getData();
				if (CommonUtil.isNotEmpty(parentGroup.getId()))
					this.currentBO.setParentGroup(parentGroup.getId());
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			if (this.selectedData != null) {
				UserGroupBO parentGroup = (UserGroupBO) this.selectedData.getData();
				this.userGroupBS.deleteAll(parentGroup);
				this.initGroupTree();
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void changeUsers() {
		try {
			if (this.selectedData != null) {
				this.currentBO = (UserGroupBO) this.selectedData.getData();
				this.usersExistsList = this.userGroupBS.loadUsers(this.currentBO, true);
				this.usersNotExistList = this.userGroupBS.loadUsers(this.currentBO, false);
				userModel = new DualListModel<UserVO>(usersNotExistList, usersExistsList);
			} else {
				PubShowMessage.showInfo("请选中一个父节点！以便添加。");
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void saveGroupUserRights() {
		try {
			if (this.currentBO != null) {
				this.userGroupBS.saveGroupRights(this.currentBO, this.funcRightsModel.getTarget());
			}
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}
	
	public void changeRights() {
		try {
			if (this.selectedData != null) {
				this.currentBO = (UserGroupBO) this.selectedData.getData();
				this.funcRightsNotExistList=this.rightsBS.findAllFuncRights(this.currentBO.getId(), false);
				this.funcRightsExistsList=this.rightsBS.findAllFuncRights(this.currentBO.getId(), true);
				funcRightsModel = new DualListModel<RightsVO>(funcRightsNotExistList, funcRightsExistsList);	
			} else {
				PubShowMessage.showInfo("请选中一个用户组！以便修改。");
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void saveGroupUser() {
		try {
			if (this.currentBO != null) {
				this.userGroupBS.saveGroupUsers(this.currentBO, this.userModel.getTarget());
			}
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}
	
	

	public void handleTransfer(TransferEvent event) {
		// event.getItems() : List of items transferred //event.isAdd() : Is
		// transfer from source to target //event.isRemove() : Is transfer from
		// target to source
	}

	/**
	 * 通用 -获取用户组树状结构
	 * 
	 * @param dataClassCode
	 * @return
	 * @throws BizException
	 */
	public void initGroupTree() {
		this.groupTree=DataCodeUtil.getUserGroupTree(null);
	}



	public void save() {
		try {
			this.setUserAndDate(currentBO);
			if (CommonUtil.isEmpty(this.currentBO.getId())) {
				this.userGroupBS.saveNotExist(this.currentBO);
			} else {
				this.userGroupBS.updateNotExist(this.currentBO);
			}
			this.initGroupTree();
			this.currentBO = new UserGroupBO();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void findByPager() {
		try {
			this.list = this.userGroupBS.findUserGroupByParam(param);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public UserGroupBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(UserGroupBO currentBO) {
		this.currentBO = currentBO;
	}

	public UserGroupQueryVO getParam() {
		return param;
	}

	public void setParam(UserGroupQueryVO param) {
		this.param = param;
	}

	public IUserGroupBS getUserGroupBS() {
		return userGroupBS;
	}

	public void setUserGroupBS(IUserGroupBS userGroupBS) {
		this.userGroupBS = userGroupBS;
	}

	public List<SelectItem> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<SelectItem> typeList) {
		this.typeList = typeList;
	}

	public List<UserGroupBO> getList() {
		return list;
	}

	public void setList(List<UserGroupBO> list) {
		this.list = list;
	}

	public TreeNode getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(TreeNode selectedData) {
		this.selectedData = selectedData;
	}

	public void setGroupTree(TreeNode groupTree) {
		this.groupTree = groupTree;
	}

	public TreeNode getGroupTree() {
		return groupTree;
	}

	public List<UserVO> getUsersExistsList() {
		return usersExistsList;
	}

	public void setUsersExistsList(List<UserVO> usersExistsList) {
		this.usersExistsList = usersExistsList;
	}

	public List<UserVO> getUsersNotExistList() {
		return usersNotExistList;
	}

	public void setUsersNotExistList(List<UserVO> usersNotExistList) {
		this.usersNotExistList = usersNotExistList;
	}

	public DualListModel<UserVO> getUserModel() {
		return userModel;
	}

	public void setUserModel(DualListModel<UserVO> userModel) {
		this.userModel = userModel;
	}

	public TreeNode getFuncRightsTree() {
		return funcRightsTree;
	}

	public void setFuncRightsTree(TreeNode funcRightsTree) {
		this.funcRightsTree = funcRightsTree;
	}

	public TreeNode[] getSelectedFuncRights() {
		return selectedFuncRights;
	}

	public void setSelectedFuncRights(TreeNode[] selectedFuncRights) {
		this.selectedFuncRights = selectedFuncRights;
	}

	public List<RightsVO> getFuncRightsExistsList() {
		return funcRightsExistsList;
	}

	public void setFuncRightsExistsList(List<RightsVO> funcRightsExistsList) {
		this.funcRightsExistsList = funcRightsExistsList;
	}

	public List<RightsVO> getFuncRightsNotExistList() {
		return funcRightsNotExistList;
	}

	public void setFuncRightsNotExistList(List<RightsVO> funcRightsNotExistList) {
		this.funcRightsNotExistList = funcRightsNotExistList;
	}

	public DualListModel<RightsVO> getFuncRightsModel() {
		return funcRightsModel;
	}

	public void setFuncRightsModel(DualListModel<RightsVO> funcRightsModel) {
		this.funcRightsModel = funcRightsModel;
	}

	public IRightsBS getRightsBS() {
		return rightsBS;
	}

	public void setRightsBS(IRightsBS rightsBS) {
		this.rightsBS = rightsBS;
	}

}