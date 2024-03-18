package com.geekcloud.kanborad.model;

import java.util.List;

public class TaskGroupAction {
     private long taskGroupId;

    private Task task;

    private List<Long> sourceTaskIds;

    private List<Long> targetTaskIds;

    public long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Long> getSourceTaskIds() {
        return sourceTaskIds;
    }

    public void setSourceTaskIds(List<Long> sourceTaskIds) {
        this.sourceTaskIds = sourceTaskIds;
    }

    public List<Long> getTargetTaskIds() {
        return targetTaskIds;
    }

    public void setTargetTaskIds(List<Long> targetTaskIds) {
        this.targetTaskIds = targetTaskIds;
    }
}
