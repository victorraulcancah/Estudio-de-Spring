package com.example.agenda_contactos.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.agenda_contactos.dto.ContactoGrupoRequest;
import com.example.agenda_contactos.dto.ContactoGrupoResponse;
import com.example.agenda_contactos.model.ContactoGrupo;
import com.example.agenda_contactos.repository.interfaces.ContactoGrupoRepository;
import com.example.agenda_contactos.service.interfaces.ContactoGrupoService;

@Service
public class ContactoGrupoServiceImpl implements ContactoGrupoService {

    private final ContactoGrupoRepository contactoGrupoRepository;

    public ContactoGrupoServiceImpl(ContactoGrupoRepository contactoGrupoRepository) {
        this.contactoGrupoRepository = contactoGrupoRepository;
    }

    @Override
    @Async
    public CompletableFuture<ContactoGrupoResponse> agregarContactoAGrupo(ContactoGrupoRequest request) {
        ContactoGrupo cg = new ContactoGrupo();
        cg.setIdContacto(request.getIdContacto());
        cg.setIdGrupo(request.getIdGrupo());

        return contactoGrupoRepository.save(cg)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<ContactoGrupoResponse>> obtenerGruposPorContacto(int idContacto) {
        return contactoGrupoRepository.findByContactoId(idContacto)
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<List<ContactoGrupoResponse>> obtenerContactosPorGrupo(int idGrupo) {
        return contactoGrupoRepository.findByGrupoId(idGrupo)
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<Boolean> eliminarContactoDeGrupo(int idContacto, int idGrupo) {
        return contactoGrupoRepository.delete(idContacto, idGrupo);
    }

    private ContactoGrupoResponse mapToResponse(ContactoGrupo cg) {
        ContactoGrupoResponse response = new ContactoGrupoResponse();
        response.setIdContacto(cg.getIdContacto());
        response.setIdGrupo(cg.getIdGrupo());
        return response;
    }
}
