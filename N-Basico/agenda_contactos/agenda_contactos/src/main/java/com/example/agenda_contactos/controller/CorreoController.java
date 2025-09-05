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

import com.example.agenda_contactos.dto.CorreoRequest;
import com.example.agenda_contactos.dto.CorreoResponse;
import com.example.agenda_contactos.service.interfaces.CorreoService;

@RestController
@RequestMapping("api/correos")
public class CorreoController {

    private final CorreoService correoService;

    public CorreoController(CorreoService correoService) {
        this.correoService = correoService;
    }

    @PostMapping("/{idContacto}")
    public CompletableFuture<CorreoResponse> agregarCorreo(@PathVariable int idContacto, @RequestBody CorreoRequest request) {
        return correoService.agregarCorreo(request, idContacto);
    }

    @GetMapping("/contacto/{idContacto}")
    public CompletableFuture<List<CorreoResponse>> obtenerCorreosPorContacto(@PathVariable int idContacto) {
        return correoService.obtenerCorreosPorContacto(idContacto);
    }

    @PutMapping("/{idCorreo}")
    public CompletableFuture<CorreoResponse> actualizarCorreo(@PathVariable int idCorreo, @RequestBody CorreoRequest request) {
        return correoService.actualizarCorreo(idCorreo, request);
    }

    @DeleteMapping("/{idCorreo}")
    public CompletableFuture<Boolean> eliminarCorreo(@PathVariable int idCorreo) {
        return correoService.eliminarCorreo(idCorreo);
    }
}
