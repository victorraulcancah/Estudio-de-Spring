package com.example.agenda_contactos.repository.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.model.Correo;

public interface CorreoRepository {
    CompletableFuture<Correo> save(Correo correo);
    CompletableFuture<List<Correo>> findByContactoId(int idContacto);
    CompletableFuture<Correo> update(int idCorreo, Correo correo);
    CompletableFuture<Boolean> delete(int idCorreo);
}
