package com.example.kpo.repository;

import com.example.kpo.entity.Tip;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class TipRepository implements IRestRepository<com.example.kpo.entity.Tip> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"tip_name\" " +
            "FROM \"tip\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"tip_name\"" +
            "FROM \"tip\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"tip\"(\"tip_name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"tip_name\"";

    private static String updateQuery = "UPDATE \"tip\" " +
            "SET \"tip_name\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"tip_name\"";

    private static String deleteQuery = "DELETE FROM \"tip\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"tip_name\"";

    public TipRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Tip[] select() {
        ArrayList<Tip> values = new ArrayList<Tip>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Tip(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Tip[] result = new Tip[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Tip select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Tip(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }


    @Override
    public Tip insert(Tip entity) {
        Object[] params = new Object[] { entity.getTip_name() };
        int[] types = new int[] {  Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Tip(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Tip update(Integer id, Tip entity) {
        Object[] params = new Object[] { entity.getTip_name(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Tip(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Tip delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Tip(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}

