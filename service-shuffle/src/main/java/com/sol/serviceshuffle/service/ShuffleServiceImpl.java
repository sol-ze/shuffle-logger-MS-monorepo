package com.sol.serviceshuffle.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Random;

@Service
public class ShuffleServiceImpl implements ShuffleService{

    @Override
    /**
     * Gets a number from 1 to 1000
     * Return and returns a shuffled array from 1 to this number
     * Without the use of Collection.shuffle() function
     */
    public ArrayList<Integer> shuffle(int number) {
        if(number < 0)
            return null;

        if(number == 0)
            return new ArrayList<>();
        ArrayList<Integer> shuffledArray = new ArrayList<>();

        // Create an array with numbers from 0 to number O(N)
        for(int i = 1 ; i <= number; i++) {
            shuffledArray.add(i);
        }

        // Shuffle the array O(N)
        Random random = new Random();
        for(int i = 0; i < number; i++) {
            int randomIndex = random.nextInt(number);
            int currentValue = shuffledArray.get(i);

            // Swap current index and random index
            shuffledArray.set(i, shuffledArray.get(randomIndex));
            shuffledArray.set(randomIndex, currentValue);

        }
        return shuffledArray;
    }

    @Override
    public String printList(ArrayList<?> list) {
        StringBuilder sb = new StringBuilder();
        if (list == null || list.isEmpty())
            return null;

        sb.append("|");
        list.forEach(n -> {
            sb.append(n).append("|");
        });
        return sb.toString();
    }
}
