package com.example.springbootmysql.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

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

    @NotNull
    @Column(name = "id_usuario") // Columna para almacenar el ID del usuario
    private Long idUsuario;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioModel usuario;

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter para precio
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getPrecio() {
        java.text.NumberFormat formatter = java.text.NumberFormat.getInstance();
        return formatter.format(precio);
    }

    // Getter y Setter para stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter y Setter para fechaCreacion
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Getter y Setter para fechaActualizacion
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getter y Setter para idUsuario
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getter y Setter para usuario
    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
