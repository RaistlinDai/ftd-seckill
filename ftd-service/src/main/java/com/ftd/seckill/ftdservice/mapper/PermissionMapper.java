package com.ftd.seckill.ftdservice.mapper;

import com.ftd.seckill.ftdservice.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    public Permission getPermissionByName(String permissionName);
    public List<String> getPermissionValuesByUserId(String userId);
}
