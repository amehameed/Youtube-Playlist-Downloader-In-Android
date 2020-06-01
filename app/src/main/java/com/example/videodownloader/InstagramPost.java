package com.example.videodownloader;

import org.json.JSONObject;

public class InstagramPost {
    private final String type;
    protected final String image;
    protected final String video;
    public InstagramPost(JSONObject post) {
        this.video = post.optString("video", "");
        this.image = post.optString("image", "");
        if(this.video.isEmpty()) {
            this.type = "IMAGE";
        } else {
            this.type = "VIDEO";
        }
    }

    public String getPostType() {
        return this.type;
    }
}
