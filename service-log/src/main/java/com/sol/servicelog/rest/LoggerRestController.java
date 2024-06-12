package com.sol.servicelog.rest;

import com.sol.servicelog.model.Log;
import com.sol.servicelog.service.LoggerService;
import jakarta.validation.Valid;
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

    /**
     * POST /api/log
     * @param log object
     * @return prints the logs, and returns empty response with HTTP status 200 in case everything is ok
     */
    @PostMapping("/log")
    public ResponseEntity<Void> logRequest(@Valid @RequestBody Log log) {
        // Prints logs in console, recommended to send the logs to a Database, and have saved logs
        loggerService.printLogs(log);
        return ResponseEntity.ok().build();

    }
}
