package com.qingtian.apps.permission.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/17.
 */
public class PermissionMenu implements Serializable{

    private String permissionId;
    private String id;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
