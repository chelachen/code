package com.jifan.pssmis.web.backbean.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.excel.IReportService;
import com.jifan.pssmis.foundation.excel.ReportTemplate;
import com.jifan.pssmis.foundation.exception.ExceptionMsg;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.foundation.paging.PagedListDataModel;
import com.jifan.pssmis.foundation.paging.Paginator;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.foundation.util.ContextUtil;
import com.jifan.pssmis.foundation.util.DateUtil;
import com.jifan.pssmis.foundation.util.WebUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;
import com.jifan.pssmis.web.backbean.commen.RightsManager;

@SessionScoped
public class BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = -3384527821180770922L;
	/**
	 * 日志处理对象
	 */
	protected Log log = LogFactory.getLog(this.getClass());
	/**
	 * 异常处理对象
	 */
	protected ExceptionMsg msg = new ExceptionMsg();
	/**
	 * 分页对象
	 */
	protected Paginator pager = new Paginator();
	/**
	 * 分页集合对象
	 */
	protected PagedListDataModel dataModel;
	/**
	 * 编辑权限
	 */
	protected boolean editable = true;
	/**
	 * 删除权限
	 */
	protected boolean deleteable = true;
	/**
	 * 添加权限
	 */
	protected boolean addable = true;
	/**
	 * 查询权限
	 */
	protected boolean queryable = true;

	/**
	 * 弹出框是否关闭
	 */
	protected boolean showPopupPanel;

	public BaseBean() {
		super();
		pager.setPageSize(20);
		rightControl();
	}

	/**
	 * 获取功能权限控制信息, 不从session中取资源信息，而是根据当前类信息取得权限信息
	 */
	private void rightControl() {
		// 获取当前打开的页面路径
		// String src = SysSession.getLastOpenPage();
		String src = this.getClass().getSimpleName();

		// session中最后打开的tab页不为空
		if (src != null && !src.equals("") && !SysContants.ADMIN.equals(this.getSysUserCode())) {
			// 调用权限控制接口，后续完善
			editable = RightsManager.checkRightsFromSession(src + SysContants.EDIT, SysContants.RIGHTS_TYPE_FUNC);
			deleteable = RightsManager.checkRightsFromSession(src + SysContants.DELETE, SysContants.RIGHTS_TYPE_FUNC);
			addable = RightsManager.checkRightsFromSession(src + SysContants.ADD, SysContants.RIGHTS_TYPE_FUNC);
			queryable = RightsManager.checkRightsFromSession(src + SysContants.QUERY, SysContants.RIGHTS_TYPE_FUNC);
		} else if (SysContants.ADMIN.equals(this.getSysUserCode())) {
			editable = true;
			addable = true;
			deleteable = true;
			queryable = true;
		}
	}
	
	public boolean rightControl(String ctrCode){
		String src = this.getClass().getSimpleName();
		// session中最后打开的tab页不为空
		if (src != null && !src.equals("") && !SysContants.ADMIN.equals(this.getSysUserCode())) {
			return RightsManager.checkRightsFromSession(src + ctrCode, SysContants.RIGHTS_TYPE_FUNC);
		} else if (SysContants.ADMIN.equals(this.getSysUserCode())) {
			return true;
		}
		return false;
	}

	public void closePopupPanel() {
		this.showPopupPanel = false;
	}

	/**
	 * 获取用户所属的系统UserId
	 * 
	 * @return
	 */
	protected String getUserId() {
		return SysSession.getUserId();
	}

	/**
	 * 获取用户所属的系统UserName
	 * 
	 * @return
	 */
	public String getUserName() {
		return SysSession.getUserName();
	}

	/**
	 * 获取用户所属的系统UserCode
	 * 
	 * @return
	 */
	protected String getUserCode() {
		return SysSession.getUserCode();
	}

	/**
	 * 获取用户所属的系统NodeID
	 * 
	 * @return
	 */
	protected String getSysNodeId() {
		return SysSession.getSysNodeId();
	}

	/**
	 * 获取用户所属的系统NodeName
	 * 
	 * @return
	 */
	protected String getSysNodeName() {
		return SysSession.getSysNodeName();
	}

	/**
	 * 获取用户所属的BGCode
	 * 
	 * @return
	 */
	protected String getBgCode() {
		return SysSession.getBgCode();
	}

	/**
	 * 获取用户所属的BG的ID
	 * 
	 * @return
	 */
	protected String getBgId() {
		return SysSession.getBgId();
	}

	/**
	 * 获取用户所属的BG的ID
	 * 
	 * @return
	 */
	public String getBgName() {
		return SysSession.getBgName();
	}

	/**
	 * 获取用户所属的BG的ID
	 * 
	 * @return
	 */
	public String getBgEngName() {
		return SysSession.getBgEngName();
	}

	/**
	 * 获取当前中文日期
	 * 
	 * @return
	 */
	public String getCurDate() {
		return DateUtil.getStrNowDateChina();
	}

	/**
	 * 获取流水号
	 * 
	 * @return
	 */
	public String getBgNum() {
		return SysSession.getBgAliasName() + DateUtil.getStrDateShort();
	}

	/**
	 * 获取用户所属的BG的ID
	 * 
	 * @return
	 */
	protected String getBgAliasName() {
		return SysSession.getBgAliasName();
	}

	/**
	 * 获取当前用户UserCode
	 * 
	 * @return
	 */
	protected String getSysUserCode() {
		return SysSession.getSysUserCode();
	}

	/**
	 * 获取当前用户UserName
	 * 
	 * @return
	 */
	public String getSysUserName() {
		return SysSession.getSysUserName();
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	protected Date getSysDate() {
		return new Date();
	}

	/**
	 * 设置当前对象的用户和时间
	 * 
	 * @return
	 */
	protected void setUserAndDate(AuditableBO currentBO) {
		if (currentBO != null && (currentBO.getId() != null && !currentBO.getId().equals(""))) {
			currentBO.setUpdateTime(this.getSysDate());
			currentBO.setUpdateUser(this.getUserName());
		} else if (currentBO != null) {
			currentBO.setCreateUser(this.getUserName());
			currentBO.setUpdateUser(this.getUserName());
		}
	}

	/**
	 * 下载文件，导出Excel时使用
	 * 
	 * @param object
	 *            文件数据流
	 * @param fileName
	 *            文件名
	 * @param contenttype
	 *            文件类型
	 * @throws IOException
	 */
	protected void downLoadFile(Object object, String fileName, String contenttype) throws IOException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		if (!ctx.getResponseComplete()) {
			String contentType = contenttype;
			ByteArrayOutputStream baos = WebUtil.castToBAOStream(object);
			HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"),
				"ISO8859_1"));
			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
			ctx.responseComplete();
		}
	}

	/**
	 * 导出Excel统一方法
	 * 
	 * @param configUrl
	 *            导出Excel的配置文件，如"/report_template/sys_excel.xml"
	 * @param templateId
	 *            配置文件中对应的document id="reportTestExcel", 如"businessGroupExcel"
	 * @param fileName
	 *            导出Excel的文件名，如 "商业组"
	 */
	protected void toExcel(String configUrl, String templateId, String fileName, int size) {
		SysLogger.forceInfo(this.getClass(), fileName + "导出开始,操作者:" + this.getSysUserCode() + this.getSysUserName());
		try {
			long begin = System.currentTimeMillis();
			IReportService reportService = (IReportService) (ContextUtil.getBean("reportService"));
			ReportTemplate template = new ReportTemplate(configUrl, templateId);
			ByteArrayOutputStream os = null;
			try {
				os = reportService.createExcelReport2(template);

				this.downLoadFile(os, fileName + ".xls", "application/vnd.ms-excel");
			} finally {
				if (os != null) {
					os.close();
					os = null;
				}
			}
			long end = System.currentTimeMillis();
			SysLogger.forceInfo(this.getClass(), fileName + "导出结束,用时:" + (end - begin) / 1000 + "秒,导出数量:" + size);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	/**
	 * 导出Excel统一方法(支持多个sheet页)
	 * 
	 * @param configUrl
	 *            导出Excel的配置文件，如new String[] { "/report_template/sys_excel.xml",
	 *            "/report_template/sys_excel.xml"
	 * @param templateId
	 *            配置文件中对应的document id="reportTestExcel", 如new String[] {
	 *            "businessGroupExcel", "businessGroupExcel1"}
	 * @param fileName
	 *            导出Excel的文件名，如 "商业组"
	 */
	protected void toExcelMutiSheet(String[] configUrls, String[] templateIds, String fileName) {
		try {
			IReportService reportService = (IReportService) (ContextUtil.getBean("reportService"));
			ReportTemplate template = null;
			List list = new ArrayList();
			for (int i = 0; i < configUrls.length; i++) {
				template = new ReportTemplate(configUrls[i], templateIds[i]);
				list.add(template);
			}
			ByteArrayOutputStream os = null;
			try {
				os = reportService.createExcelReportMulSheet(list);
				this.downLoadFile(os, fileName + ".xls", "application/vnd.ms-excel");
			} finally {
				if (os != null) {
					os.close();
					os = null;
				}
			}
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	protected Object getBackBeanByName(String functionBean) {
		Object bb = null;
		if (functionBean != null && !"".equals(functionBean)) {
			// 首字母专成小写
			String firstStr = functionBean.substring(0, 1).toLowerCase();
			functionBean = firstStr + functionBean.substring(1);
			bb = SysSession.getObjByKey(functionBean);
		}
		return bb;
	}

	protected void findByPager() {

	}

	public ExceptionMsg getMsg() {
		return msg;
	}

	public void setMsg(ExceptionMsg msg) {
		this.msg = msg;
	}

	public Paginator getPager() {
		return pager;
	}

	public void setPager(Paginator pager) {
		this.pager = pager;
	}

	public PagedListDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(PagedListDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDeleteable() {
		return deleteable;
	}

	public void setDeleteable(boolean deleteable) {
		this.deleteable = deleteable;
	}

	public boolean isAddable() {
		return addable;
	}

	public void setAddable(boolean addable) {
		this.addable = addable;
	}

	public boolean isQueryable() {
		return queryable;
	}

	public void setQueryable(boolean queryable) {
		this.queryable = queryable;
	}

	public boolean isShowPopupPanel() {
		return showPopupPanel;
	}

	public void setShowPopupPanel(boolean showPopupPanel) {
		this.showPopupPanel = showPopupPanel;
	}

}
