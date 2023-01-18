package com.ftd.seckill.ftdservice.service.impl;

import com.ftd.seckill.ftdservice.entity.Permission;
import com.ftd.seckill.ftdservice.mapper.PermissionMapper;
import com.ftd.seckill.ftdservice.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public Permission getPermissionByName(String permissionName) {
        return baseMapper.getPermissionByName(permissionName);
    }

    @Override
    public List<String> getPermissionValuesByUserId(String userId) {
        return baseMapper.getPermissionValuesByUserId(userId);
    }

}
