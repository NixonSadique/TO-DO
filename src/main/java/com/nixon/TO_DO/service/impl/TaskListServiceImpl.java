package com.nixon.TO_DO.service.impl;

import com.nixon.TO_DO.entity.Task;
import com.nixon.TO_DO.entity.TaskList;
import com.nixon.TO_DO.entity.User;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import com.nixon.TO_DO.repository.TaskListRepository;
import com.nixon.TO_DO.repository.UserRepository;
import com.nixon.TO_DO.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final UserRepository userRepository;
    private final TaskListRepository listRepository;

    @Override
    public String createTaskList(Long userId, TaskList taskList) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Couldn't create TaskList because User Was Not Found!")
        );
        taskList.setUser(user);
        listRepository.save(taskList);
        return "TaskList created Successfully";
    }

    @Override
    public List<TaskList> getAllTaskLists() {
        return listRepository.findAll();
    }

    @Override
    public List<TaskList> getTaskListsByUser(Long userId) {
        if (userRepository.existsById(userId)){
            return listRepository.findByUserId(userId);
        }
        throw new EntityNotFoundException("User Not Found");
    }

    @Override
    public String updateTaskList(Long id, TaskList taskList) {
        return "";
    }

    @Override
    public String deleteTaskList(Long id) {
        this.listRepository.deleteById(id);
        return "TaskList Deleted with success";
    }
}
