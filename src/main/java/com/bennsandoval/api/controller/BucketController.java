package com.bennsandoval.api.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.bennsandoval.api.model.FileDownloadResponse;
import com.bennsandoval.api.model.FileUploadResult;
import com.bennsandoval.api.service.BucketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Mackbook on 7/25/15.
 */
@RestController
@RequestMapping("/api/v1/buckets")
@Api(value = "/bucket",
     description = "Basic operations with Amazon bucket")
public class BucketController {

        final Logger log = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private BucketService mBucketService;

        @ApiOperation(
                httpMethod = "GET",
                value = "List of buckets.",
                notes = "Get a list of a buckets available",
                produces = "application/json")
        @RequestMapping(method = RequestMethod.GET)
        @ResponseStatus(HttpStatus.OK)
        public List<Bucket> getListOgBuckets() throws Exception {

                return mBucketService.getList();

        }

        @ApiOperation(
                httpMethod = "POST",
                value = "Create a bucket.",
                notes = "We need a prefix to identify you bucket.",
                produces = "application/json")
        @RequestMapping(value = {"/{bucketName}"}, method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.OK)
        public Bucket createBucket(
                @ApiParam(value = "Please use a prefix like a bennsandoval-bucket- ", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName) throws Exception {

                return mBucketService.create(bucketName);

        }

        @ApiOperation(
                httpMethod = "DELETE",
                value = "Delete a bucket.",
                notes = "Don't forget your prefix.")
        @RequestMapping(value = {"/{bucketName}"}, method = RequestMethod.DELETE)
        @ResponseStatus(HttpStatus.OK)
        public List<Bucket> deleteBucket(
                @ApiParam(value = "Bucket name, is necessary a prefix", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName) throws Exception {

                return mBucketService.delete(bucketName);

        }

        @ApiOperation(
                httpMethod = "POST",
                value = "Upload files to a bucket.",
                notes = "Don't forget your prefix.",
                produces = "application/json")
        @RequestMapping(value = {"/{bucketName}/files"}, method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.OK)
        public FileUploadResult uploadFile(
                @ApiParam(value = "Bucket name, is necessary a prefix", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName,
                @ApiParam(value = "File to upload in the bucket, limit 3.7 MB") @RequestPart(required = true) MultipartFile file) throws Exception {

                return mBucketService.insertFile(bucketName, file);

        }

        @ApiOperation(
                httpMethod = "GET",
                value = "Download a file from an specific bucket.",
                notes = "Don't forget your prefix.")
        @RequestMapping(value = {"/{bucketName}/files/{fileName}/"},method = RequestMethod.GET)
        @ResponseStatus(HttpStatus.OK)
        public FileDownloadResponse getAFile(
                @ApiParam(value = "Bucket name, is necessary a prefix", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName,
                @ApiParam(value = "File name, we use this name to find the file in the bucket") @PathVariable String fileName,
                @ApiParam(value = "Use this flag to enable or disable redirection", defaultValue = "false") @RequestParam(value = "redirect", defaultValue = "false") boolean redirect,
                HttpServletResponse response) throws Exception {

                if(redirect){
                        response.sendRedirect(mBucketService.getFile(bucketName,fileName).toString());
                        return null;
                }
                return new FileDownloadResponse("Copy and paste this URL to download de file directly from Amazon.", mBucketService.getFile(bucketName,fileName).toString());

        }

        @ApiOperation(
                httpMethod = "DELETE",
                value = "Delete a file from an specific bucket.",
                notes = "Don't forget your prefix.",
                produces = "application/json")
        @RequestMapping(value = {"/{bucketName}/files/{fileName}/"}, method = RequestMethod.DELETE)
        @ResponseStatus(HttpStatus.OK)
        public List<String> deleteFile(
                @ApiParam(value = "Bucket name, is necessary a prefix", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName,
                @ApiParam(value = "File name, we use this name to find the file in the bucket") @PathVariable String fileName) throws Exception {

                return mBucketService.deleteFile(bucketName,fileName);

        }

        @ApiOperation(
                httpMethod = "GET",
                value = "Get a list of files from an specific bucket.",
                notes = "Don't forget your prefix.",
                produces = "application/json")
        @RequestMapping(value = {"/{bucketName}/files"}, method = RequestMethod.GET)
        @ResponseStatus(HttpStatus.OK)
        public List<String> getFiles(
                @ApiParam(value = "Bucket name, is necessary a prefix", defaultValue = "bennsandoval-bucket-") @PathVariable String bucketName) throws Exception {

                return mBucketService.getFiles(bucketName);

        }

}
