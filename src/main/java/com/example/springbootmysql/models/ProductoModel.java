package com.example.springbootmysql.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull
    private String nombre;
    private String descripcion;

    @NotNull
    @Column(precision = 10, scale = 0)  // Para un valor entero
    private BigDecimal precio;

    @NotNull
    private int stock;

    private LocalDateTime fechaCreacion = LocalDateTime.now(); // Fecha por defecto
    private LocalDateTime fechaActualizacion = LocalDateTime.now(); // Fecha por defecto

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter and Setter for descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getPrecio() {
        java.text.NumberFormat formatter = java.text.NumberFormat.getInstance();
        return formatter.format(precio);
    }

    // Getter and Setter for stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter and Setter for fechaCreacion
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Getter and Setter for fechaActualizacion
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
