package com.example.HomeWorkScheduler.models;

public class Card {
    String name,
            lastTask,
            date,
            subjectId;

    public Card(String name, String lastTask,String date,String subjectId) {
        this.name = name;
        this.lastTask = lastTask;
        this.date=date;
        this.subjectId=subjectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastTask(String lastTask) {
        this.lastTask = lastTask;
    }

    public String getName() {
        return name;
    }

    public String getLastTask() {
        return lastTask;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
