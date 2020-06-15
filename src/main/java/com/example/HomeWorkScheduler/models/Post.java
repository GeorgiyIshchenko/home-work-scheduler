package com.example.HomeWorkScheduler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subjectName,
            body,
            date;

    public Post(String subjectName, String body, String date) {
        this.subjectName = subjectName;
        this.body = body;
        this.date = date;
    }

    public Post(){

    }

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
