package com.nixon.TO_DO.dto.response;

import com.nixon.TO_DO.entity.User;

import java.time.LocalDateTime;

public record TaskListResponse(String user, Long id, String title , String description, LocalDateTime creationDate, LocalDateTime lastUpdate) {
}
