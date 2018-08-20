package com.jifan.pssmis.model.vo.wxn;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
/**
*@ 自动生成
*@微信获取VO类
*@2014-03-15 16:47:11
*/
public class AccessTokenQueryVO extends BaseVO  {
        private String access_Token; //Access_Token
        
        private String expires_in; //
        
        private Date expiresTime; //
        
        private String appid; //
        
        private String secret; //
        
        private String weiid;
        

        public String getAccess_Token() {
                return  access_Token;
        }

        public void setAccess_Token(String access_Token) {
                this.access_Token = access_Token;
        }

        public String getExpires_in() {
                return  expires_in;
        }

        public void setExpires_in(String expires_in) {
                this.expires_in = expires_in;
        }

        public Date getExpiresTime() {
                return  expiresTime;
        }

        public void setExpiresTime(Date expiresTime) {
                this.expiresTime = expiresTime;
        }

        public String getAppid() {
                return  appid;
        }

        public void setAppid(String appid) {
                this.appid = appid;
        }

        public String getSecret() {
                return  secret;
        }

        public void setSecret(String secret) {
                this.secret = secret;
        }

		public String getWeiid() {
			return weiid;
		}

		public void setWeiid(String weiid) {
			this.weiid = weiid;
		}

}
