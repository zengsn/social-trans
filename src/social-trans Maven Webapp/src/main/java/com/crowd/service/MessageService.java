package com.crowd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.Message;
import com.crowd.dao.MessageDao;
@Service
public class MessageService {
	@Autowired
	private MessageDao messageDao;
	public int insertMessage(Message message){
		return messageDao.insertMessage(message);
	}
	
	public List<Message> selectMessageByUserId(@Param("userId")String userId){
		return messageDao.selectMessageByUserId(userId);
	}
	
	public void updateState(@Param("taskId")String taskId,@Param("userId")String userId){
		 messageDao.updateState(taskId, userId);
	}
}
