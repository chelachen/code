package com.jifan.pssmis.dao.sys.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.ICodeDAO;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.CodeBO;
import com.jifan.pssmis.model.vo.sys.CodeQueryVO;

@Repository
public class CodeDAOImpl extends BaseDAOImpl<CodeBO, String> implements
		ICodeDAO {
	public List<CodeBO> findCodeByParam(CodeQueryVO param) {
		String hql = "from " + CodeBO.class.getName() + " where 1=1";
		List list = new ArrayList();
		if (CommonUtil.isNotEmpty(param.getClassName())) {
			hql = hql + " and className = ? ";
			list.add(param.getClassName());
		}
		if (param.getBagName() != null && !param.getBagName().equals("")) {
			hql = hql + " and bagName = ? ";
			list.add(param.getBagName());
		}
		if (param.getNum() != null && param.getNum().intValue()!=0) {
			hql = hql + " and bagName = ? ";
			list.add(param.getBagName());
		}
		if (param.getMemo() != null && !param.getMemo().equals("")) {
			hql = hql + " and content = ? ";
			list.add(param.getMemo());
		}
		Object[] objects = DAOUtil.getObjectsByList(list);
		List<CodeBO> retultList = this.getHibernateTemplate()
				.find(hql, objects);
		return retultList;
	}

}
