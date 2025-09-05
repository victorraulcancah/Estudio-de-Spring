package com.example.agenda_contactos.repository.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.agenda_contactos.model.Correo;
import com.example.agenda_contactos.repository.interfaces.CorreoRepository;

@Repository
public class CorreoRepositoryImpl implements CorreoRepository {

    private final JdbcTemplate jdbcTemplate;

    public CorreoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Correo> rowMapper = (ResultSet rs, int rowNum) -> {
        Correo c = new Correo();
        c.setIdCorreo(rs.getInt("id_correo"));
        c.setIdContacto(rs.getInt("id_contacto"));
        c.setTipo(rs.getString("tipo"));
        c.setEmail(rs.getString("email"));
        return c;
    };

    @Override
    public CompletableFuture<Correo> save(Correo correo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO correos (id_contacto, tipo, email) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, correo.getIdContacto(), correo.getTipo(), correo.getEmail());
            Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            correo.setIdCorreo(id);
            return correo;
        });
    }

    @Override
    public CompletableFuture<List<Correo>> findByContactoId(int idContacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM correos WHERE id_contacto = ?";
            return jdbcTemplate.query(sql, rowMapper, idContacto);
        });
    }

    @Override
    public CompletableFuture<Correo> update(int idCorreo, Correo correo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "UPDATE correos SET tipo = ?, email = ? WHERE id_correo = ?";
            jdbcTemplate.update(sql, correo.getTipo(), correo.getEmail(), idCorreo);
            correo.setIdCorreo(idCorreo);
            return correo;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(int idCorreo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "DELETE FROM correos WHERE id_correo = ?";
            int rows = jdbcTemplate.update(sql, idCorreo);
            return rows > 0;
        });
    }
}
