/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercadoNet.demo.Repositories;

import com.mercadoNet.demo.Models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anama
 */

@Repository
public interface AdminRepository extends JpaRepository<Clientes, Integer> {
   Clientes findByCedula(int cedula);
}
