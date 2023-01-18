package com.ftd.seckill.security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class SecurityUserDetails implements UserDetails {

    // 当前登录用户信息
    private transient User currentUserInfo;

    // 当前权限
    private List<String> permissionValueList;

    public SecurityUserDetails(User user){
        if (user != null)
            this.currentUserInfo = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionValue: permissionValueList){
            if (StringUtils.hasText(permissionValue))
                continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        if (currentUserInfo != null)
            return currentUserInfo.getUserPassword();
        else
            return null;
    }

    @Override
    public String getUsername() {
        if (currentUserInfo != null)
            return currentUserInfo.getUserName();
        else
            return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
