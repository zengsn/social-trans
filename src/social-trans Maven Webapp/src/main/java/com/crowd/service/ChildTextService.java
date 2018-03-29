package com.crowd.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.ChildText;
import com.crowd.dao.ChildTextDao;
@Service
public class ChildTextService implements ChildTextDao{
	@Autowired
	private ChildTextDao childTextDao;
	public int insertChildText(ChildText childText){
		return childTextDao.insertChildText(childText);
	}
	public List<ChildText> selectChildTextByTaskId(@Param("taskId")String taskId){
		return childTextDao.selectChildTextByTaskId(taskId);
	}
}
