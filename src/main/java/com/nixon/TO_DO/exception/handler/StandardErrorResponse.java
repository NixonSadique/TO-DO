package com.nixon.TO_DO.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StandardErrorResponse {

    private int code;
    private HttpStatus status;
    private OffsetDateTime timestamp;
    private String path;
    private String title;
    private List<Validation> fields;

    @Data
    @AllArgsConstructor
    public static class Validation{
        private String field;
        private String message;
    }

}
