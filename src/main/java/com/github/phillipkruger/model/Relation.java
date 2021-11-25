package com.github.phillipkruger.model;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Relation implements Serializable {
    static final long serialVersionUID = 48L;
    
    @JsonbTransient
    private Integer id;
    
    private RelationType relationType;
    private String personURI;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public String getPersonURI() {
        return personURI;
    }

    public void setPersonURI(String personURI) {
        this.personURI = personURI;
    }
}
