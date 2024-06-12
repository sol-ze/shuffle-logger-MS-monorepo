package com.sol.serviceshuffle.rest;

import com.sol.serviceshuffle.model.ShuffleRequest;
import com.sol.serviceshuffle.service.LogServiceImpl;
import com.sol.serviceshuffle.service.ShuffleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class ShuffleRestController {

    public final ShuffleService shuffleService;
    private final LogServiceImpl logService;

    @Autowired
    public ShuffleRestController(ShuffleService shuffleService, LogServiceImpl logService) {
        this.shuffleService = shuffleService;
        this.logService = logService;
    }

    @PostMapping("/shuffle")
    public ResponseEntity<?> shuffle(@Valid @RequestBody ShuffleRequest shuffleRequest, HttpServletRequest request) {

        int number = shuffleRequest.getNumber();

        String url = request.getRequestURI(); // This will provide the URI part, e.g., /api/shuffle
        String httpMethod = request.getMethod();

        ArrayList<Integer> array = shuffleService.shuffle(number);
        logService.createAndSendLog(url, this.getClass().getName() + "#shuffle", httpMethod, "Shuffled numbers up to: " + number);
        return ResponseEntity.ok(array);
    }
}
