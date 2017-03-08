package com.qingtian.apps.permission.action;

import com.qingtian.apps.permission.entity.Permission;
import com.qingtian.apps.permission.service.PermissionService;
import com.qingtian.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import java.util.List;

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
     * @param roleId
     * @return
     */
    @RequestMapping("selectPermissionByRoleId")
    public String selectPermissionByRoleId(String roleId){
        if(StringUtils.isEmpty(roleId)){
            return JsonUtils.genUpdateDataReturnJsonStr(false,"roleId为空");
        }
        try{
            List<Permission> list = permissionService.selectPermissionByRoleId(roleId);
            if(list!=null && list.size()>0){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"获取权限集合成功",list);
            }else{
                return JsonUtils.genUpdateDataReturnJsonStr(false,"获取权限集合失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getStackTrace());
        }

    }
}
