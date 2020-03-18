package com.ccsu.zy.service;

import org.apache.shiro.authc.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccsu.zy.model.SiteSysUser;
import com.ccsu.zy.util.JWTUtil;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class LoginService extends BaseAction implements ModelDriven<SiteSysUser> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	
	SiteSysUser siteSysUser = new SiteSysUser();
	
	
     @Action("login")
     public void login(){
    	 logger.info("进入登录模块 ");
         //1.获取当前的用户
         Subject currentUser = SecurityUtils.getSubject();
         //2.把登录信息封装为一个 UsernamePasswordToken 对象
         UsernamePasswordToken uptoken=new UsernamePasswordToken(siteSysUser.getLogin_code(),siteSysUser.getPassword());
         //3.设置"记住我"功能
         uptoken.setRememberMe(false);
         try {
             currentUser.login(uptoken);
             //获取用户信息
             siteSysUser = PersistenceHelper.getInstance().getSiteSysUserDao().querySiteSysUser(siteSysUser).get(0);
             //返回token
             String token = JWTUtil.sign(siteSysUser.getLogin_code(), siteSysUser.getUser_code());
             Object object = new Object(); 
             if(token != null) {
            	 writerToUiJson(token);
             }
         } catch (UnknownAccountException uae) {
     		 logger.info("用户名不存在: " + uae);
     		 writerToUiJson("failure");
         } catch (IncorrectCredentialsException ice) {
        	 logger.info("用户名存在,但密码和用户名不匹配: " + ice);
        	 writerToUiJson("failure");
         } catch (LockedAccountException lae) {
        	 logger.info("用户被锁定: " + lae);
             writerToUiJson("failure");
         } catch (AuthenticationException ae) {
        	 logger.info("其他异常: " + ae);
             writerToUiJson("failure");
         }
     }
     
    @Action("getUserInfo")
 	public void getUserInfo() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String token = request.getHeader("token").replace("\"", "");
    	siteSysUser.setUser_code(JWTUtil.getUser_code(token));
    	siteSysUser.setLogin_code(JWTUtil.getLogin_code(token));
 		writerToUiJson(PersistenceHelper.getInstance().getSiteSysUserDao().querySiteSysUser(siteSysUser));
 	}
     
    @Action("getOnlineInfo")
  	public void getOnlineInfo() {
  		writerToUiJson(JWTUtil.getOnlineCount());
  	}
     
	@Override
	public SiteSysUser getModel() {
		// TODO Auto-generated method stub
		return siteSysUser;
	}
}

