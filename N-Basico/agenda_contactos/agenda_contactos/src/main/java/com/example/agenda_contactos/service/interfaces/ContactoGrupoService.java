package com.example.agenda_contactos.service.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.dto.ContactoGrupoRequest;
import com.example.agenda_contactos.dto.ContactoGrupoResponse;

public interface ContactoGrupoService {
    CompletableFuture<ContactoGrupoResponse> agregarContactoAGrupo(ContactoGrupoRequest request);
    CompletableFuture<List<ContactoGrupoResponse>> obtenerGruposPorContacto(int idContacto);
    CompletableFuture<List<ContactoGrupoResponse>> obtenerContactosPorGrupo(int idGrupo);
    CompletableFuture<Boolean> eliminarContactoDeGrupo(int idContacto, int idGrupo);
}
