package com.example.agenda_contactos.repository.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.model.Contacto;

public interface ContactoRepository {
    CompletableFuture<Contacto> save(Contacto contacto);
    CompletableFuture<Contacto> findById(int idContacto);
    CompletableFuture<List<Contacto>> findAll();
    CompletableFuture<Contacto> update(int idContacto, Contacto contacto);
    CompletableFuture<Boolean> delete(int idContacto);
}
