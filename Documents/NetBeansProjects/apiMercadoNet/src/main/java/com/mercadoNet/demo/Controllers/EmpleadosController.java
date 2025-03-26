/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Controllers;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Models.Empleados;
import com.mercadoNet.demo.Services.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mercadoNet.demo.Security.JwtBalancer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author anama
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/empleados")
public class EmpleadosController {
    
    @Autowired
    private EmpleadosService objservaut;

    @Autowired
    private JwtBalancer objbal;

    
    //localhost:8081/empleados/traerempleados
    @GetMapping("/traerempleados")
    public ResponseEntity<?> traerempleados() {
        return ResponseEntity.ok(objservaut.traerEmpleados());
    }
    
    //localhost:8081/empleados/traerempleado
    @GetMapping("/traerempleado")
    public ResponseEntity<?> traerempleado(@RequestParam int id, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Empleados empl = objservaut.traerEmpleado(id);
                return ResponseEntity.ok(empl);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }

    //localhost:8081/empleados/crearempleado
    @PostMapping("/crearempleado")
    public ResponseEntity<?> crearempleado(@RequestBody Empleados empl, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
            if (response) {
                Empleados resserv = objservaut.crearEmpleado(empl);
                return ResponseEntity.ok(resserv);
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es invalido");
            }

        } catch (CustomException e) {
            return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
    
    //localhost:8081/empleados/actualizarempleado
    @PutMapping("/actualizarempleado")
    public ResponseEntity<?> actualizarempleado(@RequestBody Empleados empl, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
                if (response) {
             Empleados servicioactualizar = objservaut.actualizarEmpleado(empl); 
                return ResponseEntity.ok(servicioactualizar); 
            } else {
                throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es inválido");
            }
    } catch (CustomException e) {
        return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
    
    
    //localhost:8081/empleados/eliminarempleado?id=
    @DeleteMapping("/eliminarempleado")
    public ResponseEntity<?> eliminarempleado(@RequestParam int id, @RequestHeader(value = "Authorization") String Token) {
        try {
            boolean response = objbal.Authjwt(Token);
                if (response) {
            boolean servicioeliminar = objservaut.eliminarEmpleado(id);
                if (servicioeliminar) {
                return ResponseEntity.ok("Empleado eliminado con éxito");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el empleado");
            }
        } else {
            throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "El token es inválido");
        }
        }  catch (CustomException e) {
        return ResponseEntity.status(e.getstatus()).body(e.toString());
        }
    }
}
