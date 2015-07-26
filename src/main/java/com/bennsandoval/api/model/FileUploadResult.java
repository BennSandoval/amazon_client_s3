package com.bennsandoval.api.model;

import com.amazonaws.services.s3.model.PutObjectResult;

/**
 * Created by Mackbook on 7/25/15.
 */
public class FileUploadResult {

    private String file;
    private PutObjectResult details;

    public FileUploadResult(String file, PutObjectResult putObjectResult) {
        this.file = file;
        this.details = putObjectResult;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public PutObjectResult getDetails() {
        return details;
    }

    public void setDetails(PutObjectResult details) {
        this.details = details;
    }
}
