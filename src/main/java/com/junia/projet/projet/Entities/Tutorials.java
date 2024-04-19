package com.junia.projet.projet.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tutorials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors author;

    public Tutorials() {
    }

    public Tutorials(Long id, Authors author, LocalDate date, String description, String title) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.description = description;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }
}
