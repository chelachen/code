package com.jifan.pssmis.dao.sys.impl;

 
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IModuleFunctionOperateDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionOperateVO;
@SuppressWarnings("serial")
@Repository
public class ModuleFunctionOperateDAOImpl extends BaseDAOImpl<ModuleFunctionOperateBO,String> implements IModuleFunctionOperateDAO  {
		 

	/***
	 * 判断存在性
	 * */
	@SuppressWarnings("unchecked")
	public List<ModuleFunctionOperateVO> ISModuleFunctionOperate(ModuleFunctionOperateBO bo)throws BizException{
		StringBuffer sql =new StringBuffer();
		sql.append(" select MODULE_FUNCTION_ID as moduleFunctionID	,");
		sql.append(" OPERATE_CODE as operateCode ,OPERATE_NAME as operateName  ,ICON_URL as iconUrl  from SYS_MODULE_FUNCTION_OPERATE where 1=1 ");
		if(bo.getModuleFunctionID()!=null&&!bo.getModuleFunctionID().equals("")){
			sql.append(" and MODULE_FUNCTION_ID='"+bo.getModuleFunctionID().trim()+"'");
 			if(bo.getOperateCode()!=null&&!"".equals(bo.getOperateCode())){
				sql.append(" and ( OPERATE_CODE='"+bo.getOperateCode().trim()+"'");
			}
			if(bo.getOperateName()!=null&&!"".equals(bo.getOperateName())){
				sql.append(" or OPERATE_NAME='"+bo.getOperateName().trim()+"')");
			}
		}  
 		if(bo.getId()!=null&&!"".equals(bo.getId())){
			sql.append(" and ID='"+bo.getId()+"'");
		} 
 		return super.findBySql(sql.toString(), ModuleFunctionOperateVO.class, true);
	}
	/**
	 * 更新
	 * 
	 * @param memberId
	 */
	public void updateModuleFunctionOperate(ModuleFunctionOperateBO bo) {
		StringBuffer sql=new StringBuffer();
		sql.append("UPDATE sys_module_function_operate SET MODULE_FUNCTION_ID='"+bo.getModuleFunctionID()+"'");
		if(bo.getOperateCode()!=null&&!"".equals(bo.getOperateCode())){
			sql.append(" , OPERATE_CODE='"+bo.getOperateCode()+"'");
		}
		if(bo.getOperateName()!=null&&!"".equals(bo.getOperateName())){
			sql.append(" , OPERATE_NAME='"+bo.getOperateName()+"'");
		}
		if(bo.getIconUrl()!=null&&!"".equals(bo.getIconUrl())){
			sql.append(" , ICON_URL='"+bo.getIconUrl()+"'");
		}
		sql.append("WHERE id = '"+bo.getId()+"'");
		Session s = super.getHibernateTemplate().getSessionFactory().openSession();
		try {
			Query query = s.createSQLQuery(sql.toString());
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			s.close();
		}

	}
}
