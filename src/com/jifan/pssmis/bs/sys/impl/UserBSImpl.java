package com.jifan.pssmis.bs.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.IBaseJdbcBS;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IUserBS;
import com.jifan.pssmis.dao.sys.IUserDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.UserVO;

@SuppressWarnings("serial")
@Service("userBS")
public class UserBSImpl extends BaseBSImpl<UserBO, String> implements IUserBS {

	@Resource
	private IUserDAO userDAO;

	@Resource
	private IBaseJdbcBS baseJdbcBS;

	@Resource
	public void setBaseDAO(IUserDAO userDAO) {
		super.setBaseDAO(userDAO);
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<UserBO> findUserByParam(UserVO param) {
		return this.userDAO.findUserByParam(param);
	}

	@Override
	public List<UserBO> login(UserVO param) {
		return this.userDAO.login(param);
	}

	@Override
	public String saveNotExist(UserBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getUserCode() != null && !entity.getUserCode().equals("")) {
			dis.add(Restrictions.eq("userCode", entity.getUserCode()));
		}
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("用户代号:" + entity.getUserCode() + "已经存在!");

		return super.save(entity);
	}
	
	@Override
	public void updateNotExist(UserBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getUserCode() != null && !entity.getUserCode().equals("")) {
			dis.add(Restrictions.eq("userCode", entity.getUserCode()));
		}
		detachedCriteria.add(Restrictions.ne("id", entity.getId()));
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("用户代号:" + entity.getUserCode() + "已经存在!");

		super.update(entity);
	}
	
	@Override
	public void delete (UserBO userBO){
		String sql ="DELETE from sys_user_rights WHERE user_id='"+userBO.getId()+"' ";
		this.baseJdbcBS.executeSQL(sql);
		sql ="DELETE from sys_group_user WHERE user_id='"+userBO.getId()+"' ";
		this.baseJdbcBS.executeSQL(sql);
		super.delete(userBO);
	}
	
	/**
	 * 获取用户的所有角色信息列表
	 * @param id 用户ID
	 * @return
	 */
	public List<UserGroupBO> loadUserGroupOfUser(String id){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserGroupBO.class);
		detachedCriteria.add(Restrictions.sqlRestriction(" ID in (SELECT GROUP_ID from sys_group_user where USER_ID= '"+id+"' ) "));
		return this.findByDetachedCriteria(detachedCriteria);
	}
	
	/**
	 * 获取用户的所有权限信息列表
	 * @param id 用户ID
	 * @return
	 */
	public List<RightsBO> loadRightsOfUser(String id){
		String sql = "SELECT d.id,d.SOURCE_INFO as sourceInfo,d.RIGHTS_TYPE as rightsType from sys_user a left join sys_group_user b on a.id = b.USER_ID left join sys_user_group_rights c on b.GROUP_ID=c.USER_GROUP_ID left join sys_rights d on d.id =c.RIGHTS_ID where d.id is not null and a.id ='" 
			+id+"'";
		return this.baseJdbcBS.runSQLReturnList(sql, null, RightsBO.class);
	}

}
