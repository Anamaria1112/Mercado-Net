/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Services;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Models.Empleados;
import com.mercadoNet.demo.Repositories.EmpleadosRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author anama
 */

@Service
public class EmpleadosService {
    @Autowired
    private EmpleadosRepository objservaut;
    
       
    public List<Empleados> traerEmpleados() {
        return objservaut.findAll();
    }
    
    public Empleados traerEmpleado(int id) {
        return objservaut.findById(id)
        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(), "No se encontró ningún empleado"));
    }
    
    public Empleados crearEmpleado(Empleados emple) {
    if (objservaut.findById(emple.getId()).isPresent()) {
        throw new CustomException(HttpStatus.CONFLICT.value(), "El empleado ya existe");
    }
    return objservaut.save(emple);
    }
    
    public Empleados actualizarEmpleado(Empleados emple) {
    Empleados infoempleado = objservaut.findById(emple.getId()).orElse(null);

    if (infoempleado != null) {  
        infoempleado.setNombre(emple.getNombre());
        infoempleado.setApellido(emple.getApellido());
        infoempleado.setCedula(emple.getCedula());
        infoempleado.setTelefono(emple.getTelefono());
        infoempleado.setCorreo(emple.getCorreo());
        infoempleado.setDireccion(emple.getDireccion());
        return objservaut.save(infoempleado); 
    } else {
        throw new CustomException(HttpStatus.NOT_FOUND.value(), "El empleado no fue encontrado para actualizar");
    }
    }

    @Transactional
    public boolean eliminarEmpleado(int id) {
        Optional<Empleados> infoempleado = objservaut.findById(id);

        if (infoempleado.isPresent()) {
            objservaut.deleteById(id);
            return true; 
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), 
                "El empleado con ID " + id + " no se encontró para eliminar.");
        }
    } 
}
