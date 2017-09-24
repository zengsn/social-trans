package com.crowd.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.ChildFile;
import com.crowd.bean.ChildTask;
import com.crowd.bean.FileInfo;
import com.crowd.bean.Good;
import com.crowd.bean.Message;
import com.crowd.bean.ParentFile;
import com.crowd.bean.ReceiveTask;
import com.crowd.bean.User;
import com.crowd.service.AcceptTaskService;
import com.crowd.service.FileService;
import com.crowd.service.MessageService;
import com.crowd.service.ReceiveTaskService;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
import com.crowd.trans.SortTask;
import com.crowd.trans.SplitFile;
import com.crowd.utils.Constant;
import com.crowd.utils.ToolUtils;

@Controller
@RequestMapping("task")
public class TaskController {

	private String separator = File.separator;
	@Autowired
	private ReceiveTaskService retaskService;
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	@Autowired
	private AcceptTaskService acceptTaskService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageService messageService;
	
	 String encoding = Constant.FILE_ENCODING;

	/**
	 * 添加任务
	 * 
	 * @return 
	 */
	@RequestMapping(value = "uploadTask", method = RequestMethod.GET)
	public ModelAndView insertTask1(ReceiveTask receiveTask) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("uploadTask");
		return mv;
	}


	//上传任务
	@RequestMapping(value = "uploadTask", method = RequestMethod.POST)
	public String InsertTask(ReceiveTask receiveTask, HttpSession session,
			HttpServletRequest request,@RequestParam("file") MultipartFile myfile) throws IllegalStateException,
			IOException {
		SplitFile spiltFile = new SplitFile();
		String taskId = UUID.randomUUID().toString();
		receiveTask.setTaskId(taskId);
		String account = (String) session.getAttribute("account");
		String publishId = userService.getUserIdByAccount(account);
		receiveTask.setPublishId(publishId);
		String publisher = userService.selectUserById(publishId).getUsername();
		receiveTask.setPublisher(publisher);
		 byte[] bs= myfile.getBytes();
		 System.out.println(new String(bs));
		receiveTask.setTaskText(new String(bs));
		retaskService.insertTask(receiveTask);
		List<User> userList = userService.selectAllUser();
		String describe = receiveTask.getDescription();
		Iterator<User> users = userList.iterator();
		System.out.println(users);
		while(users.hasNext()){
			User user= users.next();
			String hobby = user.getHobby();
			if(hobby.contains(describe)){
				Message message = new Message();
				message.setTaskId(taskId);
				System.out.println(taskId);
				message.setUserId(user.getUserId());
				System.out.println(publishId);
				message.setState(0);
				message.setMessage("根据您的兴趣爱好，觉得‘"+receiveTask.getTaskName()+"’非常适合你！");
				System.out.println(message);
				messageService.insertMessage(message);
			}
		}
		if(receiveTask.getTotalNum()>1){
			List<String> textList = spiltFile.spiltString(receiveTask.getTaskText(), receiveTask.getTotalNum());
			Iterator<String> list = textList.iterator();
			int i=1;
			while(list.hasNext()){
				ReceiveTask childReTask = new ReceiveTask();
				String childTaskId = UUID.randomUUID().toString();
				childReTask.setTaskId(childTaskId);
				childReTask.setTaskName(receiveTask.getTaskName()+ "[卷"+ i + "]");
				childReTask.setDescription(receiveTask.getDescription());
				childReTask.setFinishTime(receiveTask.getFinishTime());
				childReTask.setPublisher(receiveTask.getPublisher());
				childReTask.setPublishId(receiveTask.getPublishId());
				childReTask.setTaskMoney(receiveTask.getTaskMoney());
				childReTask.setIsChild(i);
				childReTask.setTaskText(list.next());
				childReTask.setParentId(receiveTask.getTaskId());
				childReTask.setTotalNum(1);
				retaskService.insertTask(childReTask);
				i++;
			}
		}
		return "redirect:/index.jsp";
	}
	/**
	 * 查询任务列表
	 * 
	 * @return
	 * @throws IOException
	 */
	// 查看所有接收任务
	@RequestMapping("getReceiveTaskList")
	public String getTaskList(Model model) throws IOException {
		List<ReceiveTask> lists = retaskService.getReceiveTaskList();
		List<ReceiveTask> reList = new ArrayList<>();
		System.out.println(lists.size());
		List<String> text = new ArrayList<>();
		if (lists != null && lists.size() > 0) {
			Iterator<ReceiveTask> ac = lists.iterator();
			System.out.println(ac);
			while (ac.hasNext()) {
				ReceiveTask reTask = ac.next();
				if(reTask.getIsChild()==0){
					reList.add(reTask);
					String taskText = reTask.getTaskText();
					text.add(taskText);
				}
			}
			model.addAttribute("textList", text);
			model.addAttribute("reList", reList);
			return "taskList";
		} else {
			return "redirect:/index.jsp";
		}
	}
	
	// 领取任务
	@RequestMapping(value = "acceptTask", method = RequestMethod.POST)
	public String acceptTask(Model model, HttpServletRequest request,
			HttpSession session) throws Exception {

		String account = (String) session.getAttribute("account");

		if (account != null && account != "") {
			String userId = userService.getUserIdByAccount(account);
			String taskId = request.getParameter("taskId");
			AcceptTask acd = acceptTaskService.selectStateByUTID(userId, taskId);
			if (acd!=null) {
				model.addAttribute("taskId", taskId);
				model.addAttribute("error", "您已领取了此任务！");
				return "redirect:/task/taskDetail";
			} else {
				AcceptTask acceptTask = new AcceptTask();

				acceptTask.setUserId(userId);
				String accepter = userService.selectUserById(userId)
						.getUsername();
				acceptTask.setAccepter(accepter);
				String acceptId = UUID.randomUUID().toString();
				acceptTask.setAcceptId(acceptId);

				acceptTask.setTaskId(taskId);
				int isSuccess = acceptTaskService.insertAcceptTask(acceptTask);

				if (isSuccess > 0) {
					ReceiveTask receiveTask = retaskService
							.selectTaskByTaskId(taskId);
					receiveTask.setIsReceive(1);
					receiveTask.setReceiveNum(receiveTask.getReceiveNum() + 1);
					boolean update = retaskService.updateReTask(receiveTask);
					if (update) {
						model.addAttribute("success", "领取任务成功！");
						return "success";
					} else {
						model.addAttribute("error", "领取任务失败，任务已下架");
						return "redirect:taskList";
					}
				} else {
					model.addAttribute("error", "领取任务失败，任务已下架");
					return "redirect:taskList";
				}
			}
		} else {
			model.addAttribute("error", "您未登陆 ，请登陆后再领取任务");
			return "error";
		}
	}

	// 查看自己领取的任务
	@RequestMapping("unfinish")
	public String showMyTask(Model model, HttpSession session) {

		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		System.out.println(userId);
		// 查看接收任务
		List<ReceiveTask> acList = new ArrayList<>();
		List<String> taskIdList = acceptTaskService
				.selectTaskIdByuserId(userId);
		if (taskIdList.size() != 0) {
			Iterator<String> ac = taskIdList.iterator();

			// hasNext是取值取的是当前值.他的运算过程是判断下个是否有值如果有继续.
			ReceiveTask receiveTask = new ReceiveTask();
			while (ac.hasNext()) {
				String taskId = ac.next().toString();
				System.out.println(taskId);
				AcceptTask acd = acceptTaskService.selectStateByUTID(userId, taskId);
				receiveTask = retaskService.selectTaskByTaskId(taskId);
				if (acd.getIsSubmit() != 1) {
					acList.add(receiveTask);
				}
			}
			model.addAttribute("acList", acList);
		}
		if (acList.size() == 0) {
			model.addAttribute("acmsg", "您暂时未接收任何任务!");
		}
		return "unfinish";
	}

	@RequestMapping("finish")
	public String finish(Model model, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		List<ReceiveTask> finishList = new ArrayList<>();
		List<String> taskIdList = acceptTaskService
				.selectTaskIdByuserId(userId);
		if (taskIdList.size() != 0) {
			Iterator<String> ac = taskIdList.iterator();

			// hasNext是取值取的是当前值.他的运算过程是判断下个是否有值如果有继续.
			ReceiveTask receiveTask = new ReceiveTask();
			while (ac.hasNext()) {
				String taskId = ac.next().toString();
				System.out.println(taskId);
				AcceptTask acd = acceptTaskService.selectStateByUTID(userId, taskId);
				receiveTask = retaskService.selectTaskByTaskId(taskId);
				if (acd.getIsSubmit()==1) {
					finishList.add(receiveTask);
				}
			}
			model.addAttribute("finishList", finishList);
		} else {
			model.addAttribute("fmsg", "您暂时未完成任何任务!");
		}
		return "finish";
	}

	// 查看发布的任务
	@RequestMapping("release")
	public String release(Model model, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		List<ReceiveTask> reList = new ArrayList<>();
		List<ReceiveTask> lists = retaskService
				.selectReceiveTaskByuserId(userId);
		
		if (lists != null && lists.size() > 0) {
			Iterator<ReceiveTask> ac = lists.iterator();
			System.out.println(ac);
			while (ac.hasNext()) {
				ReceiveTask reTask = ac.next();
				if(reTask.getIsChild()==0){
					reList.add(reTask);
				}
			}
			model.addAttribute("reList", reList);
		} else {
			model.addAttribute("remsg", "暂时未发布任何任务");
		}
		return "release";
	}


	//提交任务
	@RequestMapping("submitTask")
	public String submitTask(Model model, HttpServletRequest request,
			HttpSession session,@RequestParam("file") MultipartFile myfile) throws IOException {
		String taskId = request.getParameter("taskId");
		String userId = userService.getUserIdByAccount((String) session
				.getAttribute("account"));
		String acceptId = acceptTaskService
				.selectAcceptIdByUTID(userId, taskId);
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		 byte[] bs= myfile.getBytes();
		 System.out.println(new String(bs));
		 acceptTask.setSubmitText(new String(bs));
		acceptTask.setIsSubmit(1);
		acceptTaskService
				.updateAcceptTask(acceptTask);
		return "unfinish";
	}
	
	@RequestMapping("checkAccept")
	public String checkAccept(String taskId, Model model) throws IOException {
//		SortTask sortTask = new SortTask();
		System.out.println(taskId);
		List<AcceptTask> acList = new ArrayList<>();
		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
		Collections.sort(acList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				AcceptTask actask1 = (AcceptTask) o1;
				AcceptTask actask2 = (AcceptTask) o2;
				System.out.println(actask1.getUserId());
				System.out.println(userService
						.selectUserById(actask1.getUserId()));
				String roleId1 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask1.getUserId()).getRole());
				System.out.println(roleId1);
				int good1Num = actask1.getGoods();
				int good2Num = actask2.getGoods();
				System.out.println(actask2.getUserId());
				String roleId2 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask2.getUserId()).getRole());
				System.out.println(roleId2);
				int level1 = roleService.getLevelByRoleId(roleId1);
				int level2 = roleService.getLevelByRoleId(roleId2);
				if( level2-level1==1){
					if((good2Num==0&&good1Num-good2Num<=3)||good1Num/good2Num<=3){
						return 1;
					}
					else{
						return -1;
					}
				}
				else if (level2-level1==2 ){
					if((good2Num==0&&good1Num-good2Num<=5)||good1Num/good2Num<=5){
						return 1;
					}
					else{
						return -1;
					}
				}
