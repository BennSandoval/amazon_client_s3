package com.bennsandoval.api.model;

/**
 * Created by Mackbook on 7/25/15.
 */
public class GenericResponse {

    private String text;

    public GenericResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
