package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.Role;
@Repository
public interface RoleDao {
	 List<Role> selectAllRole();
	 int insertRole(Role role);
	 List<Role> verifyCode(Role role);
	 boolean updateById(Role role);
	 int deleteRoleById(String roleId);
	 String getRoleIdByRolename(String roleName);
	 int addUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
	  //批量添加权限 
	 int deletePermissionByRoleId(String roleId);
	 int getLevelByRoleId(@Param("roleId")String RoleId);
	/* <!-- 批量添加权限 -->
	    <insert id="insertRolePermissionBatch" parameterType="java.util.List">
	        insert p_rolepermission(roleId,permissionId,disabled)
	        values
	        <foreach collection="list" item="item"  separator="," >
	            (#{item.roleId},#{item.permissionId},#{item.disabled})
	        </foreach>
	    </insert>

	    <update id="updateRolePermissionBatch" parameterType="java.util.List">
	        <foreach collection="list" item="item" index="index" open=""
	                 close="" separator=";">
	            UPDATE p_rolepermission
	            <set>
	                disabled = #{item.disabled}
	            </set>
	            WHERE roleId = #{item.roleId} AND permissionId =
	            #{item.permissionId}
	        </foreach>
	    </update>
	    */
}
