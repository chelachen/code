package com.jifan.pssmis.model.bo.wxn;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import com.jifan.pssmis.model.bo.base.AuditableBO;
/**
*@ 自动生成
*@微信获取bo
*@2014-03-15 16:47:11
*/
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "WXN_ACCESSTOKEN")
public class AccessTokenBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "ACCESSTOKEN" ,columnDefinition="VARCHAR(1000)")
        private String access_Token; //Access_Token
        
        @Column(name = "EXPIRES_IN" , length=0)
        private int expires_in; //
        
        @Column(name = "EXPIRES_TIME" , length=0)
        private Date expiresTime; //
        
        @Column(name = "JSA_PI_EXPIRES_TIME" , length=0)
        private Date jsApiExpiresTime; //
        
        @Column(name = "APPID" , length=50)
        private String appid; //
        
        @Column(name = "SECRET" , length=200)
        private String secret; //
        
        @Column(name = "WEI_ID" , length=50 ,nullable = true )
        private String weiID; //微信公众帐号
        
        @Column(name = "JSAPI_TICKET" ,columnDefinition="VARCHAR(1000)")
        private String jsapiTicket; //公众号用于调用微信JS接口的临时票据  根据 access_token来获取
        
        @Column(name = "api_ticket" ,columnDefinition="VARCHAR(1000)")
        private String apiTicket; //公众号用于调用微信JS接口的临时票据  根据 access_token来获取

        public AccessTokenBO(){
        }

        public AccessTokenBO(String access_Token,int expires_in,Date expiresTime,String appid,String secret){
                this.access_Token=access_Token;
                this.expires_in=expires_in;
                this.expiresTime=expiresTime;
                this.appid=appid;
                this.secret=secret;
        }

        public String getAccess_Token() {
                return  access_Token;
        }

        public void setAccess_Token(String access_Token) {
                this.access_Token = access_Token;
        }

        public int getExpires_in() {
                return  expires_in;
        }

        public void setExpires_in(int expires_in) {
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

		public String getJsapiTicket() {
			return jsapiTicket;
		}

		public void setJsapiTicket(String jsapiTicket) {
			this.jsapiTicket = jsapiTicket;
		}

		public String getWeiID() {
			return weiID;
		}

		public void setWeiID(String weiID) {
			this.weiID = weiID;
		}

		public String getApiTicket() {
			return apiTicket;
		}

		public void setApiTicket(String apiTicket) {
			this.apiTicket = apiTicket;
		}

		public final Date getJsApiExpiresTime() {
			return jsApiExpiresTime;
		}

		public final void setJsApiExpiresTime(Date jsApiExpiresTime) {
			this.jsApiExpiresTime = jsApiExpiresTime;
		}

}
