package com.sol.serviceshuffle.service;

import com.sol.serviceshuffle.common.Log;

public interface LogService {
    void createAndSendLog(String url, String methodName, String httpMethod, String message, Exception exception);

    void createAndSendLog(String url, String methodName, String httpMethod, String message);

    void sendLog(Log log);
}
