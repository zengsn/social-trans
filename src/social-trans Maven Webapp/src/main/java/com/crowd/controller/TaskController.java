package com.crowd.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.crowd.bean.*;
import com.crowd.service.AcceptTaskService;
import com.crowd.service.MessageService;
import com.crowd.service.ReceiveTaskService;
import com.crowd.service.RoleService;
import com.crowd.service.UserService;
import com.crowd.trans.Simicalcu;
import com.crowd.trans.SplitFile;
import com.crowd.utils.Constant;
import com.crowd.utils.ToolUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("task")
public class TaskController {

	@Autowired
	private ReceiveTaskService retaskService;
	@Autowired
	private UserService userService;
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
	public ModelAndView insertTask1(ReceiveTask receiveTask,HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		String account = (String) session.getAttribute("account");
		if(account!=null) {
		String publishId = userService.getUserIdByAccount(account);
		User publisher = userService.selectUserById(publishId);
		String role = publisher.getRole();
		String roleId = roleService.getRoleIdByRolename(role);
		int roleLevel = roleService.getLevelByRoleId(roleId);
		mv.addObject("roleLevel",roleLevel);
		mv.setViewName("uploadTask");
		return mv;
		}else {
			mv.addObject("error", "您未登陆 ，请登陆后再发布任务");
			mv.setViewName("error");
			return mv;
		}
	}

	// 上传任务
	@RequestMapping(value = "uploadTask", method = RequestMethod.POST)
	public String InsertTask(ReceiveTask receiveTask, HttpSession session,
			HttpServletRequest request,
			@RequestParam("file") MultipartFile myfile, Model model)
			throws IllegalStateException, IOException {
		SplitFile spiltFile = new SplitFile();
		String taskId = UUID.randomUUID().toString();
		receiveTask.setTaskId(taskId);
		String account = (String) session.getAttribute("account");
		if (account != null && account != "") {
			String publishId = userService.getUserIdByAccount(account);
			receiveTask.setPublishId(publishId);
			String publisher = userService.selectUserById(publishId)
					.getUsername();
			receiveTask.setPublisher(publisher);
			String taskText = request.getParameter("taskText");
			System.out.println(taskText);
			if (taskText == null || taskText.equals("")) {
				User user = userService.selectUserById(publishId);
				String role = user.getRole();
				String roleId = roleService.getRoleIdByRolename(role);
				System.out.println("roleId:" + roleId);
				int level = roleService.getLevelByRoleId(roleId);
				System.out.println(level);
				if (level > 1) {
					byte[] bs = myfile.getBytes();
					// System.out.println(new String(bs));
					String Text = new String(bs);
					receiveTask.setTaskText(Text);
					if (Text.length() > 250) { //判断翻译内容是否大于250
						HashMap<Integer, String> textMap = (HashMap<Integer, String>) spiltFile
								.spiltText(Text);
						Set set = textMap.keySet();
						Iterator t = set.iterator();
						for (Iterator iter = set.iterator(); iter.hasNext();) {
							int key = (int) iter.next();
							System.out.println(key);
							String value = (String) textMap.get(key);
							ReceiveTask childReTask = new ReceiveTask();
							String childTaskId = UUID.randomUUID().toString();
							childReTask.setTaskId(childTaskId);
							childReTask.setTaskName(receiveTask.getTaskName()
									+ "[卷" + key + "]");
							childReTask.setDescription(receiveTask
									.getDescription());
							childReTask.setFinishTime(receiveTask
									.getFinishTime());
							childReTask
									.setPublisher(receiveTask.getPublisher());
							childReTask
									.setPublishId(receiveTask.getPublishId());
							childReTask
									.setTaskMoney(receiveTask.getTaskMoney());
							childReTask.setIsChild(key);
							childReTask.setTaskText(value);
							Text = ToolUtils.exchange(value);
							// String result = Simicalcu.translatePost1(Text);
							String result = Simicalcu.translateGet(Text);
							childReTask.setTranText(result);
							System.out.println("result" + result);
							childReTask.setParentId(receiveTask.getTaskId());
							childReTask.setTotalNum(1);
							retaskService.insertTask(childReTask);
						}
					} else {
						Text = ToolUtils.exchange(Text);
						System.out.println(Text);
						String result = Simicalcu.translateGet(Text);
						// String result = Simicalcu.translatePost1(Text);
						System.out.println("result" + result);
						receiveTask.setTranText(result);
					}
				} else {
					model.addAttribute("upLoadError", "升级为会员后可以文件上传！");
					return "uploadTask";
				}
			} else {
				receiveTask.setTaskText(taskText);
				taskText = ToolUtils.exchange(taskText);
				String result = Simicalcu.translateGet(taskText);
				// String result = Simicalcu.translatePost1(taskText);
				System.out.println("result" + result);
				receiveTask.setTranText(result);
			}
			// 发送一个HTTP请求获取机器翻译的翻译文本
			retaskService.insertTask(receiveTask);
//			List<User> userList = userService.selectAllUser();
//			String describe = receiveTask.getDescription();
//			Iterator<User> users = userList.iterator();
//			System.out.println(users);
//			while (users.hasNext()) {
//				User user = users.next();
//				String hobby = user.getHobby();
//				// System.out.println("hobby:"+hobby);
//				// System.out.println("de:"+describe);
//				String historyTrans = user.getHistoryTrans();
//				// System.out.println("his:"+historyTrans);
//				if (historyTrans != null) {
//					if (historyTrans.contains(describe)
//							|| hobby.contains(describe)) {
//						Message message = new Message();
//						message.setTaskId(taskId);
//						message.setUserId(user.getUserId());
//						message.setState(0);
//						message.setMessage("根据您的兴趣爱好，历史翻译情况。觉得‘"
//								+ receiveTask.getTaskName() + "’非常适合你！");
//						System.out.println(message);
//						messageService.insertMessage(message);
//					}
//				} else if (hobby != null && hobby.contains(describe)) {
//					Message message = new Message();
//					message.setTaskId(taskId);
//					message.setUserId(user.getUserId());
//					message.setState(0);
//					message.setMessage("根据您的兴趣爱好，历史翻译情况。觉得‘"
//							+ receiveTask.getTaskName() + "’非常适合你！");
//					System.out.println(message);
//					messageService.insertMessage(message);
//				}
//			}
			return "redirect:/task/toIndex";
		} else {
			model.addAttribute("error", "您未登陆 ，请登陆后再发布任务");
			return "error";
		}
	}

