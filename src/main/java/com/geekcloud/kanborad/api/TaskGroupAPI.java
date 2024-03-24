package com.geekcloud.kanborad.api;

import com.geekcloud.kanborad.model.Result;
import com.geekcloud.kanborad.model.TaskGroup;
import com.geekcloud.kanborad.model.TaskGroupAction;
import com.geekcloud.kanborad.service.TaskGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskGroupAPI {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping(path = "/api/taskgroup/add")
    @ResponseBody
    public Result<TaskGroup> add(@RequestBody TaskGroup taskGroup) {

        Result<TaskGroup> result = new Result<>();

        if (taskGroup == null || StringUtils.isBlank(taskGroup.getTitle())) {
            result.setMessage("缺少任务组标题");
            return result;
        }

        taskGroupService.add(taskGroup);
        result.setSuccess(true);
        result.setData(taskGroup);
        return result;
    }

    @PostMapping(path = "/api/taskgroup/modify/title")
    @ResponseBody
    public Result<TaskGroup> modifyTitle(@RequestBody TaskGroup taskGroup) {

        Result<TaskGroup> result = new Result<>();

        if (taskGroup == null || StringUtils.isBlank(taskGroup.getTitle())) {
            result.setMessage("缺少任务组标题");
            return result;
        }

        if (taskGroup.getId() < 1) {
            result.setMessage("缺少任务组id");
            return result;
        }

        taskGroupService.modifyTitle(taskGroup.getId(), taskGroup.getTitle());
        result.setSuccess(true);
        result.setData(taskGroup);
        return result;
    }

    @PostMapping(path = "/api/taskgroup/del")
    @ResponseBody
    public Result<TaskGroup> delete(@RequestBody TaskGroup taskGroup) {

        Result<TaskGroup> result = new Result<>();

        if (taskGroup == null || taskGroup.getId() < 1) {
            result.setMessage("缺少任务组id");
            return result;
        }

        TaskGroup tg = taskGroupService.findById(taskGroup.getId());
        if (!CollectionUtils.isEmpty(tg.getTasks())) {
            result.setMessage("任务组中仍然包含任务，无法删除");
            return result;
        }

        taskGroupService.delete(taskGroup.getId());
        result.setSuccess(true);
        result.setData(taskGroup);
        return result;
    }

    @PostMapping(path = "/api/taskgroup/modify/task")
    @ResponseBody
    public Result<TaskGroup> moveTask(@RequestBody TaskGroupAction taskMove) {
        Result<TaskGroup> result = new Result<>();
        if (taskMove == null || taskMove.getTaskGroupId() < 1) {
            result.setMessage("缺少任务组id");
            return result;
        }

        if (CollectionUtils.isEmpty(taskMove.getSourceTaskIds())) {
            result.setMessage("缺少移动后的任务 id 列表");
            return result;
        }

        taskGroupService.modifyTaskIds(taskMove.getTaskGroupId(), taskMove.getSourceTaskIds());

        if (!CollectionUtils.isEmpty(taskMove.getTargetTaskIds())) {
            taskGroupService.modifyTaskIds(taskMove.getTaskGroupId(), taskMove.getTargetTaskIds());
        }

        result.setSuccess(true);
        result.setData(null);
        return result;
    }



}

