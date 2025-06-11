package com.fadhlapp;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private List<MediaItem> youtubeVideos = new ArrayList<>();
    private List<MediaItem> instagramVideos = new ArrayList<>();
    private List<MediaItem> instagramPhotos = new ArrayList<>();
    private List<MediaItem> personalPhotos = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Website> websites = new ArrayList<>();

    public List<MediaItem> getYoutubeVideos() { return youtubeVideos; }
    public List<MediaItem> getInstagramVideos() { return instagramVideos; }
    public List<MediaItem> getInstagramPhotos() { return instagramPhotos; }
    public List<MediaItem> getPersonalPhotos() { return personalPhotos; }
    public List<Account> getAccounts() { return accounts; }
    public List<Website> getWebsites() { return websites; }
}
