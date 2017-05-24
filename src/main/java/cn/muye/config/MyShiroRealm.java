package cn.muye.config;

import java.util.List;
import java.util.Set;
import cn.muye.shiro.domain.Permission;
import cn.muye.shiro.domain.Role;
import cn.muye.user.domain.User;
import cn.muye.shiro.domain.UserRole;
import cn.muye.shiro.service.ShiroService;
import cn.muye.user.service.UserService;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.mockito.internal.util.collections.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MyShiroRealm
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年1月13日
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShiroService shiroService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * <p>
     * //     * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
     * //     * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * //     * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SecurityUtils.getSubject().getSession().touch();
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        //到数据库查是否有此对象
        User user = userService.getUserByName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<UserRole> listUserRoles = shiroService.listUserRolesByUserId(user.getId());
            Set<String> roleNameSet = Sets.newSet();
            List<Role> roleList = Lists.newArrayList();
            if (listUserRoles != null && listUserRoles.size() > 0) {
                for (UserRole userRole : listUserRoles) {
                    Role role = shiroService.getRoleByRoleId(userRole.getrId());
                    roleNameSet.add(role.getEnName());
                    roleList.add(role);
                }
            }

            //用户的角色集合
            info.setRoles(roleNameSet);

            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            List<String> permissionNameList = Lists.newArrayList();
            for (Role role : roleList) {
                List<Permission> permissionList = shiroService.listPermissionsByRoleId(role.getId());
                if (permissionList != null && permissionList.size() > 0) {
                    for (Permission permission : permissionList) {
                        permissionNameList.add(permission.getPattern());
                    }
                }
                info.addStringPermissions(permissionNameList);
            }

            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /*
     * 登录认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = String.valueOf(usernamePasswordToken.getUsername());
        User user = userService.getUserByName(username);
        SimpleAuthenticationInfo authenticationInfo = null;
        if (null != user) {
            //密码校验移交给了shiro的提供的一个接口实现类，所以这里注释掉
//            if (EndecryptUtils.checkMd5Password(username,password,user.getSalt(),user.getPassword())) {
            authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
//            }
        }
        return authenticationInfo;
    }
}
