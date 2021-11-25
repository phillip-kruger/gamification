package com.github.phillipkruger.model;

import java.io.Serializable;

public class Weight implements Measurable, Serializable {
    static final long serialVersionUID = 52L;
    private Long value;

    @Override
    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
    
}