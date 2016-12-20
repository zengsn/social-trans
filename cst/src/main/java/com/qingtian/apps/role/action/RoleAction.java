package com.qingtian.apps.role.action;

import com.github.pagehelper.StringUtil;
import com.qingtian.apps.role.entity.Role;
import com.qingtian.apps.role.service.RoleService;
import com.sun.tools.javac.util.StringUtils;
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
    @RequestMapping("selectAll.do")
    public String selectAll(Role role){
        List<Role> lists = roleService.selectAll(role);
        return JsonUtils.genUpdateDataReturnJsonStr(true,"查询角色列表成功",lists);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping("addRole.do")
    public String addRole(Role role){

        String rolename = role.getRolename();
        if(rolename == null || "".equals(rolename)){
            logger.error("RoleAction ------- addRole : rolename 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"rolename为空");
        }

        String code = role.getCode();
        if(code == null || "".equals(code)){
            logger.error("RoleAction ------- addRole : code 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"code为空");
        }


        Boolean isSuccess = roleService.addRole(role);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"新增角色成功");
        }else {
            logger.error("RoleAction ------- addRole : 新增角色失败");
            return JsonUtils.genUpdateDataReturnJsonStr(false,"新增角色失败");
        }
    }

    /**
     * 验证角色代码唯一性
     * @param code
     * @return
     */
    @RequestMapping("verifyAccount.do")
    public String verifyAccount(String code){


        //验证角色代码非空
        if(code == null || "".equals(code)){
            logger.error("RoleAction ------- verifyAccount : code 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"code为空");
        }


        Boolean Vresult = roleService.verifyAccount(code);
        if(Vresult){
            //角色代码已存在
            return JsonUtils.genUpdateDataReturnJsonStr(true,"角色代码已存在");
        }else {
            //角色代码不存在
            return JsonUtils.genUpdateDataReturnJsonStr(false,"角色代码不存在");
        }
    }

    /**
     * 根据角色id修改角色资料
     * @param role
     * @return
     */
    @RequestMapping("updateRole.do")
    public String updateRole(Role role){

        //验证角色id非空
        String id = role.getId();
        if(StringUtil.isEmpty(id)){
            logger.error("RoleAction ------- updateRole : id 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"id为空");
        }

        //验证角色代码非空
        String code = role.getCode();
        if(StringUtil.isEmpty(code)){
            logger.error("RoleAction ------- updateRole : code 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"code为空");
        }

        //验证角色名称非空
        String rolename = role.getRolename();
        if(StringUtil.isEmpty(rolename)){
            logger.error("RoleAction ------- updateRole : rolename 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"rolename 为空");
        }

        Boolean isSuccess = roleService.updateRole(role);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"更新角色资料成功");
        }else{
            return JsonUtils.genUpdateDataReturnJsonStr(false,"更新角色资料失败");
        }
    }

    @RequestMapping("deleteRoleById")
    public String deleteRoleById(String id){
        //验证id非空
        if(StringUtil.isEmpty(id)){
            logger.error("RoleAction ------- deleteRoleById : id 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"id 为空");
        }

        Boolean isSuccess = roleService.deleteRoleById(id);
        if(isSuccess){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"删除角色成功");
        }else{
            return JsonUtils.genUpdateDataReturnJsonStr(false,"删除角色失败");
        }
    }
}
