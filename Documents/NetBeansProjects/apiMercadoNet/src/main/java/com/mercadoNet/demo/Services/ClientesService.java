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

    public Clientes traercliente(int Cedula) {
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
    
    public String eliminarcliente(int Cedula) {
        Clientes infocliente = objservaut.findByCedula(Cedula);
        if (infocliente != null) {
            objservaut.deleteById(infocliente.getCedula());
            return "El cliente se eliminó exitosamente";
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El cliente no se encontro para eliminar");
        }
    }

}
