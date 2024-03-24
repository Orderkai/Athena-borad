package com.geekcloud.kanborad.service.impl;

import com.geekcloud.kanborad.model.TaskGroup;
import com.geekcloud.kanborad.service.TaskGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupImpl implements TaskGroupService {

    @Override
    public int add(TaskGroup taskGroup) {
        return 0;
    }

    @Override
    public int modifyTitle(long taskGroupId, String title) {
        return 0;
    }

    @Override
    public int modifyTaskIds(long taskGroupId, List<Long> taskIds) {
        return 0;
    }

    @Override
    public int delete(long taskGroupId) {
        return 0;
    }

    @Override
    public TaskGroup findById(long taskGroupId) {
        return null;
    }

    @Override
    public List<TaskGroup> findAll(boolean withTask) {
        return null;
    }
}