//					return 1;
				else if (level1-level2==1){
					if((good1Num==0&&good2Num-good1Num<=3)||good2Num/good1Num<=3){
						return -1;
					}
					else{
						return 1;
					}
				}
				else if (level1-level2==2){
					if((good1Num==0&&good2Num-good1Num<=5)||good2Num/good1Num<=5){
						return -1;
					}
					else{
						return 1;
					}
				}
				else {
					
					if (good1Num < good2Num)
						return 1;
					else if (good1Num > good2Num)
						return -1;
					else
						return 0;
				}
			}

		});
		System.out.println(acList);
		model.addAttribute("acList", acList);
		return "checkAccept";
	}
	// 删除任务
	@RequestMapping("deleteTask")
	public String deleteTask(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		int isSuccess = retaskService.deleteTaskById(taskId);

		if (isSuccess > 0) {
			int is = acceptTaskService.deleteAcceptTaskById(taskId);
			if (is > 0)
				return "redirect:showMyTask";
		}
		return "redirect:showMyTask";
	}



	// 点赞
	@RequestMapping("goods")
	public String Goods(Model model, HttpSession session,
			HttpServletRequest request) {
		String acceptId = request.getParameter("acceptId");
		System.out.println(acceptId);
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		System.out.println(acceptTask);
		System.out.println(acceptId);
		model.addAttribute("taskId", acceptTask.getTaskId());
		List<Good> goods = acceptTaskService.isGoods(acceptId, userId);
		if (goods.size() > 0) {
			acceptTaskService.deleteGoods(acceptId, userId);
			int num = acceptTaskService.updateGoods(acceptId,
					acceptTask.getGoods() - 1);
			System.out.println(num);
			System.out.println(acceptTask.getGoods());
			return "redirect:/task/taskDetail";
		} else {
			acceptTaskService.addGoods(acceptId, userId);
			int num = acceptTaskService.updateGoods(acceptId,
					acceptTask.getGoods() + 1);
			System.out.println(num);
			return "redirect:/task/taskDetail";

		}

	}


	// 查看任务信息情况，并根据角色等级对翻译作品的排序
	@RequestMapping("taskDetail")
	public @ResponseBody ModelAndView taskDetail(HttpServletRequest request)
			throws IOException {
		SortTask sortTask = new SortTask();
		ModelAndView mv = new ModelAndView();
		List<AcceptTask> acList = new ArrayList<>();
		String taskId = request.getParameter("taskId");
		// 获取发布任务的信息
		taskId = new String(taskId.getBytes("ISO-8859-1"), "utf-8");
		System.out.println(taskId);
		ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
		if(reTask.getTotalNum()==1){
		// 获取提交的任务
		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
		System.out.println("排序前：" + acList);
		
//		acList = sortTask.sortAccept(acList);
		Collections.sort(acList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				AcceptTask actask1 = (AcceptTask) o1;
				AcceptTask actask2 = (AcceptTask) o2;
				System.out.println(actask1.getUserId());
				System.out.println(userService
						.selectUserById(actask1.getUserId()));
				String roleId1 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask1.getUserId()).getRole());
				System.out.println(roleId1);
				int good1Num = actask1.getGoods();
				int good2Num = actask2.getGoods();
				System.out.println(actask2.getUserId());
				String roleId2 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask2.getUserId()).getRole());
				System.out.println(roleId2);
				int level1 = roleService.getLevelByRoleId(roleId1);
				int level2 = roleService.getLevelByRoleId(roleId2);
				if( level2-level1==1){
					if((good2Num==0&&good1Num-good2Num<=3)||good1Num/good2Num<=3){
						return 1;
					}
					else{
						return -1;
					}
				}
				else if (level2-level1==2 ){
					if((good2Num==0&&good1Num-good2Num<=5)||good1Num/good2Num<=5){
						return 1;
					}
					else{
						return -1;
					}
				}
