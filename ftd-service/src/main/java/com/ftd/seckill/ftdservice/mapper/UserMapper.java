package com.ftd.seckill.ftdservice.mapper;

import com.ftd.seckill.ftdservice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * User Mapper 接口
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
public interface UserMapper extends BaseMapper<User> {

    User selectUserByEmail(String userEmail);

    User selectUserByMobile(String mobile);

    User selectUserByCode(String userName);
}
