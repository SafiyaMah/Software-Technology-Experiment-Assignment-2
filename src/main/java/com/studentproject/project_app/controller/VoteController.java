package com.studentproject.project_app.controller;

import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.domain.Vote;
import com.studentproject.project_app.manager.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/polls/{pollId}/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    // Cast a vote
    @PostMapping
    public Vote castVote(@PathVariable Long pollId, @RequestParam String username, @RequestParam Long voteOptionId, @RequestBody Vote vote) {
        return pollManager.castVote(username, pollId, voteOptionId, vote.getPublishedAt());
    }

    // Get all votes for a specific poll
    @GetMapping
    public Set<Vote> getVotesForPoll(@PathVariable Long pollId) {
        Poll poll = pollManager.getPolls().get(pollId);
        return poll.getVotes();
    }
}