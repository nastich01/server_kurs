package com.example.kpo.repository;

import com.example.kpo.entity.Faculty;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class FacultyRepository implements IRestRepository<com.example.kpo.entity.Faculty> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"faculty_name\" " +
            "FROM \"faculty\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"faculty_name\"" +
            "FROM \"faculty\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"faculty\"(\"faculty_name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"faculty_name\"";

    private static String updateQuery = "UPDATE \"faculty\" " +
            "SET \"faculty_name\" = ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"faculty_name\"";

    private static String deleteQuery = "DELETE FROM \"faculty\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"faculty_name\"";

    public FacultyRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Faculty[] select() {
        ArrayList<Faculty> values = new ArrayList<Faculty>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Faculty(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Faculty[] result = new Faculty[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Faculty select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Faculty(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }


    @Override
    public Faculty insert(Faculty entity) {
        Object[] params = new Object[] { entity.getFaculty_name() };
        int[] types = new int[] {  Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Faculty(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Faculty update(Integer id, Faculty entity) {
        Object[] params = new Object[] { entity.getFaculty_name(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Faculty(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Faculty delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Faculty(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}


