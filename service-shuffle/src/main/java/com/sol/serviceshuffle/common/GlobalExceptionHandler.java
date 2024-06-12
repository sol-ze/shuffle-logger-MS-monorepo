package com.sol.serviceshuffle.common;

import com.sol.serviceshuffle.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final LogService logService;

    @Autowired
    public GlobalExceptionHandler(LogService logService) {
        this.logService = logService;
    }

    /**
     * Validation exception handler, the exception will be sent to service-log
     * it's important to check Log object in the request body in order to secure it from possible injections attacks
     * @param ex
     * @param request
     * @param handlerMethod
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request, HandlerMethod handlerMethod) {

        String url = request.getRequestURI();
        String httpMethod = request.getMethod();
        String className = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        // Send logs including exception details
        logService.createAndSendLog(url, className + "#" + methodName, httpMethod, "Validation error", ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Global Exception handler, in case of exception it will return an ErrorResponse to the user
     * and send logs info to service-log including exception details
     * @param exc
     * @param request
     * @param handlerMethod
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc, HttpServletRequest request, HandlerMethod handlerMethod) {
        String url = request.getRequestURI();
        String httpMethod = request.getMethod();
        String className = handlerMethod.getBeanType().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();

        // Send logs including exception details
        logService.createAndSendLog(url, className + "#" + methodName, httpMethod, "Exception occurred", exc);

        ErrorResponse err = new ErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Bad Request");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
