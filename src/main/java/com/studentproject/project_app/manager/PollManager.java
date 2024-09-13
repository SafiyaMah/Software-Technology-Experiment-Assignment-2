package com.studentproject.project_app.manager;

import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.domain.User;
import com.studentproject.project_app.domain.Vote;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private Long nextPollId = 1L;

    // Add a user
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Get a user by username
    public User getUser(String username) {
        return users.get(username);
    }

    // Get all users (Added this method)
    public Map<String, User> getUsers() {
        return users;
    }

    // Create a poll
    public Poll createPoll(String username, String question, boolean isPublic) {
        User creator = users.get(username);
        if (creator == null) {
            throw new IllegalArgumentException("User not found.");
        }
        Poll poll = new Poll(question, null, creator, isPublic);  // Updated constructor usage
        poll.setId(nextPollId++);
        creator.getCreatedPolls().add(poll);
        polls.put(poll.getId(), poll);
        return poll;
    }

    // Get all polls
    public Map<Long, Poll> getPolls() {
        return polls;
    }

    // Cast a vote
    public Vote castVote(String username, Long pollId, Vote vote) {
        User voter = users.get(username);
        if (voter == null) {
            throw new IllegalArgumentException("User not found.");
        }
        Poll poll = polls.get(pollId);
        if (poll == null) {
            throw new IllegalArgumentException("Poll not found.");
        }
        vote.setPoll(poll);
        vote.setVoter(voter);
        poll.getVotes().add(vote);
        voter.getVotes().add(vote);
        return vote;
    }

    public void deletePoll(Long pollId) {
        polls.remove(pollId);
    }
}
