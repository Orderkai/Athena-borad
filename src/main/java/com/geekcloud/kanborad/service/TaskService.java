package com.geekcloud.kanborad.service;

import java.util.List;
import com.geekcloud.kanborad.model.Task;

public interface TaskService {
     public int add(Task task);

    public int update(Task task);

    public int delete(long taskId);

    public List<Task> findByIds(List<Long> taskIds);

    public Task findById(long taskId);
}
