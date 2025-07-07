package com.nixon.TO_DO.dto.response;

import com.nixon.TO_DO.entity.TaskList;
import com.nixon.TO_DO.entity.enums.Priority;

public record TaskResponse(Long id, String message, boolean completed, Priority priority, String taskList) {
}
