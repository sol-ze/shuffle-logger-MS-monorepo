package com.sol.serviceshuffle.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ShuffleServiceImplTest {

    private ShuffleService shuffleService;

    @BeforeEach
    public void setUp() {
        shuffleService = new ShuffleServiceImpl();
    }

    @Test
    public void testShuffle_validNumber() {
        int number = 5;
        List<Integer> result = shuffleService.shuffle(number);

        assertNotNull(result);
        assertEquals(number, result.size());
        for (int i = 1; i <= number; i++) {
            assertTrue(result.contains(i));
        }
    }

    @Test
    public void testShuffle_zero() {
        int number = 0;
        List<Integer> result = shuffleService.shuffle(number);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShuffle_minimumNumber() {
        int number = 1;
        List<Integer> result = shuffleService.shuffle(number);

        assertNotNull(result);
        assertEquals(number, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    public void testShuffle_maximumNumber() {
        int number = 1000;
        List<Integer> result = shuffleService.shuffle(number);

        assertNotNull(result);
        assertEquals(number, result.size());

        for (int i = 1; i <= number; i++) {
            assertTrue(result.contains(i));
        }
    }
}