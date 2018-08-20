package com.jifan.pssmis.web.backbean.cdm;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.jd.open.api.sdk.internal.util.StringUtil;
import com.jifan.pssmis.bs.cdm.ICustomerBS;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.PingYinUtil;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "customerBB", eager = true)
public class CustomerBB extends BaseBean {
	/**
        *
        **/
	private static final long serialVersionUID = -1054820662761921269L;

	private CustomerQueryVO param = new CustomerQueryVO();

	private CustomerBO currentBO = new CustomerBO();

	private List<CustomerBO> resultList = new ArrayList<CustomerBO>();

	@ManagedProperty(name = "customerBS", value = "#{customerBS}")
	private ICustomerBS customerBS;

	public CustomerBB() {
		CustomerQueryVO param = new CustomerQueryVO();
		param.setType(1);
	}
	
	@PostConstruct
	public void init(){
		
	}

	public void save() {
		try {
			this.currentBO.setType(1);
			this.setUserAndDate(currentBO);
			if (CommonUtil.isEmpty(this.currentBO.getId())) {
				this.customerBS.saveNotExist(this.currentBO);
			} else {
				this.customerBS.updateNotExist(this.currentBO);
			}
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			this.customerBS.delete(this.currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void add() {
		this.currentBO = new CustomerBO();
	}

	public void findByPager() {
		try {
			param.setType(1);
			this.resultList = this.customerBS.findCustomerByParam(param);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}
	
	public void flashShortCode(){
		try {
			param.setType(null);
			this.resultList = this.customerBS.findCustomerByParam(param);
			for(CustomerBO current: this.resultList){
				if(!StringUtil.isEmpty(current.getName())){
					current.setShortCode(PingYinUtil.getFirstSpellOne(current.getName()));
					this.customerBS.update(current);
				}
			}
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
		
	}
	
	public void autoCode(){
		if(CommonUtil.isNotEmpty(this.currentBO.getName()))
			this.currentBO.setCode(PingYinUtil.getFirstSpell(this.currentBO.getName()).toLowerCase());
		else
			PubShowMessage.showInfo("请先填写名称后再试！");
	}
	
	public CustomerBO getCustomerBOByID(String id){
		return this.customerBS.get(id);
	}

	public CustomerQueryVO getParam() {
		return param;
	}

	public void setParam(CustomerQueryVO param) {
		this.param = param;
	}

	public CustomerBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(CustomerBO currentBO) {
		this.currentBO = currentBO;
	}

	public List<CustomerBO> getResultList() {
		return resultList;
	}

	public void setResultList(List<CustomerBO> resultList) {
		this.resultList = resultList;
	}

	public ICustomerBS getCustomerBS() {
		return customerBS;
	}

	public void setCustomerBS(ICustomerBS customerBS) {
		this.customerBS = customerBS;
	}


}
