package com.github.phillipkruger.model;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class Address implements Serializable {
    static final long serialVersionUID = 42L;
    
    @JsonbTransient
    private Integer id;
    
    private List<String> lines;
    private String code;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ElementCollection(fetch = FetchType.EAGER,targetClass=String.class) 
    @OrderColumn
    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
