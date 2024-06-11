package com.sol.serviceshuffle.service;

import com.sol.serviceshuffle.common.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LogService {
    private final WebClient webClient;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    public LogService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void createAndSendLog(String methodName, String httpMethod, String message) {
        Log log = new Log();
        log.setTimestamp(System.currentTimeMillis());
        log.setServiceName(serviceName);
        log.setMethodName(methodName);
        log.setHttpMethod(httpMethod);
        log.setMessage(message);
        log.setThreadId(Long.toString(Thread.currentThread().getId()));
        log.setStackTrace("");
        sendLog(log);
    }

    private void sendLog(Log log) {
        webClient.post()
//                .uri("/")
                .bodyValue(log)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }
}
