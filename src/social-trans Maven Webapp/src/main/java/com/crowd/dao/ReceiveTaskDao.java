package com.crowd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.ChildTask;
import com.crowd.bean.ReceiveTask;
import com.github.pagehelper.Page;

@Repository
public interface ReceiveTaskDao {
		int insertTask(ReceiveTask receiveTask);
		
		ReceiveTask selectTaskByTaskId(String taskId);
	    /**
	     * 查询任务列
	     * @return
	     */
		List<ReceiveTask> getReceiveTaskList();

	    /**
	     * 根据任务id来删除任务
	     * @param taskId
	     * @return
	     */
	    int deleteTaskById(@Param("taskId")String taskId);

	    /**
	     * 切割任务
	     * @param sourceFilePath
	     * @return
	     * @throws Exception
	     */
	    Map<String,Object> splitTask(String fileId,String sourceFilePath,int receivePeopleNum) throws Exception;

	    /**
	     * 领取任务，即更新任务
	     * @param task
	     * @return
	     */
	    boolean updateReTask(ReceiveTask receiveTask) throws Exception;

	   // Page selectTaskByUerId(String userId,RowBounds rowBounds)throws Exception;
	    
	    List<ReceiveTask> selectReceiveTaskByuserId(@Param("userId")String userId);
	    
	    int insertChildTask(ChildTask childTask);
	    
	    List<ReceiveTask> selectChildTaskByParentTaskId(@Param("parentId")String parentId);
}
