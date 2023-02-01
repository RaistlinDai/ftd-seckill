package com.ftd.seckill.ftdservice.service;

import com.ftd.seckill.ftdservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
public interface IUserService extends IService<User> {

    User selectUserByEmail(String userEmail);

    User selectUserByMobile(String mobile);

    User selectUserByCode(String userName);
}
