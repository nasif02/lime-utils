package com.xlib.limeutils.notification;

public class NotBundle {

    private String title;
    private String body;
    private String imageUrl;
    private String url;

    public NotBundle() {
    }

    public NotBundle(String title, String body) {
        this.title = title;
        this.body = body;
    }


    @Override
    public String toString() {
        return "NotBundle{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
