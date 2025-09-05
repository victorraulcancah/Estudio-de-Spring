package com.example.Biblioteca.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.Biblioteca.Model.Libro;
import com.example.Biblioteca.Repository.ILibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {
    
    private final ILibroRepository LibroRepository;

    public LibroServiceImpl(ILibroRepository LibroRepository) {
        this.LibroRepository = LibroRepository;
    }

    @Override
    public CompletableFuture<List<Libro>> listarLibros() {
        return LibroRepository.listarLibros();
    }

    @Override
    public CompletableFuture<Libro> obtenerLibroPorId(Long id) {
        return LibroRepository.obtenerLibroPorId(id);
    }

    @Override
    public CompletableFuture<Libro> guardarLibro(Libro libro) {
        return LibroRepository.guardarLibro(libro);
    }

    @Override
    public CompletableFuture<Void> eliminarLibro(Long id) {
        return LibroRepository.eliminarLibro(id);
    }

    @Override
    public CompletableFuture<Libro> actualizarLibro(Long id, Libro libro) {
        return LibroRepository.actualizarLibro(id, libro);
    }
}
