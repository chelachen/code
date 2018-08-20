package com.jifan.pssmis.web.backbean.cdm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.jifan.pssmis.bs.cdm.IMachineBS;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.paging.DataPage;
import com.jifan.pssmis.foundation.paging.PagedListDataModel;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.SysInitUtil;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "machineBB")
public class MachineBB extends BaseBean {

	private static final long serialVersionUID = -1054820662761921269L;

	private MachineQueryVO param = new MachineQueryVO();

	private MachineBO currentBO = new MachineBO();

	private List<MachineBO> resultList = new ArrayList<MachineBO>();
	
	private List<SelectItem> craftTypeList = new ArrayList<SelectItem>();// 工艺类型
	
	private String finishCode;

	@ManagedProperty(name = "machineBS", value = "#{machineBS}")
	private IMachineBS machineBS;
	
	private List<CustomerBO> providerList = new ArrayList<CustomerBO>();// 供应商列表

	public MachineBB() {
		pager.setPageNumber(1);
		pager.setPageSize(10);
		currentBO = new MachineBO();
		this.craftTypeList = DataCodeUtil.getCraftTypeSelectList();
		finishCode=SysInitUtil.getFinishCode();
		this.providerList=DataCodeUtil.getProviderBOList();
	}

	public void save() {
		try {
			this.setUserAndDate(currentBO);
			if (CommonUtil.isEmpty(this.currentBO.getId())) {
				this.machineBS.saveNotExist(this.currentBO);
			} else {
				this.machineBS.updateNotExist(this.currentBO);
			}
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			this.machineBS.delete(currentBO);
			this.resultList.remove(this.currentBO);
			this.getDataModel().refresh();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	public void add() {
		this.currentBO = new MachineBO();
		this.resultList.add(currentBO);
	}


	public void findByPager() {
		try {
			this.resultList = this.machineBS.findMachineByParam(param);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public String next() {
		return "Machine";
	}

	public MachineQueryVO getParam() {
		return param;
	}

	public void setParam(MachineQueryVO param) {
		this.param = param;
	}

	public MachineBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(MachineBO currentBO) {
		this.currentBO = currentBO;
	}

	public List<MachineBO> getResultList() {
		return resultList;
	}

	public void setResultList(List<MachineBO> resultList) {
		this.resultList = resultList;
	}

	public IMachineBS getMachineBS() {
		return machineBS;
	}

	public void setMachineBS(IMachineBS machineBS) {
		this.machineBS = machineBS;
	}

	public List<SelectItem> getCraftTypeList() {
		return craftTypeList;
	}

	public void setCraftTypeList(List<SelectItem> craftTypeList) {
		this.craftTypeList = craftTypeList;
	}

	public String getFinishCode() {
		return finishCode;
	}

	public void setFinishCode(String finishCode) {
		this.finishCode = finishCode;
	}

	public List<CustomerBO> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<CustomerBO> providerList) {
		this.providerList = providerList;
	}

}
