package com.example.springbootmysql.repositories;

import com.example.springbootmysql.models.ProductoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {
    ArrayList<ProductoModel> findByPrecio(int precio);
}
