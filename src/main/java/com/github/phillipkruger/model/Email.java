package com.github.phillipkruger.model;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Email implements Serializable {
    static final long serialVersionUID = 45L;
    
    @JsonbTransient
    private Integer id;
    
    private String value;

    public Email() {
    }

    public Email(String value) {
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
