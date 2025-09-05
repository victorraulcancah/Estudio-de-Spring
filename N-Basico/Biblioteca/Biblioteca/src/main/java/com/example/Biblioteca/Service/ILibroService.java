package com.example.Biblioteca.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.Biblioteca.Model.Libro;

public interface ILibroService {
    CompletableFuture<List<Libro>> listarLibros();

    CompletableFuture<Libro> obtenerLibroPorId(Long id);

    CompletableFuture<Libro> guardarLibro(Libro libro);

    CompletableFuture<Void> eliminarLibro(Long id);

    CompletableFuture<Libro> actualizarLibro(Long id, Libro libro);
}