//					return 1;
				else if (level1-level2==1){
					if((good1Num==0&&good2Num-good1Num<=3)||good2Num/good1Num<=3){
						return -1;
					}
					else{
						return 1;
					}
				}
				else if (level1-level2==2){
					if((good1Num==0&&good2Num-good1Num<=5)||good2Num/good1Num<=5){
						return -1;
					}
					else{
						return 1;
					}
				}
				else {
					
					if (good1Num < good2Num)
						return 1;
					else if (good1Num > good2Num)
						return -1;
					else
						return 0;
				}
			}

		});
		System.out.println("排序后："+acList);
		String error = request.getParameter("error");
		if(error!=null){
			error = new String(error.getBytes("ISO-8859-1"), "utf-8");
			mv.addObject("error", error);
		}
		mv.addObject("acList", acList);
		mv.addObject("reTask", reTask);
		mv.setViewName("taskDetail");
		return mv;
		}
		else{
			List<ReceiveTask> reList = new ArrayList<>();
			String parentId = reTask.getTaskId();
			System.out.println(parentId);
			reList = retaskService.selectChildTaskByParentTaskId(parentId);
			System.out.println(reList);
			mv.addObject("reList", reList);
		//	model.addAttribute("reTask", reTask);
			mv.setViewName("childTask");
			return mv;
		}
	}

	@RequestMapping("adoptTrans")
	public String adoptTrans(String acceptId) throws IOException{
		AcceptTask acceptTask = acceptTaskService.selectAccepTaskByATID(acceptId);
		ReceiveTask receiveTask = retaskService.selectTaskByTaskId(acceptTask.getTaskId());
		  String targetPath = Constant.DOWLOAD_FILE_PATH+ receiveTask.getTaskName()+"." + Constant.SUFFIX;
		  targetPath = targetPath.replace("\\","\\\\");
		  System.out.println("存储路径："+targetPath);
		  File file = new File(targetPath);
		  if (!file.exists()) {
			  file.createNewFile();
			  }
		  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), encoding));
          
          bufferedWriter.append(acceptTask.getSubmitText());
          bufferedWriter.flush();
          bufferedWriter.close();
          return "redirect:/task/release";
	}
	
	@RequestMapping("adoptBigTrans")
	public String adoptBigTrans(String taskId) throws IOException{
		List<ReceiveTask> reList = new ArrayList<>();
		ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
		reList = retaskService.selectChildTaskByParentTaskId(taskId);
		 Collections.sort(reList, new Comparator<Object>() {

				@Override
				public int compare(Object o1, Object o2) {
					ReceiveTask reTask1= (ReceiveTask) o1;
					ReceiveTask reTask2 = (ReceiveTask) o2;
					
					int child1 = reTask1.getIsChild();
					int child2 = reTask2.getIsChild();
					if (child1 < child2)
						return -1;
					else {
						return 1;}
				}

			});
		 String targetPath = Constant.DOWLOAD_FILE_PATH+ reTask.getTaskName()+"." + Constant.SUFFIX;
		  targetPath = targetPath.replace("\\","\\\\");
		  System.out.println("存储路径："+targetPath);
		  File file = new File(targetPath);
		  if (!file.exists()) {
			  file.createNewFile();
			  }
		  BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), encoding));
		Iterator<ReceiveTask> reL = reList.iterator();
		while(reL.hasNext()){
			List<AcceptTask> acList = new ArrayList<>();
		ReceiveTask receiveTask = retaskService.selectTaskByTaskId(reL.next().getTaskId());
		List<AcceptTask> Lists = acceptTaskService.selectAccpetTaskByTaskId(receiveTask.getTaskId());
		Iterator<AcceptTask> lists = Lists.iterator();
		while(lists.hasNext()){
			AcceptTask actask = lists.next();
			if(actask.getIsSubmit()==1){
				acList.add(actask);
			}
		}
		Collections.sort(acList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				AcceptTask actask1 = (AcceptTask) o1;
				AcceptTask actask2 = (AcceptTask) o2;
				System.out.println(actask1.getUserId());
				System.out.println(userService
						.selectUserById(actask1.getUserId()));
				String roleId1 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask1.getUserId()).getRole());
				System.out.println(roleId1);
				int good1Num = actask1.getGoods();
				int good2Num = actask2.getGoods();
				System.out.println(actask2.getUserId());
				String roleId2 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask2.getUserId()).getRole());
				System.out.println(roleId2);
				int level1 = roleService.getLevelByRoleId(roleId1);
				int level2 = roleService.getLevelByRoleId(roleId2);
				if( level2-level1==1){
					if((good2Num==0&&good1Num-good2Num<=3)||good1Num/good2Num<=3){
						return 1;
					}
					else{
						return -1;
					}
				}
				else if (level2-level1==2 ){
					if((good2Num==0&&good1Num-good2Num<=5)||good1Num/good2Num<=5){
						return 1;
					}
					else{
						return -1;
					}
				}
