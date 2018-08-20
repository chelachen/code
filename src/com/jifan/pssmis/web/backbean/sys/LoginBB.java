package com.jifan.pssmis.web.backbean.sys;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jifan.pssmis.bs.sys.IBusinessGroupBS;
import com.jifan.pssmis.bs.sys.IUserBS;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.foundation.util.SecurityUtil;
import com.jifan.pssmis.foundation.util.SysInitUtil;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.vo.sys.BusinessGroupQueryVO;
import com.jifan.pssmis.model.vo.sys.UserSessionVO;
import com.jifan.pssmis.model.vo.sys.UserVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;
import com.jifan.pssmis.web.backbean.commen.RightsManager;

@ManagedBean(name = "loginBB")
@ViewScoped
public class LoginBB extends BaseBean {

	@ManagedProperty(name = "userBS", value = "#{userBS}")
	private IUserBS userBS;
	
	@ManagedProperty(name = "businessGroupBS", value = "#{businessGroupBS}")
	private IBusinessGroupBS businessGroupBS;
	private String systemTitle;
	private UserVO param = new UserVO();
	
	/**
	 * 单据弹出框宽度
	 */
	private int invoiceWidth = 0;

	/**
	 * 单据弹出框高度
	 */
	private int invoiceHeight = 0;

	public LoginBB() {
		systemTitle = SysInitUtil.getSystemTitle();
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
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
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
		userSession.setScreenHeight(this.invoiceHeight);
		userSession.setScreenWidth(this.invoiceWidth);

		List<RightsBO> rightsList = this.userBS.loadRightsOfUser(userBO.getId());
		userSession.setMap(RightsManager.getUserAllRights(rightsList));
		List<BusinessGroupBO> bgList =businessGroupBS.findBusinessGroupByParam(new BusinessGroupQueryVO());
		if(bgList!=null && bgList.size()>0){
			BusinessGroupBO bg=bgList.get(0);
			userSession.setBgBO(bg);
		}else
			SysLogger.error(this.getClass(), "BG商业组信息未维护，请及时维护！");
		
		// 如果Session中已存在用户信息则先清除Session，可能影响电子商务的session
		if (SysSession.getSysUserCode() != null) {
			SysSession.clearSession();
		}
		SysSession.setUserInfoToSession(userSession);
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

	public IBusinessGroupBS getBusinessGroupBS() {
		return businessGroupBS;
	}

	public void setBusinessGroupBS(IBusinessGroupBS businessGroupBS) {
		this.businessGroupBS = businessGroupBS;
	}

	public int getInvoiceWidth() {
		return invoiceWidth;
	}

	public void setInvoiceWidth(int invoiceWidth) {
		this.invoiceWidth = invoiceWidth;
	}

	public int getInvoiceHeight() {
		return invoiceHeight;
	}

	public void setInvoiceHeight(int invoiceHeight) {
		this.invoiceHeight = invoiceHeight;
	}

	public String getSystemTitle() {
		return systemTitle;
	}

	public void setSystemTitle(String systemTitle) {
		this.systemTitle = systemTitle;
	}

}
