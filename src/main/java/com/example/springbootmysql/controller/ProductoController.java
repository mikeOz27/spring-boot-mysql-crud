package com.example.springbootmysql.controller;

import com.example.springbootmysql.models.ProductoModel;
import com.example.springbootmysql.models.UsuarioModel;
import com.example.springbootmysql.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootmysql.services.ProductoService;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<ArrayList<ProductoModel>>> getProductos() {
        ArrayList<ProductoModel> productos = productoService.getProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>("No products found", productos, 200));
        }
        return ResponseEntity.ok(new ApiResponse<>("Products retrieved successfully", productos, 200));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<ProductoModel>> saveProducto(ProductoModel producto) {
        try {
            productoService.saveProducto(producto);
            return ResponseEntity.ok(new ApiResponse<>("Product created", producto, 200));
        } catch (Exception e) {
            System.out.println("Error save product: " + e.getMessage());
            return ResponseEntity
                    .status(404)
                    .body(new ApiResponse<>("Error saving product", null, 404));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<ProductoModel>> updateProducto(ProductoModel producto) {
        try {
            Object findProducto = productoService.findProducto(producto.getId());
            if (findProducto == null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("Product not found", null, 404));
            }
            productoService.updateProducto(producto);
            return ResponseEntity.ok(new ApiResponse<>("No products found", producto, 200));
        } catch (Exception e) {
            System.out.println("Error al guardar el producto: " + e.getMessage());
            return ResponseEntity
                    .status(404)
                    .body(new ApiResponse<>("Product not found", null, 404));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> findProductoForId(@PathVariable("id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>("Product ID cannot be null", null, 400));
            }

            Object producto = productoService.findProducto(id);
            if (producto == null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("Product not found", null, 404));
            }

            return ResponseEntity
                    .ok(new ApiResponse<>("Product retrieved successfully", producto, 200));

        } catch (Exception ex) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse<>("An error occurred: " + ex.getMessage(), null, 500));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProducto(@PathVariable("id") Long id) {
        try {
            if (id == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>("Product ID cannot be null", null, 400));
            }

            productoService.findProducto(id);

            boolean ok = productoService.deleteProducto(id);
            if (ok) {
                return ResponseEntity
                        .ok(new ApiResponse<>("Product deleted successfully", "Product ID: " + id, 200));
            } else {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>("Product not found", null, 404));
            }
        } catch (Exception ex) {
            // Manejo de errores
            throw new IllegalArgumentException("Product ID not found");
        }
    }


    @GetMapping("/query")
    public ArrayList<ProductoModel> findProductoPrecio(int precio) {
        return this.productoService.findProductoPrecio(precio);
    }
}
