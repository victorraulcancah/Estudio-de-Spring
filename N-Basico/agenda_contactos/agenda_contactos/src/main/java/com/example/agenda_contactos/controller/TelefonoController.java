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

import com.example.agenda_contactos.dto.TelefonoRequest;
import com.example.agenda_contactos.dto.TelefonoResponse;
import com.example.agenda_contactos.service.interfaces.TelefonoService;

@RestController
@RequestMapping("api/telefonos")
public class TelefonoController {

    private final TelefonoService telefonoService;

    public TelefonoController(TelefonoService telefonoService) {
        this.telefonoService = telefonoService;
    }

    @PostMapping("/{idContacto}")
    public CompletableFuture<TelefonoResponse> agregarTelefono(@PathVariable int idContacto, @RequestBody TelefonoRequest request) {
        return telefonoService.agregarTelefono(request, idContacto);
    }

    @GetMapping("/contacto/{idContacto}")
    public CompletableFuture<List<TelefonoResponse>> obtenerTelefonosPorContacto(@PathVariable int idContacto) {
        return telefonoService.obtenerTelefonosPorContacto(idContacto);
    }

    @PutMapping("/{idTelefono}")
    public CompletableFuture<TelefonoResponse> actualizarTelefono(@PathVariable int idTelefono, @RequestBody TelefonoRequest request) {
        return telefonoService.actualizarTelefono(idTelefono, request);
    }

    @DeleteMapping("/{idTelefono}")
    public CompletableFuture<Boolean> eliminarTelefono(@PathVariable int idTelefono) {
        return telefonoService.eliminarTelefono(idTelefono);
    }
}
