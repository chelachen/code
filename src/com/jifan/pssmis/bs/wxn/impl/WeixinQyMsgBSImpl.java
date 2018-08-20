package com.jifan.pssmis.bs.wxn.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.wxn.IWeixinQyMsgBS;
import com.jifan.pssmis.dao.wxn.IWeixinQyMsgDAO;
import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.model.bo.wxn.WeixinQyMsgBO;
import com.jifan.pssmis.model.vo.wxn.WeixinQyMsgQueryVO;
@Service
public class WeixinQyMsgBSImpl extends BaseBSImpl<WeixinQyMsgBO,String> implements IWeixinQyMsgBS{
        @Resource
        private IWeixinQyMsgDAO weixinQyMsgDAO;
        
        @Resource
        public void setBaseDAO(IWeixinQyMsgDAO weixinQyMsgDAO) {
                super.setBaseDAO(weixinQyMsgDAO);
        }
        public List<WeixinQyMsgBO> findWeixinQyMsgByParam(WeixinQyMsgQueryVO param) {
                return this.weixinQyMsgDAO.findWeixinQyMsgByParam(param);
        }
        public String saveNotExist(WeixinQyMsgBO entity) throws BizException{
                return super.save(entity);
        }

        public IWeixinQyMsgDAO getWeixinQyMsgDAO() {
                return weixinQyMsgDAO;
        }

        public void setWeixinQyMsgDAO(IWeixinQyMsgDAO weixinQyMsgDAO) {
                this.weixinQyMsgDAO = weixinQyMsgDAO;
        }
        
    	/**
    	 * 微信企业号消息发送
    	 * 
    	 * @throws Exception
    	 */
    	public void saveSendWxQy() throws Exception {
    		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(WeixinQyMsgBO.class);
    		detachedCriteria.add(Restrictions.eq("status", DataCodeContants.MSG_STATUS_ZERO));
    		detachedCriteria.add(Restrictions.le("sendNum", 1));
    		detachedCriteria.add(Restrictions.sqlRestriction("1=1 order by update_time desc LIMIT 100"));// 每次只取100条记录
    		List<WeixinQyMsgBO> qyList = this.findByDetachedCriteria(detachedCriteria);

    		if (!qyList.isEmpty()) {
    			for (WeixinQyMsgBO weixinQyMsgBO : qyList) {
    				if (weixinQyMsgBO != null) {
    					try {
//    						String result = WeixinQyUtil.sendWeixinQyTextMsg(weixinQyMsgBO);
//    						if (result != null && "ok".equals(result)) {
    							weixinQyMsgBO.setStatus(1);
    							weixinQyMsgBO.setSendNum(weixinQyMsgBO.getSendNum() + 1);
    							weixinQyMsgBO.setSendTime(new Date());
    							this.update(weixinQyMsgBO);
    					         EventBus eventBus = EventBusFactory.getDefault().eventBus();
    					         String CHANNEL = "/notify";
    					         eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml("操作提醒"), StringEscapeUtils.escapeHtml(weixinQyMsgBO.getContent())));
//    						} else {
//    							weixinQyMsgBO.setSendNum(weixinQyMsgBO.getSendNum() + 1);
//    							this.update(weixinQyMsgBO);
//    						}
    					} catch (Exception e) {
    						SysLogger.error(this.getClass(), "消息发送失败，ID为：" + weixinQyMsgBO.getId() + e.getMessage());
    					}
    				}
    			}
    		}

    	}

}
