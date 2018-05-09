package com.crowd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.Comment;
import com.crowd.bean.Good;
import com.crowd.bean.Score;
import com.crowd.dao.AcceptTaskDao;
@Service
public class AcceptTaskService implements AcceptTaskDao{
	@Autowired
	private AcceptTaskDao acceptTaskDao;
	
	public int insertAcceptTask(AcceptTask acceptTask){
		
		return acceptTaskDao.insertAcceptTask(acceptTask);
	}
	
	public List<AcceptTask> selectAccpetTaskByuserId(String userId){
		return acceptTaskDao.selectAccpetTaskByuserId(userId);
	}
	public List<String> selectTaskIdByuserId(String userId){
		return acceptTaskDao.selectTaskIdByuserId(userId);
	}
	public String selectAcceptIdByUTID(String userId,String taskId){
		return acceptTaskDao.selectAcceptIdByUTID(userId, taskId);
	}
	
	public AcceptTask selectAccepTaskByATID(String acceptId){
		return acceptTaskDao.selectAccepTaskByATID(acceptId);
	}
	public int updateAcceptTask(@Param("acceptTask")AcceptTask acceptTask){
		return  acceptTaskDao.updateAcceptTask(acceptTask);
	}
	public AcceptTask selectStateByUTID(@Param("userId")String userId,@Param("taskId")String taskId){
		return acceptTaskDao.selectStateByUTID(userId, taskId);
	}
	public int deleteAcceptTaskById(@Param("taskId")String taskId){
		return acceptTaskDao.deleteAcceptTaskById(taskId);
	}
	public List<AcceptTask> selectAccpetTaskByTaskId(@Param("taskId")String taskId){
		return acceptTaskDao.selectAccpetTaskByTaskId(taskId);
	}
	public List<AcceptTask> selectcheckAcceptByTaskId(@Param("taskId")String taskId){
		return acceptTaskDao.selectcheckAcceptByTaskId(taskId);
	}
	
	public List<Good> isGoods(@Param("acceptId")String acceptId,@Param("userId")String userId){
    	return acceptTaskDao.isGoods(acceptId, userId);
    }
    
    public  boolean deleteGoods(@Param("acceptId")String acceptId,@Param("userId")String userId){
    	return acceptTaskDao.deleteGoods(acceptId, userId);
    }
    
    public  boolean addGoods(@Param("acceptId")String acceptId,@Param("userId")String userId){
    	return acceptTaskDao.addGoods(acceptId, userId);
    }
    public  int updateGoods(@Param("acceptId")String acceptId,@Param("goods")int goods){
    	return acceptTaskDao.updateGoods(acceptId, goods);
    }
    public int selectGoods(@Param("acceptId")String acceptId){
    	return acceptTaskDao.selectGoods(acceptId);
    }
    public int updateScore(@Param("acceptId")String acceptId,@Param("score")int score){
    	return acceptTaskDao.updateScore(acceptId, score);
    }
    public List<Score> selectScore(@Param("acceptId")String acceptId,@Param("userId")String userId){
    	return acceptTaskDao.selectScore(acceptId, userId);
    }
    public int insertScore(Score score){
    	return acceptTaskDao.insertScore(score);
    }
    public int userComment(Comment comment){
    	return acceptTaskDao.userComment(comment);
    }
    public List<Comment> showComment(@Param("acceptId")String acceptId){
    	return acceptTaskDao.showComment(acceptId);
    }

	public boolean cancelAccept(String acceptId) {
		// TODO Auto-generated method stub
		return acceptTaskDao.cancelAccept(acceptId);
	}
}
