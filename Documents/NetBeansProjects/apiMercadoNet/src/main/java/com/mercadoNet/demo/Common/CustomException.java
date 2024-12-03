/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Common;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;

/**
 *
 * @author anama
 */
public class CustomException extends RuntimeException{
    private String timestamp;
    private int status;
    private String error;
    private String description;
    
    public CustomException(int status, String description) {
        super(description);
        this.timestamp = LocalDate.now().toString();
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.description = description;
    }
    
    public int getstatus() {
        return this.status;
    }
    
    @Override
    public String toString() {
    return "{" +
            "\"timestamp\": \"" + timestamp + "\"," +
            "\"status\": " + status + "," +
            "\"error\": \"" + error + "\"," +
            "\"description\": \"" + description + "\"" +
            "}";
    }
}

