package com.ftd.seckill.ftdservice.service.impl;

import com.ftd.seckill.ftdservice.entity.User;
import com.ftd.seckill.ftdservice.mapper.UserMapper;
import com.ftd.seckill.ftdservice.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  用户服务实现类
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User selectUserByEmail(String userEmail) {
        return baseMapper.selectUserByEmail(userEmail);
    }

    @Override
    public User selectUserByMobile(String mobile) {
        return baseMapper.selectUserByMobile(mobile);
    }

    @Override
    public User selectUserByCode(String userName) {
        return baseMapper.selectUserByCode(userName);
    }
}
