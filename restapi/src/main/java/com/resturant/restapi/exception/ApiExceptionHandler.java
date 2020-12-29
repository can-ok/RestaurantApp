package com.resturant.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import javax.xml.ws.Response;
import java.util.Date;

@RestController
@ControllerAdvice
//@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFound.class})
    public ResponseEntity<ErrorResponseDto> entityNotFound(EntityNotFound e, WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {ContentNotAllowed.class})
    public ResponseEntity<ErrorResponseDto> contentNotAllowed(ContentNotAllowed e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthenticationNotAllowed.class})
    public ResponseEntity<ErrorResponseDto> authenticationNotAllowed(AuthenticationNotAllowed e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> genericException(Exception e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

    public ErrorResponseDto prepareResponseModel(String message,WebRequest webRequest){
        Date date = new Date(System.currentTimeMillis());
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(date,message,webRequest.getDescription(true));

        return errorResponseDto;
    }

}
