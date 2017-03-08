package com.qingtian.apps.permission.service;

import com.qingtian.apps.permission.entity.Permission;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
@Service("permissionService")
public class PermissionService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 根据角色id来查选权限集合
     * @param roleId
     * @return
     */
    public List<Permission> selectPermissionByRoleId(String roleId) throws Exception{

        List<Permission> list = sqlSession.selectList("Permission.selectPermissionByRoleId",roleId);
        return list;
    }
}
