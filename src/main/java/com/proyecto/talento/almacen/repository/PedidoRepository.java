package com.proyecto.talento.almacen.repository;

import com.proyecto.talento.almacen.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

  // ===============================================
  // 🚀 MÉTODOS CRUD INCLUIDOS AUTOMÁTICAMENTE
  // ===============================================
  // findAll() -> Lista todos los artículos
  // findById(Long id) -> Busca uno por ID
  // save(Articulo a) -> Inserta o actualiza
  // deleteById(Long id) -> Elimina por ID
  // count() -> Cuenta registros
  // existsById(Long id) -> Verifica si existe un ID

  // ===============================================
  // 🛠️ MÉTODOS PERSONALIZADOS (se generan por nombre)
  // ===============================================

}
