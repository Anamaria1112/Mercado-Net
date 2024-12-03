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
import lombok.Data;


/**
 *
 * @author anama
 */
@Entity
@Table(name = "clientes")
@Data
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "CEDULA")
    private int cedula;

    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private int telefono;
}
