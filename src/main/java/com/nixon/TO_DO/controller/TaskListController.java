package com.nixon.TO_DO.controller;

import com.nixon.TO_DO.dto.request.TaskListRequest;
import com.nixon.TO_DO.dto.response.TaskListResponse;
import com.nixon.TO_DO.dto.response.UserResponse;
import com.nixon.TO_DO.entity.TaskList;
import com.nixon.TO_DO.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo/list")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService listService;

    @PostMapping("/create")
    public ResponseEntity<String> createList(@RequestBody TaskListRequest taskListRequest){
        TaskList taskList = new TaskList();
        taskList.setTitle(taskListRequest.title());
        taskList.setDescription(taskListRequest.description());
        taskList.setCreationDate(LocalDateTime.now());
        taskList.setLastUpdate(taskList.getCreationDate());
        return ResponseEntity.ok(listService.createTaskList(taskListRequest.userId(), taskList));
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskListResponse>> getAllTaskLists(){
        return ResponseEntity.ok(listService.getAllTaskLists().stream().map(
                (list) -> new  TaskListResponse(list.getUser().getUsername()
                        ,list.getId()
                        ,list.getTitle()
                        ,list.getDescription()
                        ,list.getCreationDate()
                        ,list.getLastUpdate())
        ).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TaskListResponse>> getTaskListsByUser(@PathVariable Long id){
        return ResponseEntity.ok(listService.getTaskListsByUser(id).stream().map(
                (list) -> new  TaskListResponse(list.getUser().getUsername()
                        ,list.getId()
                        ,list.getTitle()
                        ,list.getDescription()
                        ,list.getCreationDate()
                        ,list.getLastUpdate())
        ).collect(Collectors.toList()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTaskList(@PathVariable Long id){
        return ResponseEntity.ok(listService.deleteTaskList(id));
    }
}
