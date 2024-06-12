package com.sol.serviceshuffle.service;

import com.sol.serviceshuffle.common.Log;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LogServiceImpl implements LogService {
    private final WebClient webClient;

    @Value("${spring.application.name}")
    private String serviceName;


    @Autowired
    public LogServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public void createAndSendLog(String url, String methodName, String httpMethod, String message) {
        Log log = new Log();
        log.setTimestamp(System.currentTimeMillis());
        log.setUrl(url);
        log.setServiceName(serviceName);
        log.setMethodName(methodName);
        log.setHttpMethod(httpMethod);
        log.setMessage(message);
        log.setThreadId(Long.toString(Thread.currentThread().getId()));
        sendLog(log);
    }

    @Override
    public void createAndSendLog(String url, String methodName, String httpMethod, String message, Exception exception) {
        Log log = new Log();
        log.setTimestamp(System.currentTimeMillis());
        log.setUrl(url);
        log.setServiceName(serviceName);
        log.setMethodName(methodName);
        log.setHttpMethod(httpMethod);
        log.setMessage(message);
        log.setThreadId(Long.toString(Thread.currentThread().getId()));

        if (exception != null) {
            log.setErrorMessage(exception.getMessage());
        }

        sendLog(log);
    }

    @Override
    public void sendLog(Log log) {
        webClient.post()
                .bodyValue(log)
                .retrieve()
                .toBodilessEntity()
                .doOnError(error -> System.out.println("Error sending log: " + error.getMessage()))
                .onErrorResume(error -> Mono.empty())
                .subscribe();
    }

    @Override
    public void logRequestDetails(HttpServletRequest request, String classAndMethodName, String message) {
        try {
            String url = request.getRequestURI();
            String httpMethod = request.getMethod();
            createAndSendLog(url, classAndMethodName, httpMethod, message);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
