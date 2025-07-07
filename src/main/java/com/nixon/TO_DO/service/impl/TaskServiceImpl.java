package com.nixon.TO_DO.service.impl;

import com.nixon.TO_DO.entity.Task;
import com.nixon.TO_DO.entity.TaskList;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import com.nixon.TO_DO.repository.TaskListRepository;
import com.nixon.TO_DO.repository.TaskRepository;
import com.nixon.TO_DO.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;
    public final TaskListRepository taskListRepository;

    @Override
    public String createTask(Task task, Long taskListId) {
        task.setCompleted(false);
        TaskList rawList = taskListRepository.findById(taskListId).orElseThrow(
                () -> new EntityNotFoundException("Task List Not Found!!")
        );
        
        task.setTaskList(rawList);
        taskRepository.save(task);
        return "Task created susccesfully!!";
    }

    @Override
    public List<Task> getTasksInTaskList(Long taskListId) {
        return taskRepository.findAllByTaskListId(taskListId);
    }

    @Override
    public String completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task Not Found!")
        );
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return "Task completion changed with success!!";
    }
}