	// 查看所有接收任务
	@RequestMapping("getReceiveTaskList")
	public String getTaskList(Model model) throws IOException {
		List<ReceiveTask> lists = retaskService.getNewsReceiveTaskList();
		
		List<ReceiveTask> reList = new ArrayList<>();
		System.out.println(lists.size());
//		List<String> text = new ArrayList<>();
		if (lists != null && lists.size() > 0) {
			Iterator<ReceiveTask> ac = lists.iterator();
			System.out.println(ac);
			while (ac.hasNext()) {
				ReceiveTask reTask = ac.next();
				if (reTask.getState() == 0) {
					if (reTask.getIsChild() == 0) {
						reList.add(reTask);
//						String taskText = reTask.getTaskText();
//						text.add(taskText);
					}
				}
			}
			List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
			List<ReceiveTask> newList = new ArrayList<>();
//			model.addAttribute("textList", text);
			
			
			if(nList != null && nList.size() > 0) {
				for(int i = 0;i<nList.size();i++) {
					ReceiveTask re = nList.get(i);
					if (re.getIsChild() == 0) {
						newList.add(re);
					}
					if(newList.size()>4)
						break;
				}
			}
			model.addAttribute("newList", newList);
			model.addAttribute("reList", reList);
			return "taskList";
		} else {
			return "redirect:/task/toIndex";
		}
	}
	
