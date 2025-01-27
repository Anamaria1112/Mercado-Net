/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Controllers;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Models.Clientes;
import com.mercadoNet.demo.Security.JwtBalancer;
import com.mercadoNet.demo.Services.ClientesService;
import org.eclipse.persistence.annotations.DeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anama
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/clientes")

public class ClientesController {

    @Autowired
    private ClientesService objservaut;

    @Autowired
    private JwtBalancer objbal;

    //localhost:8081/clientes/traerclientes
    @GetMapping("/traerclientes")
    public ResponseEntity<?> traerclientes() {
        return ResponseEntity.ok(objservaut.traerclientes());
    }

    @GetMapping("/traercliente")
    public ResponseEntity<?> traercliente(@RequestParam String Cedula, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Clientes cli = objservaut.traercliente(Cedula);
                return ResponseEntity.ok(cli);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }

    @PostMapping("/crearcliente")
    public ResponseEntity<?> crearcliente(@RequestBody Clientes cli, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Clientes resserv = objservaut.crearcliente(cli);
                return ResponseEntity.ok(resserv);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }

    }

    @PutMapping("/actualizarcliente")
    public ResponseEntity<?> actualizarcliente(@RequestBody Clientes cli, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                String servicioactualizar = objservaut.actualizarcliente(cli);
                return ResponseEntity.ok(servicioactualizar);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }

    }
    
    @DeleteMapping("/eliminarcliente")
    public ResponseEntity<?> eliminarcliente(@RequestParam String Cedula, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                String servicioeliminar = objservaut.eliminarcliente(Cedula);
                return ResponseEntity.ok(servicioeliminar);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }

    }
    

}
