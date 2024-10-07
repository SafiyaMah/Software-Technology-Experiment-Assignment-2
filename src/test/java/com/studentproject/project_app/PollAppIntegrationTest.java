package com.studentproject.project_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PollAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;
    private Poll poll;

    @BeforeEach
    public void setup() {
        // Initialize User and Poll objects without the password
        user = new User("user1", "user1@example.com");
        poll = new Poll("What is your favorite color?", null, user, true);
    }


    @Test
    public void testFullApiFlow() throws Exception {
        // 1. Create a new user
        ResultActions createUser = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"));

        // 2. Get all users
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user1.username").value("user1"));

        // 3. Create a poll
        ResultActions createPoll = mockMvc.perform(post("/polls")
                        .param("username", "user1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(poll)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question").value("What is your favorite color?"));

        // 4. Get all polls
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.1.question").value("What is your favorite color?"));

        // 5. Cast a vote on the poll
        String voteJson = "{\"voteOption\": {\"caption\": \"Red\", \"presentationOrder\": 1}}";
        mockMvc.perform(post("/polls/1/votes")
                        .param("username", "user1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.voteOption.caption").value("Red"));

        // 6. Delete the poll
        mockMvc.perform(delete("/polls/1"))
                .andExpect(status().isOk());

        // 7. Verify poll deletion
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(content().string("{}"));
    }
}