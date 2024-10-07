package com.studentproject.project_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.Instant;

public class Vote {
    private Instant publishedAt;
    private VoteOption voteOption;

    @JsonBackReference("user-vote")
    private User voter;

    @JsonBackReference("poll-vote")
    private Poll poll;

    public Vote() {
        this.publishedAt = Instant.now();
    }

    // Getters and Setters
    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public VoteOption getVoteOption() { return voteOption; }
    public void setVoteOption(VoteOption voteOption) { this.voteOption = voteOption; }

    public User getVoter() { return voter; }
    public void setVoter(User voter) { this.voter = voter; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }

}