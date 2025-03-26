/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Controllers;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Models.Productos;
import com.mercadoNet.demo.Security.JwtBalancer;
import com.mercadoNet.demo.Services.ProductosService;
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
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author anama
 */


@RestController
@CrossOrigin("*")
@RequestMapping("/productos")

public class ProductosController {
        @Autowired
    private ProductosService objservaut;

    @Autowired
    private JwtBalancer objbal;
    
    @GetMapping("/traerproductos")
    public ResponseEntity<?> traerproductos() {
        return ResponseEntity.ok(objservaut.traerProductos());
    }
    
    @GetMapping("/traerproducto")
    public ResponseEntity<?> traerproductoxid(@RequestParam int IdProducto, @RequestHeader(value = "Authorization") String Token) {
                try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Productos prod = objservaut.traerProducto(IdProducto);
                return ResponseEntity.ok(prod);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
    
    @PostMapping("/crearproducto")
    public ResponseEntity<?> crearProducto (@RequestBody Productos prod, @RequestHeader(value = "Authorization") String Token){
            try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Productos resserv = objservaut.crearProducto(prod);
                return ResponseEntity.ok(resserv);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.getMessage());
        }

    }
    
    @PutMapping("/actualizarproducto")
    public ResponseEntity<?> actualizarProducto(@RequestBody Productos prod, @RequestHeader(value = "Authorization") String Token) {
        try {
        boolean response = objbal.Authjwt(Token);
        
        if (!response) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token es inválido");
        }

        Productos servicioActualizar = objservaut.actualizarProducto(prod);
        return ResponseEntity.ok(servicioActualizar);
        
    } catch (CustomException e) {
        return ResponseEntity.status(e.getstatus()).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/eliminarproducto/{IdProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int IdProducto, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);

            if (!response) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El token es inválido");
            }

            boolean eliminado = objservaut.eliminarProducto(IdProducto);

            if (eliminado) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado");
            }
        } catch (CustomException e) {
        return ResponseEntity.status(e.getstatus()).body(e.getMessage());
        }
}
    
}
