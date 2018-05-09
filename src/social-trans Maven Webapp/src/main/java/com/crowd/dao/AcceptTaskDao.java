package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.AcceptTask;
import com.crowd.bean.Comment;
import com.crowd.bean.Good;
import com.crowd.bean.Score;

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
	//查看点赞的数目
	int selectGoods(@Param("acceptId")String acceptId);
	//判断是否已经点赞
	List<Good> isGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    //取消赞
    boolean deleteGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    //在goods表添加点赞的数据
    boolean addGoods(@Param("acceptId")String acceptId,@Param("userId")String userId);
    //更改点赞的数目
    int updateGoods(@Param("acceptId")String acceptId,@Param("goods")int goods);
    //修改评分
    int updateScore(@Param("acceptId")String acceptId,@Param("score")int score);
    //查看是否已经评论
    List<Score> selectScore(@Param("acceptId")String acceptId,@Param("userId")String userId);
    //添加评分到Score表
    int insertScore(Score score);
    //评论
    int userComment(Comment comment);
    //查看评论
    List<Comment> showComment(@Param("acceptId")String acceptId);
    //删除已领取的任务
	boolean cancelAccept(String acceptId);

}
