package com.sol.servicelog.service;

import com.sol.servicelog.model.Log;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Override
    public void printLogs(Log logs) {
        System.out.println(logs.toString());

    }
}
