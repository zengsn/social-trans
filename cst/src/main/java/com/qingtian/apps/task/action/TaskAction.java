package com.qingtian.apps.task.action;

import com.github.pagehelper.Page;

import com.qingtian.apps.system.entity.PageInfo;
import com.qingtian.apps.task.entity.ReceiveTask;
import com.qingtian.apps.task.entity.SubbmitTask;
import com.qingtian.apps.task.service.TaskService;
import com.qingtian.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qingtian on 2017/1/25.
 */
@RestController
@RequestMapping("TaskAction")
public class TaskAction {

    private Logger logger = org.slf4j.LoggerFactory.getLogger("TaskAction");

    @Resource(name="taskService")
    private TaskService taskService;

    /**
     * 查询任务列表
     * @return
     */
    @RequestMapping("getTaskList.do")
    public String getTaskList(){

        List<SubbmitTask> lists = taskService.getTaskList();
        if(lists!=null && lists.size()>0){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",lists);
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"查询失败");
        }
    }

    @RequestMapping("getReceiveTaskList.do")
    public String getReceiveTaskList(){
        List<ReceiveTask> lists = taskService.getReceiveTaskList();
        if(lists!=null && lists.size()>0){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",lists);
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"查询失败");
        }
    }

    /**
     * 提交
     * @param task
     */
    @RequestMapping("saveTask.do")
    public String saveTask(SubbmitTask task){

        //验证任务非空
        String comment = task.getComment();
        if(StringUtils.isEmpty(comment)){
            logger.error("TaskAction ------- saveTask : comment 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"comment为空");
        }

        //验证提交者非空
        String submitter = task.getSubmitter();
        if(StringUtils.isEmpty(submitter)){
            logger.error("TaskAction ------- saveTask : submitter 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"submitter为空");
        }

        //验证提交者非空
        String submitterId = task.getSubmitterId();
        if(StringUtils.isEmpty(submitterId)){
            logger.error("TaskAction ------- saveTask : submitterId 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"submitterId");
        }

        //验证文件路径非空
        String filePath = task.getFilePath();
        if(StringUtils.isEmpty(filePath)){
            logger.error("TaskAction ------- saveTask : filePath 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"filePath");
        }

        //验证附件标识号非空
        String fileCode = task.getFileCode();
        if(StringUtils.isEmpty(fileCode)){
            logger.error("TaskAction ------- saveTask : fileCode 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"fileCode 为空");
        }

        String fileId = task.getFileId();
        if(StringUtils.isEmpty(fileId)){
            logger.error("TaskAction ------- saveTask : fileId 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"fileId 为空");
        }

        try{
            Boolean isSuccess = taskService.saveTask(task);
            if(isSuccess){
                return  JsonUtils.genUpdateDataReturnJsonStr(true,"任务提交成功");
            }else{
                return  JsonUtils.genUpdateDataReturnJsonStr(false,"任务提交失败");
            }
        }catch (Exception e){
            logger.error("TaskAction ------- saveTask : 操作由于异常而任务提交失败"+e.getMessage());
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而任务提交失败"+e.getMessage());
        }

    }

    /**
     * 根据任务id来删除任务
     * @param taskId
     * @return
     */
    @RequestMapping("deleteTaskById.do")
    public String deleteTaskById(String taskId){
        //验证任务id非空
        if(StringUtils.isEmpty(taskId)){
            logger.error("TaskAction ------- deleteTaskById : taskId 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"taskId");
        }

        try{
            Boolean isSuccess = taskService.deleteTaskById(taskId);
            if(isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"删除成功");
            }else{
                return JsonUtils.genUpdateDataReturnJsonStr(false,"删除失败");
            }
        }catch (Exception e){
            logger.error("TaskAction ------- deleteTaskById : 操作由于异常而任务提交失败"+e.getMessage());
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而任务提交失败"+e.getMessage());
        }
    }

    /**
     * 领取任务，即更新接受任务表
     * @param task
     * @return
     */
    @RequestMapping("updateReTask.do")
    public String updateReTask(ReceiveTask task){

        //验证任务id非空
        String id=task.getId();
        if(StringUtils.isEmpty(id)){
            logger.error("TaskAction ------- updateReTask : id 为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"id为空");
        }

        //验证接受者id非空
        String receiveId = task.getReceiverId();
        if(StringUtils.isEmpty(receiveId )){
            logger.error("TaskAction ------- updateReTask : receiveId  为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"receiveId 为空");
        }

        String fileId = task.getFileId();
        if(StringUtils.isEmpty(fileId )){
            logger.error("TaskAction ------- updateReTask : fileId  为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"fileId 为空");
        }

        try{
            Boolean isSuccess = taskService.updateReTask(task);
            if(isSuccess){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"领取任务成功");
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"领取任务失败");
            }
        }catch (Exception e){
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常失败" + e.getMessage());
        }
    }

    /**
     * 根据当前用户来筛选任务
     * @param userId
     * @return
     */
    @RequestMapping("selectTaskByUerId.do")
    public String selectTaskByUerId(@RequestParam(name="rows")int rows, @RequestParam(name="page")int page, String userId){

        if(StringUtils.isEmpty(userId )){
            logger.error("TaskAction ------- updateReTask : userId  为空");
            return  JsonUtils.genUpdateDataReturnJsonStr(false,"userId 为空");
        }
        RowBounds pageBounds=new RowBounds(page,rows);
        Page list=null;
        try{
            list = taskService.selectTaskByUerId(userId,pageBounds);
            PageInfo reList=new PageInfo(list);
            if(reList!=null){
                return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",reList);
            }else {
                return JsonUtils.genUpdateDataReturnJsonStr(false,"查询失败");
            }
        }catch (Exception e){
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常而失败"+e.getMessage());
        }

    }


}
