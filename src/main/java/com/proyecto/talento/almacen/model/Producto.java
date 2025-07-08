package com.proyecto.talento.almacen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private float precio;
  private int stock;

  public Producto() {
  }

  public Producto(Long id, String nombre, float precio, int stock) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public float getPrecio() {
    return precio;
  }

  public int getStock() {
    return stock;
  }

  public void setNombre(String nuevoNombre) {
    this.nombre = nuevoNombre;
  }

  public void setPrecio(float nuevoPrecio) {
    this.precio = nuevoPrecio;
  }

  public void sacar(int cantidad) throws StockInsuficienteException {
    if (this.stock < cantidad) {
      throw new StockInsuficienteException();
    }
    this.stock -= cantidad;
  }
}
