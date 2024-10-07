package com.studentproject.project_app.manager;

import com.studentproject.project_app.domain.*;
import com.studentproject.project_app.domain.VoteOption;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private Long nextPollId = 1L;
    private Long nextVoteOptionId = 1L;

    // Add a user
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Get a user by username
    public User getUser(String username) {
        return users.get(username);
    }

    // Get all users
    public Map<String, User> getUsers() {
        return users;
    }

    // Create a poll
    public Poll createPoll(String username, String question, boolean isPublic, Instant validUntil) {
        User creator = users.get(username);
        if (creator == null) {
            throw new IllegalArgumentException("User not found.");
        }
        Poll poll = new Poll(question, validUntil, creator, isPublic);
        poll.setId(nextPollId++);
        creator.getCreatedPolls().add(poll);
        polls.put(poll.getId(), poll);
        return poll;
    }

    // Get all polls
    public Map<Long, Poll> getPolls() {
        return polls;
    }

    // Create a vote option
    public VoteOption createVoteOption(Long pollId, String caption, int presentationOrder) {
        Poll poll = polls.get(pollId);
        if (poll == null) {
            throw new IllegalArgumentException("Poll not found.");
        }
        VoteOption voteOption = new VoteOption(caption, presentationOrder);
        voteOption.setId(nextVoteOptionId++);
        voteOption.setPoll(poll);
        poll.getVoteOptions().add(voteOption);
        return voteOption;
    }

    // Cast a vote
    public Vote castVote(String username, Long pollId, Long voteOptionId, Instant publishedAt) {
        User voter = users.get(username);
        if (voter == null) {
            throw new IllegalArgumentException("User not found.");
        }
        Poll poll = polls.get(pollId);
        if (poll == null) {
            throw new IllegalArgumentException("Poll not found.");
        }
        VoteOption voteOption = poll.getVoteOptions().stream()
                .filter(option -> option.getId().equals(voteOptionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vote option not found."));
        Vote vote = new Vote();
        vote.setPublishedAt(publishedAt);
        vote.setVoteOption(voteOption);
        vote.setVoter(voter);
        vote.setPoll(poll);
        poll.getVotes().add(vote);
        voter.getVotes().add(vote);
        return vote;
    }

    // Delete a poll
    public void deletePoll(Long pollId) {
        polls.remove(pollId);
    }
}
