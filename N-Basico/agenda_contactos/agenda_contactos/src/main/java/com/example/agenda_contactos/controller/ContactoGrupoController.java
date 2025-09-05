package com.example.agenda_contactos.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda_contactos.dto.ContactoGrupoRequest;
import com.example.agenda_contactos.dto.ContactoGrupoResponse;
import com.example.agenda_contactos.service.interfaces.ContactoGrupoService;

@RestController
@RequestMapping("api/contacto-grupo")
public class ContactoGrupoController {

    private final ContactoGrupoService contactoGrupoService;

    public ContactoGrupoController(ContactoGrupoService contactoGrupoService) {
        this.contactoGrupoService = contactoGrupoService;
    }

    @PostMapping
    public CompletableFuture<ContactoGrupoResponse> agregar(@RequestBody ContactoGrupoRequest request) {
        return contactoGrupoService.agregarContactoAGrupo(request);
    }

    @GetMapping("/contacto/{idContacto}")
    public CompletableFuture<List<ContactoGrupoResponse>> obtenerGruposPorContacto(@PathVariable int idContacto) {
        return contactoGrupoService.obtenerGruposPorContacto(idContacto);
    }

    @GetMapping("/grupo/{idGrupo}")
    public CompletableFuture<List<ContactoGrupoResponse>> obtenerContactosPorGrupo(@PathVariable int idGrupo) {
        return contactoGrupoService.obtenerContactosPorGrupo(idGrupo);
    }

    @DeleteMapping
    public CompletableFuture<Boolean> eliminar(@RequestParam int idContacto, @RequestParam int idGrupo) {
        return contactoGrupoService.eliminarContactoDeGrupo(idContacto, idGrupo);
    }
}
