package com.example.kpo.repository;

import com.example.kpo.entity.Direction;
import com.example.kpo.repository.IRestRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class DirectionRepository implements IRestRepository<com.example.kpo.entity.Direction> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"direction_name\" " +
            "FROM \"direction\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"direction_name\"" +
            "FROM \"direction\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"direction\"(\"direction_name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"direction_name\"";

    private static String updateQuery = "UPDATE \"direction\" " +
            "SET \"direction_name\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"direction_name\"";

    private static String deleteQuery = "DELETE FROM \"direction\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"direction_name\"";

    public DirectionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Direction[] select() {
        ArrayList<Direction> values = new ArrayList<Direction>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Direction(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Direction[] result = new Direction[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Direction select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Direction(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }


    @Override
    public Direction insert(Direction entity) {
        Object[] params = new Object[] { entity.getDirection_name() };
        int[] types = new int[] {  Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Direction(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Direction update(Integer id, Direction entity) {
        Object[] params = new Object[] { entity.getDirection_name(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Direction(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Direction delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Direction(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}

