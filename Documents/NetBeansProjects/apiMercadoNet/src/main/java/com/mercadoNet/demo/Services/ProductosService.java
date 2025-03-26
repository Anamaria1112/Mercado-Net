/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Services;

import com.mercadoNet.demo.Common.CustomException;
import com.mercadoNet.demo.Models.Productos;
import com.mercadoNet.demo.Repositories.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Optional;



/**
 *
 * @author anama
 */

@Service
public class ProductosService {
    @Autowired
    private ProductoRepository objservaut;
    
       
    public List<Productos> traerProductos() {
        return objservaut.findAll();
    }
    
    public Productos traerProducto(int IdProducto) {
        return objservaut.findById(IdProducto)
        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(), "No se encontró ningún producto"));
    }
    
    public Productos crearProducto(Productos prod) {
    if (objservaut.findById(prod.getId()).isPresent()) {
        throw new CustomException(HttpStatus.CONFLICT.value(), "El producto ya existe");
    }
    return objservaut.save(prod);
    }
    
    public Productos actualizarProducto(Productos prod) {
    Productos infoproducto = objservaut.findById(prod.getId()).orElse(null);

    if (infoproducto != null) {  
        infoproducto.setReferencia(prod.getReferencia());
        infoproducto.setMarca(prod.getMarca());
        infoproducto.setDescripcion(prod.getDescripcion());
        infoproducto.setPrecio(prod.getPrecio());
        infoproducto.setStock(prod.getStock());

        return objservaut.save(infoproducto); 
    } else {
        throw new CustomException(HttpStatus.NOT_FOUND.value(), "El producto no fue encontrado para actualizar");
    }
    }

    @Transactional
    public boolean eliminarProducto(int IdProducto) {
        Optional<Productos> infoproducto = objservaut.findById(IdProducto);

        if (infoproducto.isPresent()) {
            objservaut.deleteById(IdProducto);
            return true; 
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), 
                "El producto con ID " + IdProducto + " no se encontró para eliminar.");
        }
    } 
    
    
}