//					return 1;
				else if (level1-level2==1){
					if((good1Num==0&&good2Num-good1Num<=3)||good2Num/good1Num<=3){
						return -1;
					}
					else{
						return 1;
					}
				}
				else if (level1-level2==2){
					if((good1Num==0&&good2Num-good1Num<=5)||good2Num/good1Num<=5){
						return -1;
					}
					else{
						return 1;
					}
				}
				else {
					
					if (good1Num < good2Num)
						return 1;
					else if (good1Num > good2Num)
						return -1;
					else
						return 0;
				}
			}

		});
          bufferedWriter.append(acList.get(0).getSubmitText());
          
		}
			bufferedWriter.flush();
          bufferedWriter.close();
          return "redirect:/task/release";
	}
	// 上传任务
//	@RequestMapping(value = "uploadTask", method = RequestMethod.POST)
//	public String InsertTask(ReceiveTask receiveTask, HttpSession session,
//			HttpServletRequest request,@RequestParam("file") MultipartFile myfile) throws IllegalStateException,
//			IOException {
//		SplitFile spiltFile = new SplitFile();
//		String taskId = UUID.randomUUID().toString();
//		receiveTask.setTaskId(taskId);
//		String account = (String) session.getAttribute("account");
//		String publishId = userService.getUserIdByAccount(account);
//		receiveTask.setPublishId(publishId);
//		String publisher = userService.selectUserById(publishId).getUsername();
//		receiveTask.setPublisher(publisher);
//		 byte[] bs= myfile.getBytes();
//		 System.out.println(new String(bs));
//		receiveTask.setTaskText(new String(bs));
//		retaskService.insertTask(receiveTask);
//		return "redirect:/index.jsp";
		// System.out.println("fileName："+file.getOriginalFilename());
		// String path="E:/"+new Date().getTime()+file.getOriginalFilename();
		//
		// File newFile=new File(path);
		// //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		// file.transferTo(newFile);
		//

