package com.bhs.todoapp;

import jakarta.persistence.*;

@Entity
@Table(name = "Todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private int user_id;
    private String title;
    private boolean completed;
    public Todo(int user_id, String title, boolean completed){
        this.title = title;
        this.completed = completed;
        this.user_id = user_id;
    }

    public Todo() {

    }


    public int getUser_id() {
        return user_id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String toString() {
        return "Todo{" +
                "id = " + id + '\'' +
                ",completed = " + completed + '\'' +
                ",title = " + title + '\'' +
                ",userId = " + user_id + '\'' +
                "}";
    }
}
