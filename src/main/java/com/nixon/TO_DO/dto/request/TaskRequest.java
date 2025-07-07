package com.nixon.TO_DO.dto.request;

import com.nixon.TO_DO.entity.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(
        @NotNull Long listId,
        @NotBlank String message,
        String priority) {
}
