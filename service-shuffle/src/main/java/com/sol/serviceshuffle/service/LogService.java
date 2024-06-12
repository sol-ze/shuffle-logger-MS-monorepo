package com.sol.serviceshuffle.service;

import com.sol.serviceshuffle.common.Log;
import jakarta.servlet.http.HttpServletRequest;

public interface LogService {
    void createAndSendLog(String url, String methodName, String httpMethod, String message, Exception exception);
    void createAndSendLog(String url, String methodName, String httpMethod, String message);
    void sendLog(Log log);
    void logRequestDetails(HttpServletRequest request, String classAndMethodName, String message);
}
