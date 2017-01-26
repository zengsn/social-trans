package com.qingtian.apps.task.action;

import com.qingtian.apps.task.entity.Task;
import com.qingtian.apps.task.service.TaskService;
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

        List<Task> lists = taskService.getTaskList();
        if(lists!=null && lists.size()>0){
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",lists);
        }else {
            return JsonUtils.genUpdateDataReturnJsonStr(true,"查询失败");
        }
    }
}
