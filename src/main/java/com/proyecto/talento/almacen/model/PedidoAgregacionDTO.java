package com.proyecto.talento.almacen.model;

public class PedidoAgregacionDTO {
  private Long idProducto;
  private int cantidad;

  public PedidoAgregacionDTO() {
  }

  public PedidoAgregacionDTO(Long idPedido, int cantidad) {
    this.idProducto = idPedido;
    this.cantidad = cantidad;
  }

  public Long getIdProducto() {
    return idProducto;
  }

  public int getCantidad() {
    return cantidad;
  }
}
