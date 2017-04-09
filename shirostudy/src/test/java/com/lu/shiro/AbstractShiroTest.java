package com.lu.shiro;

import com.lu.shiro.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;

/**
 * Created by Administrator on 2017/4/9.
 */
public abstract class AbstractShiroTest {
    abstract public String iniPath();

    @Before
    public void prepare() {
        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory(iniPath());
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
    }

    protected Subject login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = TokenUtil.createTokenByUsernameAndPassword(username, password);
        subject.login(authenticationToken);
        return subject;
    }

}
