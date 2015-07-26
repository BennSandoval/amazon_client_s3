package com.bennsandoval.api.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.bennsandoval.api.exception.ServiceException;
import com.bennsandoval.api.model.FileUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mackbook on 7/25/15.
 */
@Component
public class BucketService {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Qualifier("environment")
    @Autowired
    private Environment env;

    public List<Bucket> getList() {
        AmazonS3Client s3client = null;
        boolean result = false;
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        return s3client.listBuckets();
    }

    public Bucket create(String bucketName) throws ServiceException {
        AmazonS3Client s3client = null;
        Bucket result = null;
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        try {
            if (s3client.doesBucketExist(bucketName) == false) {
                result = s3client.createBucket(bucketName);
            } else {
                throw new ServiceException("You create a bucket:" + bucketName + " previously.");
            }
        } catch (AmazonClientException ace) {
            throw new ServiceException("Unable to create a new Amazon S3 bucket: " + ace.getMessage());
        }

        return result;
    }

    public List<Bucket> delete(String bucketName) throws ServiceException {
        AmazonS3Client s3client = null;
        boolean result = false;
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        if (s3client.doesBucketExist(bucketName) == true) {
            s3client.deleteBucket(bucketName);
            result = true;
        } else {
            throw new ServiceException("This bucket:" + bucketName + " doesn't exist.");
        }

        return s3client.listBuckets();
    }

    public FileUploadResult insertFile(String bucketName, MultipartFile multipartFile) throws IOException {

        AmazonS3Client s3client = null;
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        String key =  file.getName();
        return new FileUploadResult(key, s3client.putObject(new PutObjectRequest(bucketName, key , file)));

    }

    public URL getFile(String bucketName, String fileName) {

        AmazonS3Client s3client = null;
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        Date expirationDate = new Date( System.currentTimeMillis() + 3600000 );
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName);
        request.setExpiration(expirationDate);
        return s3client.generatePresignedUrl(request);
    }

    public List<String> deleteFile(String bucketName, String fileName) throws ServiceException {

        if(fileName.toLowerCase().contains(".war")){
            throw new ServiceException("Sorry, we have a restriction for WAR files.");
        }

        AmazonS3Client s3client = null;
        List<String> result = new ArrayList<String>();
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
        ObjectListing objectListing;
        do {
            objectListing = s3client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                result.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());

        return result;
    }

    public List<String> getFiles(String bucketName) {

        AmazonS3Client s3client = null;
        List<String> result = new ArrayList<String>();
        try {
            s3client = new AmazonS3Client(new BasicAWSCredentials(env.getProperty("global.amazon.accesKey"), env.getProperty("global.amazon.secretKey")));
        } catch (Exception e) {
            throw new AmazonClientException("Invalid amazon credentials.",e);
        }

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
        ObjectListing objectListing;
        do {
            objectListing = s3client.listObjects(listObjectsRequest);
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                result.add(objectSummary.getKey());
            }
            listObjectsRequest.setMarker(objectListing.getNextMarker());
        } while (objectListing.isTruncated());

        return result;
    }
}
