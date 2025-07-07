package com.nixon.TO_DO.dto.request;

import java.time.LocalDateTime;

public record TaskListRequest(Long userId, String title, String description) {
}
