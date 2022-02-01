package ua.vitalik.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.vitalik.models.Item;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@Component
public class ItemDAO {
    private final JdbcTemplate jdbcTemplate;
    private static Connection connection;


    @Autowired
    public ItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Connection getConnection() {
        return connection;
    }

    public List<Item> index() {
        return jdbcTemplate.query("SELECT * From items", new BeanPropertyRowMapper<>(Item.class));
    }

    public Item show(int id) {
        return jdbcTemplate.query("SELECT * FROM items WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Item.class))
                .stream().findAny().orElse(null);
    }


    public void save(Item person) throws IOException {
        jdbcTemplate.update("INSERT INTO items VALUES (null, ?, ?, ?)", person.getName(), person.getDescription(), person.getImage());
    }

    public void update(int id, Item updatedPerson) throws IOException {
        jdbcTemplate.update("UPDATE items SET name=?, description=?, image=? WHERE id=?", updatedPerson.getName(), updatedPerson.getDescription(), updatedPerson.getImage(), updatedPerson.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM items WHERE id=?", id);
    }
}
