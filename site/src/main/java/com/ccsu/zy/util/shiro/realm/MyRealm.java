package com.ccsu.zy.util.shiro.realm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
 
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccsu.zy.model.SiteSysUser;
import com.ccsu.zy.util.PersistenceHelper;
 
public class MyRealm extends AuthorizingRealm implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
 
	//一、认证 （自定义认证）
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		logger.info("成功进入ShiroRealm认证器");
		//强转为UsernamePasswordToken类型
		UsernamePasswordToken token=(UsernamePasswordToken)arg0; 
		//获取用户名
		String username = (String) token.getUsername();
		SiteSysUser siteSysUser = new SiteSysUser();
		siteSysUser.setLogin_code(username);
		
		//查询数据库进行登录操作
		siteSysUser = PersistenceHelper.getInstance().getSiteSysUserDao().querySiteSysUser(siteSysUser).get(0);
		SimpleAuthenticationInfo authenticationInfo;
		
		if(siteSysUser != null) {
            // 用户存在，检查用户状态 code = 0 为保护状态
            // 用户存在，且不为保护状态，与前端传来的password比对
            String password = (String)siteSysUser.getPassword();
            //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            authenticationInfo  = new SimpleAuthenticationInfo(username,password,getName());//getName() realm name
        } else {
            return null;
        }
		
//		//利用新建的类来创建对象
//		 ShiroUser usr=new ShiroUser();
//		 usr.setUsername(siteSysUser.getLogin_code()); //将页面中的username值设置进去
		
        return authenticationInfo;
	}
	
	//二、授权(自定义)
	 @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//arg0.getPrimaryPrincipal(): 实际上是在认证时返回的 SimpleAuthenticationInfo 的第一个参数!
	        Object principal = arg0.getPrimaryPrincipal();
	        ShiroUser user = (ShiroUser) principal;
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(user.getRoles());
	        return info;
	    }
	 
 
	//新建一个类定义用户角色和权限
	class ShiroUser implements Serializable{
		private static final long serialVersionUID = 1L;
		private String username;
		private Set<String>roles=new HashSet<String>();
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Set<String> getRoles() {
			return roles;
		}
		public void setRoles(Set<String> roles) {
			this.roles = roles;
		}
	}
	
}
