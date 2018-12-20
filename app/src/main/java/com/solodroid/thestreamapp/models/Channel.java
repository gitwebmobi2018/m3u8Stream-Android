package com.solodroid.thestreamapp.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Channel implements Serializable {

    @NonNull
    private static List<Channel> audioSamples = new ArrayList<>();

    public int id;

    public String category_name = "";

    public String channel_id = "";
    public String channel_name = "";
    public String channel_image = "";
    public String channel_url = "";
    public String channel_description = "";

    public Channel() {
    }

    public Channel(String channel_id) {
        this.channel_id = channel_id;
    }

    public Channel(String category_name, String channel_id, String channel_name, String channel_image, String channel_url, String channel_description) {
        this.category_name = category_name;
        this.channel_id = channel_id;
        this.channel_image = channel_image;
        this.channel_name = channel_name;
        this.channel_url = channel_url;
        this.channel_description = channel_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_image() {
        return channel_image;
    }

    public void setChannel_image(String channel_image) {
        this.channel_image = channel_image;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getChannel_url() {
        return channel_url;
    }

    public void setChannel_url(String channel_url) {
        this.channel_url = channel_url;
    }

    public String getChannel_description() {
        return channel_description;
    }

    public void setChannel_description(String channel_description) {
        this.channel_description = channel_description;
    }

    @NonNull
    public static List<Channel> getAudioSamples() {
        return audioSamples;
    }

}
