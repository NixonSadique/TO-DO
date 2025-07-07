package com.nixon.TO_DO.service;

import com.nixon.TO_DO.entity.TaskList;

import java.util.List;

public interface TaskListService {

    String createTaskList(Long userId, TaskList taskList);

    List<TaskList> getAllTaskLists();

    List<TaskList> getTaskListsByUser(Long userId);

    String updateTaskList(Long id, TaskList taskList);

    String deleteTaskList(Long id);

}
