package com.example.Biblioteca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Biblioteca.Model.Libro;

@Repository
public class LibroRepositoryImpl implements ILibroRepository {

    private final JdbcTemplate jdbcTemplate;

    public LibroRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class LibroRowMapper implements RowMapper<Libro> {
        @Override
        public Libro mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Libro(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("autor")
            );
        }
    }

    @Override
    public CompletableFuture<List<Libro>> listarLibros() {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM libros";
            return jdbcTemplate.query(sql, new LibroRowMapper());
        });
    }

    @Override
    public CompletableFuture<Libro> obtenerLibroPorId(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "SELECT * FROM libros WHERE id = ?";
            return jdbcTemplate.query(sql, new LibroRowMapper(), id)
                    .stream()
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public CompletableFuture<Libro> guardarLibro(Libro libro) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "INSERT INTO libros (titulo, autor) VALUES (?, ?)";
            jdbcTemplate.update(sql, libro.getTitulo(), libro.getAutor());

            String sqlSelect = "SELECT * FROM libros ORDER BY id DESC LIMIT 1";
            return jdbcTemplate.query(sqlSelect, new LibroRowMapper())
                    .stream()
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public CompletableFuture<Void> eliminarLibro(Long id) {
        return CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM libros WHERE id = ?";
            jdbcTemplate.update(sql, id);
        });
    }

    @Override
    public CompletableFuture<Libro> actualizarLibro(Long id, Libro libro) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = "UPDATE libros SET titulo = ?, autor = ? WHERE id = ?";
            jdbcTemplate.update(sql, libro.getTitulo(), libro.getAutor(), id);
            return obtenerLibroPorId(id).join();
        });
    }
}
