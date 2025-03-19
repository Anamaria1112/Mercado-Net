/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mercadoNet.demo.Models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author anama
 */

@Entity
@Table(name = "producto")
@Data
public class Productos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "IDPRODUCTO")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "REFERENCIA")
    private String referencia;

    @Basic(optional = false)
    @Column(name = "MARCA")
    private String marca;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private BigDecimal precio;
        
    @Basic(optional = false)
    @Column(name = "STOCK")
    private Integer stock;
}
