package com.example.agenda_contactos.repository.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.agenda_contactos.model.ContactoGrupo;
import com.example.agenda_contactos.repository.interfaces.ContactoGrupoRepository;

@Repository
public class ContactoGrupoRepositoryImpl implements ContactoGrupoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactoGrupoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ContactoGrupo> rowMapper = (ResultSet rs, int rowNum) -> {
        ContactoGrupo cg = new ContactoGrupo();
        cg.setIdContacto(rs.getInt("id_contacto"));
        cg.setIdGrupo(rs.getInt("id_grupo"));
        return cg;
    };

    @Override
    public CompletableFuture<ContactoGrupo> save(ContactoGrupo contactoGrupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO contacto_grupo (id_contacto, id_grupo) VALUES (?, ?)";
            jdbcTemplate.update(sql, contactoGrupo.getIdContacto(), contactoGrupo.getIdGrupo());
            return contactoGrupo;
        });
    }

    @Override
    public CompletableFuture<List<ContactoGrupo>> findByContactoId(int idContacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM contacto_grupo WHERE id_contacto = ?";
            return jdbcTemplate.query(sql, rowMapper, idContacto);
        });
    }

    @Override
    public CompletableFuture<List<ContactoGrupo>> findByGrupoId(int idGrupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM contacto_grupo WHERE id_grupo = ?";
            return jdbcTemplate.query(sql, rowMapper, idGrupo);
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(int idContacto, int idGrupo) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "DELETE FROM contacto_grupo WHERE id_contacto = ? AND id_grupo = ?";
            int rows = jdbcTemplate.update(sql, idContacto, idGrupo);
            return rows > 0;
        });
    }
}
