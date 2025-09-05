package com.example.agenda_contactos.service.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.dto.TelefonoRequest;
import com.example.agenda_contactos.dto.TelefonoResponse;

public interface TelefonoService {
    CompletableFuture<TelefonoResponse> agregarTelefono(TelefonoRequest request, int idContacto);
    CompletableFuture<List<TelefonoResponse>> obtenerTelefonosPorContacto(int idContacto);
    CompletableFuture<TelefonoResponse> actualizarTelefono(int idTelefono, TelefonoRequest request);
    CompletableFuture<Boolean> eliminarTelefono(int idTelefono);
}
