package com.geekcloud.kanborad.api;

import com.geekcloud.kanborad.model.Result;
import com.geekcloud.kanborad.model.Task;
import com.geekcloud.kanborad.model.TaskGroup;
import com.geekcloud.kanborad.model.TaskGroupAction;
import com.geekcloud.kanborad.service.TaskGroupService;
import com.geekcloud.kanborad.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskAPI {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping(path = "/api/task/save")
    @ResponseBody
    public Result<Task> save(@RequestBody TaskGroupAction taskAdd) {
        Result<Task> result = new Result<>();

        if (taskAdd == null) {
            result.setMessage("缺少任务数据");
            return result;
        }

        Task task = taskAdd.getTask();

        if (task == null) {
            result.setMessage("缺少任务数据");
            return result;
        }

        if (task.getId() < 1) {
            taskService.add(task);

            long newId = task.getId();
            TaskGroup tg = taskGroupService.findById(taskAdd.getTaskGroupId());
            List<Long> taskIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(tg.getTasks())) {
                for (Task oldTask : tg.getTasks()) {
                    taskIds.add(oldTask.getId());
                }
            }
            taskIds.add(newId);
            taskGroupService.modifyTaskIds(tg.getId(), taskIds);
        } else {
            taskService.update(task);
        }

        result.setSuccess(true);
        result.setData(task);
        return result;
    }

    @PostMapping(path = "/api/task/del")
    @ResponseBody
    public Result<Task> del(@RequestBody TaskGroupAction taskDel) {
        Result<Task> result = new Result<>();

        if (taskDel == null) {
            result.setMessage("缺少任务数据");
            return result;
        }

        Task task = taskDel.getTask();

        if (task == null) {
            result.setMessage("缺少任务数据");
            return result;
        }

        if (task.getId() < 1) {
            result.setMessage("缺少任务 id");
            return result;
        }

        TaskGroup tg = taskGroupService.findById(taskDel.getTaskGroupId());
        List<Long> taskIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tg.getTasks())) {
            for (Task oldTask : tg.getTasks()) {
                if (oldTask.getId() != task.getId()) {
                    taskIds.add(oldTask.getId());
                }
            }
        }

        taskGroupService.modifyTaskIds(tg.getId(), taskIds);

        taskService.delete(task.getId());

        result.setSuccess(true);
        result.setData(task);
        return result;
    }
}

