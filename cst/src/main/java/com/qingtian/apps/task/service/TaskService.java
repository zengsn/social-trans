package com.qingtian.apps.task.service;

import com.github.pagehelper.Page;
import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.task.entity.*;
import com.qingtian.apps.system.taskTranslate.SplitFile;
import com.qingtian.utils.Constant;
import com.qingtian.utils.StringUtils;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Map<String,String>> getReceiveTaskList1(){
        List<Map<String,String>> list = null;
        list = sqlSession.selectList("ReceiveTask.select1");
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
        //分割任务并存库
        int receivePeopleNum = Integer.parseInt(task.getReceivePeopleNum());
        Map<String, Object> map = splitTask(task.getFileId(), task.getFilePath(),receivePeopleNum);
        List<ChildFile> childFileList = (ArrayList)map.get("childFileList");
        //计算任务金钱
        //任务总钱
        double totalMoney = task.getTaskMoney();
        //领取人数：receivePeopleNum
        //取平均
        double taskMoney = StringUtils.div(totalMoney,(double)receivePeopleNum);
        //根据生成的文件来设置任务
        //任务id
        String taskId = task.getTaskId();
        //任务提交者
        String submitter = task.getSubmitter();
        //任务提交者id
        String submitterId = task.getSubmitterId();
        List<ReceiveTask> receiveTasklist = new ArrayList<>();
        ReceiveTask rt= null;
        for(int i=0;i<childFileList.size();i++){
            rt = new ReceiveTask();
            rt.setFileId(childFileList.get(i).getFileId());
            rt.setChilefileId(childFileList.get(i).getId());
            rt.setTaskId(taskId);
            rt.setSubmitter(submitter);
            rt.setSubmitterId(submitterId);
            rt.setIsReceive("0");
            rt.setTaskMoney(taskMoney);
            receiveTasklist.add(rt);
        }
        //将任务插入数据库
        int receiveTaskResult = sqlSession.insert("ReceiveTask.insertTaskBatch",receiveTasklist);
        return (subbmitTaskResult & receiveTaskResult)>-1? true:false;
//        return subbmitTaskResult>0? true:false;
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
    public Map<String,Object> splitTask(String fileId,String sourceFilePath,int receivePeopleNum) throws Exception{
        List<FileInfo> list = null;
        //获取文件行数，字数和文件内容
        SplitFile splitFile = new SplitFile();
        ParentFile parentFile = splitFile.getFileCountByFilePath(sourceFilePath);
        parentFile.setId(new RandomGUID().toString());
        parentFile.setFileId(fileId);
        parentFile.setFilePath(sourceFilePath);
        //父文本存库
        sqlSession.insert("SubbmitTask.saveParentFile", parentFile);
        //对父文件进行分割，获取分割后的文件信息
        Map<String, Object> map = splitFile.sqlitFile1(parentFile,receivePeopleNum);
        //获取子文本，批量存库
        List<ChildFile> childFileList = (ArrayList)map.get("childFileList");
        sqlSession.insert("SubbmitTask.insertChildFileBatch",childFileList);
        //获取附件，批量存库
        List<FileInfo> fileInfoList = (ArrayList)map.get("fileInfoList");
        sqlSession.insert("File.insertFileBatch1",fileInfoList);
        return map;
    }

    /**
     * 领取任务，即更新任务
     * @param task
     * @return
     */
    public Boolean updateReTask(ReceiveTask task) throws Exception{
        task.setIsReceive("1");
        int result = sqlSession.update("ReceiveTask.updateReTask",task);

        return result>0? true:false;
    }

    public Page selectTaskByUerId(String userId,RowBounds rowBounds)throws Exception{
        Page list = (Page)sqlSession.selectList("ReceiveTask.selectTaskByUerId",userId,rowBounds);
        return list;
    }

    /**
     * 根据附件地址来显示
     * @param filePath
     * @return
     */
    public List<TranslateComment> selectTranslateComment(String filePath,String fileId)throws Exception{
        List<TranslateComment> list = null;
        if(haveTranslate(fileId)){//已经存在，则从数据库表中获取翻译内容和翻译结果
            list = sqlSession.selectList("TranslateComment.selectList",fileId);
        }else {//不存在的插表
            SplitFile splitFile = new SplitFile();
            int pageLine = Constant.PAGE_LINE;
            list = splitFile.getFile(filePath,fileId,pageLine);
            sqlSession.insert("TranslateComment.insertTranslateBatch",list);
        }
        return list;
    }

    /**
     * 保存翻译结果
     * @param tc
     * @return
     */
    public Boolean updateTranslateResult(TranslateComment tc){
        int result = sqlSession.update("TranslateComment.updateTranslateResult",tc);
        return result>0? true:false;
    }

    /**
     * 判断翻译文件是否已经按照要求划分
     */
    public Boolean haveTranslate(String fileId){
        List<TranslateComment> list = sqlSession.selectList("TranslateComment.selectList",fileId);
        if(list!=null && list.size()>0){
            return true;
        }else{
            return false;
        }
    }

    public List<TranslateComment> selectTranslateResult(String fileId,int commentId){
        TranslateComment tc = new TranslateComment();
        tc.setFileId(fileId);
        tc.setCommentId(commentId);
        List<TranslateComment> list = sqlSession.selectList("TranslateComment.selectListByFC",tc);
        return list;
    }

    public ParentFile getParentFile() throws Exception{
        String id = "F7E3D3E2-4FC4-B6FD-3CB0-E7923A14772A";
        ParentFile parentFile = sqlSession.selectOne("SubbmitTask.getParentFileById", id);
        return parentFile;
    }


}
