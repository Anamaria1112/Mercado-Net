/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mercadoNet.demo.Repositories;

import com.mercadoNet.demo.Models.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 *
 * @author anama
 */
@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer>{
    Optional<Productos> findById(Integer id);
    void deleteById(Integer id);
}
