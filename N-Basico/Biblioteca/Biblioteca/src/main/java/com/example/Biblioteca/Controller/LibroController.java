package com.example.Biblioteca.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biblioteca.Model.Libro;
import com.example.Biblioteca.Service.ILibroService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/libros")
public class LibroController {

    private final ILibroService LibroService;

    public LibroController(ILibroService LibroService) {
        this.LibroService = LibroService;
    }

    @GetMapping
    public CompletableFuture<List<Libro>> listarLibros(){
        return LibroService.listarLibros();
    }
    
    @GetMapping("/{id}")
    public CompletableFuture<Libro> obtenerLibroPorId(@RequestParam Long id){
        return LibroService.obtenerLibroPorId(id);
    }

    @PostMapping
    public CompletableFuture<Libro> guardarLibro(@RequestBody Libro libro) {
        return LibroService.guardarLibro(libro);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return LibroService.actualizarLibro(id, libro);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> eliminarLibro(@PathVariable Long id) {
        return LibroService.eliminarLibro(id);
    }
    
}