//		try {
//			// 获取解析器
//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//					request.getSession().getServletContext());
//
//			// 解析请求
//			// 检查form中是否有enctype="multipart/form-data"
//			if (multipartResolver.isMultipart(request)) {
//				// 将request变成多部分request
//				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//				// 附件实体类
//				List<FileInfo> fileInfos = new ArrayList<FileInfo>();
//				// 获取multiRequest 中所有的文件名
//				Iterator<String> iter = multiRequest.getFileNames();
//				while (iter.hasNext()) {
//					MultipartFile multipartFile = multiRequest.getFile(iter
//							.next());
//					InputStream in = multipartFile.getInputStream();
//					try {
//
//						if (null != in) {
//							// 获取附件名
//							
//							String fileName = multipartFile
//									.getOriginalFilename();
//						
//							
//							if (null != fileName && !"".equals(fileName)) {
//								
//								FileInfo fileInfo = new FileInfo();
//								// 附件id
//								String fileId = UUID.randomUUID().toString();
//								fileInfo.setFileId(fileId);
//								receiveTask.setFileId(fileId);
//								String fileCode = UUID.randomUUID().toString();
//								fileInfo.setFileCode(fileCode);
//								receiveTask.setFileCode(fileCode);
//								// 附件名：带后缀
//								fileInfo.setFileName(fileName);
//								// 附件大小
//								fileInfo.setFileSize(String
//										.valueOf(multipartFile.getSize()));
//								// 附件类型
//								fileInfo.setFileType(fileName
//										.substring(fileName.lastIndexOf(".") + 1));
//								// 附件地址
//								String path = ToolUtils
//										.getPath(Constant.SOURCE_FILE_PATH)
//										+ separator
//										+ fileInfo.getFileId()
//										+ "." + Constant.SUFFIX;
//								System.out.println(path);
//								fileInfo.setFilePath(path);
//								boolean isSuccess = fileService.insertFile(
//										fileInfo, in);
//
//								if (isSuccess) {
//									fileInfos.add(fileInfo);
//									retaskService.insertTask(receiveTask);
//									if (receiveTask.getTotalNum() > 1) {
//										FileInfo file = fileService
//												.selectFileById(receiveTask
//														.getFileId());
//										String parentFilePath = file
//												.getFilePath();
//										System.out.println(parentFilePath);
//										ParentFile parentFile = spiltFile
//												.getFileCountByFilePath(parentFilePath);
//										System.out.println(parentFile);
//										Map<String, Object> map = spiltFile.sqlitFile(parentFile,receiveTask.getTotalNum());
//										System.out.println("人数："
//												+ receiveTask.getTotalNum());
									//	List<ChildFile> childList = spiltFile.sqlitFile1(parentFile,receiveTask.getTotalNum());
									//	List<ChildFile> childList = (List<ChildFile>) map.get("childFileList");
