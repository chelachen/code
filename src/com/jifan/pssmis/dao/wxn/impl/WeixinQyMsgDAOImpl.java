package com.jifan.pssmis.dao.wxn.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.wxn.IWeixinQyMsgDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.wxn.WeixinQyMsgBO;
import com.jifan.pssmis.model.vo.wxn.WeixinQyMsgQueryVO;
@Repository
public class WeixinQyMsgDAOImpl extends BaseDAOImpl<WeixinQyMsgBO,String> implements IWeixinQyMsgDAO {
        public List<WeixinQyMsgBO> findWeixinQyMsgByParam(WeixinQyMsgQueryVO param) {
                String hql = "from " + WeixinQyMsgBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getUserId() !=null && !param.getUserId().equals("")){
                        hql = hql + " and userId = ? ";
                        list.add(param.getUserId());
                }
                        if (param.getEnterpriseID() !=null && !param.getEnterpriseID().equals("")){
                        hql = hql + " and enterpriseID = ? ";
                        list.add(param.getEnterpriseID());
                }
                        if (param.getStatus() !=null && !param.getStatus().equals("")){
                        hql = hql + " and status = ? ";
                        list.add(param.getStatus());
                }
                        if (param.getContent() !=null && !param.getContent().equals("")){
                        hql = hql + " and content = ? ";
                        list.add(param.getContent());
                }
                        if (param.getSendTime() !=null && !param.getSendTime().equals("")){
                        hql = hql + " and sendTime = ? ";
                        list.add(param.getSendTime());
                }
                        if (param.getResult() !=null && !param.getResult().equals("")){
                        hql = hql + " and result = ? ";
                        list.add(param.getResult());
                }
                        if (param.getSendNum() !=null ){
                            hql = hql + " and sendNum = ? ";
                            list.add(param.getSendNum());
                    }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<WeixinQyMsgBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
