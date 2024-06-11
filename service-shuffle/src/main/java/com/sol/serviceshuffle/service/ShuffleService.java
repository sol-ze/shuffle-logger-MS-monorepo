package com.sol.serviceshuffle.service;

import java.util.ArrayList;

public interface ShuffleService {
    ArrayList<Integer> shuffle(int number);
    String printList(ArrayList<?> list);
}
