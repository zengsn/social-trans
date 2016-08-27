package com.cst.dao.user.action;

import java.io.File;
import java.rmi.ServerException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.cst.dao.core.action.BaseAction;
import com.cst.dao.core.execption.ActionException;
import com.cst.dao.user.entity.User;
import com.cst.dao.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	private List<User> userList;//用户列表
	private User user;

	
	private File headImage;//用户头像
	private String headImageFileName;//头像名
	

	
	//跳转的页面用字符来表示
	//列表页面
	public String listUI() throws Exception{
		try {
			userList = userService.findObjects();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(user != null){
				//处理头像
				if(headImage != null){
					//1、保存头像到upload/user
					//以后将图片放在服务器上以url获取
					//获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//利用UUID产生一个36位的随机数
					//replaceAll("-","")将产生的随机数中的-用空字符代替
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImageFileName.substring(headImageFileName.lastIndexOf("."));
					//String fileName="abc";
					//复制文件
					//File(文件地址，文件名)
					FileUtils.copyFile(headImage, new File(filePath, fileName));
					
					//2、设置用户头像路径
					user.setHeadImage("user/" + fileName);
				}
				userService.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		if (user != null && user.getId() != null) {
			user = userService.findObjectById(user.getId());
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		System.out.println("edit");
		try {
			if(user != null){
				//处理头像
				if(headImage != null){
					//1、保存头像到upload/user
					//以后将图片放在服务器上以url获取
					//获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					//利用UUID产生一个36位的随机数
					//replaceAll("-","")将产生的随机数中的-用空字符代替
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImageFileName.substring(headImageFileName.lastIndexOf("."));
					//复制文件
					//File(文件地址，文件名)
					FileUtils.copyFile(headImage, new File(filePath, fileName));
					
					//2、设置用户头像路径
					user.setHeadImage("user/" + fileName);
				}
				userService.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	//删除
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	//校验用户帐号唯一
		public void verifyAccount(){
			try {
				//1、获取帐号
				//user.getAccount().equals(' ') && user.getAccount() != null
				if(user != null && StringUtils.isNotBlank(user.getAccount())){
					//2、根据帐号到数据库中校验是否存在该帐号对应的用户
					List<User> list = userService.findUserByAccountAndId(user.getAccount(),user.getId());
					String strResult = "true";
					if(list != null && list.size() > 0){
						//说明该帐号已经存在
						strResult = "false";
					}
					
					//输出
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write(strResult.getBytes());
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
	public File getHeadImage() {
		return headImage;
	}
	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}
	public String getHeadImageFileName() {
		return headImageFileName;
	}
	public void setHeadImageFileName(String headImageFileName) {
		this.headImageFileName = headImageFileName;
	}
}