//										FileInfo childFileInfo = new FileInfo();
//										Iterator<ChildFile> cl = childList
//												.iterator();

//										while (cl.hasNext()) {
//											ChildTask childTask = new ChildTask();
//											ChildFile childFile = cl.next();
//											System.out.println(childFile);
//											childFileInfo.setFileCode(fileCode);
//											childFileInfo.setFileId(childFile
//													.getFileId());
//											childFileInfo.setFilePath(childFile
//													.getFilePath());
//											childFileInfo
//													.setChildFile(childFile
//															.getSort());
//											childFileInfo
//													.setFileName(fileName
//															+ "[卷"
//															+ childFile
//																	.getSort()
//															+ "]");
//											childFileInfo.setFileType("txt");
//											boolean isS = fileService
//													.insertFile(childFileInfo,
//															in);
//											if (isS) {
//												ReceiveTask childReTask = new ReceiveTask();
//												String childTaskId = UUID
//														.randomUUID()
//														.toString();
//												childReTask
//														.setTaskId(childTaskId);
//												childReTask
//														.setTaskName(receiveTask
//																.getTaskName()
//																+ "[卷"
//																+ childFile
//																		.getSort()
//																+ "]");
//												childReTask
//														.setDescription(receiveTask
//																.getDescription());
//												childReTask
//														.setFileCode(fileCode);
//												childReTask
//														.setFileId(childFileInfo
//																.getFileId());
//												childReTask
//														.setFinishTime(receiveTask
//																.getFinishTime());
//												childReTask
//														.setPublisher(receiveTask
//																.getPublisher());
//												childReTask
//														.setPublishId(receiveTask
//																.getPublishId());
//												childReTask
//														.setTaskMoney(receiveTask
//																.getTaskMoney());
//												childReTask.setIsChild(1);
//												retaskService
//														.insertTask(childReTask);
//												childTask
//														.setChildTaskId(childTaskId);
//												childTask
//														.setParentTaskId(receiveTask
//																.getTaskId());
//												childTask.setPart(childFile
//														.getSort());
//												retaskService
//														.insertChildTask(childTask);
//											}
//										}
//									}
//								}
//							}
//						} else {
//							mv.addObject("error", "上传的文件不能为空");
//							return "uploadTask";
//						}
//
//					} catch (Exception e) {
//						System.out.println("读取文件时操作由于异常失败");
//						System.out.println(e.getMessage());
//					} finally {
//						if (in != null) {
//							in.close();
//						}
//					}
//				}
//				// 检查是否有成功上传文件
//				if (fileInfos.size() > 0) {
//					mv.addObject("success", "上传成功");
//
//				} else {
//					mv.addObject("error", "文件上传失败");
//					return "uploadTask";
//				}
//			} else {
//				mv.addObject("error", "当前表单不是文件上传表单");
//				return "uploadTask";
//			}
//		} catch (Exception e) {
//			System.out.println("操作由于异常失败");
//			e.getMessage();
//
//		}
//		return "redirect:/index.jsp";

	//}
