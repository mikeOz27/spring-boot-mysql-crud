package com.example.springbootmysql.services;

import com.example.springbootmysql.models.ProductoModel;
import com.example.springbootmysql.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ArrayList<ProductoModel> getProductos() {
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    public ProductoModel saveProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public boolean deleteProducto(Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
        return false;
    }

    public ProductoModel updateProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public Optional<ProductoModel> findProducto(Long id) {
        if(productoRepository.findById(id).isEmpty()){
            throw new RuntimeException("Producto no encontrado");
        }
        return productoRepository.findById(id);
    }

    public ArrayList<ProductoModel> findProductoPrecio(int precio) {
        return productoRepository.findByPrecio(precio);
    }
}
