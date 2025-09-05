package com.example.agenda_contactos.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.agenda_contactos.dto.TelefonoRequest;
import com.example.agenda_contactos.dto.TelefonoResponse;
import com.example.agenda_contactos.model.Telefono;
import com.example.agenda_contactos.repository.interfaces.TelefonoRepository;
import com.example.agenda_contactos.service.interfaces.TelefonoService;

@Service
public class TelefonoServiceImpl implements TelefonoService {

    private final TelefonoRepository telefonoRepository;

    public TelefonoServiceImpl(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    @Override
    @Async
    public CompletableFuture<TelefonoResponse> agregarTelefono(TelefonoRequest request, int idContacto) {
        Telefono telefono = new Telefono();
        telefono.setIdContacto(idContacto);
        telefono.setTipo(request.getTipo());
        telefono.setNumero(request.getNumero());

        return telefonoRepository.save(telefono)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<TelefonoResponse>> obtenerTelefonosPorContacto(int idContacto) {
        return telefonoRepository.findByContactoId(idContacto)
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<TelefonoResponse> actualizarTelefono(int idTelefono, TelefonoRequest request) {
        Telefono telefono = new Telefono();
        telefono.setTipo(request.getTipo());
        telefono.setNumero(request.getNumero());

        return telefonoRepository.update(idTelefono, telefono)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> eliminarTelefono(int idTelefono) {
        return telefonoRepository.delete(idTelefono);
    }

    private TelefonoResponse mapToResponse(Telefono telefono) {
        TelefonoResponse response = new TelefonoResponse();
        response.setIdTelefono(telefono.getIdTelefono());
        response.setTipo(telefono.getTipo());
        response.setNumero(telefono.getNumero());
        return response;
    }
}
