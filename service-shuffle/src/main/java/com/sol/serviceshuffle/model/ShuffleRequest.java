package com.sol.serviceshuffle.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ShuffleRequest {
    @Min(value = 1, message = "Number must be between 1 and 1000")
    @Max(value = 1000, message = "Number must be between 1 and 1000")
    private int number;

    public ShuffleRequest() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
