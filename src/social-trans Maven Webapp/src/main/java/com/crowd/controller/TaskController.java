package com.crowd.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.FileInfo;
import com.crowd.bean.Good;
import com.crowd.bean.ReceiveTask;
import com.crowd.service.AcceptTaskService;
import com.crowd.service.FileService;
import com.crowd.service.ReceiveTaskService;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
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

	// 上传任务
	@RequestMapping(value = "uploadTask", method = RequestMethod.POST)
	public String InsertTask(ReceiveTask receiveTask, HttpSession session,
			HttpServletRequest request) throws IllegalStateException,
			IOException {
		ModelAndView mv = new ModelAndView();
		String taskId = UUID.randomUUID().toString();
		receiveTask.setTaskId(taskId);
		String account = (String) session.getAttribute("account");
		String publishId = userService.getUserIdByAccount(account);
		receiveTask.setPublishId(publishId);
		String publisher = userService.selectUserById(publishId).getUsername();
		receiveTask.setPublisher(publisher);

		// System.out.println("fileName："+file.getOriginalFilename());
		// String path="E:/"+new Date().getTime()+file.getOriginalFilename();
		//
		// File newFile=new File(path);
		// //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		// file.transferTo(newFile);
		//

		try {
			// 获取解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 解析请求
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 附件实体类
				List<FileInfo> fileInfos = new ArrayList<FileInfo>();
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile multipartFile = multiRequest.getFile(iter
							.next());
					InputStream in = multipartFile.getInputStream();
					try {

						if (null != in) {
							// 获取附件名
							String fileName = multipartFile
									.getOriginalFilename();
							if (null != fileName && !"".equals(fileName)) {
								FileInfo fileInfo = new FileInfo();
								// 附件id
								String fileId = UUID.randomUUID().toString();
								fileInfo.setFileId(fileId);
								receiveTask.setFileId(fileId);
								String fileCode = UUID.randomUUID().toString();
								fileInfo.setFileCode(fileCode);
								receiveTask.setFileCode(fileCode);
								// 附件名：带后缀
								fileInfo.setFileName(fileName);
								// 附件大小
								fileInfo.setFileSize(String
										.valueOf(multipartFile.getSize()));
								// 附件类型
								fileInfo.setFileType(fileName
										.substring(fileName.lastIndexOf(".") + 1));
								// 附件地址
								String path = ToolUtils
										.getPath(Constant.SOURCE_FILE_PATH)
										+ separator + fileInfo.getFileId();
								System.out.println(path);
								fileInfo.setFilePath(path);
								boolean isSuccess = fileService.insertFile(
										fileInfo, in);

								if (isSuccess) {
									fileInfos.add(fileInfo);
									retaskService.insertTask(receiveTask);
								}
							}
						} else {
							mv.addObject("error", "上传的文件不能为空");
							return "uploadTask";
						}

					} catch (Exception e) {
						System.out.println("读取文件时操作由于异常失败");
						System.out.println(e.getMessage());
					} finally {
						if (in != null) {
							in.close();
						}
					}
				}
				// 检查是否有成功上传文件
				if (fileInfos.size() > 0) {
					mv.addObject("success", "上传成功");

				} else {
					mv.addObject("error", "文件上传失败");
					return "uploadTask";
				}
			} else {
				mv.addObject("error", "当前表单不是文件上传表单");
				return "uploadTask";
			}
		} catch (Exception e) {
			System.out.println("操作由于异常失败");
			e.getMessage();

		}
		return "redirect:/index.jsp";

	}

	/**
	 * 查询任务列表
	 * 
	 * @return
	 */
	// 查看所有接收任务
	@RequestMapping("getReceiveTaskList")
	public String getTaskList(Model model) {
		List<ReceiveTask> lists = retaskService.getReceiveTaskList();
		if (lists != null && lists.size() > 0) {
			model.addAttribute("reList", lists);
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
			AcceptTask acceptTask = new AcceptTask();
			String userId = userService.getUserIdByAccount(account);
			acceptTask.setUserId(userId);
			String accepter = userService.selectUserById(userId).getUsername();
			acceptTask.setAccepter(accepter);
			String acceptId = UUID.randomUUID().toString();
			acceptTask.setAcceptId(acceptId);
			String taskId = request.getParameter("taskId");
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
				int state = acceptTaskService.selectStateByUTID(userId, taskId);
				receiveTask = retaskService.selectTaskByTaskId(taskId);
				if (state != 1) {
					acList.add(receiveTask);
				}
			}
			model.addAttribute("acList", acList);
		} 
		if(acList.size()==0) {
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
				int state = acceptTaskService.selectStateByUTID(userId, taskId);
				receiveTask = retaskService.selectTaskByTaskId(taskId);
				if (state > 0) {
					finishList.add(receiveTask);
				}
			}
			model.addAttribute("finishList", finishList);
		} else {
			model.addAttribute("fmsg", "您暂时未完成任何任务!");
		}
		return "finish";
	}

	// 发布的任务
	@RequestMapping("release")
	public String release(Model model, HttpSession session) {
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		List<ReceiveTask> relists = retaskService
				.selectReceiveTaskByuserId(userId);
		if (relists != null && relists.size() > 0) {
			model.addAttribute("reList", relists);
		} else {
			model.addAttribute("remsg", "暂时未发布任何任务");
		}
		return "release";
	}

	// 提交任务
	@RequestMapping("submitTask")
	public String submitTask(Model model, HttpServletRequest request,
			HttpSession session) {
		String taskId = request.getParameter("taskId");
		String userId = userService.getUserIdByAccount((String) session
				.getAttribute("account"));
		String acceptId = acceptTaskService
				.selectAcceptIdByUTID(userId, taskId);
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);

		try {
			// 获取解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 解析请求
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 附件实体类
				List<FileInfo> fileInfos = new ArrayList<FileInfo>();
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile multipartFile = multiRequest.getFile(iter
							.next());
					InputStream in = multipartFile.getInputStream();
					try {

						if (null != in) {
							// 获取附件名
							String fileName = multipartFile
									.getOriginalFilename();
							if (null != fileName && !"".equals(fileName)) {
								FileInfo fileInfo = new FileInfo();
								// 附件id
								String fileId = UUID.randomUUID().toString();
								fileInfo.setFileId(fileId);
								acceptTask.setSubmitFileId(fileId);
								String fileCode = UUID.randomUUID().toString();
								fileInfo.setFileCode(fileCode);

								// 附件名：带后缀
								fileInfo.setFileName(fileName);
								// 附件大小
								fileInfo.setFileSize(String
										.valueOf(multipartFile.getSize()));
								// 附件类型
								fileInfo.setFileType(fileName
										.substring(fileName.lastIndexOf(".") + 1));
								// 附件地址
								String path = ToolUtils
										.getPath(Constant.SOURCE_FILE_PATH)
										+ separator + fileInfo.getFileId();
								System.out.println(path);
								fileInfo.setFilePath(path);
								boolean isSuccess = fileService.insertFile(
										fileInfo, in);

								if (isSuccess) {
									fileInfos.add(fileInfo);
									acceptTask.setIsSubmit(1);
									acceptTaskService
											.updateAcceptTask(acceptTask);
								}
							}
						} else {
							model.addAttribute("msg", "上传的文件不能为空");
						}

					} catch (Exception e) {
						System.out.println("读取文件时操作由于异常失败");
						System.out.println(e.getMessage());
					} finally {
						if (in != null) {
							in.close();
						}
					}
				}
				// 检查是否有成功上传文件
				if (fileInfos.size() > 0) {
					model.addAttribute("msg", "任务已提交");
				} else {
					model.addAttribute("msg", "文件上传失败");
				}
			} else {
				model.addAttribute("msg", "当前表单不是文件上传表单");
			}
		} catch (Exception e) {
			System.out.println("操作由于异常失败");
			e.getMessage();

		}
		return "unfinish";
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

	// 查看任务完成情况，并根据角色等级对翻译作品的排序
	@RequestMapping("checkAccept")
	public String checkAccept(String taskId, Model model) {
		System.out.println(taskId);
		List<AcceptTask> acList = new ArrayList<>();
		// List<AcceptTask> pList = new ArrayList<>();
		// List<AcceptTask> vList = new ArrayList<>();
		// List<AcceptTask> cList = new ArrayList<>();
		// Map<String, List> map = new HashMap<>();
		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
		System.out.println("排序前：" + acList);
		// 排序
		Collections.sort(acList, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				AcceptTask actask1 = (AcceptTask) o1;
				AcceptTask actask2 = (AcceptTask) o2;
				System.out.println(actask1.getUserId());

				String roleId1 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask1.getUserId()).getRole());

				System.out.println(roleId1);
				String roleId2 = roleService.getRoleIdByRolename(userService
						.selectUserById(actask2.getUserId()).getRole());
				System.out.println(roleId2);
				int level1 = roleService.getLevelByRoleId(roleId1);
				int level2 = roleService.getLevelByRoleId(roleId2);
				if (level1 < level2)
					return 1;
				else if (level1 > level2)
					return -1;
				else {
					int good1Num = actask1.getGoods();
					int good2Num = actask2.getGoods();
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
		// // acList =
		// acceptTaskService.selectAccpetTaskByTaskId("9bb057ca-f7f3-4f6b-ae56-400ef0c63364");
		// Iterator<AcceptTask> ac=acList.iterator();
		// while(ac.hasNext()){
		// acceptTask = ac.next();
		// String level =
		// userService.selectUserById(acceptTask.getUserId()).getRoleId();
		// if(level == "3"){
		// pList.add(acceptTask);
		// }
		// else if(level =="2"){
		// vList.add(acceptTask);
		// }
		// else{
		// cList.add(acceptTask);
		// }
		// }
		// map.put("专家级", pList);
		// map.put("超级用户", vList);
		// map.put("普通用户", cList);
		// model.addAttribute("map", map);
		model.addAttribute("acList", acList);
		return "checkAccept";
	}

	@RequestMapping("goods")
	public String Goods(String acceptId, Model model, HttpSession session) {
		System.out.println(acceptId);
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		System.out.println(acceptTask);
		System.out.println(acceptId);
		List<Good> goods = acceptTaskService.isGoods(acceptId, userId);
		if (goods.size() > 0) {
			boolean isSuccess = acceptTaskService.deleteGoods(acceptId, userId);
			int num = acceptTaskService.updateGoods(acceptId,
					acceptTask.getGoods() - 1);
			System.out.println(num);
			System.out.println(acceptTask.getGoods());
			return "forward:/task/getReceiveTaskList";

		} else {
			boolean isSuccess1 = acceptTaskService.addGoods(acceptId, userId);
			int num = acceptTaskService.updateGoods(acceptId,
					acceptTask.getGoods() + 1);
			System.out.println(num);
			return "forward:/task/getReceiveTaskList";

		}

	}

}
//

