package com.bennsandoval.api.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.bennsandoval.api.exception.ServiceException;
import com.bennsandoval.api.model.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mackbook on 7/25/15.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleException(Exception exception) {
        GenericResponse failureResult = new GenericResponse(exception.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if(exception instanceof ServiceException){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            failureResult.setText("Internal error : " + exception.getMessage());
        }

        if(exception instanceof NullPointerException){
            status = HttpStatus.BAD_REQUEST;
            failureResult.setText(exception.getMessage());
        }

        if(exception instanceof AmazonS3Exception){
            status = HttpStatus.BAD_REQUEST;
            failureResult.setText(exception.getMessage());
        }

        if(exception instanceof AmazonClientException){
            status = HttpStatus.UNAUTHORIZED;
            failureResult.setText(exception.getMessage());
        }

        if(exception instanceof AmazonServiceException){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            failureResult.setText(exception.getMessage() + " Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
        }

        if(exception instanceof AmazonClientException){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            failureResult.setText(exception.getMessage() + "Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
        }

        return new ResponseEntity<GenericResponse>(failureResult,status);
    }
}
