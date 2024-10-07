package com.studentproject.project_app.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.domain.User;
import com.studentproject.project_app.domain.VoteOption;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class PollManagerTest {

    private PollManager pollManager;

    @BeforeEach
    public void setUp() {
        pollManager = new PollManager();
    }

    @Test
    public void testCreatePoll() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);

        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true, Instant.now().plusSeconds(3600));
        assertNotNull(poll);
        assertEquals("What is your favorite color?", poll.getQuestion());
        assertEquals(user, poll.getCreator());
    }

    @Test
    public void testCreateVoteOption() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);
        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true, Instant.now().plusSeconds(3600));

        VoteOption voteOption = pollManager.createVoteOption(poll.getId(), "Red", 1);
        assertNotNull(voteOption);
        assertEquals("Red", voteOption.getCaption());
        assertEquals(1, voteOption.getPresentationOrder());
        assertTrue(poll.getVoteOptions().contains(voteOption));
    }

    @Test
    public void testCastVote() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);
        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true, Instant.now().plusSeconds(3600));
        VoteOption voteOption = pollManager.createVoteOption(poll.getId(), "Red", 1);

        assertDoesNotThrow(() -> pollManager.castVote("testUser", poll.getId(), voteOption.getId(), Instant.now()));
        assertEquals(1, poll.getVotes().size());
    }

    @Test
    public void testUserNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pollManager.createPoll("nonExistentUser", "What is your favorite color?", true, Instant.now().plusSeconds(3600));
        });

        assertEquals("User not found.", exception.getMessage());
    }

    @Test
    public void testPollNotFound() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pollManager.createVoteOption(99L, "Blue", 1);
        });

        assertEquals("Poll not found.", exception.getMessage());
    }

    @Test
    public void testVoteOptionNotFound() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);
        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true, Instant.now().plusSeconds(3600));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pollManager.castVote("testUser", poll.getId(), 99L, Instant.now());
        });

        assertEquals("Vote option not found.", exception.getMessage());
    }
}