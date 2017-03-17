package com.qingtian.apps.permission.service;

import com.qingtian.apps.permission.entity.Permission;


import com.qingtian.apps.permission.entity.PermissionMenu;
import com.qingtian.apps.role.entity.Role;
import com.qingtian.apps.role.entity.RolePermission;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String,String> p = new HashMap<String,String>();
        p.put("roleId",roleId);
//        p.put("disabled","1");
        List<Permission> list = sqlSession.selectList("Permission.selectPermissionByRoleId",p);
        return list;
    }

    /**
     * 查询所有权限
     * @return
     */
    public List<Permission> selectAllPermission(){
//        Permission p = new Permission();
//        p.setDisabled("1");
        List<Permission> list = sqlSession.selectList("Permission.selectAllPermission");
        return list;
    }

    /**
     *
     * @param p
     * @param permissionIdReList
     * @return
     */
    public Boolean addPermission(Map p,List<String> permissionIdReList)throws Exception{
        //获取用户id
        String userId = (String)p.get("userId");
        //生成一个roleId
        String roleId = new RandomGUID().toString();
        //插入p_userrole表(userId,roleId)
        Map userroleMap = new HashMap();
        userroleMap.put("userId",userId);
        userroleMap.put("roleId",roleId);
        int userRoleResult = sqlSession.insert("Role.addUserRole",userroleMap);
        //插入p_role表(roleId,rolename,description,disabled)
        Role role = new Role();
        //设置角色id
        role.setRoleId(roleId);
        //设置角色名称
        String rolename = (String)p.get("rolename");
        role.setRolename(rolename);
        //设置角色说明
        String description = (String)p.get("description");
        role.setDescription(description);
        //设置角色是否可用
        role.setDisabled("1");
        //插入角色表
        int roleResult = sqlSession.insert("Role.insert",role);
        List<RolePermission> addList = new ArrayList<>();
        //循环插入表p_rolepermission(roleId,permissionId) n次
        for(int i=0;i<permissionIdReList.size();i++){
            RolePermission rp = new RolePermission();
            //设置角色id
            rp.setRoleId(roleId);
            //设置权限id
            rp.setPermissionId(permissionIdReList.get(i));
            //设置权限可用
            rp.setDisabled("1");
            //加入list中
            addList.add(rp);
        }
        int permissionResult = sqlSession.insert("Role.insertRolePermissionBatch",addList);
        return (userRoleResult>0 & roleResult>0 & permissionResult>0);
    }


    public Boolean updatePermission(Map p,List<String> permissionIdReList)throws Exception{
        //1：把原来的权限删了
        String roleId = (String)p.get("roleId");
        int deleteRolePermissionResult = sqlSession.delete("Role.deletePermissionByRoleId",roleId);
        //2：更新p_role表
        int roleResult = sqlSession.update("Role.updateById",p);
        //3：插入p_rolepermission表
        List<RolePermission> addList = new ArrayList<>();
        RolePermission rolePermission = null;
        //循环插入表p_rolepermission
        for(int i=0;i<permissionIdReList.size();i++){
            rolePermission = new RolePermission();
            //设置角色id
            rolePermission.setRoleId(roleId);
            //设置权限id
            rolePermission.setPermissionId(permissionIdReList.get(i));
            //设置权限可用
            rolePermission.setDisabled("1");
            //加入list中
            addList.add(rolePermission);
        }
        int permissionResult = sqlSession.insert("Role.insertRolePermissionBatch",addList);

//        int updateRolePermissionResult = sqlSession.update("Role.updateRolePermissionBatch",updateList);
        return (deleteRolePermissionResult>0 & roleResult>0 & permissionResult>0);
    }

    public Boolean addPermissionAndMenu(Map p,List<String> menuIdList)throws Exception{
        //1：生成一个permissionId
        String permissionId = new RandomGUID().toString();
        //2：插入p_permission表
        p.put("permissionId",permissionId);
        p.put("disabled","1");
        int permissionResult = sqlSession.insert("Permission.insert",p);
        //3：插入p_permissionmenu表
        PermissionMenu pm = null;
        List<PermissionMenu> addList = new ArrayList<>();
        for (int i=0;i<menuIdList.size();i++){
            pm = new PermissionMenu();
            pm.setId(menuIdList.get(i));
            pm.setPermissionId(permissionId);
            addList.add(pm);
        }
        int permissionMenuResult = sqlSession.insert("Permission.insertPermissionMenuBatch",addList);
        return (permissionResult>0 & permissionMenuResult>0);
    }
}
