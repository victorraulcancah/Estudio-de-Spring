package com.example.agenda_contactos.repository.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.model.Telefono;

public interface TelefonoRepository {
    CompletableFuture<Telefono> save(Telefono telefono);
    CompletableFuture<List<Telefono>> findByContactoId(int idContacto);
    CompletableFuture<Telefono> update(int idTelefono, Telefono telefono);
    CompletableFuture<Boolean> delete(int idTelefono);
}
