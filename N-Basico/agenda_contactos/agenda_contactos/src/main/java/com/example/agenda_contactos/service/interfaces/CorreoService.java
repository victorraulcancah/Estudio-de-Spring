package com.example.agenda_contactos.service.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.dto.CorreoRequest;
import com.example.agenda_contactos.dto.CorreoResponse;

public interface CorreoService {
    CompletableFuture<CorreoResponse> agregarCorreo(CorreoRequest request, int idContacto);
    CompletableFuture<List<CorreoResponse>> obtenerCorreosPorContacto(int idContacto);
    CompletableFuture<CorreoResponse> actualizarCorreo(int idCorreo, CorreoRequest request);
    CompletableFuture<Boolean> eliminarCorreo(int idCorreo);
}
