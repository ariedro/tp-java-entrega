package com.proyecto.talento.almacen.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany
  @JoinTable(name = "pedido_producto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "producto_id"))
  private List<Producto> productos = new ArrayList<>();

  private String direccion;

  public Pedido() {
  }

  public Pedido(Long id, String direccion) {
    this.id = id;
    this.productos = new ArrayList<Producto>();
    this.direccion = direccion;
  }

  public Long getId() {
    return id;
  }

  public List<Producto> getProductos() {
    return productos;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setProductos(List<Producto> productos) {
    this.productos = productos;
  }

  public void addProducto(Producto producto, int cantidad) throws StockInsuficienteException {
    producto.sacar(cantidad);
    for (int i = 0; i < cantidad; i++) {
      this.productos.add(producto);
    }
  }

  public float getCostoTotal() {
    float suma = 0;
    for (Producto producto : this.productos) {
      suma += producto.getPrecio();
    }
    return suma;
  }
}
