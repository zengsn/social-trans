package com.crowd.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.crowd.bean.Role;
import com.crowd.service.RoleService;

@RequestMapping("role")
public class RoleController {
	  
	 @Autowired  
	    private RoleService roleService;  
    @RequestMapping("selectAllRole")
    public ModelAndView selectAllRole(){
        List<Role> lists = roleService.selectAllRole();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("redirect:/index.jsp");
        mv.addObject("admin", lists);
        return mv;
    }

    /**
     * 根据roleId来逻辑删除角色和角色权限
     * @param roleId
     * @return
     */
//    @RequestMapping("deleteRoleAndPermissionByRoleId")
//    public String deleteRoleAndPermissionByRoleId(String roleId){
//        if(StringUtils.isEmpty(roleId)){
//            return JsonUtils.genUpdateDataReturnJsonStr(false,"roleId为空");
//        }
//
//        try{
//            Boolean isSuccess = roleService.deleteRoleAndPermissionByRoleId(roleId);
//            if (isSuccess){
//                return JsonUtils.genUpdateDataReturnJsonStr(true,"删除成功");
//            }else {
//                return JsonUtils.genUpdateDataReturnJsonStr(false,"删除失败");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getMessage());
//        }
  //  }
    
    @RequestMapping("addRole")
    public String addRole(Model model){
        List<Role> lists = roleService.selectAllRole();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("redirect:/index.jsp");
        mv.addObject("admin", lists);
        return "";
    }
}
