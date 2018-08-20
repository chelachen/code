package com.jifan.pssmis.dao.wxn;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.wxn.AccessTokenBO;
import com.jifan.pssmis.model.vo.wxn.AccessTokenQueryVO;
/**
*@ 自动生成
*@微信获取DAO接口类
*@2014-03-15 16:47:11
*/
public interface IAccessTokenDAO extends IBaseDAO<AccessTokenBO,String>{
        public List<AccessTokenBO> findAccessTokenByParam(AccessTokenQueryVO param);
        //取得AccessToken
        public AccessTokenBO getAccessToken(String appId);
      //重新取得AccessToken
        public AccessTokenBO getReAccessToken(String appId);

}
