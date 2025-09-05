package com.example.agenda_contactos.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.agenda_contactos.dto.GrupoRequest;
import com.example.agenda_contactos.dto.GrupoResponse;
import com.example.agenda_contactos.model.Grupo;
import com.example.agenda_contactos.repository.interfaces.GrupoRepository;
import com.example.agenda_contactos.service.interfaces.GrupoService;

@Service
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;

    public GrupoServiceImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    @Async
    public CompletableFuture<GrupoResponse> crearGrupo(GrupoRequest request) {
        Grupo grupo = new Grupo();
        grupo.setNombreGrupo(request.getNombreGrupo());
        grupo.setDescripcion(request.getDescripcion());

        return grupoRepository.save(grupo)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<GrupoResponse>> obtenerTodosLosGrupos() {
        return grupoRepository.findAll()
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<GrupoResponse> buscarGrupoPorNombre(String nombre) {
        return grupoRepository.findByNombre(nombre)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<GrupoResponse> actualizarGrupo(int idGrupo, GrupoRequest request) {
        Grupo grupo = new Grupo();
        grupo.setNombreGrupo(request.getNombreGrupo());
        grupo.setDescripcion(request.getDescripcion());

        return grupoRepository.update(idGrupo, grupo)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> eliminarGrupo(int idGrupo) {
        return grupoRepository.delete(idGrupo);
    }

    private GrupoResponse mapToResponse(Grupo grupo) {
        GrupoResponse response = new GrupoResponse();
        response.setIdGrupo(grupo.getIdGrupo());
        response.setNombreGrupo(grupo.getNombreGrupo());
        response.setDescripcion(grupo.getDescripcion());
        return response;
    }
}
