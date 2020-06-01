package com.example.videodownloader;

import org.json.JSONObject;

public class InstagramImage extends InstagramPost {
    public InstagramImage(JSONObject post) {
        super(post);
    }

    public String getImageURL() {
        return this.image;
    }
}
