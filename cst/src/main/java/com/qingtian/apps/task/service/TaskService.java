package com.qingtian.apps.task.service;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.entity.TaskFile;
import com.qingtian.apps.system.taskTranslate.SplitFile;
import com.qingtian.apps.task.entity.ReceiveTask;
import com.qingtian.apps.task.entity.SubbmitTask;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.ArrayList;
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
    public List<SubbmitTask> getTaskList(){
        List<SubbmitTask> lists= null;
        lists = sqlSession.selectList("SubbmitTask.select");
        return lists;
    }

    public List<ReceiveTask> getReceiveTaskList(){
        List<ReceiveTask> list = null;
        list = sqlSession.selectList("ReceiveTask.select");
        return list;
    }

    /**
     * 提交任务和分割任务
     * @param task
     * @return
     */
    public Boolean saveTask(SubbmitTask task) throws Exception{
        List<FileInfo> list = null;
        //附件id
        task.setTaskId(new RandomGUID().toString());
        //是否有人接任务,0为无
        task.setIsReceive("0");
        //插入任务信息
        int subbmitTaskResult = sqlSession.insert("SubbmitTask.saveTask",task);
        //分割任务
        list = splitTask(task.getFilePath(),task.getFileCode());
        //将分割后的文件批量插入数据库
        int fileInsertResult = sqlSession.insert("File.insertFileBatch",list);
        //根据生成的文件来设置任务
        //任务id
        String taskId = task.getTaskId();
        //任务提交者
        String submitter = task.getSubmitter();
        //任务提交者id
        String submitterId = task.getSubmitterId();
        List<ReceiveTask> receiveTasklist = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            ReceiveTask rt = new ReceiveTask();
            rt.setFileId(list.get(i).getFileId());
            rt.setFileCode(list.get(i).getFileCode());
            rt.setTaskId(taskId);
            rt.setSubmitter(submitter);
            rt.setSubmitterId(submitterId);
            rt.setIsReceive("0");
            receiveTasklist.add(rt);
        }
        //将任务插入数据库
        int receiveTaskResult = sqlSession.insert("ReceiveTask.insertTaskBatch",receiveTasklist);
        return (subbmitTaskResult & fileInsertResult & receiveTaskResult)>0? true:false;
    }

    /**
     * 根据任务id来删除任务
     * @param taskId
     * @return
     */
    public Boolean deleteTaskById(String taskId){
        int result = sqlSession.delete("SubbmitTask.deleteTaskById",taskId);
        return result>0?true:false;
    }

    /**
     * 切割任务
     * @param sourceFilePath
     * @return
     * @throws Exception
     */
    public List<FileInfo> splitTask(String sourceFilePath,String fileCode) throws Exception{
        List<FileInfo> list = null;
        //获取文件行数和文件内容
        SplitFile splitFile = new SplitFile();
        TaskFile taskFile = splitFile.getFileCountByFilePath(sourceFilePath);
        //对文件进行分割，获取分割后的文件信息
        list = splitFile.sqlitFile1(taskFile,sourceFilePath,fileCode);
        return list;
    }
}
