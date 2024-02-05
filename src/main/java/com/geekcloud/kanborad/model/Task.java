package com.geekcloud.kanborad.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    //任务标题
    private String title;
    //任务状态
    private TaskStatus status;
    //任务主导者
    private User director;
    //任务参与人
    private List<User> players;
    //任务详情
    private String detail;
    //任务开始时间
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime startTime;
    //任务结束时间
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime endTime;
}
