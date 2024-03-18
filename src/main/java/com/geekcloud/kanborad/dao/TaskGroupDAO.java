package com.geekcloud.kanborad.dao;

import com.geekcloud.kanborad.dataobject.TaskGroupDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskGroupDAO {
    int add(TaskGroupDO taskGroupDO);

    int update(TaskGroupDO taskGroupDO);

    int delete(@Param("id") long taskGroupId);

    List<TaskGroupDO> findAll();

    TaskGroupDO findById(@Param("id") long taskGroupId);
}
