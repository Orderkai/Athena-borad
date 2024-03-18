package com.geekcloud.kanborad.dataobject;

import com.alibaba.fastjson.JSON;
import com.geekcloud.kanborad.model.Task;
import com.geekcloud.kanborad.model.TaskStatus;
import com.geekcloud.kanborad.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDO {
     private long id;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    // 任务标题
    private String title;

    // 任务状态
    private String status;

    // 任务负责人
    private long directorId;

    // 任务其它参与人或关注人
    private String playerIds;

    // 任务详情
    private String detail;

    // 任务开始时间
    private LocalDateTime startTime;

    // 任务结束时间
    private LocalDateTime endTime;

    public TaskDO() {

    }

    public TaskDO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.status = task.getStatus().name();

        if (task.getDirector() != null) {
            this.directorId = task.getDirector().getId();
        }

        if (!CollectionUtils.isEmpty(task.getPlayers())) {
            List<Long> playIds = new ArrayList<>();
            for (User player : task.getPlayers()) {
                playIds.add(player.getId());
            }
            this.playerIds = JSON.toJSONString(playIds);
        }

        this.detail = task.getDetail();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
    }

    public Task toModel() {
        Task task = new Task();
        task.setId(this.getId());
        task.setGmtCreated(this.getGmtCreated());
        task.setGmtModified(this.getGmtModified());
        task.setTitle(this.getTitle());

        if (StringUtils.isNotBlank(this.getStatus())) {
            task.setStatus(TaskStatus.valueOf(this.getStatus()));
        }

        task.setDetail(this.getDetail());
        task.setStartTime(this.getStartTime());
        task.setEndTime(this.getEndTime());

        return task;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public String getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(String playerIds) {
        this.playerIds = playerIds;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

