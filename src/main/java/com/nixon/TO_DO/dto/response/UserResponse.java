package com.nixon.TO_DO.dto.response;

import com.nixon.TO_DO.entity.TaskList;

import java.util.List;

public record UserResponse(Long id, String username, String password, List<TaskListResponse> lists) {
}
