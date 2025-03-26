/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercadoNet.demo.Repositories;

import com.mercadoNet.demo.Models.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


/**
 *
 * @author anama
 */


@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Integer> {
    Optional<Empleados> findById(Integer idEmpleado);
    void deleteById(Integer idEmpleado);
}