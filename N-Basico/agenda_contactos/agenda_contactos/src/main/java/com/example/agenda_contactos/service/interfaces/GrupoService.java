package com.example.agenda_contactos.service.interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.agenda_contactos.dto.GrupoRequest;
import com.example.agenda_contactos.dto.GrupoResponse;

public interface GrupoService {
    CompletableFuture<GrupoResponse> crearGrupo(GrupoRequest request);
    CompletableFuture<List<GrupoResponse>> obtenerTodosLosGrupos();
    CompletableFuture<GrupoResponse> buscarGrupoPorNombre(String nombre);
    CompletableFuture<GrupoResponse> actualizarGrupo(int idGrupo, GrupoRequest request);
    CompletableFuture<Boolean> eliminarGrupo(int idGrupo);
}
