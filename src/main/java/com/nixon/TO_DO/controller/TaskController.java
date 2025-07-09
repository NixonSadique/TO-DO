package com.nixon.TO_DO.controller;

import com.nixon.TO_DO.dto.request.TaskRequest;
import com.nixon.TO_DO.dto.response.TaskResponse;
import com.nixon.TO_DO.entity.Task;
import com.nixon.TO_DO.entity.enums.Priority;
import com.nixon.TO_DO.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    
    @PostMapping("/create")
    ResponseEntity<String> createTask(@RequestBody TaskRequest request){
        Task task = new Task();
        task.setMessage(request.message());

        String priority = request.priority();

        task.setPriority(
                priority!=null?
                        Priority.valueOf(priority.toUpperCase().strip()):
                        Priority.NONE
        );

        return ResponseEntity.ok(taskService.createTask(task, request.listId()));
    }

    @GetMapping("/{id}")
    ResponseEntity<List<TaskResponse>> getTasksInList(@PathVariable Long id){
        return ResponseEntity.ok(
                taskService.getTasksInTaskList(id).stream().map(
                        (task) -> new TaskResponse(
                                task.getId(),
                                task.getMessage(),
                                task.isCompleted(),
                                task.getPriority(),
                                task.getTaskList().getTitle())
                ).collect(Collectors.toList())
        );
    }

    @PutMapping("/complete/{id}")
    ResponseEntity<String> completeTask(@PathVariable Long id){
        return ResponseEntity.ok(
                taskService.completeTask(id)
        );
    }
}
