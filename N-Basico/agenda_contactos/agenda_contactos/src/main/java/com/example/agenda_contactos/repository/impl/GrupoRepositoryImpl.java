package com.example.agenda_contactos.repository.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.agenda_contactos.model.Grupo;
import com.example.agenda_contactos.repository.interfaces.GrupoRepository;

@Repository
public class GrupoRepositoryImpl implements GrupoRepository {

    private final JdbcTemplate jdbcTemplate;

    public GrupoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Grupo> rowMapper = (ResultSet rs, int rowNum) -> {
        Grupo g = new Grupo();
        g.setIdGrupo(rs.getInt("id_grupo"));
        g.setNombreGrupo(rs.getString("nombre_grupo"));
        g.setDescripcion(rs.getString("descripcion"));
        g.setFechaCreacion(rs.getDate("fecha_creacion"));
        return g;
    };

    @Override
    public CompletableFuture<Grupo> save(Grupo grupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO grupos (nombre_grupo, descripcion) VALUES (?, ?)";
            jdbcTemplate.update(sql, grupo.getNombreGrupo(), grupo.getDescripcion());
            Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            grupo.setIdGrupo(id);
            return grupo;
        });
    }

    @Override
    public CompletableFuture<List<Grupo>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM grupos";
            return jdbcTemplate.query(sql, rowMapper);
        });
    }

    @Override
    public CompletableFuture<Grupo> findById(int idGrupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM grupos WHERE id_grupo = ?";
            return jdbcTemplate.queryForObject(sql, rowMapper, idGrupo);
        });
    }

    @Override
    public CompletableFuture<Grupo> findByNombre(String nombre) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM grupos WHERE nombre_grupo = ?";
            return jdbcTemplate.queryForObject(sql, rowMapper, nombre);
        });
    }

    @Override
    public CompletableFuture<Grupo> update(int idGrupo, Grupo grupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "UPDATE grupos SET nombre_grupo = ?, descripcion = ? WHERE id_grupo = ?";
            jdbcTemplate.update(sql, grupo.getNombreGrupo(), grupo.getDescripcion(), idGrupo);
            grupo.setIdGrupo(idGrupo);
            return grupo;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(int idGrupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "DELETE FROM grupos WHERE id_grupo = ?";
            int rows = jdbcTemplate.update(sql, idGrupo);
            return rows > 0;
        });
    }
}
