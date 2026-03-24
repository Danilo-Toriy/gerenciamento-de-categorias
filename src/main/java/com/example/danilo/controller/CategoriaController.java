package com.example.danilo.controller;

import com.example.danilo.entity.Categoria;
import com.example.danilo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria novaCategoria){
        Categoria categoria = categoriaService.save(novaCategoria);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> categoriaList = categoriaService.findAll();
        return categoriaList.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(categoriaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Optional<Categoria> categoriaAchada = categoriaService.findById(id);
        return categoriaAchada.isPresent()
                ? ResponseEntity.ok().body(categoriaAchada.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada){
        Categoria categoria = categoriaService.update(id, categoriaAtualizada);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        categoriaService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}
