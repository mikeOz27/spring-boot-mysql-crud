package com.example.springbootmysql.controller;

import com.example.springbootmysql.models.UsuarioModel;
import com.example.springbootmysql.services.UsuarioService;
import com.example.springbootmysql.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<ArrayList<UsuarioModel>>> getUsuarios() {
        ArrayList<UsuarioModel> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("No users found", usuarios, 200));
        }
        return ResponseEntity.ok(new ApiResponse<>("Users retrieved successfully", usuarios, 200));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UsuarioModel>> saveUsuario(UsuarioModel usuario) {
        try {
            Object findUsuario = usuarioService.findUsuario(usuario.getId());
            if (findUsuario != null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("User already exists", null, 404));
            }
            usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(new ApiResponse<>("No users found", usuario, 200));
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return ResponseEntity
                    .status(404)
                    .body(new ApiResponse<>("Usuario not found", null, 404));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UsuarioModel>> updateUsuario(UsuarioModel usuario) {
        try {
            Optional<UsuarioModel> existingUsuario = usuarioService.findUsuario(usuario.getId());
            if (existingUsuario.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse<>("User not found", null, 404));
            }else {
                usuarioService.updateUsuario(usuario);
            }
            return ResponseEntity.ok(new ApiResponse<>("No users found", usuario, 200));
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> findUsuarioForId(@PathVariable("id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>("User ID cannot be null", null, 400));
            }

            Object usuario = usuarioService.findUsuario(id);
            if (usuario == null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("User not found", null, 404));
            }

            return ResponseEntity
                    .ok(new ApiResponse<>("User retrieved successfully", usuario, 200));

        } catch (Exception ex) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse<>("Internal server error", null, 500));
        }
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ApiResponse<String>> deleteUsuario(@PathVariable("id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>("User ID cannot be null", null, 400));
            }
            usuarioService.findUsuario(id);

            boolean ok = usuarioService.deleteUsuario(id);
            if (ok) {
                return ResponseEntity
                        .ok(new ApiResponse<>("User deleted successfully", "User ID: " + id, 200));
            } else {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("User not found", null, 404));
            }
        } catch (Exception ex) {
            // Manejo de errores
            throw new IllegalArgumentException("User ID not found");
        }
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> findUsuarioRol(@RequestParam("rol") Integer rol) {
        return this.usuarioService.findUsuriousRol(rol);
    }
}
