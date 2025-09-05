package com.example.agenda_contactos.repository.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.model.ContactoGrupo;

public interface ContactoGrupoRepository {
    CompletableFuture<ContactoGrupo> save(ContactoGrupo contactoGrupo);
    CompletableFuture<List<ContactoGrupo>> findByContactoId(int idContacto);
    CompletableFuture<List<ContactoGrupo>> findByGrupoId(int idGrupo);
    CompletableFuture<Boolean> delete(int idContacto, int idGrupo);
}
