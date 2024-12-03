/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Controllers;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Dto.DtoInfoLogin;
import com.mercadoNet.demo.Dto.DtoLogin;
import com.mercadoNet.demo.Security.JwtBalancer;
import com.mercadoNet.demo.Models.Clientes;
import com.mercadoNet.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anama
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/admin/")
public class AdminController {
    
    @Autowired
    AdminService objserv;
    
    
    //localhost:8081/admin/login
    @PostMapping("login") 
    public ResponseEntity<?> auth(@RequestBody DtoLogin dtolog) {

        try {
            DtoInfoLogin infoauth = objserv.auth(dtolog);
            if(infoauth != null){
                return ResponseEntity.ok(infoauth);
            } else {
                throw new CustomException(HttpStatus.CONFLICT.value(), "El token no se genero correctamente");
            }
        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
}
