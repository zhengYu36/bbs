package com.zy.shiro;




import com.zy.dao.UsersDao;
import com.zy.entity.Users;
import com.zy.untils.WebHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ShiroDBRealm extends AuthorizingRealm {

	//获取用户
    @Autowired
    private UsersDao userService;

    /**
     * 验证当前用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        if (StringUtils.isEmpty(token.getUsername())) {
            return null;
        }

        String username = token.getUsername();
		Users user = userService.selectUser(username);;

		//如果是当前用户进行了登录，
        if (user != null) {

           /* if (user.getStatus() == User.STATUS_NO) {
                throw new LockedAccountException();
            }*/
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUname(), user.getUpwd(), getName());
            setSession(WebHelper.SESSION_LOGIN_USER, user);

            return authcInfo;
        }

        return null;
    }


    /**
     * @param key
     * @param value
     */
    private void setSession(Object key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Session session = subject.getSession();
            if (session != null) {
                session.setAttribute(key, value);
            }
        }
    }

	/**
	 * 授权
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
}
