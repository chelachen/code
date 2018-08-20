package com.jifan.pssmis.web.backbean.sys;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.jifan.pssmis.bs.sys.ITestBS;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.model.bo.sys.TestBO;
import com.jifan.pssmis.model.vo.sys.TestQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;
@ManagedBean(name = "testBB")
public class TestBB extends BaseBean {
	private static final long serialVersionUID = -1054820662761921269L;
	private TestQueryVO param = new TestQueryVO();
	private TestBO currentBO = new TestBO();
	private List<TestBO> resultList = new ArrayList<TestBO>();
	@ManagedProperty(name = "testBS", value = "#{testBS}")
	private ITestBS testBS;
	public TestBB() {

	}

	@PostConstruct
	public void init(){

	}

	public void save() {
		try {
			this.setUserAndDate(currentBO);
			this.testBS.saveNotExist(this.currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			this.testBS.delete(this.currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public void add() {
		this.currentBO = new TestBO();
	}

	public void findByPager() {
		try {
			this.resultList = this.testBS.findTestByParam(param);
		} catch (Exception e) {
			this.msg.setMainMsg(e);
		}
	}

	public TestQueryVO getParam() {
		return param;
	}

	public void setParam(TestQueryVO param) {
		this.param = param;
	}

	public TestBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(TestBO currentBO) {
		this.currentBO = currentBO;
	}

	public List<TestBO> getResultList() {
		return resultList;
	}

	public void setResultList(List<TestBO> resultList) {
		this.resultList = resultList;
	}

	public ITestBS getTestBS() {
		return testBS;
	}

	public void setTestBS(ITestBS testBS) {
		this.testBS = testBS;
	}

}