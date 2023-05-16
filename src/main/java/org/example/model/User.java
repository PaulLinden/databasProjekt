package org.example.model;

import java.time.LocalDate;

public class User {

    private int id;
    private String name;
    private String email;
    private LocalDate created;
    private boolean online;
    private boolean lastOnline;

    private boolean newsLetter;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(boolean lastOnline) {
        this.lastOnline = lastOnline;
    }

    public boolean isNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
}



