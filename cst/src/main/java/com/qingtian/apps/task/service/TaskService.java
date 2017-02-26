package com.qingtian.apps.task.service;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.entity.TaskFile;
import com.qingtian.apps.system.taskTranslate.SplitFile;
import com.qingtian.apps.task.entity.Task;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

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

    /**
     * 提交任务和分割任务
     * @param task
     * @return
     */
    public Boolean saveTask(Task task) throws Exception{
        //附件id
        task.setTaskId(new RandomGUID().toString());
        //是否有人接任务,0为无
        task.setIsReceive("0");
        int result = sqlSession.insert("Task.saveTask",task);
        //分割任务
        int result1 = splitTask(task.getFilePath(),task.getFileCode());
        return (result & result1)>0?true:false;
    }

    /**
     * 根据任务id来删除任务
     * @param taskId
     * @return
     */
    public Boolean deleteTaskById(String taskId){
        int result = sqlSession.delete("Task.deleteTaskById",taskId);
        return result>0?true:false;
    }

    /**
     * 切割任务
     * @param sourceFilePath
     * @return
     * @throws Exception
     */
    public int splitTask(String sourceFilePath,String fileCode) throws Exception{
        //获取文件行数和文件内容
        SplitFile splitFile = new SplitFile();
        TaskFile taskFile = splitFile.getFileCountByFilePath(sourceFilePath);
        //对文件进行分割，获取分割后的文件信息
        List<FileInfo> list = splitFile.sqlitFile1(taskFile,sourceFilePath,fileCode);
        //批量插入数据库
        int result = sqlSession.insert("File.insertFileBatch",list);
        return result;
    }
}
