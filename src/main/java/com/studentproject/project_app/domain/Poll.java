package com.studentproject.project_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Poll {
    private Long id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private boolean isPublic;  // Added this field to indicate public/private status

    @JsonManagedReference("poll-option")
    private Set<VoteOption> voteOptions = new HashSet<>();

    @JsonManagedReference("poll-vote")
    private Set<Vote> votes = new HashSet<>();

    @JsonBackReference("user-poll")
    private User creator;

    public Poll() {}

    public Poll(String question, Instant validUntil, User creator, boolean isPublic) {
        this.question = question;
        this.publishedAt = Instant.now();
        this.validUntil = validUntil;
        this.creator = creator;
        this.isPublic = isPublic;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getValidUntil() { return validUntil; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }

    public Set<VoteOption> getVoteOptions() { return voteOptions; }
    public void setVoteOptions(Set<VoteOption> voteOptions) { this.voteOptions = voteOptions; }

    public Set<Vote> getVotes() { return votes; }
    public void setVotes(Set<Vote> votes) { this.votes = votes; }

}