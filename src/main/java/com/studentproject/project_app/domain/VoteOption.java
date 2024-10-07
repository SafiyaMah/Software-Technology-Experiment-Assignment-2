package com.studentproject.project_app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class VoteOption {
    private Long id;
    private String caption;
    private int presentationOrder;

    @JsonBackReference("poll-option")
    private Poll poll;

    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public int getPresentationOrder() { return presentationOrder; }
    public void setPresentationOrder(int presentationOrder) { this.presentationOrder = presentationOrder; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }

}