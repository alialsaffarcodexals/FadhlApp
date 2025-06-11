package com.fadhlapp;

import java.time.LocalDate;

public class MediaItem {
    private String name;
    private String path;
    private LocalDate dateAdded;

    public MediaItem(String name, String path) {
        this.name = name;
        this.path = path;
        this.dateAdded = LocalDate.now();
    }

    public String getName() { return name; }
    public String getPath() { return path; }
    public LocalDate getDateAdded() { return dateAdded; }

    public void setName(String name) { this.name = name; }
    public void setPath(String path) { this.path = path; }
}
