package com.nixon.TO_DO.exception.handler;

import com.nixon.TO_DO.exception.BadRequestException;
import com.nixon.TO_DO.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<StandardErrorResponse.Validation> validations = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            String name = ( (FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            validations.add(new StandardErrorResponse.Validation(name,message));
        }

        StandardErrorResponse response = new StandardErrorResponse();

        response.setCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle("ERRO DE VALIDACAO DE DADOS! Um ou mais campos estao invalidos!");
        response.setPath(request.getContextPath());
        response.setFields(validations);

        return super.handleExceptionInternal(ex, response, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException (EntityNotFoundException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorResponse response = new StandardErrorResponse();
        response.setCode(status.value());
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle(ex.getMessage());
        response.setStatus(status);
        response.setPath(request.getContextPath());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorResponse response = new StandardErrorResponse();
        response.setCode(status.value());
        response.setTimestamp(OffsetDateTime.now());
        response.setTitle(ex.getMessage());
        response.setStatus(status);
        response.setPath(request.getServletPath().toString());
        return new ResponseEntity<>(response, status);
    }
}
