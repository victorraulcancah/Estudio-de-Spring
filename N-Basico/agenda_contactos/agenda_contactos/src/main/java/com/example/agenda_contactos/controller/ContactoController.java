package com.example.agenda_contactos.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda_contactos.dto.ContactoRequest;
import com.example.agenda_contactos.dto.ContactoResponse;
import com.example.agenda_contactos.service.interfaces.ContactoService;

@RestController
@RequestMapping("api/contactos")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public CompletableFuture<ContactoResponse> crearContacto(@RequestBody ContactoRequest request) {
        return contactoService.crearContacto(request);
    }

    @GetMapping
    public CompletableFuture<List<ContactoResponse>> obtenerTodosContactos() {
        return contactoService.obtenerTodosContactos();
    }

    @GetMapping("/{id}")
    public CompletableFuture<ContactoResponse> obtenerContactoPorId(@PathVariable int id) {
        return contactoService.obtenerContactoPorId(id);
    }

    @PutMapping("/{id}")
    public CompletableFuture<ContactoResponse> actualizarContacto(@PathVariable int id, @RequestBody ContactoRequest request) {
        return contactoService.actualizarContacto(id, request);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Boolean> eliminarContacto(@PathVariable int id) {
        return contactoService.eliminarContacto(id);
    }
}
