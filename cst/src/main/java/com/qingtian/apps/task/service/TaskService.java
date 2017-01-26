package com.qingtian.apps.task.service;

import com.qingtian.apps.task.entity.Task;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qingtian on 2017/1/25.
 */
@Service
public class TaskService {

    @Autowired
    private SqlSessionTemplate sqlSession;


    /**
     * 查询任务列表
     * @return
     */
    public List<Task> getTaskList(){
        List<Task> lists= null;
        lists = sqlSession.selectList("Task.select");
        return lists;
    }
}
