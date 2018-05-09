package com.crowd.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.ChildTask;
import com.crowd.bean.ReceiveTask;
import com.crowd.dao.ReceiveTaskDao;
@Service
public class ReceiveTaskService implements ReceiveTaskDao{
	@Autowired
	private ReceiveTaskDao receiveTaskDao;
	/**
	 * 添加任务
	 * @return
	 */
	public int insertTask(ReceiveTask receiveTask){
		return receiveTaskDao.insertTask(receiveTask);
	}
	 /**
     * 查询任务列表
     * @return
     */
	public List<ReceiveTask> getReceiveTaskList(){
		return receiveTaskDao.getReceiveTaskList(); 
	}
	
	public  List<ReceiveTask> selectReceiveTaskByuserId(String userId){
		return receiveTaskDao.selectReceiveTaskByuserId(userId);
	}
	
	public ReceiveTask selectTaskByTaskId(String taskId){
		return receiveTaskDao.selectTaskByTaskId(taskId);
	}
	
	public boolean updateReTask(ReceiveTask receiveTask) throws Exception{
		return receiveTaskDao.updateReTask(receiveTask);
	}

    /**
     * 根据任务id来删除任务
     * @param taskId
     * @return
     */
    public int deleteTaskById(String taskId){
    	return receiveTaskDao.deleteTaskById(taskId);
    }
    public int insertChildTask(ChildTask childTask)
    {
    	return receiveTaskDao.insertChildTask(childTask);
    }
    
    public List<ReceiveTask> selectChildTaskByParentTaskId(@Param("parentId")String parentId){
    	return receiveTaskDao.selectChildTaskByParentTaskId(parentId);
    }
    
    public  boolean updateReTaskState(ReceiveTask receiveTask) throws Exception{
    	return receiveTaskDao.updateReTaskState(receiveTask);
    }
    
    public boolean updateSchedule(ReceiveTask receiveTask) throws Exception{
    	return receiveTaskDao.updateSchedule(receiveTask);
    }
	@Override
	public Map<String, Object> splitTask(String fileId, String sourceFilePath,
			int receivePeopleNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public List<ReceiveTask> getNewsReceiveTaskList() {
		return receiveTaskDao.getNewsReceiveTaskList(); 
	}
	public List<ReceiveTask> getTaskByDesc(String desc) {
		// TODO Auto-generated method stub
		return receiveTaskDao.getTaskByDesc(desc);
	}


}
