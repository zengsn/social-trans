package com.qingtian.apps.task.action;

import com.qingtian.apps.task.entity.ReceiveTask;
import com.qingtian.apps.task.entity.SubbmitTask;
import com.qingtian.apps.task.service.TaskService;
import com.qingtian.utils.StringUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
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
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询失败");
        }
    }

    @RequestMapping("getReceiveTaskList.do")
    public String getReceiveTaskList(){
        List<ReceiveTask> lists = taskService.getReceiveTaskList();
        if(lists!=null && lists.size()>0){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",lists);
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询失败");
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


}