//	// 提交任务
//	@RequestMapping("submitTask")
//	public String submitTask(Model model, HttpServletRequest request,
//			HttpSession session) {
//		String taskId = request.getParameter("taskId");
//		String userId = userService.getUserIdByAccount((String) session
//				.getAttribute("account"));
//		String acceptId = acceptTaskService
//				.selectAcceptIdByUTID(userId, taskId);
//		AcceptTask acceptTask = acceptTaskService
//				.selectAccepTaskByATID(acceptId);
//
//		try {
//			// 获取解析器
//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
//					request.getSession().getServletContext());
//
//			// 解析请求
//			// 检查form中是否有enctype="multipart/form-data"
//			if (multipartResolver.isMultipart(request)) {
//				// 将request变成多部分request
//				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//				// 附件实体类
//				List<FileInfo> fileInfos = new ArrayList<FileInfo>();
//				// 获取multiRequest 中所有的文件名
//				Iterator<String> iter = multiRequest.getFileNames();
//				while (iter.hasNext()) {
//					MultipartFile multipartFile = multiRequest.getFile(iter
//							.next());
//					InputStream in = multipartFile.getInputStream();
//					try {
//
//						if (null != in) {
//							// 获取附件名
//							String fileName = multipartFile
//									.getOriginalFilename();
//							if (null != fileName && !"".equals(fileName)) {
//								FileInfo fileInfo = new FileInfo();
//								// 附件id
//								String fileId = UUID.randomUUID().toString();
//								fileInfo.setFileId(fileId);
//								acceptTask.setSubmitFileId(fileId);
//								String fileCode = UUID.randomUUID().toString();
//								fileInfo.setFileCode(fileCode);
//
//								// 附件名：带后缀
//								fileInfo.setFileName(fileName);
//								// 附件大小
//								fileInfo.setFileSize(String
//										.valueOf(multipartFile.getSize()));
//								// 附件类型
//								fileInfo.setFileType(fileName
//										.substring(fileName.lastIndexOf(".") + 1));
//								// 附件地址
//								String path = ToolUtils
//										.getPath(Constant.SOURCE_FILE_PATH)
//										+ separator + fileInfo.getFileId();
//								System.out.println(path);
//								fileInfo.setFilePath(path);
//								boolean isSuccess = fileService.insertFile(
//										fileInfo, in);
//
//								if (isSuccess) {
//									fileInfos.add(fileInfo);
//									acceptTask.setIsSubmit(1);
//									acceptTaskService
//											.updateAcceptTask(acceptTask);
//								}
//							}
//						} else {
//							model.addAttribute("msg", "上传的文件不能为空");
//						}
//
//					} catch (Exception e) {
//						System.out.println("读取文件时操作由于异常失败");
//						System.out.println(e.getMessage());
//					} finally {
//						if (in != null) {
//							in.close();
//						}
//					}
//				}
//				// 检查是否有成功上传文件
//				if (fileInfos.size() > 0) {
//					model.addAttribute("msg", "任务已提交");
//				} else {
//					model.addAttribute("msg", "文件上传失败");
//				}
//			} else {
//				model.addAttribute("msg", "当前表单不是文件上传表单");
//			}
//		} catch (Exception e) {
//			System.out.println("操作由于异常失败");
//			e.getMessage();
//
//		}
//		return "unfinish";
//	}
	
