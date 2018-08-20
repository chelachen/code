package com.jifan.pssmis.bs.wxn;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.wxn.AccessTokenBO;
import com.jifan.pssmis.model.vo.wxn.AccessTokenQueryVO;
/**
*@ 自动生成
*@微信获取bs接口
*@2014-03-15 16:47:11
*/
public interface IAccessTokenBS extends IBaseBS<AccessTokenBO,String>  {
        public List<AccessTokenBO> findAccessTokenByParam(AccessTokenQueryVO param);
        public String saveNotExist(AccessTokenBO entity) throws BizException;
        //取得AccessToken
        public AccessTokenBO getAccessToken(String appId);
      //重新取得AccessToken
        public AccessTokenBO getReAccessToken(String appId);
        public void updateAccessToken(AccessTokenBO param);
        public void saveAccessToken(AccessTokenBO param) throws BizException;
}
