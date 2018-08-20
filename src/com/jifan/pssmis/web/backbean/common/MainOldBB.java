package com.jifan.pssmis.web.backbean.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabCloseEvent;

import com.jifan.pssmis.bs.sys.IModuleBS;
import com.jifan.pssmis.bs.sys.IUserBS;
import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.SecurityUtil;
import com.jifan.pssmis.foundation.util.SysInitUtil;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;
import com.jifan.pssmis.model.vo.sys.UserSessionVO;
import com.jifan.pssmis.model.vo.sys.UserVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;
import com.jifan.pssmis.web.backbean.commen.RightsManager;

@ManagedBean(name = "mainOldBB")
@SessionScoped
public class MainOldBB extends BaseBean {
	private List<TabFunction> tabList;
	private TabFunction currentTab;
	private List<String> tabFunctionCodeList = new ArrayList<String>();
	private int activeTabIndex = 0;
	private boolean logined = false;
	private UserVO param = new UserVO();
	/**
	 * 系统版本号 
	 */
	private String systemVersion;
	/**
	 * 当前模块
	 */
	private ModuleBO currentModuleBO;
	/**
	 * 中心窗体include的页面
	 */
	private String mainContentPage;
	//列出所有模块功能
	private boolean mainOnePanelGroup;
	//列出当前模块功能
	private boolean mainTowPanelGroup;

	@ManagedProperty(name = "userBS", value = "#{userBS}")
	private IUserBS userBS;
	@ManagedProperty(name = "moduleBS", value = "#{moduleBS}")
	private IModuleBS moduleBS;
	
	/**
	 * 当前菜单节点，点击菜单时使用
	 */
	private String currentItem;
	/**
	 * 当前查询选中的Tab
	 */
	private String seleteTab = "firstTab";
	private String currentName;

	/**
	 * 模块列表
	 */
	private List<ModuleBO> moduleList = new ArrayList<ModuleBO>();

	public MainOldBB() {
		systemVersion = SysInitUtil.getSysVersion();
		tabList = new ArrayList<TabFunction>();
		mainOnePanelGroup=true;

		tabFunctionCodeList.add("流程自动化");

		TabFunction tab = new TabFunction();
		tab.setTitle("流程自动化");
		tab.setFunctionCode("homeBB");
		tab.setUrl("pages/sys/homePage.xhtml");
		tabList.add(tab);

		currentTab = new TabFunction();
		currentTab.setTitle("流程自动化");
		currentTab.setFunctionCode("homePage");
		currentTab.setUrl("pages/sys/homePage.xhtml");

	}

	public void openTab() {
		if (!tabFunctionCodeList.contains(currentTab.getTitle())) {
			tabList.get(0).setActive(false);
			tabFunctionCodeList.add(currentTab.getTitle());
			TabFunction tab = new TabFunction();
			tab.setTitle(currentTab.getTitle());
			tab.setUrl(currentTab.getUrl());
			tab.setFunctionCode(currentTab.getFunctionCode());
			tab.setActive(true);
			tabList.add(tab);
			activeTabIndex = tabList.size() - 1;
		} else {
			for (int i = 0; i < tabList.size(); i++) {
				if (currentTab.getTitle().equals(tabList.get(i).getTitle())) {
					activeTabIndex = i;
					break;
				}
			}
		}

	}

	@PostConstruct
	public void getModules() {
		ModuleVO moduleVO = new ModuleVO();
		this.moduleList = this.moduleBS.findModuleByParam(moduleVO);
		Collections.sort(this.moduleList);
		Iterator<ModuleBO> moIterator = this.moduleList.iterator();
		boolean removed = false;
		while (moIterator.hasNext()) {
			ModuleBO moduleBO = moIterator.next();
			removed = false;
			// 模块功能不为空
			if (moduleBO.getModuleFunctions() != null && moduleBO.getModuleFunctions().size() > 0) {
				this.getModelFunction(moduleBO);
				Collections.sort(moduleBO.getModuleFunctions());
			}
			// 权限过滤后模块功能为空则剔除改模块
			if (!removed && (moduleBO.getModuleFunctions() == null || moduleBO.getModuleFunctions().size() == 0)) {
				moIterator.remove();
			}
		}
	}

	/**
	 * 根据模块BO获取并处理其中的功能点信息
	 * 
	 * @param moduleBO
	 */
	private void getModelFunction(ModuleBO moduleBO) {
		Iterator<ModuleFunctionBO> moFuIterator = moduleBO.getModuleFunctions().iterator();
		while (moFuIterator.hasNext()) {
			ModuleFunctionBO functionBO = moFuIterator.next();
			if (!RightsManager.checkModuleRightsFromSession(functionBO.getFunctionBean(), SysContants.RIGHTS_TYPE_FUNC))
				moFuIterator.remove();
		}

	}
	
