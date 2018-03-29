package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.Message;
@Repository("messageDao")
public interface MessageDao {
	
	int insertMessage(Message message);
	
	List<Message> selectMessageByUserId(@Param("userId")String userId);
	
	void updateState(@Param("taskId")String taskId,@Param("userId")String userId);

	void deleteMessageByTaskId(@Param("taskId")String taskId);
}
