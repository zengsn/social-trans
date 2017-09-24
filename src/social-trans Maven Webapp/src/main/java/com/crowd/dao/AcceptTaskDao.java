package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.Good;

@Repository
public interface AcceptTaskDao {
	
	int insertAcceptTask(AcceptTask acceptTask);
	List<AcceptTask> selectAccpetTaskByuserId(@Param("userId")String userId);
	List<String> selectTaskIdByuserId(@Param("userId")String userId);
	AcceptTask selectAccepTaskByATID(@Param("acceptId")String acceptId);
	//根据用户ID和任务ID查找接收任务的ID
	String selectAcceptIdByUTID(@Param("userId")String userId,@Param("taskId")String taskId);
	int updateAcceptTask(AcceptTask acceptTask);
	AcceptTask selectStateByUTID(@Param("userId")String userId,@Param("taskId")String taskId);
	int deleteAcceptTaskById(@Param("taskId")String taskId);
	List<AcceptTask> selectAccpetTaskByTaskId(@Param("taskId")String taskId);
	List<AcceptTask> selectcheckAcceptByTaskId(@Param("taskId")String taskId);
	int selectGoods(@Param("acceptId")String acceptId);
	List<Good> isGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    
    boolean deleteGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    
    boolean addGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    int updateGoods(@Param("acceptId")String acceptId,@Param("goods")int goods);
}