//	@RequestMapping("checkAccept")
//	public String checkAccept(String taskId, Model model) throws IOException {
//		SplitFile spiltFile = new SplitFile();
//		SortTask sortTask = new SortTask();
//		List<ParentFile> fileList = new ArrayList<>();
//		System.out.println(taskId);
//		List<AcceptTask> acList = new ArrayList<>();
//		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
//		acList = sortTask.sortAccept(acList);
//		System.out.println(acList);
//		// // acList =
//		// acceptTaskService.selectAccpetTaskByTaskId("9bb057ca-f7f3-4f6b-ae56-400ef0c63364");
//		// Iterator<AcceptTask> ac=acList.iterator();
//		// while(ac.hasNext()){
//		// acceptTask = ac.next();
//		// String level =
//		// userService.selectUserById(acceptTask.getUserId()).getRoleId();
//		// if(level == "3"){
//		// pList.add(acceptTask);
//		// }
//		// else if(level =="2"){
//		// vList.add(acceptTask);
//		// }
//		// else{
//		// cList.add(acceptTask);
//		// }
//		// }
//		// map.put("专家级", pList);
//		// map.put("超级用户", vList);
//		// map.put("普通用户", cList);
//		// model.addAttribute("map", map);
//		Iterator<AcceptTask> ac = acList.iterator();
//		System.out.println(ac);
//		while (ac.hasNext()) {
//			AcceptTask acTask = ac.next();
//			String fileId = acTask.getSubmitFileId();
//			System.out.println(fileId);
//			FileInfo file = fileService.selectFileById(fileId);
//			System.out.println(file);
//			if (file.getFilePath() != null) {
//				String filePath = file.getFilePath();
//				System.out.println(filePath);
//				ParentFile parentFile = spiltFile
//						.getFileCountByFilePath(filePath);
//				fileList.add(parentFile);
//			}
//		}
//		System.out.println(fileList.size());
//		model.addAttribute("file", fileList);
//		model.addAttribute("acList", acList);
//		return "checkAccept";
//	}
	
//	// 查看任务信息情况，并根据角色等级对翻译作品的排序
//	@RequestMapping("taskDetail")
//	public String taskDetail(Model model, HttpServletRequest request)
//			throws IOException {
//		SortTask sortTask = new SortTask();
//		SplitFile spiltFile = new SplitFile();
//		List<ParentFile> fileList = new ArrayList<>();
//		List<AcceptTask> acList = new ArrayList<>();
//		String taskId = request.getParameter("taskId");
//		// 获取发布任务的信息
//		taskId = new String(taskId.getBytes("ISO-8859-1"), "utf-8");
//		System.out.println(taskId);
//		ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
//		String fileId = reTask.getFileId();
//		System.out.println(fileId);
//		FileInfo file = fileService.selectFileById(fileId);
//		System.out.println(file);
//		if (file.getFilePath() != null) {
//			String filePath = file.getFilePath();
//			System.out.println(filePath);
//			ParentFile parentFile = spiltFile.getFileCountByFilePath(filePath);
//			model.addAttribute("pfile", parentFile);
//		}
//		// 获取提交的任务
//		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
//		System.out.println("排序前：" + acList);
//		acList = sortTask.sortAccept(acList);
//		Iterator<AcceptTask> ac = acList.iterator();
//		System.out.println(ac);
//		while (ac.hasNext()) {
//			AcceptTask acTask = ac.next();
//			String fileId1 = acTask.getSubmitFileId();
//			System.out.println(fileId1);
//			FileInfo fileInfo = fileService.selectFileById(fileId1);
//			System.out.println(fileInfo);
//			if (fileInfo.getFilePath() != null) {
//				String filePath = fileInfo.getFilePath();
//				System.out.println(filePath);
//				ParentFile parentFile = spiltFile
//						.getFileCountByFilePath(filePath);
//				fileList.add(parentFile);
//			}
//		}
//		System.out.println(fileList.size());
//		model.addAttribute("file", fileList);
//		model.addAttribute("acList", acList);
//		model.addAttribute("reTask", reTask);
//		return "taskDetail";
//	}
//
//}
//
	
	//采纳大型任务
	
	
}


