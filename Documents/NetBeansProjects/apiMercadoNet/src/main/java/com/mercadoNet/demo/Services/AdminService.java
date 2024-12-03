/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Services;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Dto.DtoInfoLogin;
import com.mercadoNet.demo.Dto.DtoLogin;
import com.mercadoNet.demo.Models.Clientes;
import com.mercadoNet.demo.Repositories.AdminRepository;
import com.mercadoNet.demo.Security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author anama
 */

@Service
public class AdminService {
    @Autowired 
    AdminRepository objrep;
    
    @Autowired
    JwtTokenUtils objtokutils;
    
    
    public DtoInfoLogin auth(DtoLogin log) {
        Clientes infoadmin = objrep.findByCedula(log.getCedula());
        if(infoadmin == null){             
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "Cédula no encontrada");    
        }
        if(!infoadmin.getNombre().equals(log.getNombre())){
            throw new CustomException(HttpStatus.CONFLICT.value(), "El nombre es incorrecto");
        }
        if(infoadmin.getCedula()!= log.getCedula()){
            throw new CustomException(HttpStatus.CONFLICT.value(), "La cédula es incorrecta");
        }
        String token = objtokutils.GenerateToken(String.valueOf(log.getCedula()));
        DtoInfoLogin info = new DtoInfoLogin();
        info.setToken(token);
        info.setAdminInfo(log);
        return info;
    }
}
