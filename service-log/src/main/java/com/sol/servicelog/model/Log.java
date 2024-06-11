package com.sol.servicelog.model;

public class Log {
    private long timestamp;
    private String ServiceName;
    private String method;
    private String host;
    private String message;
    private String threadId;
    private String stackTrace;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "Log{" +
                "timestamp=" + timestamp +
                ", ServiceName='" + ServiceName + '\'' +
                ", method='" + method + '\'' +
                ", host='" + host + '\'' +
                ", message='" + message + '\'' +
                ", threadId='" + threadId + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }
}
