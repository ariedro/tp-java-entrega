package com.proyecto.talento.almacen.controller;

import com.proyecto.talento.almacen.model.Producto;
import com.proyecto.talento.almacen.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  private final ProductoService productoService;

  @Autowired
  public ProductoController(ProductoService productoService) {
    this.productoService = productoService;
  }

  @GetMapping
  public List<Producto> listar() {
    return productoService.listarProductos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
    return productoService.obtenerProductoPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Producto crear(@RequestBody Producto producto) {
    return productoService.guardarProducto(producto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
    if (productoService.obtenerProductoPorId(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    if (productoService.obtenerProductoPorId(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    productoService.eliminarProducto(id);
    return ResponseEntity.noContent().build();
  }
}
