package com.sebastian.apirest.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.apirest.apirest.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    
}
