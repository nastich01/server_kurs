package com.example.kpo.repository;

import com.example.kpo.entity.Dolzh;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class DolzhRepository implements IRestRepository<com.example.kpo.entity.Dolzh>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"dolzh_name\" " +
            "FROM \"dolzh\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"dolzh_name\"" +
            "FROM \"dolzh\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"dolzh\"(\"dolzh_name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"dolzh_name\"";

    private static String updateQuery = "UPDATE \"dolzh\" " +
            "SET \"dolzh_name\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"dolzh_name\"";

    private static String deleteQuery = "DELETE FROM \"dolzh\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"dolzh_name\"";

    public DolzhRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Dolzh[] select() {
        ArrayList<Dolzh> values = new ArrayList<Dolzh>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Dolzh(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Dolzh[] result = new Dolzh[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Dolzh select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Dolzh(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }


    @Override
    public Dolzh insert(Dolzh entity) {
        Object[] params = new Object[] { entity.getDolzh_name() };
        int[] types = new int[] {  Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Dolzh(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Dolzh update(Integer id, Dolzh entity) {
        Object[] params = new Object[] { entity.getDolzh_name(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Dolzh(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Dolzh delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Dolzh(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
