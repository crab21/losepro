package com.example.combinerabbit.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String password = new String(utoken.getPassword());
        String dbpassword = (String) info.getCredentials();
        System.out.println(password + "-------------------");
        System.out.println(dbpassword + "--------------------");
        return this.equals(password, dbpassword);
    }
}
