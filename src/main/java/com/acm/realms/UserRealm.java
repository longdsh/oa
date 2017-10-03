package com.acm.realms;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.acm.entity.Department;
import com.acm.entity.User;

/**
 * @author 计算机网络软件应用1501 路素飞 QQ 2512977541
 * @version 创建时间：2017年9月30日 下午4:53:50 类说明
 */
public class UserRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Object principal = principals.getPrimaryPrincipal();
		System.out.println(principal);

		// 2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		
		if ("admin".equals(principal)) {
			roles.add("admin");
			roles.add("user");
			roles.add("dept");
		}else if("dept".equals(principal)) {
			roles.add("dept");
		}else {
			roles.add("user");
		}

		// 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		// 4. 返回 SimpleAuthorizationInfo 对象.
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String) token.getPrincipal();
		Object principal = null;
		Object credentials = null;
		ByteSource credentialsSalt = null;
/********************************************************************************************/
/**
 * 权限控制
 */
		Session session = getSession();
		String role = session.getAttribute("role").toString();
		System.out.println(role);
		if(role.equals("user")) {
			User user = (User) session.getAttribute("userOrDept");
			System.out.println(user);
			credentials = user.getPassword();
			principal = "user";
			credentialsSalt = ByteSource.Util.bytes(user.getUserId());
		}else {
			Department department = (Department) session.getAttribute("userOrDept");
			System.out.println(department);
			credentials = department.getPassword();
			if(department.getPower()==3) {
				principal = "admin";
			}else {
				principal = "dept";
			}
			credentialsSalt = ByteSource.Util.bytes(department.getName());
		}
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		String realmName = getName();
/****************************************************************************************/
		// 4). 盐值.
		  
		SimpleAuthenticationInfo info = null; // new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		System.out.println(info);
		return info;
	}
	
	public Session getSession() {
		Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
	}

}
