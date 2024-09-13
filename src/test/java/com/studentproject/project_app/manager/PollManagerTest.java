package com.studentproject.project_app.manager;

import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.domain.User;
import com.studentproject.project_app.domain.Vote;
import com.studentproject.project_app.domain.VoteOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PollManagerTest {

    private PollManager pollManager;

    @BeforeEach
    public void setUp() {
        pollManager = new PollManager();
    }

    @Test
    public void testAddUserAndGetUser() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);

        User retrievedUser = pollManager.getUser("testUser");
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
    }

    @Test
    public void testCreatePoll() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);

        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true);
        assertNotNull(poll);
        assertEquals("What is your favorite color?", poll.getQuestion());
        assertEquals(user, poll.getCreator());
    }

    @Test
    public void testCastVote() {
        User user = new User("testUser", "test@example.com");
        pollManager.addUser(user);

        Poll poll = pollManager.createPoll("testUser", "What is your favorite color?", true);
        Set<VoteOption> voteOptions = new HashSet<>();
        voteOptions.add(new VoteOption("Red", 1));
        poll.setVoteOptions(voteOptions);

        Vote vote = new Vote();
        vote.setVoteOption(voteOptions.iterator().next());

        Vote castVote = pollManager.castVote("testUser", poll.getId(), vote);
        assertNotNull(castVote);
        assertEquals(vote.getVoteOption().getCaption(), "Red");
        assertEquals(1, poll.getVotes().size());
    }
}
