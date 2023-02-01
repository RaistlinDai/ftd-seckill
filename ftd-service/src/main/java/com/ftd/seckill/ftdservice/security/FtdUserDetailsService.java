package com.ftd.seckill.ftdservice.security;

import com.ftd.seckill.ftdservice.entity.User;
import com.ftd.seckill.ftdservice.service.IPermissionService;
import com.ftd.seckill.ftdservice.service.IUserService;
import com.ftd.seckill.security.entity.SecurityUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FtdUserDetailsService implements UserDetailsService {
    @Autowired
    IUserService userService;
    @Autowired
    IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " not found");

        com.ftd.seckill.security.entity.User currUser = new com.ftd.seckill.security.entity.User();
        BeanUtils.copyProperties(user, currUser);
        // 获得权限
        List<String> permissions = permissionService.getPermissionValuesByUserId(user.getUserId());
        // 创建UserDetails
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(currUser);
        securityUserDetails.setPermissionValueList(permissions);
        return securityUserDetails;
    }
}
