package com.example.springbootmysql.repositories;


import com.example.springbootmysql.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public ArrayList<UsuarioModel> findByRol(int rol);

}
