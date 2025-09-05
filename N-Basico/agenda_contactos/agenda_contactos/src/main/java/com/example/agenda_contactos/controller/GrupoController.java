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

import com.example.agenda_contactos.dto.GrupoRequest;
import com.example.agenda_contactos.dto.GrupoResponse;
import com.example.agenda_contactos.service.interfaces.GrupoService;

@RestController
@RequestMapping("api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @PostMapping
    public CompletableFuture<GrupoResponse> crearGrupo(@RequestBody GrupoRequest request) {
        return grupoService.crearGrupo(request);
    }

    @GetMapping
    public CompletableFuture<List<GrupoResponse>> obtenerTodosLosGrupos() {
        return grupoService.obtenerTodosLosGrupos();
    }

    @GetMapping("/nombre/{nombre}")
    public CompletableFuture<GrupoResponse> buscarGrupoPorNombre(@PathVariable String nombre) {
        return grupoService.buscarGrupoPorNombre(nombre);
    }

    @PutMapping("/{idGrupo}")
    public CompletableFuture<GrupoResponse> actualizarGrupo(@PathVariable int idGrupo, @RequestBody GrupoRequest request) {
        return grupoService.actualizarGrupo(idGrupo, request);
    }

    @DeleteMapping("/{idGrupo}")
    public CompletableFuture<Boolean> eliminarGrupo(@PathVariable int idGrupo) {
        return grupoService.eliminarGrupo(idGrupo);
    }
}
