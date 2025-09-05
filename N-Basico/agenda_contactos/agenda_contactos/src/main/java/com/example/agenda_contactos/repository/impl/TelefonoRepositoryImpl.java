package com.example.agenda_contactos.repository.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.agenda_contactos.model.Telefono;
import com.example.agenda_contactos.repository.interfaces.TelefonoRepository;

@Repository
public class TelefonoRepositoryImpl implements TelefonoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TelefonoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Telefono> rowMapper = (ResultSet rs, int rowNum) -> {
        Telefono t = new Telefono();
        t.setIdTelefono(rs.getInt("id_telefono"));
        t.setIdContacto(rs.getInt("id_contacto"));
        t.setTipo(rs.getString("tipo"));
        t.setNumero(rs.getString("numero"));
        return t;
    };

    @Override
    public CompletableFuture<Telefono> save(Telefono telefono) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO telefonos (id_contacto, tipo, numero) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, telefono.getIdContacto(), telefono.getTipo(), telefono.getNumero());
            
            Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            telefono.setIdTelefono(id);
            return telefono;
        });
    }

    @Override
    public CompletableFuture<List<Telefono>> findByContactoId(int idContacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM telefonos WHERE id_contacto = ?";
            return jdbcTemplate.query(sql, rowMapper, idContacto);
        });
    }

    @Override
    public CompletableFuture<Telefono> update(int idTelefono, Telefono telefono) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "UPDATE telefonos SET tipo = ?, numero = ? WHERE id_telefono = ?";
            jdbcTemplate.update(sql, telefono.getTipo(), telefono.getNumero(), idTelefono);
            telefono.setIdTelefono(idTelefono);
            return telefono;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(int idTelefono) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "DELETE FROM telefonos WHERE id_telefono = ?";
            int rows = jdbcTemplate.update(sql, idTelefono);
            return rows > 0;
        });
    }
}
