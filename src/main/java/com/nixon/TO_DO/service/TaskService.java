package com.nixon.TO_DO.service;

import com.nixon.TO_DO.entity.Task;
import com.nixon.TO_DO.entity.TaskList;

import java.util.List;

public interface TaskService {

    String createTask(Task task, Long taskListId);

    List<Task> getTasksInTaskList(Long taskListId);

    String completeTask(Long id);
}
