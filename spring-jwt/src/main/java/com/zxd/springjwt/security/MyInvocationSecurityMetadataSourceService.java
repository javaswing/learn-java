package com.zxd.springjwt.security;

import com.zxd.springjwt.domain.Role;
import com.zxd.springjwt.domain.RolePermission;
import com.zxd.springjwt.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest httpServletRequest = ((FilterInvocation) object).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String url = it.next();
            if(new AntPathRequestMatcher(url).matches(httpServletRequest)) {
                return map.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResource();
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private void loadResource() {
        map = new HashMap<>(16);

        List<RolePermission>  list = permissionMapper.getRolePermissions();

        for (RolePermission rp : list) {
            String url = rp.getUrl();
            String roleName = rp.getRoleName();
            ConfigAttribute ca = new SecurityConfig(roleName);

            if(map.containsKey(url)) {
                map.get(url).add(ca);
            } else {
                List<ConfigAttribute> ls = new ArrayList<>();
                ls.add(ca);
                map.put(url, ls);
            }
        }
    }
}
