package com.geekcloud.kanborad.dataobject;

import com.alibaba.fastjson.JSON;
import com.geekcloud.kanborad.model.Task;
import org.springframework.util.CollectionUtils;
import com.geekcloud.kanborad.model.TaskGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupDO {
    private long id;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    private String title;

    private String taskIds;

    public TaskGroupDO() {

    }

    public TaskGroupDO(TaskGroup taskGroup) {
        this.id = taskGroup.getId();
        this.title = taskGroup.getTitle();

        if (!CollectionUtils.isEmpty(taskGroup.getTasks())) {
            List<Long> taskIds = new ArrayList<>();

            for (Task task : taskGroup.getTasks()) {
                taskIds.add(task.getId());
            }

            this.taskIds = JSON.toJSONString(taskIds);
        }
    }

    public TaskGroup toModel() {
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setId(this.id);
        taskGroup.setGmtCreated(this.gmtCreated);
        taskGroup.setGmtModified(this.gmtModified);
        taskGroup.setTitle(this.title);

        return taskGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(String taskIds) {
        this.taskIds = taskIds;
    }
}
