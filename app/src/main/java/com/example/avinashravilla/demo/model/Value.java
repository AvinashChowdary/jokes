package com.example.avinashravilla.demo.model;

public class Value {
    private String author;

    private String[] categories;

    private String id;

    private String joke;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "ClassPojo [author = " + author + ", categories = " + categories + ", id = " + id + ", joke = " + joke + "]";
    }
}
