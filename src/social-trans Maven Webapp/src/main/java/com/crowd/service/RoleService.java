package com.crowd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.Role;
import com.crowd.dao.RoleDao;
@Service("RoleService")
public class RoleService implements RoleDao{
	@Autowired
	private RoleDao roleDao;
	
	public List<Role> selectAllRole(){
       
        List<Role> list = roleDao.selectAllRole();
        return list;
    }

    public Boolean deleteRoleAndPermissionByRoleId(String roleId){
        //1：根据roleId更新p_role表，设置disabled字段为0，逻辑删除
        Role role = new Role();
        role.setRoleId(roleId);
        int roleResult = roleDao.deleteRoleById(roleId);
        //2：根据roleId删除p_rolepermission表中对应的权限
        int rolePermissionResult = roleDao.deletePermissionByRoleId(roleId);
        return (roleResult>0 && rolePermissionResult>0);
    }
 
    public int addUserRole(String userId,String roleId){
    	return roleDao.addUserRole(userId, roleId);
    }
   
    
    public int insertRole(Role role){
    	return roleDao.insertRole(role);
    }
    
    public  List<Role> verifyCode(Role role){
    	return roleDao.verifyCode(role);
    }
    
    public boolean updateById(Role role){
    	return roleDao.updateById(role);
    }
    
   public int deleteRoleById(String roleId){
	   return roleDao.deleteRoleById(roleId);
   }
   
   public String getRoleIdByRolename(String roleName){
	   return roleDao.getRoleIdByRolename(roleName);
			   
   }
   
   public int getLevelByRoleId(@Param("roleId")String roleId){
	   return roleDao.getLevelByRoleId(roleId);
   }

@Override
public int deletePermissionByRoleId(String roleId) {
	// TODO Auto-generated method stub
	return 0;
}
}
