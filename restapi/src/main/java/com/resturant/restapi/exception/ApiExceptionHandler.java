package com.resturant.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
//@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFound.class})
    public ResponseEntity<ErrorResponseDto> entityNotFound(EntityNotFound e, WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest,null);
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {ContentNotAllowed.class})
    public ResponseEntity<ErrorResponseDto> contentNotAllowed(ContentNotAllowed e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest,null);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AuthenticationNotAllowed.class})
    public ResponseEntity<ErrorResponseDto> authenticationNotAllowed(AuthenticationNotAllowed e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest,null);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException exception,WebRequest webRequest){

        Map<String,String > validationErrors=new HashMap<>();
        for(FieldError fieldError:exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorResponseDto responseDto=prepareResponseModel(exception.getMessage(),webRequest,validationErrors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> genericException(Exception e,WebRequest webRequest){
        ErrorResponseDto responseDto=prepareResponseModel(e.getMessage(),webRequest,null);
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }



    public ErrorResponseDto prepareResponseModel(String message, WebRequest webRequest, Map<String,String> errors){
        Date date = new Date(System.currentTimeMillis());
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(date,message,webRequest.getDescription(true),errors);

        return errorResponseDto;
    }

}
