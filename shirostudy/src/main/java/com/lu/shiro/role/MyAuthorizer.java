package com.lu.shiro.role;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolverAware;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolverAware;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;
import java.util.List;

/**
 * 自定义权限验证器
 * <P>{@link RolePermissionResolver} 该接口需要实现权限集合与角色匹配</P>
 * <P>{@link PermissionResolverAware} 该接口接收权限解释器</P>
 * <P>{@link RolePermissionResolverAware} 该接口接受角色解释器</P>
 */
public class MyAuthorizer implements Authorizer {

    private Multimap<String, Permission> permissionMultimap = ArrayListMultimap.create();

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        System.err.println(principals.getPrimaryPrincipal().toString() + ":" + permission);
        return true;
    }

    @Override
    public boolean isPermitted(PrincipalCollection subjectPrincipal, Permission permission) {
        return false;
    }

    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, String... permissions) {
        return new boolean[0];
    }

    @Override
    public boolean[] isPermitted(PrincipalCollection subjectPrincipal, List<Permission> permissions) {
        return new boolean[0];
    }

    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, String... permissions) {
        return false;
    }

    @Override
    public boolean isPermittedAll(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) {
        return false;
    }

    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, String permission) throws AuthorizationException {

    }

    @Override
    public void checkPermission(PrincipalCollection subjectPrincipal, Permission permission) throws AuthorizationException {

    }

    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, String... permissions) throws AuthorizationException {

    }

    @Override
    public void checkPermissions(PrincipalCollection subjectPrincipal, Collection<Permission> permissions) throws AuthorizationException {

    }

    @Override
    public boolean hasRole(PrincipalCollection subjectPrincipal, String roleIdentifier) {
        return false;
    }

    @Override
    public boolean[] hasRoles(PrincipalCollection subjectPrincipal, List<String> roleIdentifiers) {
        return new boolean[0];
    }

    @Override
    public boolean hasAllRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) {
        return false;
    }

    @Override
    public void checkRole(PrincipalCollection subjectPrincipal, String roleIdentifier) throws AuthorizationException {

    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, Collection<String> roleIdentifiers) throws AuthorizationException {

    }

    @Override
    public void checkRoles(PrincipalCollection subjectPrincipal, String... roleIdentifiers) throws AuthorizationException {

    }
}
