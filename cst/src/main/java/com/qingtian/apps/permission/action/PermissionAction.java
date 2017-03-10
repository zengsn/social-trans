package com.qingtian.apps.permission.action;

import com.alibaba.fastjson.JSON;
import com.qingtian.apps.permission.entity.AddPermission;
import com.qingtian.apps.permission.entity.Permission;
import com.qingtian.apps.permission.service.PermissionService;
import com.qingtian.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/8.
 */
@RestController
@RequestMapping("Permission")
public class PermissionAction {

    @Autowired
    private PermissionService permissionService;


    /**
     * 根据角色id来获取权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping("selectPermissionByRoleId")
    public String selectPermissionByRoleId(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return JsonUtils.genUpdateDataReturnJsonStr(false, "roleId为空");
        }
        try {
            List<Permission> list = permissionService.selectPermissionByRoleId(roleId);
            if (list != null && list.size() > 0) {
                return JsonUtils.genUpdateDataReturnJsonStr(true, "获取权限集合成功", list);
            } else {
                return JsonUtils.genUpdateDataReturnJsonStr(false, "获取权限集合失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false, "操作由于异常而失败" + e.getStackTrace());
        }

    }

    /**
     * 查询所有的权限
     *
     * @return
     */
    @RequestMapping("selectAllPermission.do")
    public String selectAllPermission() {

        try {
            List<Permission> list = permissionService.selectAllPermission();
            if (list != null && list.size() > 0) {
                return JsonUtils.genUpdateDataReturnJsonStr(true, "查询权限集合成功", list);
            } else {
                return JsonUtils.genUpdateDataReturnJsonStr(false, "查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false, "操作由于异常而失败");
        }
    }

    /**
     * 插入权限
     *
     * @param
     * @return
     */
    @RequestMapping("addPermission.do")
    public String addPermission(@RequestParam HashMap<String,String> params) {

        String param1 = params.get("param1");
        AddPermission addPermission = JSON.parseObject(param1,AddPermission.class);

        //验证userId非空
//        if(userId == null || "".equals(userId)){
//            return  JsonUtils.genUpdateDataReturnJsonStr(false,"userId为空");
//        }
//
//        if(rolename == null || "".equals(rolename)){
//            return  JsonUtils.genUpdateDataReturnJsonStr(false,"rolename为空");
//        }

        return null;
    }
}
