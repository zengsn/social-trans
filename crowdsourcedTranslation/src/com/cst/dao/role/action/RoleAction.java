package com.cst.dao.role.action;

import java.io.File;
import java.rmi.ServerException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.cst.dao.core.action.BaseAction;
import com.cst.dao.core.constant.Constant;
import com.cst.dao.role.entity.Role;
import com.cst.dao.role.entity.RolePrivilege;
import com.cst.dao.role.entity.RolePrivilegeId;
import com.cst.dao.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {

	@Resource
	private RoleService roleService;
	private List<Role> roleList;// 用户列表
	private Role role;
	private String[] privilegeIds;// 权限集合

	// 跳转的页面用字符来表示
	// 列表页面
	public String listUI() throws Exception {
		// 加载权限集合
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		try {
			roleList = roleService.findObjects();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载权限集合
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					// 创建一个哈希对象set
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					// 循环取出privilegeIds集合中的权限值
					for (int i = 0; i < privilegeIds.length; i++) {
						// 添加到哈希set中
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		// 加载权限集合
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			role = roleService.findObjectById(role.getRoleId());
			//处理权限回显
			//权限集合不为空
			if(role.getRolePrivileges() != null){
				//获取集合长度
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				//循环赋值
				for(RolePrivilege rp: role.getRolePrivileges()){
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		System.out.println("edit");
		try {
			if (role != null) {
				if (privilegeIds != null) {
					// 创建一个哈希对象set
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					// 循环取出privilegeIds集合中的权限值
					for (int i = 0; i < privilegeIds.length; i++) {
						// 添加到哈希set中
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
