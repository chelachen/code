package com.jifan.pssmis.dao.wxn;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.wxn.WeixinQyMsgBO;
import com.jifan.pssmis.model.vo.wxn.WeixinQyMsgQueryVO;
public interface IWeixinQyMsgDAO extends IBaseDAO<WeixinQyMsgBO,String>{
        public List<WeixinQyMsgBO> findWeixinQyMsgByParam(WeixinQyMsgQueryVO param);

}
