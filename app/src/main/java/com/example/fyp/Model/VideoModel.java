package com.example.fyp.Model;

public class VideoModel {
    private int statuscode=0;
    private boolean completed=false;
    private int percentage=0;
    private String id="";
    private String name="";
    private boolean favSelected;
    private String url="";
    private String size="";
    private int downloadid;
    public VideoModel(String id, String name, boolean favSelected, String url) {
        this.id = id;
        this.name = name;
        this.favSelected = favSelected;
        this.url = url;
    }
    public VideoModel(String id, String name, boolean favSelected, String url,int statuscode) {
        this.id = id;
        this.name = name;
        this.favSelected = favSelected;
        this.url = url;
        this.statuscode=statuscode;
    }
    public VideoModel(String id, String name, boolean favSelected, String url, String size) {
        this.id = id;
        this.name = name;
        this.favSelected = favSelected;
        this.url = url;
        this.size = size;
    }

    public int getDownloadid() {
        return downloadid;
    }

    public void setDownloadid(int downloadid) {
        this.downloadid = downloadid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavSelected() {
        return favSelected;
    }

    public void setFavSelected(boolean favSelected) {
        this.favSelected = favSelected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
