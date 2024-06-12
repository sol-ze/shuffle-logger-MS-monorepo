package com.sol.serviceshuffle.rest;

import com.sol.serviceshuffle.service.LogService;
import com.sol.serviceshuffle.service.ShuffleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ShuffleRestController.class)
public class ShuffleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShuffleService shuffleService;

    @MockBean
    private LogService logService;

    @BeforeEach
    public void setUp() {
        when(shuffleService.shuffle(anyInt())).thenAnswer(invocation -> {
            int number = invocation.getArgument(0);
            return IntStream.rangeClosed(1, number)
                    .boxed()
                    .collect(Collectors.toList());
        });
    }


    @Test
    public void testShuffle() throws Exception {
        String requestBody = "{\"number\": 5}";

        mockMvc.perform(post("/api/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0]").value(1))
                .andExpect(jsonPath("$[1]").value(2))
                .andExpect(jsonPath("$[2]").value(3))
                .andExpect(jsonPath("$[3]").value(4))
                .andExpect(jsonPath("$[4]").value(5));
    }

    @Test
    public void testShuffle_invalidInput() throws Exception {
        String requestBody = "{}"; // Missing 'number' field

        mockMvc.perform(post("/api/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testShuffle_wrongBody() throws Exception {
        String requestBody = "{\"num\": 2}"; // Missing 'number' field

        mockMvc.perform(post("/api/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testShuffle_minimumNumber() throws Exception {
        String requestBody = "{\"number\": 1}";

        mockMvc.perform(post("/api/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0]").value(1));
    }

    @Test
    public void testShuffle_maximumNumber() throws Exception {
        String requestBody = "{\"number\": 1000}";

        mockMvc.perform(post("/api/shuffle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1000));
    }
}
