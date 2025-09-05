package com.example.agenda_contactos.repository.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.model.Grupo;

public interface GrupoRepository {
    CompletableFuture<Grupo> save(Grupo grupo);
    CompletableFuture<List<Grupo>> findAll();
    CompletableFuture<Grupo> findById(int idGrupo);
    CompletableFuture<Grupo> findByNombre(String nombre);
    CompletableFuture<Grupo> update(int idGrupo, Grupo grupo);
    CompletableFuture<Boolean> delete(int idGrupo);
}
