package com.proyecto.talento.almacen.model;

public class StockInsuficienteException extends Exception {
  public StockInsuficienteException() {
    super("No hay suficiente stock");
  }
}