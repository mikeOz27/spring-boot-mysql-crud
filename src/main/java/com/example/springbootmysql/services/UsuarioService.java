package com.example.springbootmysql.services;

import com.example.springbootmysql.models.UsuarioModel;
import com.example.springbootmysql.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> getUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel saveUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
        return false;
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> findUsuario(Long id) {
        if(usuarioRepository.findById(id).isEmpty()){
            throw new RuntimeException("Producto no encontrado");
        }
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> findUsuriousRol(Integer rol) {
        return usuarioRepository.findByRol(rol);
    }
}
