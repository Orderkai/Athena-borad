package com.geekcloud.kanborad.model;

public enum TaskStatus {
    //初始化还未开始
    init,
    //进行中
    in_progress,
    //正常完成
    complete,
    //出现问题终止
    terminated
}
