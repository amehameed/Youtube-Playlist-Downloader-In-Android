package com.example.videodownloader;

import org.json.JSONObject;

public class InstagramVideo extends InstagramPost {
    public InstagramVideo(JSONObject post) {
        super(post);
    }

    public String getThumbnailURL() {
        return this.image;
    }

    public String getVideoURL() {
        return this.video;
    }
}
