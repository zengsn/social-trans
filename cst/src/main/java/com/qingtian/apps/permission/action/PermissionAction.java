package com.qingtian.apps.permission.action;


import com.alibaba.fastjson.JSON;
import com.qingtian.apps.permission.entity.Permission;
import com.qingtian.apps.permission.service.PermissionService;
import com.qingtian.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import java.util.ArrayList;
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
    public String addPermission(@RequestParam HashMap<String,String> resultParams) {
        //获取前台的JSON字符串
        String userParam = resultParams.get("userParam");
        String permissionIdList = resultParams.get("permissionIdList");
        String permissionNameList = resultParams.get("permissionNameList");
        //获取权限id的List
        List<String> permissionIdReList = (List<String>) JSON.parse(permissionIdList);
        //获取权限name的List
        List<String> permissionNameReList = (List<String>) JSON.parse(permissionNameList);
        //获取用户信息
        Map userMap = (Map) JSON.parse(userParam);

        //验证userId非空
        String userId = (String)userMap.get("userId");
        if(userId == null || "".equals(userId)){
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"userId为空");
        }
        //验证角色名非空
        String rolename = (String)userMap.get("rolename");
        if(rolename == null || "".equals(rolename)){
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"rolename为空");
        }

        try{
            Boolean isSuccess = permissionService.addPermission(userMap,permissionIdReList);
            if (isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"保存角色成功");
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"保存角色失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getMessage());
        }
    }

    @RequestMapping("updatePermission.do")
    public String updatePermission(@RequestParam HashMap<String,String> resultParams){
        //获取前台的JSON字符串
        String roleParam = resultParams.get("roleParam");
        String permissionIdList = resultParams.get("permissionIdList");
        String permissionNameList = resultParams.get("permissionNameList");

        //获取权限id的List
        List<String> permissionIdReList = (List<String>) JSON.parse(permissionIdList);
        //获取权限name的List
        List<String> permissionNameReList = (List<String>) JSON.parse(permissionNameList);
        //获取用户信息
        Map roleMap = (Map)JSON.parse(roleParam);

        String roleId = (String)roleMap.get("roleId");
        if(roleId == null || "".equals(roleId)){
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"roleId为空");
        }

        try{
            Boolean isSuccess = permissionService.updatePermission(roleMap,permissionIdReList);
            if(isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"更新成功");
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getMessage());
        }

    }

    /**
     * 添加权限
     */
    @RequestMapping("addPermissionAndMenu.do")
    public String addPermissionAndMenu(@RequestParam HashMap<String,String> params){
        //获取前台的JSON字符串
        String permissionParam = params.get("params");
        String menuIds = params.get("menuIds");

        //获取权限信息
        Map permissionMap = (Map)JSON.parse(permissionParam);
        String permissionName = (String)permissionMap.get("permissionName");
        if(permissionName == null || "".equals(permissionName)){
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"permissionName为空");
        }

        //获取菜单id的List
        List<String> menuIdList = (List<String>) JSON.parse(menuIds);

        try{
            Boolean isSuccess = permissionService.addPermissionAndMenu(permissionMap,menuIdList);
            if(isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"插入成功");
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"插入失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(true,"操作由于异常而失败"+e.getMessage());
        }
    }

}
