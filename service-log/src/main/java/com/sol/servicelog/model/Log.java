package com.sol.servicelog.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class Log {
    private long timestamp;
    @NotBlank(message = "Service name cannot be blank")
    @NotNull(message = "Service name cannot be blank")
    private String ServiceName;

    @NotBlank(message = "Http Method cannot be blank or null")
    @NotNull(message = "Http Method cannot be blank or null")
    private String httpMethod;

    @NotBlank(message = "Method name cannot be blank")
    @NotNull(message = "Method name cannot be blank")
    private String methodName;

    private String url;
    private String message;
    private String threadId;
    private String errorMessage;

    public Log() {}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Log{" +
                "timestamp=" + new Date(timestamp ) +
                ", ServiceName='" + ServiceName + '\'' +
                ", MethodName='" + methodName + '\'' +
                ", HTTPMethod='" + httpMethod + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                ", threadId='" + threadId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
