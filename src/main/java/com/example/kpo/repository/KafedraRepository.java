package com.example.kpo.repository;

import com.example.kpo.entity.Kafedra;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class KafedraRepository implements IRestRepository<com.example.kpo.entity.Kafedra> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"kafedra_name\" " +
            "FROM \"kafedra\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"kafedra_name\"" +
            "FROM \"kafedra\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"kafedra\"(\"kafedra_name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"kafedra_name\"";

    private static String updateQuery = "UPDATE \"kafedra\" " +
            "SET \"kafedra_name\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"kafedra_name\"";

    private static String deleteQuery = "DELETE FROM \"kafedra\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"kafedra_name\"";

    public KafedraRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Kafedra[] select() {
        ArrayList<Kafedra> values = new ArrayList<Kafedra>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Kafedra(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Kafedra[] result = new Kafedra[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Kafedra select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Kafedra(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }


    @Override
    public Kafedra insert(Kafedra entity) {
        Object[] params = new Object[] { entity.getKafedra_name() };
        int[] types = new int[] {  Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Kafedra(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Kafedra update(Integer id, Kafedra entity) {
        Object[] params = new Object[] { entity.getKafedra_name(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Kafedra(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Kafedra delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Kafedra(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}

