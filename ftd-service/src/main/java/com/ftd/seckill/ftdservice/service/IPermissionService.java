package com.ftd.seckill.ftdservice.service;

import com.ftd.seckill.ftdservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ftd
 * @since 2023-01-17 12:06:22
 */
public interface IPermissionService extends IService<Permission> {
    public Permission getPermissionByName(String permissionName);
    public List<String> getPermissionValuesByUserId(String userId);
}
