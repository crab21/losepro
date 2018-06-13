package com.example.combinerabbit.config.shiro;

import com.example.combinerabbit.model.Role;
import com.example.combinerabbit.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFormAuthentication extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        System.out.println(principal.getUid() + "-------------" + principal.getPassword() + "---" + principal.getRoles().size());
        WebUtils.redirectToSavedRequest(request, response, "/rabb");
        return false;
    }
}

