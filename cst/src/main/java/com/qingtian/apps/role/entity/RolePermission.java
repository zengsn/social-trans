package com.qingtian.apps.role.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/15.
 */
public class RolePermission implements Serializable{

    private String roleId;
    private String permissionId;
    private String disabled;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
