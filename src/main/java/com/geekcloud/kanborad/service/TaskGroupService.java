package com.geekcloud.kanborad.service;

import com.geekcloud.kanborad.model.TaskGroup;

import java.util.List;

public interface TaskGroupService {
    public int add(TaskGroup taskGroup);

    public int modifyTitle(long taskGroupId, String title);

    public int modifyTaskIds(long taskGroupId, List<Long> taskIds);

    public int delete(long taskGroupId);

    public TaskGroup findById(long taskGroupId);

    public List<TaskGroup> findAll(boolean withTask);
}
