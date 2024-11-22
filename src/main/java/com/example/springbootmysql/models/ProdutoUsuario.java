package com.example.springbootmysql.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos_usuarios")
public class ProdutoUsuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private int cantidad;

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
