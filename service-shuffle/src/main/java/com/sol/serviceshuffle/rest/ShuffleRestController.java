package com.sol.serviceshuffle.rest;

import com.sol.serviceshuffle.model.ShuffleRequest;
import com.sol.serviceshuffle.service.LogService;
import com.sol.serviceshuffle.service.ShuffleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ShuffleRestController {

    public final ShuffleService shuffleService;
    private final LogService logService;

    @Autowired
    public ShuffleRestController(ShuffleService shuffleService, LogService logService) {
        this.shuffleService = shuffleService;
        this.logService = logService;
    }

    /**
     * POST /api/shuffle
     * @param shuffleRequest contains "number" between 1 and 1000
     * @param request
     * @return array with a size of number, containing shuffled numbers from 1 to number
     */
    @PostMapping("/shuffle")
    public ResponseEntity<?> shuffle(@Valid @RequestBody ShuffleRequest shuffleRequest, HttpServletRequest request) {
        int number = shuffleRequest.getNumber();
        List<Integer> array = shuffleService.shuffle(number);
        // log request details using Async
        logService.logRequestDetails(request, this.getClass().getName() + "#shuffle", "Shuffled numbers up to: " + number);
        return ResponseEntity.ok(array);
    }
}
