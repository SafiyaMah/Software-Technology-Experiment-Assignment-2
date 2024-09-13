package com.studentproject.project_app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String email;

    @JsonManagedReference("user-poll")
    private Set<Poll> createdPolls = new HashSet<>();

    @JsonManagedReference("user-vote")
    private Set<Vote> votes = new HashSet<>();

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Poll> getCreatedPolls() { return createdPolls; }
    public void setCreatedPolls(Set<Poll> createdPolls) { this.createdPolls = createdPolls; }

    public Set<Vote> getVotes() { return votes; }
    public void setVotes(Set<Vote> votes) { this.votes = votes; }
}
