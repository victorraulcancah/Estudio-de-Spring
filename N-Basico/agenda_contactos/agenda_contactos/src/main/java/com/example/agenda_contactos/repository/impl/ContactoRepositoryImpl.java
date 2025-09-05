package com.example.agenda_contactos.repository.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.agenda_contactos.model.Contacto;
import com.example.agenda_contactos.repository.interfaces.ContactoRepository;

@Repository
public class ContactoRepositoryImpl implements ContactoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Contacto> rowMapper = (ResultSet rs, int rowNum) -> {
        Contacto c = new Contacto();
        c.setIdContacto(rs.getInt("id_contacto"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setDireccion(rs.getString("direccion"));
        c.setEmpresa(rs.getString("empresa"));
        c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        c.setNotas(rs.getString("notas"));
        c.setFavorito(rs.getBoolean("favorito"));
        c.setFechaCreacion(rs.getDate("fecha_creacion"));
        c.setFechaActualizacion(rs.getDate("fecha_actualizacion"));
        return c;
    };

    @Override
    public CompletableFuture<Contacto> save(Contacto contacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO contactos (nombre, apellido, direccion, empresa, fecha_nacimiento, notas, favorito) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, contacto.getNombre(), contacto.getApellido(),
                    contacto.getDireccion(), contacto.getEmpresa(),
                    contacto.getFechaNacimiento(), contacto.getNotas(),
                    contacto.getFavorito());
            Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            contacto.setIdContacto(id);
            return contacto;
        });
    }

    @Override
    public CompletableFuture<Contacto> findById(int idContacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM contactos WHERE id_contacto = ?";
            return jdbcTemplate.queryForObject(sql, rowMapper, idContacto);
        });
    }

    @Override
    public CompletableFuture<List<Contacto>> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM contactos";
            return jdbcTemplate.query(sql, rowMapper);
        });
    }

    @Override
    public CompletableFuture<Contacto> update(int idContacto, Contacto contacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "UPDATE contactos SET nombre = ?, apellido = ?, direccion = ?, empresa = ?, fecha_nacimiento = ?, notas = ?, favorito = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE id_contacto = ?";
            jdbcTemplate.update(sql, contacto.getNombre(), contacto.getApellido(),
                    contacto.getDireccion(), contacto.getEmpresa(),
                    contacto.getFechaNacimiento(), contacto.getNotas(),
                    contacto.getFavorito(), idContacto);
            contacto.setIdContacto(idContacto);
            return contacto;
        });
    }

    @Override
    public CompletableFuture<Boolean> delete(int idContacto) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "DELETE FROM contactos WHERE id_contacto = ?";
            int rows = jdbcTemplate.update(sql, idContacto);
            return rows > 0;
        });
    }
}
