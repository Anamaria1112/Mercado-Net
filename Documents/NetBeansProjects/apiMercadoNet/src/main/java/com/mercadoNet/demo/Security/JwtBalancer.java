/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Security;

import com.mercadoNet.demo.Common.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 *
 * @author anama
 */

@Component
public class JwtBalancer {
    @Autowired
    JwtTokenUtils objtekenu;
    
    public boolean Authjwt(String Token){
        if(Token == null || Token.trim().isEmpty()) {
            throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token no puede ser nulo");
        }
        else {
            try {
                String RealToken = Token.substring(7);
                String TokenChecked = objtekenu.ValidateToken(RealToken);
                if(TokenChecked.equalsIgnoreCase("Valido")){
                    return true;
                } else {
                    throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token no está autorizado" + TokenChecked);
                }
            } catch (StringIndexOutOfBoundsException e){
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token tiene un formato incorrecto");
            }
        }
    }

}
