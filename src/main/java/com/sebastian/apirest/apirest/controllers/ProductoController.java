package com.sebastian.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sebastian.apirest.apirest.entities.Producto;

import com.sebastian.apirest.apirest.repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){
        return productoRepository.findAll();
    } 

    @GetMapping("/{id}")
    public Producto  getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id: "+id));
    }
    
    @PostMapping
    public Producto saveProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoId = getProductById(id);

        productoId.setNombre(producto.getNombre());
        productoId.setPrecio(producto.getPrecio());

        return productoRepository.save(productoId);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto productoId = getProductById(id);

        productoRepository.delete(productoId);
        
        return "El producto de la ID: "+id+" fue eliminado";
    }

}
