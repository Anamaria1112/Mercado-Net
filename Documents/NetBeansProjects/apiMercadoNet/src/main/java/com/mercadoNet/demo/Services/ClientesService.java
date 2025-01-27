/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Services;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mercadoNet.demo.Models.Clientes;
import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author anama
 */
@Service
public class ClientesService {

    @Autowired
    private ClientesRepository objservaut;

    public List<Clientes> traerclientes() {
        return objservaut.findAll();
    }

    @Transactional
    public Clientes traercliente(String Cedula) {
        Clientes infocliente = objservaut.findByCedula(Cedula);
        if (infocliente != null) {
            return infocliente;
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "No se encontró cliente");
        }
    }

    public Clientes crearcliente(Clientes cli) {
        Clientes infocliente = objservaut.findByCedula(cli.getCedula());
        if (infocliente == null) {
            objservaut.save(cli);
            return cli;
        } else {
            throw new CustomException(HttpStatus.CONFLICT.value(), "El cliente ya existe");
        }
    }

    public String actualizarcliente(Clientes cli) {
        Clientes infocliente = objservaut.findByCedula(cli.getCedula());
        if (infocliente != null) {
            infocliente.setNombre(cli.getNombre());
            infocliente.setTelefono(cli.getTelefono());
            objservaut.save(infocliente);
            return "El cliente se actualizó exitosamente";
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El cliente no fue encontrado para actualizar");
        }
    }
    
    @Transactional
    public String eliminarcliente(String Cedula) {
        Clientes infocliente = objservaut.findByCedula(Cedula);
        if (infocliente != null) {
            objservaut.deleteByCedula(Cedula);
            return "El cliente se eliminó exitosamente";
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El cliente con cédula " + Cedula + " no se encontró para eliminar.");
        }
    }
    
   

}