	// 领取任务
	@RequestMapping(value = { "/acceptTask" }, method = { RequestMethod.POST })
	@ResponseBody
	public String acceptTask(@RequestParam("taskId") String taskId,Model model, HttpServletRequest request,
			HttpSession session) throws Exception {
		String account = (String) session.getAttribute("account");
		if (account != null && account != "") {
			String userId = userService.getUserIdByAccount(account);
			System.out.println(userId);
			ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
			List<AcceptTask> List = acceptTaskService.selectAccpetTaskByTaskId(taskId);
			AcceptTask acd = acceptTaskService
					.selectStateByUTID(userId, taskId);
			List<AcceptTask> acList = acceptTaskService
					.selectAccpetTaskByuserId(userId);
			if (acd != null) {
				model.addAttribute("ReError", "您已领取了此任务！");
				return "ReError";
			}else if(List.size()>=reTask.getTotalNum()){
				
				model.addAttribute("Over", "此任务已经被领完了");
				return "Over";
			}else if (reTask.getParentId() != null&&acList.size() > 0) {
					String parentId = reTask.getParentId();
					System.out.println(parentId);
					Iterator<AcceptTask> ac = acList.iterator();
					int num = 0;
					while (ac.hasNext()) {
						AcceptTask acceptTask = ac.next();
						if (parentId.equals(retaskService.selectTaskByTaskId(
								acceptTask.getTaskId()).getParentId())
								&& (acceptTask.getIsSubmit() != 1)) {
								num++;
						}
					}
					System.out.println(num);
					if (num >= 3) {
						model.addAttribute("Max", "此任务最多领取3个，请完成后再领取新的任务！");
						return "Max";
					}
					else {
					 AcceptTask acceptTask = new AcceptTask();
					
					 acceptTask.setUserId(userId);
					 String accepter = userService.selectUserById(userId)
					 .getUsername();
					 acceptTask.setAccepter(accepter);
					 String acceptId = UUID.randomUUID().toString();
					 acceptTask.setAcceptId(acceptId);
					
					 acceptTask.setTaskId(taskId);
					 int isSuccess =
					 acceptTaskService.insertAcceptTask(acceptTask);
					
					 if (isSuccess > 0) {
					 ReceiveTask receiveTask = retaskService
					 .selectTaskByTaskId(taskId);
					 receiveTask.setIsReceive(1);
					 receiveTask.setReceiveNum(receiveTask.getReceiveNum() +
					 1);
					 boolean update = retaskService.updateReTask(receiveTask);
					 if (update) {
					 model.addAttribute("success", "领取任务成功！");
					 return "success";
					 } else {
					 model.addAttribute("error", "领取任务失败，任务已下架");
					 return "error";
					 }
					 } else {
					 model.addAttribute("error", "领取任务失败，任务已下架");
					 return "error";
					 }
				}

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
						return "error";
					}
				} else {
					model.addAttribute("error", "领取任务失败，任务已下架");
					return "error";
				}
			}
		} else {
			model.addAttribute("login", "您未登陆 ，请登陆后再领取任务");
			return "login";
		}
	}

	// 查看自己领取的任务
	@RequestMapping("unfinish")
	public String showMyTask(Model model, HttpSession session,
			HttpServletRequest request) throws UnsupportedEncodingException {

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
				AcceptTask acd = acceptTaskService.selectStateByUTID(userId,
						taskId);
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
		String subError = request.getParameter("subError");
		if (subError != null) {
			subError = new String(subError.getBytes("ISO-8859-1"), "utf-8");
			model.addAttribute("subError", subError);
		}
		return "unfinish";
	}

	// 查看自己完成的任务
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
				AcceptTask acd = acceptTaskService.selectStateByUTID(userId,
						taskId);
				receiveTask = retaskService.selectTaskByTaskId(taskId);
				if (acd.getIsSubmit() == 1) {
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
				if (reTask.getIsChild() == 0) {
					reList.add(reTask);
				}
			}

			model.addAttribute("reList", reList);
		} else {
			model.addAttribute("remsg", "暂时未发布任何任务");
		}
		return "release";
	}

	// 提交任务
	@RequestMapping("submitTask")
	public String submitTask(Model model, HttpServletRequest request,
			HttpSession session, @RequestParam("file") MultipartFile myfile)
			throws IOException {
		String taskId = request.getParameter("taskId");
		String userId = userService.getUserIdByAccount((String) session
				.getAttribute("account"));
		String acceptId = acceptTaskService
				.selectAcceptIdByUTID(userId, taskId);
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		byte[] bs = myfile.getBytes();
		System.out.println(new String(bs));
		String strA, strB;
		String text1 = new String(bs);
		String tranText = retaskService.selectTaskByTaskId(taskId)
				.getTranText();
		System.out.println(tranText);
		if (!(Simicalcu.removeSign(text1).length() == 0 && Simicalcu
				.removeSign(tranText).length() == 0)) {
			if (text1.length() >= tranText.length()) {
				strA = text1;
				strB = tranText;
			} else {
				strA = tranText;
				strB = text1;
			}
			double result = Simicalcu.SimilarDegree(strA, strB);
			System.out.println(result);
			System.out.println("相似的内容为："
					+ Simicalcu.longestCommonSubstring(strA, strB));
			String smilar = Simicalcu.similarityResult(result);
			System.out.println("相似度为：" + smilar);
		 // String tempA = smilar.substring(0,3);
		 // int num = Integer.parseInt(tempA);
		 // System.out.println(num);
		 if(result*100>Constant.HIGH_SIMILAR||result*100<Constant.LOW_SIMILAR){
		 model.addAttribute("subError", "您提交的内容与机器翻译相似度太高或太低！");
		 System.out.println("return");
		 return "redirect:/user/toIndex";
		 }
		 }
		// 比较文本内容
		List<AcceptTask> acList = acceptTaskService
				.selectcheckAcceptByTaskId(taskId);
		Iterator<AcceptTask> ac = acList.iterator();
		while (ac.hasNext()) {

			String text2 = ac.next().getSubmitText();
			if (!(Simicalcu.removeSign(text1).length() == 0 && Simicalcu
					.removeSign(text2).length() == 0)) {
				if (text1.length() >= text2.length()) {
					strA = text1;
					strB = text2;
				} else {
					strA = text2;
					strB = text1;
				}
				double result = Simicalcu.SimilarDegree(strA, strB);
				System.out.println(result);
				System.out.println("相似的内容为："
						+ Simicalcu.longestCommonSubstring(strA, strB));
				String smilar = Simicalcu.similarityResult(result);
				System.out.println("相似度为：" + smilar);
				// String tempA = smilar.substring(0,3);
				// int num = Integer.parseInt(tempA);
				// System.out.println(num);
				if (result * 100 > Constant.SIMILAR_CONTEXT) {
					model.addAttribute("subError", "您提交的内容与其他提交者相似度太高！");
					System.out.println("return");
					return "redirect:/user/toIndex";
				}
			}
		}

		acceptTask.setSubmitText(new String(bs));
		acceptTask.setIsSubmit(1);
		acceptTaskService.updateAcceptTask(acceptTask);
		//更改稿件的完成度
//		Iterator<ReceiveTask> re = reList.iterator();
//		while (re.hasNext()) {
//			ReceiveTask reT = re.next();
//			List<ReceiveTask> childList = retaskService.selectChildTaskByParentTaskId(reT.getTaskId());
//			if(childList.size()>0){
//				int num = 0;
//				Iterator<ReceiveTask> child = childList.iterator();
//				while(child.hasNext()){
//					ReceiveTask ChildTask = child.next();
//					List<AcceptTask> acL = acceptTaskService.selectcheckAcceptByTaskId(ChildTask.getTaskId());
//					if(acL.size()>0){
//						Iterator<AcceptTask> acChild = acL.iterator();
//						while(acChild.hasNext()){
//							if(acChild.next().getScore()>=60){
//								num++;
//								break;
//							}
//						}
//					}
//				}
//			reT.setState((num/childList.size())*100);
//			}
//		}
		
		User user = userService.selectUserById(userId);

		user.setTransNum(user.getTransNum() + 1);
		user.setWordNum(acceptTask.getSubmitText().length() + user.getWordNum());
		String historyTrans = user.getHistoryTrans();
		String describe = retaskService.selectTaskByTaskId(taskId)
				.getDescription();
		if (historyTrans == null || historyTrans.equals("")) {
			user.setHistoryTrans(describe);
		} else if (!historyTrans.contains(describe)) {
			historyTrans = historyTrans + ","
					+ retaskService.selectTaskByTaskId(taskId).getDescription();
			user.setHistoryTrans(historyTrans);
		}
		userService.UpdateUserByUserId(user);
		return "redirect:/user/toIndex";
	}

	// 查看别人提交的翻译
	@SuppressWarnings("unchecked")
	@RequestMapping("checkAccept")
	public String checkAccept(String taskId, Model model) throws IOException {
		// SortTask sortTask = new SortTask();
		System.out.println(taskId);
		List<AcceptTask> acList = new ArrayList<>();
		acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
		if (acList.size() > 0) {
			if (acList.size() > 1) {
				Collections.sort(acList, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						AcceptTask actask1 = (AcceptTask) o1;
						AcceptTask actask2 = (AcceptTask) o2;
						System.out.println(actask1.getUserId());
						System.out.println(userService.selectUserById(actask1
								.getUserId()));
						String roleId1 = roleService
								.getRoleIdByRolename(userService
										.selectUserById(actask1.getUserId())
										.getRole());
						System.out.println(roleId1);
						int good1Num = actask1.getGoods();
						int good2Num = actask2.getGoods();
						System.out.println(actask2.getUserId());
						String roleId2 = roleService
								.getRoleIdByRolename(userService
										.selectUserById(actask2.getUserId())
										.getRole());
						System.out.println(roleId2);
						int level1 = roleService.getLevelByRoleId(roleId1);
						int level2 = roleService.getLevelByRoleId(roleId2);
						if (level2 - level1 == 1) {
							if (good2Num == 0) {
								if (good1Num - good2Num <= 3)
									return 1;
								else
									return -1;
							} else {
								if (good1Num / good2Num <= 3)
									return 1;
								else
									return -1;
							}

						} else if (level2 - level1 == 2) {
							if (good2Num == 0) {
								if (good1Num - good2Num <= 5)
									return 1;
								else
									return -1;
							} else {
								if (good1Num / good2Num <= 5)
									return 1;
								else
									return -1;
							}
						}
						// return 1;
						else if (level1 - level2 == 1) {
							if (good1Num == 0) {
								if (good2Num - good1Num <= 3)
									return -1;
								else
									return 1;
							} else {
								if (good2Num / good1Num <= 3)
									return -1;
								else
									return 1;
							}
						} else if (level1 - level2 == 2) {
							if (good1Num == 0) {
								if (good2Num - good1Num <= 5)
									return -1;
								else
									return 1;
							} else {
								if (good2Num / good1Num <= 5)
									return -1;
								else
									return 1;
							}
						} else {

							if (good1Num < good2Num)
								return 1;
							else if (good1Num > good2Num)
								return -1;
							else
								return 0;
						}
					}

				});
			}
			System.out.println(acList);
			model.addAttribute("acList", acList);
		} else {
			model.addAttribute("msg", "暂时未有任何人提交");
		}
		return "checkAccept";

	}

	// 删除任务
	@RequestMapping(value = { "/deleteTask" }, method = { RequestMethod.POST })
	@ResponseBody
	public String deleteTask(@RequestParam("taskId") String taskId,HttpServletRequest request) {
		int isSuccess = retaskService.deleteTaskById(taskId);
		messageService.deleteMessageByTaskId(taskId);
		if (isSuccess > 0) {
			acceptTaskService.deleteAcceptTaskById(taskId);
			return "success";
		}
		return "false";
	}
	
	@RequestMapping(value = { "/cancelAccept" }, method = { RequestMethod.POST })
	@ResponseBody
	public String cancelAccept(@RequestParam("taskId") String taskId,
			HttpServletRequest request,HttpSession session) {
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		AcceptTask ac = acceptTaskService.selectStateByUTID(userId, taskId);
		boolean isSuccess = acceptTaskService.cancelAccept(ac.getAcceptId());
		if(isSuccess) {
			return "success";
		}else {
			return "false";
		}
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
	@SuppressWarnings("unchecked")
	@RequestMapping("taskDetail")
	public @ResponseBody
	ModelAndView taskDetail(HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView();
		List<AcceptTask> acList = new ArrayList<>();
		List<ReceiveTask> reList = new ArrayList<>();
		HashMap<String, List<Comment>> commentMap =  new HashMap<>();
		String taskId = request.getParameter("taskId");
		// 获取发布任务的信息
		taskId = new String(taskId.getBytes("ISO-8859-1"), "utf-8");
		// System.out.println(taskId);
		ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
		String parentId = reTask.getTaskId();
		// System.out.println(parentId);
		reList = retaskService.selectChildTaskByParentTaskId(parentId);
		if (reList.size() == 0) {
			// 获取提交的任务
			acList = acceptTaskService.selectcheckAcceptByTaskId(taskId);
			// System.out.println("排序前：" + acList);
			if (acList.size() > 1) {
				// acList = sortTask.sortAccept(acList);
				Collections.sort(acList, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						AcceptTask actask1 = (AcceptTask) o1;
						AcceptTask actask2 = (AcceptTask) o2;
						System.out.println(actask1.getUserId());
						System.out.println(userService.selectUserById(actask1
								.getUserId()));
						String roleId1 = roleService
								.getRoleIdByRolename(userService
										.selectUserById(actask1.getUserId())
										.getRole());
						System.out.println(roleId1);
						int good1Num = actask1.getGoods();
						int good2Num = actask2.getGoods();
						System.out.println(actask2.getUserId());
						String roleId2 = roleService
								.getRoleIdByRolename(userService
										.selectUserById(actask2.getUserId())
										.getRole());
						System.out.println(roleId2);
						int level1 = roleService.getLevelByRoleId(roleId1);
						int level2 = roleService.getLevelByRoleId(roleId2);
						if (level2 - level1 == 1) {
							if (good2Num == 0) {
								if (good1Num - good2Num <= 3)
									return 1;
								else
									return -1;
							} else {
								if (good1Num / good2Num <= 3)
									return 1;
								else
									return -1;
							}

						} else if (level2 - level1 == 2) {
							if (good2Num == 0) {
								if (good1Num - good2Num <= 5)
									return 1;
								else
									return -1;
							} else {
								if (good1Num / good2Num <= 5)
									return 1;
								else
									return -1;
							}
						}
						// return 1;
						else if (level1 - level2 == 1) {
							if (good1Num == 0) {
								if (good2Num - good1Num <= 3)
									return -1;
								else
									return 1;
							} else {
								if (good2Num / good1Num <= 3)
									return -1;
								else
									return 1;
							}
						} else if (level1 - level2 == 2) {
							if (good1Num == 0) {
								if (good2Num - good1Num <= 5)
									return -1;
								else
									return 1;
							} else {
								if (good2Num / good1Num <= 5)
									return -1;
								else
									return 1;
							}
						} else {

							if (good1Num < good2Num)
								return 1;
							else if (good1Num > good2Num)
								return -1;
							else
								return 0;
						}
					}

				});
			}
			//查看评论
			Iterator<AcceptTask> ac =acList.iterator();
			while(ac.hasNext()){
				AcceptTask acTask = ac.next();
				List<Comment> commentList = acceptTaskService.showComment(acTask.getAcceptId());
				commentMap.put(acTask.getAcceptId(), commentList);
			}
			// System.out.println("排序后："+acList);
			String error = request.getParameter("error");
			if (error != null) {
				System.out.println(error);
				/*error = new String(error.getBytes("ISO-8859-1"), "utf-8");
				System.out.println(error);*/
				mv.addObject("error", error);
			}
			String gradeError = request.getParameter("gradeError");
			if (gradeError != null) {
				
				mv.addObject("gradeError", gradeError);
			}
			List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
			List<ReceiveTask> newList = new ArrayList<>();
//			model.addAttribute("textList", text);
			
			
			if(nList != null && nList.size() > 0) {
				for(int i = 0;i<nList.size();i++) {
					ReceiveTask re = nList.get(i);
					if (re.getIsChild() == 0) {
						newList.add(re);
					}
					if(newList.size()>4)
						break;
				}
			}
			mv.addObject("newList", newList);
			mv.addObject("acList", acList);
			mv.addObject("reTask", reTask);
			mv.addObject("commentMap", commentMap);
			mv.setViewName("taskDetail");
			return mv;
		} else {
			List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
			List<ReceiveTask> newList = new ArrayList<>();
			if(nList != null && nList.size() > 0) {
				for(int i = 0;i<nList.size();i++) {
					ReceiveTask re = nList.get(i);
					if (re.getIsChild() == 0) {
						newList.add(re);
					}
					if(newList.size()>4)
						break;
				}
			}
			mv.addObject("newList", newList);
			System.out.println(reList);
			mv.addObject("reList", reList);
			// model.addAttribute("reTask", reTask);
			mv.setViewName("childTask");
			return mv;
		}
	}

	// 采纳翻译
	@RequestMapping("adoptTrans")
	public String adoptTrans(String acceptId) throws Exception {
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		ReceiveTask receiveTask = retaskService.selectTaskByTaskId(acceptTask
				.getTaskId());
		String targetPath = Constant.DOWLOAD_FILE_PATH
				+ receiveTask.getTaskName() + "." + Constant.SUFFIX;
		targetPath = targetPath.replace("\\", "\\\\");
		System.out.println("存储路径：" + targetPath);
		File file = new File(targetPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(targetPath),
						encoding));

		bufferedWriter.append(acceptTask.getSubmitText());
		bufferedWriter.flush();
		bufferedWriter.close();

		receiveTask.setState(1);
		retaskService.updateReTaskState(receiveTask);

		String userId = acceptTask.getUserId();
		User user = userService.selectUserById(userId);
		user.setAdoptNum(user.getAdoptNum() + 1);
		userService.UpdateUserByUserId(user);
		return "redirect:/user/toIndex";
	}

	// 大型翻译采纳
	@SuppressWarnings("unchecked")
	@RequestMapping("adoptBigTrans")
	public String adoptBigTrans(String taskId) throws Exception {

		List<ReceiveTask> reList = new ArrayList<>();
		ReceiveTask reTask = retaskService.selectTaskByTaskId(taskId);
		reList = retaskService.selectChildTaskByParentTaskId(taskId);
		Collections.sort(reList, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				ReceiveTask reTask1 = (ReceiveTask) o1;
				ReceiveTask reTask2 = (ReceiveTask) o2;

				int child1 = reTask1.getIsChild();
				int child2 = reTask2.getIsChild();
				if (child1 < child2)
					return -1;
				else {
					return 1;
				}
			}

		});
		String targetPath = Constant.DOWLOAD_FILE_PATH + reTask.getTaskName()
				+ "." + Constant.SUFFIX;
		targetPath = targetPath.replace("\\", "\\\\");
		System.out.println("存储路径：" + targetPath);
		File file = new File(targetPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(targetPath),
						encoding));
		Iterator<ReceiveTask> reL = reList.iterator();
		while (reL.hasNext()) {
			List<AcceptTask> acList = new ArrayList<>();
			ReceiveTask receiveTask = retaskService.selectTaskByTaskId(reL
					.next().getTaskId());
			List<AcceptTask> Lists = acceptTaskService
					.selectAccpetTaskByTaskId(receiveTask.getTaskId());
			Iterator<AcceptTask> lists = Lists.iterator();
			while (lists.hasNext()) {
				AcceptTask actask = lists.next();
				if (actask.getIsSubmit() == 1) {
					acList.add(actask);
				}
			}
			Collections.sort(acList, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					AcceptTask actask1 = (AcceptTask) o1;
					AcceptTask actask2 = (AcceptTask) o2;
					System.out.println(actask1.getUserId());
					System.out.println(userService.selectUserById(actask1
							.getUserId()));
					String roleId1 = roleService
							.getRoleIdByRolename(userService.selectUserById(
									actask1.getUserId()).getRole());
					System.out.println(roleId1);
					int good1Num = actask1.getGoods();
					int good2Num = actask2.getGoods();
					System.out.println(actask2.getUserId());
					String roleId2 = roleService
							.getRoleIdByRolename(userService.selectUserById(
									actask2.getUserId()).getRole());
					System.out.println(roleId2);
					int level1 = roleService.getLevelByRoleId(roleId1);
					int level2 = roleService.getLevelByRoleId(roleId2);
					if (level2 - level1 == 1) {
						if (good2Num == 0) {
							if (good1Num - good2Num <= 3)
								return 1;
							else
								return -1;
						} else {
							if (good1Num / good2Num <= 3)
								return 1;
							else
								return -1;
						}

					} else if (level2 - level1 == 2) {
						if (good2Num == 0) {
							if (good1Num - good2Num <= 5)
								return 1;
							else
								return -1;
						} else {
							if (good1Num / good2Num <= 5)
								return 1;
							else
								return -1;
						}
					}
					// return 1;
					else if (level1 - level2 == 1) {
						if (good1Num == 0) {
							if (good2Num - good1Num <= 3)
								return -1;
							else
								return 1;
						} else {
							if (good2Num / good1Num <= 3)
								return -1;
							else
								return 1;
						}
					} else if (level1 - level2 == 2) {
						if (good1Num == 0) {
							if (good2Num - good1Num <= 5)
								return -1;
							else
								return 1;
						} else {
							if (good2Num / good1Num <= 5)
								return -1;
							else
								return 1;
						}
					} else {

						if (good1Num < good2Num)
							return 1;
						else if (good1Num > good2Num)
							return -1;
						else
							return 0;
					}
				}

			});
			;
			bufferedWriter.append(acList.get(0).getSubmitText());

		}
		bufferedWriter.flush();
		bufferedWriter.close();

		reTask.setState(1);
		retaskService.updateReTaskState(reTask);
		return "redirect:/user/toIndex";
	}

	// 翻译评分
	@RequestMapping("gradeTask")
	public String gradeTask(Model model, int score, HttpSession session,
			HttpServletRequest request) throws Exception {

		String account = (String) session.getAttribute("account");
		
		String userId = userService.getUserIdByAccount(account);
		User user = userService.selectUserById(userId);
		String acceptId = request.getParameter("acceptId");
		AcceptTask acceptTask = acceptTaskService
				.selectAccepTaskByATID(acceptId);
		ReceiveTask reTask = retaskService.selectTaskByTaskId(acceptTask
				.getTaskId());
		if(account!=null&&account!="") {
		List<Score> scoreList = acceptTaskService.selectScore(acceptId, userId);
		if (scoreList.size() > 0) {
			model.addAttribute("gradeError", "您已经评论了此翻译");
		} else {
			String roleId = roleService.getRoleIdByRolename(user.getRole());
			int level = roleService.getLevelByRoleId(roleId);
			System.out.println(user.getUserId());
			System.out.println(reTask.getPublishId());
			int totleScore = 0;
			if (user.getUserId().equals(reTask.getPublishId()) || level > 2) {
				int preScore = acceptTask.getScore();
				if (preScore != 0) {
					totleScore = (preScore + score) / 2;
				} else {
					totleScore = score;
				}
				acceptTaskService.updateScore(acceptId, totleScore);
				Score grade = new Score();
				grade.setAcceptId(acceptId);
				grade.setScore(score);
				grade.setUserId(userId);
				acceptTaskService.insertScore(grade);
				if(score>=60){
					reTask.setSchedule(100);
					retaskService.updateSchedule(reTask);
				}
				if(reTask.getParentId()!=null){
					ReceiveTask parentTask = retaskService.selectTaskByTaskId(reTask.getParentId());
//				List<ReceiveTask> reList = retaskService.selectChildTaskByParentTaskId(reTask.getParentId());
//				//更改稿件的完成度
//				Iterator<ReceiveTask> re = reList.iterator();
//				while (re.hasNext()) {
//					ReceiveTask reT = re.next();
					List<ReceiveTask> childList = retaskService.selectChildTaskByParentTaskId(parentTask.getTaskId());
					if(childList.size()>0){
						double num = 0;
						Iterator<ReceiveTask> child = childList.iterator();
						while(child.hasNext()){
							ReceiveTask ChildTask = child.next();
							List<AcceptTask> acL = acceptTaskService.selectcheckAcceptByTaskId(ChildTask.getTaskId());
							if(acL.size()>0){
								Iterator<AcceptTask> acChild = acL.iterator();
								while(acChild.hasNext()){
									if(acChild.next().getScore()>=60){
										num++;
										break;
									}
								}
							}
						}
						System.out.println(num);
						System.out.println(childList.size());
						System.out.println(num/childList.size());
						parentTask.setSchedule((num/childList.size())*100);
						System.out.println((num/childList.size())*100);
						retaskService.updateSchedule(parentTask);
					}
				}
				
					
				
			} else {
				model.addAttribute("gradeError", "您不能参与评论");
			}
		}
	}else {
		model.addAttribute("gradeError", "请登陆后在操作！");
	}
		model.addAttribute("taskId", reTask.getTaskId());
		return "redirect:/task/taskDetail";
	}
	
	//任务推送
	@RequestMapping("pushTask")
	public String pushTask(Model model,HttpSession session){
		List<ReceiveTask> lists = retaskService.getReceiveTaskList();
		List<ReceiveTask> reList = new ArrayList<>();
		List<ReceiveTask> pushList = new ArrayList<>();
		List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
		List<ReceiveTask> newList = new ArrayList<>();
		if(nList != null && nList.size() > 0) {
			for(int i = 0;i<nList.size();i++) {
				ReceiveTask re = nList.get(i);
				if (re.getIsChild() == 0) {
					newList.add(re);
				}
				if(newList.size()>4)
					break;
			}
		}
		model.addAttribute("newList", newList);
		String account = (String) session.getAttribute("account");
		System.out.println(lists.size());
		if (lists != null && lists.size() > 0&& account != null && account != "") {
			Iterator<ReceiveTask> ac = lists.iterator();
			System.out.println(ac);
			while (ac.hasNext()) {
				ReceiveTask reTask = ac.next();
				if (reTask.getState() == 0) {
					if (reTask.getIsChild() == 0) {
						reList.add(reTask);
					}
				}
			}
			
			String userId = userService.getUserIdByAccount(account);
			User user = userService.selectUserById(userId);
			String historyTrans = user.getHistoryTrans();
			String hobby = user.getHobby();
			Iterator<ReceiveTask> re = reList.iterator();
			if(historyTrans != null||hobby != null){
			while (re.hasNext()) {
				ReceiveTask retask = re.next();
				String describe = retask.getDescription();
			if (historyTrans != null) {
				if (historyTrans.contains(describe)|| hobby.contains(describe)) {
					pushList.add(retask);
				}
			} else if (hobby != null && hobby.contains(describe)) {
				pushList.add(retask);
				}
			}
		}else{
			model.addAttribute("pushList", lists);
			return "userTask";
		}
			model.addAttribute("pushList", pushList);
			return "userTask";
		} else {
			return "redirect:/task/toIndex";
		}
	}
	
	//评论翻译
	@RequestMapping("comment")
	public String Comment(Model model,HttpSession session,HttpServletRequest request){
		String acceptId = request.getParameter("acceptId");
		System.out.println(acceptId+"acc");
		AcceptTask acceptTask = acceptTaskService.selectAccepTaskByATID(acceptId);
		Comment comment = new Comment();
		String account = (String) session.getAttribute("account");
		System.out.println(account);
		String userId = userService.getUserIdByAccount(account);
		User user = userService.selectUserById(userId);
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(df.format(day));   
		comment.setCreateTime(df.format(day));
		comment.setUserId(userId);
		comment.setUsername(user.getUsername());
		comment.setComment(request.getParameter("comment"));
		comment.setAcceptId(acceptId);
		acceptTaskService.userComment(comment);
		model.addAttribute("taskId", acceptTask.getTaskId());
		return "redirect:/task/taskDetail";
	}
	@RequestMapping("getTaskByDesc")
	public String getTaskByDesc(Model model,HttpServletRequest request){
		String desc = request.getParameter("desc");
		List<ReceiveTask> dList = retaskService.getTaskByDesc(desc);
		List<ReceiveTask> pushList = new ArrayList<>();
		if(dList != null && dList.size() > 0) {
			for(int i = 0;i<dList.size();i++) {
				ReceiveTask reTask = dList.get(i);
				if (reTask.getState() == 0) {
					if (reTask.getIsChild() == 0) {
						pushList.add(reTask);
					}
				}
			}
		}
		List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
		List<ReceiveTask> newList = new ArrayList<>();
		if(nList != null && nList.size() > 0) {
			for(int i = 0;i<nList.size()&&i<4;i++) {
				newList.add(nList.get(i));
			}
		}
		model.addAttribute("newList", newList);
		model.addAttribute("pushList", pushList);
		return "userTask";
	}
	
	@RequestMapping("toIndex")
	public String toIndex(Model model,HttpServletRequest request,HttpSession session) {
		List<ReceiveTask> lists = retaskService.getReceiveTaskList();
		List<ReceiveTask> reList = new ArrayList<>();
		List<ReceiveTask> pushList = new ArrayList<>();
		List<ReceiveTask> nList = retaskService.getNewsReceiveTaskList();
		List<ReceiveTask> newList = new ArrayList<>();
		if(nList != null && nList.size() > 0) {
			for(int i = 0;i<nList.size();i++) {
				ReceiveTask rt = nList.get(i);
				if(rt.getParentId()==null||rt.getParentId()=="")
					newList.add(rt);
				if(newList.size()==6)
					break;
			}
		}
		model.addAttribute("newList", newList);
		System.out.println(lists.size());
		String account = (String) session.getAttribute("account");
		if (lists != null && lists.size() > 0 && account != null && account != "") {
			Iterator<ReceiveTask> ac = lists.iterator();
			System.out.println(ac);
			while (ac.hasNext()) {
				ReceiveTask reTask = ac.next();
				if (reTask.getState() == 0) {
					if (reTask.getIsChild() == 0) {
						reList.add(reTask);
					}
				}
			}
			/* if(account!=null&&account!="") { */
			String userId = userService.getUserIdByAccount(account);
			User user = userService.selectUserById(userId);
			String historyTrans = user.getHistoryTrans();
			String hobby = user.getHobby();
			Iterator<ReceiveTask> re = reList.iterator();
			if (historyTrans != null || hobby != null) {
				while (re.hasNext()) {
					ReceiveTask retask = re.next();
					String parent = retask.getParentId();
					String describe = retask.getDescription();
					if (historyTrans != null && pushList.size() < 6&&(parent==null||parent=="")) {
						if (historyTrans.contains(describe) || hobby.contains(describe)) {
							pushList.add(retask);
						}
					} else if (hobby != null && hobby.contains(describe)
							&& pushList.size() < 6&&(parent==null||parent=="")) {
						pushList.add(retask);
					}
				}
				model.addAttribute("pushList", pushList);
			} else {
				model.addAttribute("pushList", newList);
			}

		} else {
			model.addAttribute("pushList", newList);
		}
		List<ReceiveTask> hisList = retaskService.getTaskByDesc("历史");
		List<ReceiveTask> historyList = new ArrayList<>();
		for(int i =0; i<hisList.size();i++) {
			ReceiveTask rt = hisList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
			historyList.add(rt);
			if(historyList.size()==4)
				break;
		}
		List<ReceiveTask> polList = retaskService.getTaskByDesc("政治");
		List<ReceiveTask> politicalList = new ArrayList<>();
		for(int i =0;i<polList.size();i++) {
			ReceiveTask rt = polList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
				politicalList.add(rt);
			if(politicalList.size()==4)
				break;
		}
		List<ReceiveTask> humList = retaskService.getTaskByDesc("人文");
		List<ReceiveTask> humanitiesList = new ArrayList<>();
		for(int i =0; i<humList.size();i++) {
			ReceiveTask rt = humList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
				humanitiesList.add(rt);
			if(humanitiesList.size()==4)
				break;
		}
		List<ReceiveTask> busList = retaskService.getTaskByDesc("商业");
		List<ReceiveTask> businessList = new ArrayList<>();
		for(int i =0; i<busList.size();i++) {
			ReceiveTask rt = busList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
				businessList.add(rt);
			if(businessList.size()==4)
				break;
		}
		List<ReceiveTask> schList = retaskService.getTaskByDesc("校园");
		List<ReceiveTask> schoolyList = new ArrayList<>();
		for(int i =0; i<schList.size();i++) {
			ReceiveTask rt = schList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
				schoolyList.add(rt);
			if(schoolyList.size()==4)
				break;
		}
		List<ReceiveTask> novList = retaskService.getTaskByDesc("小说");
		List<ReceiveTask> novelList = new ArrayList<>();
		for(int i =0; i<novList.size();i++) {
			ReceiveTask rt = novList.get(i);
			if(rt.getParentId()==null||rt.getParentId()=="")
				novelList.add(rt);
			if(novelList.size()==4)
				break;
		}
		model.addAttribute("novelList", novelList);
		model.addAttribute("schoolyList", schoolyList);
		model.addAttribute("businessList", businessList);
		model.addAttribute("humanitiesList", humanitiesList);
		model.addAttribute("politicalList", politicalList);
		model.addAttribute("historyList", historyList);
		return "index";
	}
	
	@RequestMapping(value = { "/getComment" },produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object getComment(HttpServletRequest request){
		String acceptId = request.getParameter("acceptId");
		System.out.println(acceptId);
		List<Comment> commentList = acceptTaskService.showComment(acceptId);
		System.out.println(commentList.size());
		System.out.println(JSONArray.fromObject(commentList));
		return JSONArray.fromObject(commentList);
		//JSONArray.toJSONString(commentList);
		//return JSON.toJSON(commentList).toString();
	}
	
}