	public void changeMemuTab() {
		try {
			this.currentItem = "";
			this.currentName = "";
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}
	

	
	/*
	 * 根据当前功能点代码获取功能点BO
	 */
	private ModuleFunctionBO getCurrentModuleFunctionBO() {
		for (ModuleBO bo : moduleList) {
			for (ModuleFunctionBO fbo : bo.getModuleFunctions())
				if (fbo.getFunctionCode().equals(this.currentItem)) {
					return fbo;
				}
		}
		return null;
	}


	/**
	 * 
	 * @return
	 */
	public String login() {
		try {
			if (param.getUserCode() == null || param.getUserCode().equals("") || param.getPassWord() == null || param
				.getPassWord().equals("")) {
				PubShowMessage.showError("您输入的用户名错误!");
				return "";
			}
			boolean rightPassword = false;
			List<UserBO> userList = this.userBS.login(param);
			UserBO userBO = null;
			if (userList.size() != 0) {
				// 密码校验，解决不同node的同一用户名的登录问题
				for (UserBO user : userList) {
					if (user.getPassWord().equals(new SecurityUtil().encryptMD5(param.getPassWord()))) {
						rightPassword = true;
						userBO = user;
						break;
					} else {
						// 判断是否明文
						if (user.getPassWord().equals(param.getPassWord())) {
							// 明文转成md5
							user.setPassWord(new SecurityUtil().encryptMD5(param.getPassWord()));
							user.setUpdateTime(this.getSysDate());
							user.setUpdateUser("sys");
							this.userBS.update(user);
							rightPassword = true;
							userBO = user;
							break;
						}
					}
				}

				// 密码正确
				if (rightPassword) {
					this.setSessionInfo(userBO);
					this.logined = true;
					PubShowMessage.showInfo("欢迎回来!" + (userBO.getUserName() == null ? "" : userBO.getUserName()));
					return "";
				} else {
					PubShowMessage.showError("您输入的密码错误!");
					return "";
				}
			}

		} catch (Exception e) {
			msg.setMainMsg(e);
			;
		}
		PubShowMessage.showError("您输入的用户名或密码错误!");
		return "";
	}

	/**
	 * 用户信息写入Session中
	 * 
	 * @param userBO
	 */
	private void setSessionInfo(UserBO userBO) {
		UserSessionVO userSession = new UserSessionVO();
		userSession.setUserID(userBO.getId());
		userSession.setPassword(userBO.getPassWord());
		userSession.setUserCode(userBO.getUserCode());
		userSession.setUserName(userBO.getUserName());

		// 如果Session中已存在用户信息则先清除Session，可能影响电子商务的session
		// if (SysSession.getSysUserCode() != null) {
		// SysSession.clearSession();
		// }
		SysSession.setUserInfoToSession(userSession);
	}

	public String logout() {
		SysSession.clearSession();
		return "/login";
	}

	public List<TabFunction> getTabList() {
		return tabList;
	}

	public void setTabList(List<TabFunction> tabList) {
		this.tabList = tabList;
	}

	public TabFunction getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(TabFunction currentTab) {
		this.currentTab = currentTab;
	}

	public List<String> getTabFunctionCodeList() {
		return tabFunctionCodeList;
	}

	public void setTabFunctionCodeList(List<String> tabFunctionCodeList) {
		this.tabFunctionCodeList = tabFunctionCodeList;
	}

	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

	public void onTabClose(TabCloseEvent event) {
		String title = event.getTab().getTitle();
		tabFunctionCodeList.remove(title);
		for (TabFunction tab : tabList) {
			if (tab.getTitle() != null && title.equals(tab.getTitle())) {
				this.clearSessionByKey(tab.getFunctionCode());
				tabList.remove(tab);
				break;
			}
		}
	}
	
    /**
     * 从session中删除BB,FunctionBean为UserBB key应该为userBB chela 2011-07-03
     * 
     * @param functionBean
     */
    private void clearSessionByKey(String functionBean)
    {
        if (functionBean != null && !"".equals(functionBean))
        {
            // 首字母专成小写
            String firstStr = functionBean.substring(0, 1).toLowerCase();
            functionBean = firstStr + functionBean.substring(1);
            SysSession.clearSessionByKey(functionBean);
        }
    }

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public UserVO getParam() {
		return param;
	}

	public void setParam(UserVO param) {
		this.param = param;
	}

	public IUserBS getUserBS() {
		return userBS;
	}

	public void setUserBS(IUserBS userBS) {
		this.userBS = userBS;
	}

	public List<ModuleBO> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<ModuleBO> moduleList) {
		this.moduleList = moduleList;
	}

	public IModuleBS getModuleBS() {
		return moduleBS;
	}

	public void setModuleBS(IModuleBS moduleBS) {
		this.moduleBS = moduleBS;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public ModuleBO getCurrentModuleBO() {
		return currentModuleBO;
	}

	public void setCurrentModuleBO(ModuleBO currentModuleBO) {
		this.currentModuleBO = currentModuleBO;
	}

	public String getMainContentPage() {
		return mainContentPage;
	}

	public void setMainContentPage(String mainContentPage) {
		this.mainContentPage = mainContentPage;
	}

	public boolean isMainOnePanelGroup() {
		return mainOnePanelGroup;
	}

	public void setMainOnePanelGroup(boolean mainOnePanelGroup) {
		this.mainOnePanelGroup = mainOnePanelGroup;
	}

	public boolean isMainTowPanelGroup() {
		return mainTowPanelGroup;
	}

	public void setMainTowPanelGroup(boolean mainTowPanelGroup) {
		this.mainTowPanelGroup = mainTowPanelGroup;
	}

	public String getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}

	public String getSeleteTab() {
		return seleteTab;
	}

	public void setSeleteTab(String seleteTab) {
		this.seleteTab = seleteTab;
	}

	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}


}
