package com.roshan.traversity.Models;

/**
 * Created by arobc on 1/20/2018.
 */

public class Routes {
    private int id,user_id;
    private String title, category, description, thumbnail;

    public Routes(int id, int user_id, String title, String category, String description, String thumbnail) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;

        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
