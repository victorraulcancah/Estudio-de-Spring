package com.example.agenda_contactos.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.agenda_contactos.dto.ContactoRequest;
import com.example.agenda_contactos.dto.ContactoResponse;
import com.example.agenda_contactos.model.Contacto;
import com.example.agenda_contactos.repository.interfaces.ContactoRepository;
import com.example.agenda_contactos.service.interfaces.ContactoService;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoServiceImpl(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @Override
    @Async
    public CompletableFuture<ContactoResponse> crearContacto(ContactoRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.getNombre());
        contacto.setApellido(request.getApellido());
        contacto.setDireccion(request.getDireccion());
        contacto.setEmpresa(request.getEmpresa());
        contacto.setFechaNacimiento(request.getFechaNacimiento());
        contacto.setNotas(request.getNotas());
        contacto.setFavorito(request.getFavorito());

        return contactoRepository.save(contacto)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<ContactoResponse>> obtenerTodosContactos() {
        return contactoRepository.findAll()
                .thenApply(list -> list.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<ContactoResponse> obtenerContactoPorId(int idContacto) {
        return contactoRepository.findById(idContacto)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<ContactoResponse> actualizarContacto(int idContacto, ContactoRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.getNombre());
        contacto.setApellido(request.getApellido());
        contacto.setDireccion(request.getDireccion());
        contacto.setEmpresa(request.getEmpresa());
        contacto.setFechaNacimiento(request.getFechaNacimiento());
        contacto.setNotas(request.getNotas());
        contacto.setFavorito(request.getFavorito());

        return contactoRepository.update(idContacto, contacto)
                .thenApply(this::mapToResponse);
    }

    @Override
    @Async
    public CompletableFuture<Boolean> eliminarContacto(int idContacto) {
        return contactoRepository.delete(idContacto);
    }

    // Método privado para mapear de modelo a DTO
    private ContactoResponse mapToResponse(Contacto contacto) {
        ContactoResponse response = new ContactoResponse();
        response.setIdContacto(contacto.getIdContacto());
        response.setNombre(contacto.getNombre());
        response.setApellido(contacto.getApellido());
        response.setDireccion(contacto.getDireccion());
        response.setEmpresa(contacto.getEmpresa());
        response.setFechaNacimiento(contacto.getFechaNacimiento());
        response.setNotas(contacto.getNotas());
        response.setFavorito(contacto.getFavorito());
        response.setFechaCreacion(contacto.getFechaCreacion());
        response.setFechaActualizacion(contacto.getFechaActualizacion());
        return response;
    }
}
