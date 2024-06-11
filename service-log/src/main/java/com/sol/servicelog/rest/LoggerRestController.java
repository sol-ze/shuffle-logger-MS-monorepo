package com.sol.servicelog.rest;

import com.sol.servicelog.model.Log;
import com.sol.servicelog.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoggerRestController {

    private LoggerService loggerService;

    @Autowired
    public LoggerRestController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @PostMapping("/log")
    public ResponseEntity<Void> logRequest(@RequestBody Log log) {

        loggerService.printLogs(log);
        return ResponseEntity.ok().build();

    }
}
