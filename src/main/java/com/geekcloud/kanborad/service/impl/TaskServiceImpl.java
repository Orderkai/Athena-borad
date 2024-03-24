package com.geekcloud.kanborad.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geekcloud.kanborad.dao.TaskDAO;
import com.geekcloud.kanborad.dataobject.TaskDO;
import com.geekcloud.kanborad.model.Task;
import com.geekcloud.kanborad.model.User;
import com.geekcloud.kanborad.service.TaskService;
import com.geekcloud.kanborad.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserService userService;

    @Override
    public int add(Task task) {
        if(task == null) {
            return 0;
        }
        TaskDO taskDO = new TaskDO(task);
        int count = taskDAO.add(taskDO);
        task.setId(taskDO.getId());

        return count;
    }

    @Override
    public int update(Task task) {
        if (task == null || task.getId() < 1) {
            return 0;
        }

        TaskDO taskDO = new TaskDO(task);

        return taskDAO.update(taskDO);
    }

    @Override
    public int delete(long taskId) {
        if (taskId < 1) {
            return 0;
        }

        return taskDAO.delete(taskId);
    }

    @Override
    public List<Task> findByIds(List<Long> taskIds) {
        if (CollectionUtils.isEmpty(taskIds)) {
            return null;
        }

        List<TaskDO> taskDOS = taskDAO.findByIds(taskIds);
        return fillTaskMember(taskDOS);
    }

    @Override
    public Task findById(long taskId) {
        if (taskId < 1) {
            return null;
        }

        TaskDO taskDO = taskDAO.findById(taskId);

        if (taskDO == null) {
            return null;
        }

        List<TaskDO> taskDOS = new ArrayList<>();
        taskDOS.add(taskDO);
        List<Task> tasks = fillTaskMember(taskDOS);

        return tasks.get(0);
    }

    // 按照 id 顺序构建 task 模型
    private List<Task> fillTaskMember(List<TaskDO> taskDOS) {
        List<Task> tasks = new ArrayList<>();
        if (CollectionUtils.isEmpty(taskDOS)) {
            return tasks;
        }

        Set<Long> userIds = new HashSet<>();
        for (TaskDO taskDO : taskDOS) {
            userIds.add(taskDO.getDirectorId());

            if (StringUtils.isNotBlank(taskDO.getPlayerIds())) {
                List<Long> playerIds = JSON.parseObject(taskDO.getPlayerIds(), new TypeReference<List<Long>>() {
                });
                userIds.addAll(playerIds);
            }
        }

        List<User> users = userService.findByIds(new ArrayList<>(userIds));
        Map<Long, User> userMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getId(), user);
        }

        for (TaskDO taskDO : taskDOS) {
            Task taskModel = taskDO.toModel();

            taskModel.setDirector(userMap.get(taskDO.getDirectorId()));

            if (StringUtils.isNotBlank(taskDO.getPlayerIds())) {
                List<Long> pIds = JSON.parseObject(taskDO.getPlayerIds(), new TypeReference<List<Long>>() {
                });
                List<User> players = new ArrayList<>();
                for (Long pid : pIds) {
                    User player = userMap.get(pid);
                    if (player != null) {
                        players.add(player);
                    }
                }

                taskModel.setPlayers(players);
            }

            tasks.add(taskModel);
        }

        return tasks;
    }
}
