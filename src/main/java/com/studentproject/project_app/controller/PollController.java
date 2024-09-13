package com.studentproject.project_app.controller;

import com.studentproject.project_app.domain.Poll;
import com.studentproject.project_app.manager.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;

    // Create a new poll
    @PostMapping
    public Poll createPoll(@RequestParam String username, @RequestBody Poll poll) {
        return pollManager.createPoll(username, poll.getQuestion(), poll.isPublic());
    }

    // Get all polls
    @GetMapping
    public Map<Long, Poll> getAllPolls() {
        return pollManager.getPolls();
    }

    // Get a specific poll by ID
    @GetMapping("/{pollId}")
    public Poll getPoll(@PathVariable Long pollId) {
        return pollManager.getPolls().get(pollId);
    }

    // **Add this method to handle the DELETE request**
    @DeleteMapping("/{pollId}")
    public void deletePoll(@PathVariable Long pollId) {
        pollManager.deletePoll(pollId);  // This method needs to be implemented in PollManager
    }
}
