package com.qingtian.apps.role.service;

import com.qingtian.apps.role.entity.Role;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("roleService")
public class RoleService {

    private Logger logger = LoggerFactory.getLogger("RoleService.class");

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 查询全部角色
     * @return
     */
    public List<Role> selectAllRole(){
        Role role = new Role();
        //角色可用
        role.setDisabled("1");
        List<Role> list = sqlSession.selectList("Role.selectAllRole",role);
        return list;
    }

    public Boolean deleteRoleAndPermissionByRoleId(String roleId){

        //1：根据roleId更新p_role表，设置disabled字段为0，逻辑删除
        Role role = new Role();
        role.setRoleId(roleId);
        role.setDisabled("0");
        int roleResult = sqlSession.update("Role.deleteRoleById",role);
        //2：根据roleId删除p_rolepermission表中对应的权限
        int rolePermissionResult = sqlSession.delete("Role.deletePermissionByRoleId",roleId);
        return (roleResult>0 && rolePermissionResult>0);
    }

}
