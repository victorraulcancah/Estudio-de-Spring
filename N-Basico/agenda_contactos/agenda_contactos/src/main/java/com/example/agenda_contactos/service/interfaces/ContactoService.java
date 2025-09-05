package com.example.agenda_contactos.service.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.dto.ContactoRequest;
import com.example.agenda_contactos.dto.ContactoResponse;

public interface ContactoService {
    CompletableFuture<ContactoResponse> crearContacto(ContactoRequest request);
    CompletableFuture<List<ContactoResponse>> obtenerTodosContactos();
    CompletableFuture<ContactoResponse> obtenerContactoPorId(int idContacto);
    CompletableFuture<ContactoResponse> actualizarContacto(int idContacto, ContactoRequest request);
    CompletableFuture<Boolean> eliminarContacto(int idContacto);
}
