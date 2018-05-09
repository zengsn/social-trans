package com.crowd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crowd.bean.ReceiveTask;
import com.crowd.bean.Role;
import com.crowd.bean.User;
import com.crowd.service.ReceiveTaskService;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
@Controller  
@RequestMapping(value = "admin")  
public class AdminController {
	
	 	@Autowired  
	    private UserService userService;  
	    @Autowired
	    private RoleService roleService;
	    @Autowired
		private ReceiveTaskService retaskService;
	    
	@RequestMapping("deleteUser")
	public String deleteUser(String userId,Model model){
		int isSuccess = userService.deleteUserByUserId(userId);
		if(isSuccess<0)
			model.addAttribute("msg", "删除用户失败");
			return "forward:/admin/selectAllUser";
	}
	
	  @RequestMapping(value="/",method=RequestMethod.GET)
	    public ModelAndView admin()throws Exception{
	    	ModelAndView mv =new ModelAndView();
	    	mv.setViewName("/manage/admin");
	    	return mv;
	    }
	  
	@RequestMapping("selectAllUser")
	public String selectAllUser(Model model){
		List<User> list = userService.selectAllUser();
		System.out.println(list);
		if(list.size()>0)
		model.addAttribute("UserList", list);
		return "manage/userManage";
	}
	
	@RequestMapping("selectAllRole")
	public String selectAllRole(Model model){
		List<Role> list = roleService.selectAllRole();
		if(list.size()>0)
			model.addAttribute("roleList", list);
			return "manage/roleManage";
	}
	
	@RequestMapping("deleteRole")
	public String deleteRole(String roleId,Model model){
		int isSuccess = roleService.deleteRoleById(roleId);
		if(isSuccess<0)
			model.addAttribute("msg", "删除失败");
			return "forward:/admin/selectAllRole";
		
	}
	
	@RequestMapping("addRole")
	public String addRole(Role role,Model model){
		List<Role> role1 =  roleService.verifyCode(role);
		if(role1.size()==0){
			if(role.getRolename()==""||role.getRolename().equals("")){
				
				model.addAttribute("nameerror", "Rolename不能为空！");
			}

			else if(role.getRolelevel()<=0){
				model.addAttribute("levelerror", "Rolelevel()小于1！");
				
			}
			else{
				int isSuccess = roleService.insertRole(role);
			}
		}
		return "forward:/admin/selectAllRole";
	}
	
	@RequestMapping("selectAllTask")
	public String selectAllTask(Model model){
		List<ReceiveTask> list = retaskService.getReceiveTaskList();
		if(list.size()>0)
			model.addAttribute("taskList", list);
			return "manage/taskManage";
	}
	
	@RequestMapping("deleteTask")
	public String deleteTask(String taskId,Model model){
		int isSuccess = retaskService.deleteTaskById(taskId);
		if(isSuccess<0)
			model.addAttribute("msg", "删除失败");
			return "forward:/admin/selectAllTask";
	}
}
