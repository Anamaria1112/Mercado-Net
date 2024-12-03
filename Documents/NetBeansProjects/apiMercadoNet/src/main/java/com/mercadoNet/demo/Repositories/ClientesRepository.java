/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercadoNet.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mercadoNet.demo.Models.Clientes;


/**
 *
 * @author anama
 */

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer>{
    Clientes findByCedula(int cedula);
    
}
