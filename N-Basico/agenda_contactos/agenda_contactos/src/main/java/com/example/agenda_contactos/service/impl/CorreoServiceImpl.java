package com.example.agenda_contactos.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.agenda_contactos.dto.CorreoRequest;
import com.example.agenda_contactos.dto.CorreoResponse;
import com.example.agenda_contactos.model.Correo;
import com.example.agenda_contactos.repository.interfaces.CorreoRepository;
import com.example.agenda_contactos.service.interfaces.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService {

    private final CorreoRepository correoRepository;

    public CorreoServiceImpl(CorreoRepository correoRepository) {
        this.correoRepository = correoRepository;
    }

    @Override
    @Async
    public CompletableFuture<CorreoResponse> agregarCorreo(CorreoRequest request, int idContacto) {
        Correo correo = new Correo();
        correo.setIdContacto(idContacto); 
        correo.setTipo(request.getTipo());
        correo.setEmail(request.getEmail());

        return correoRepository.save(correo)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<CorreoResponse>> obtenerCorreosPorContacto(int idContacto) {
        return correoRepository.findByContactoId(idContacto)
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<CorreoResponse> actualizarCorreo(int idCorreo, CorreoRequest request) {
        Correo correo = new Correo();
        correo.setTipo(request.getTipo());
        correo.setEmail(request.getEmail());

        return correoRepository.update(idCorreo, correo)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> eliminarCorreo(int idCorreo) {
        return correoRepository.delete(idCorreo);
    }

    private CorreoResponse mapToResponse(Correo correo) {
        CorreoResponse response = new CorreoResponse();
        response.setIdCorreo(correo.getIdCorreo());
        response.setTipo(correo.getTipo());
        response.setEmail(correo.getEmail());
        return response;
    }
}
