package com.qingtian.apps.role.action;

import com.github.pagehelper.StringUtil;
import com.qingtian.apps.role.entity.Role;
import com.qingtian.apps.role.service.RoleService;
import com.qingtian.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@RestController
@RequestMapping("Role")
public class RoleAction {

    private Logger logger = LoggerFactory.getLogger("RoleAction.class");

    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 查询角色列表
     * @return
     */
    @RequestMapping("selectAllRole.do")
    public String selectAllRole( ){
        List<Role> lists = roleService.selectAllRole();
        return JsonUtils.genUpdateDataReturnJsonStr(true,"查询角色列表成功",lists);
    }

    /**
     * 根据roleId来逻辑删除角色和角色权限
     * @param roleId
     * @return
     */
    @RequestMapping("deleteRoleAndPermissionByRoleId")
    public String deleteRoleAndPermissionByRoleId(String roleId){
        if(StringUtils.isEmpty(roleId)){
            return JsonUtils.genUpdateDataReturnJsonStr(false,"roleId为空");
        }

        try{
            Boolean isSuccess = roleService.deleteRoleAndPermissionByRoleId(roleId);
            if (isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"删除成功");
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getMessage());
        }
    }
}
