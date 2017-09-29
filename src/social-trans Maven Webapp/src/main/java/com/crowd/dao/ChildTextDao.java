package com.crowd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crowd.bean.ChildText;
@Repository
public interface ChildTextDao {
	int insertChildText(ChildText childText);
	List<ChildText> selectChildTextByTaskId(@Param("taskId")String taskId);
}
