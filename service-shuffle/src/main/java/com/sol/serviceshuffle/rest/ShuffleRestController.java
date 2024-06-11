package com.sol.serviceshuffle.rest;

import com.sol.serviceshuffle.model.ShuffleRequest;
import com.sol.serviceshuffle.service.ShuffleService;
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

    public ShuffleService shuffleService;

    @Autowired
    public ShuffleRestController(ShuffleService shuffleService) {
        this.shuffleService = shuffleService;
    }

    @PostMapping("/shuffle")
    public ResponseEntity<?> shuffle(@Valid @RequestBody ShuffleRequest shuffleRequest) {
        int number = shuffleRequest.getNumber();

        ArrayList<Integer> array = shuffleService.shuffle(number);
        return ResponseEntity.ok(array);
    }
}
