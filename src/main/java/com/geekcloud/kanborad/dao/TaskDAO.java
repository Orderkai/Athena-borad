package com.geekcloud.kanborad.dao;

import com.geekcloud.kanborad.dataobject.TaskDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskDAO {
    int add(TaskDO taskDO);

    int update(TaskDO taskDO);

    int delete(@Param("id") long taskId);

    List<TaskDO> findByIds(@Param("ids") List<Long> taskIds);

    TaskDO findById(@Param("id") long taskId);
}
