package com.crowd.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crowd.bean.Role;
import com.crowd.bean.User;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
@Controller  
@RequestMapping(value = "user")  
public class UserController {
	
	    @Autowired  
	    private UserService userService;  
	    @Autowired
	    private RoleService roleService;
	    
//	    @RequestMapping(value="query", method=RequestMethod.POST)
//	public ModelAndView selectUserById(@RequestParam("userId") String userId) {
//		User user = new User();
//		user = userService.selectUserById(userId);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("result");
//		return modelAndView;
//	}
//	    
//	    @RequestMapping(value="query",method=RequestMethod.GET)
//	    public ModelAndView selectUserById1(User user){
//	    	ModelAndView mv =new ModelAndView();
//	    	mv.setViewName("regist");
//	    	return mv;
//	    }
	    //用户注册
	    @RequestMapping(value="register",method=RequestMethod.GET)
	    public ModelAndView AddUser1(User user)throws Exception{
	    	ModelAndView mv =new ModelAndView();
	    	mv.setViewName("register");
	    	return mv;
	    }
	    @RequestMapping(value="register",  method=RequestMethod.POST)
	    public String AddUser(User user)throws Exception{
		// 随机产生一个GUID
		ModelAndView mv = new ModelAndView();
		  boolean isExit = userService.verifyAccount(user.getAccount());
		System.out.println(isExit);
		if (isExit) {	
			if(user.getAccount()==""||user.getAccount().equals("")){
				mv.setViewName("register");
				mv.addObject("Accounterror", "用户ID不能为空！");
			}

			else if(user.getPassword()==""||user.getPassword().equals("")){
				mv.setViewName("register");
				mv.addObject("Passworderror", "用户密码不能为空！");
			}
			else{
			String userId = UUID.randomUUID().toString();
			user.setUsername(user.getAccount());
			user.setUserId(userId);
			int UserSuccess = userService.insertUser(user);
			User user1 =userService.selectUserById(userId);
			int RoleSuccess = roleService.addUserRole(userId,roleService.getRoleIdByRolename(user1.getRole()));
			if (UserSuccess > 0 && RoleSuccess > 0) {
				mv.addObject("User", user1);
				return "login";
			} else {
				mv.setViewName("register");
				mv.addObject("error", "注册失败");
				}
			}
		} else {
			mv.addObject("error", "用户ID已经注册！");
			return "register";
		}
		return "register";
	}
	    //更新用户信息
	    @RequestMapping(value="updateUser",method=RequestMethod.GET)
	    public String UpdateUser1(HttpSession session,Model model)throws Exception{
	    	String account=(String) session.getAttribute("account");
	    	String userId = userService.getUserIdByAccount(account);
	    	User user = userService.selectUserById(userId);
	    	String hobby = user.getHobby();
	    	System.out.println(hobby);
	    	model.addAttribute("user", user);
	    	return "updateUser";
	    }
	    
	    @RequestMapping(value="updateUser", method=RequestMethod.POST)
	    public String UpdateUser(User user, Model model,HttpSession session) throws Exception{
		// 验证密码
		System.out.println(user);
		
		if (user.getUsername() == "" || user.getUsername().equals("")) {
			model.addAttribute("Usernameerror", "用户名不能为空");
		} else if (user.getPassword() == "" || user.getPassword().equals("")) {
			model.addAttribute("Passworderror", "用户密码不能为空！");
		} else {
			String account = (String) session.getAttribute("account");
			String userId = userService.getUserIdByAccount(account);
			user.setUserId(userId);
			int isSuccess = userService.UpdateUserByUserId(user);
			if (isSuccess > 0) {
				model.addAttribute("user", "user");
			} else {
				model.addAttribute("error", "更新失败");
			}
		}
		return "redirect:updateUser";
	}
	//查询用户
//	@RequestMapping("selectUser")
//	public ModelAndView SelectUser(String account)throws Exception{
//		ModelAndView mv=new ModelAndView();
//		if(account.equals("")||account==""){
//			mv.addObject("error", "查询ID不能为空");
//			mv.setViewName("redirect:index");
//		}
//		else{
//			String userId = userService.getUserIdByAccount(account);
//			User user = userService.selectUserById(userId);
//			if(user==null){
//				mv.addObject("error", "查询ID不存在！");
//				mv.setViewName("redirect:/index");
//			}else{
//				mv.addObject("user", user);
//				mv.setViewName("Userindex");
//			}
//		}
//		return mv;
//	}
	@RequestMapping("selectUser")
	public String SelectUser(String account,Model model)throws Exception{
		
		if(account.equals("")||account==""){
			model.addAttribute("error", "查询ID不能为空");
			return "redirect:/index.jsp";
		}
		else{
			String userId = userService.getUserIdByAccount(account);
			User user = userService.selectUserById(userId);
			if(user==null){
				model.addAttribute("error", "查询用户不存在");
				return "redirect:/index.jsp";
			}else{
				model.addAttribute("user",user);
				return "Userindex";
			}
		}
	}
	//用户主页
	@RequestMapping("userData")
	public ModelAndView UserData(HttpSession session){
		ModelAndView mv= new ModelAndView();
		String account= (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		User user = userService.selectUserById(userId);
		mv.addObject("user", user);
		mv.setViewName("Userindex");
		return mv;
	}
	//用户登录
	 @RequestMapping(value="login",method=RequestMethod.GET)
	    public ModelAndView login1(User user)throws Exception{
	    	ModelAndView mv =new ModelAndView();
	    	mv.setViewName("login");
	    	return mv;
	    }
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String Login(String account,String password,HttpServletRequest request,HttpServletResponse response){
		
		System.out.println(account);
		boolean isSuccess = userService.Userlogin(account, password);	
		Cookie cookieAccount = new Cookie("accountCookie",account);
		cookieAccount.setMaxAge(3600); //Cookie保存时间
		cookieAccount.setPath(request.getContextPath());
		//创建用户密码Cookie对象
		Cookie cookiePassword = new Cookie("password",password);
		cookiePassword.setMaxAge(3600); //Cookie保存时间
		cookiePassword.setPath(request.getContextPath());
		//添加到客户端
		response.addCookie(cookieAccount);
		response.addCookie(cookiePassword);
		HttpSession session = request.getSession(); 
		session.setAttribute("account", account);
		System.out.println(isSuccess);
		if(isSuccess){
			return  "redirect:/index.jsp";
		}else{
			return "login";
		}
	}
	
	
	
	
}
