package com.jifan.pssmis.bs.wxn;
import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.wxn.WeixinQyMsgBO;
import com.jifan.pssmis.model.vo.wxn.WeixinQyMsgQueryVO;
public interface IWeixinQyMsgBS extends IBaseBS<WeixinQyMsgBO,String>  {
        public List<WeixinQyMsgBO> findWeixinQyMsgByParam(WeixinQyMsgQueryVO param);
        public String saveNotExist(WeixinQyMsgBO entity) throws BizException;
    	/**
    	 * 微信企业号消息发送
    	 * 
    	 * @throws Exception
    	 */
    	public void saveSendWxQy() throws Exception;

}
