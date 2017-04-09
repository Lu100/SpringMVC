package com.lu.shiro.configure;

import com.google.common.collect.Lists;
import com.lu.shiro.realm.MyShiroRealm;
import com.lu.shiro.role.MyAuthorizer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * shiro 配置
 */
@Configuration
public class ShiroConfigure {
    @Bean
    public SecurityManager securityManager(@Autowired MyShiroRealm myShiroRealm) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //账户验证器
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Lists.newArrayList(myShiroRealm));
        defaultSecurityManager.setAuthenticator(authenticator);

        //权限、角色验证器
        MyAuthorizer authorizer = new MyAuthorizer();

        defaultSecurityManager.setAuthorizer(authorizer);
        defaultSecurityManager.setRealm(myShiroRealm);
        return defaultSecurityManager;
    }

    @Autowired
    private SecurityManager securityManager;

    @PostConstruct
    public void configureSecurityUtils() {
        SecurityUtils.setSecurityManager(securityManager);
    }
}
