package com.bennsandoval.api.model;

/**
 * Created by Mackbook on 7/25/15.
 */
public class FileDownloadResponse {

    private String text;
    private String url;

    public FileDownloadResponse(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
