package com.proyecto.talento.almacen.controller;

import com.proyecto.talento.almacen.model.Pedido;
import com.proyecto.talento.almacen.model.PedidoAgregacionDTO;
import com.proyecto.talento.almacen.model.Producto;
import com.proyecto.talento.almacen.service.PedidoService;
import com.proyecto.talento.almacen.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

  private final PedidoService pedidoService;
  private final ProductoService productoService;

  @Autowired
  public PedidoController(PedidoService pedidoService, ProductoService productoService) {
    this.pedidoService = pedidoService;
    this.productoService = productoService;
  }

  @GetMapping
  public List<Pedido> listar() {
    return pedidoService.listarPedidos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
    return pedidoService.obtenerPedidoPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Pedido crear(@RequestBody Pedido pedido) {
    return pedidoService.guardarPedido(pedido);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Pedido> agregarProducto(@PathVariable Long id,
      @RequestBody PedidoAgregacionDTO pedidoAgregacion) {
    Optional<Pedido> pedidoOptional = pedidoService.obtenerPedidoPorId(id);
    Optional<Producto> productoOptional = productoService.obtenerProductoPorId(pedidoAgregacion.getIdProducto());

    if (pedidoOptional.isEmpty() || productoOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Pedido pedido = pedidoOptional.get();
    Producto producto = productoOptional.get();

    for (int i = 0; i < pedidoAgregacion.getCantidad(); i++) {
      pedido.getProductos().add(producto);
    }

    Pedido pedidoActualizado = pedidoService.guardarPedido(pedido);

    return ResponseEntity.ok(pedidoActualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    if (pedidoService.obtenerPedidoPorId(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    pedidoService.eliminarPedido(id);
    return ResponseEntity.noContent().build();
  }
}
