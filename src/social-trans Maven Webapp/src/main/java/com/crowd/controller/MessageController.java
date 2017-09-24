package com.crowd.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowd.bean.Message;
import com.crowd.service.MessageService;
import com.crowd.service.UserService;

@Controller
@RequestMapping("message")
public class MessageController {
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@RequestMapping("showMessage")
	public String showMessage(Model model,HttpSession session){
		String account = (String) session.getAttribute("account");
		String userId = userService.getUserIdByAccount(account);
		System.out.println(userId);
		List<Message> messageList = messageService.selectMessageByUserId(userId);
		System.out.println(messageList);
		model.addAttribute("messageList", messageList);
		return "message";
	}
}
