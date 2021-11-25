package com.github.phillipkruger.model;

import java.io.Serializable;

public class Score implements Measurable, Serializable  {
    static final long serialVersionUID = 49L;
    private String id;
    private ScoreType name;
    private Long value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScoreType getName() {
        return name;
    }

    public void setName(ScoreType name) {
        this.name = name;
    }

    @Override
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
