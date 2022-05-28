package com.example.kpo.repository;


import com.example.kpo.entity.Teacher;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class TeacherRepository implements IRestRepository<com.example.kpo.entity.Teacher>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\" " +
            "FROM \"teacher\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\"" +
            "FROM \"teacher\" " +
            "WHERE \"id\" = ?";

    private static String selectByKafedraIdQuery = "SELECT \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\" " +
            "FROM \"teacher\" " +
            "WHERE \"id_kafedra\" = ?";

    private static String selectByDolzhIdQuery = "SELECT \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\" " +
            "FROM \"teacher\" " +
            "WHERE \"id_dolzh\" = ?";

    private static String insertQuery = "INSERT INTO \"teacher\"(\"fio_teacher\", \"id_dolzh\", \"id_kafedra\") " +
            "VALUES (?,?,?) " +
            "RETURNING \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\"";

    private static String updateQuery = "UPDATE \"teacher\" " +
            "SET \"fio_teacher\" = ?, \"id_dolzh\"= ?, \"id_kafedra\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\"";

    private static String deleteQuery = "DELETE FROM \"teacher\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"fio_teacher\", \"id_dolzh\", \"id_kafedra\"";

    public TeacherRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Teacher[] select() {
        ArrayList<Teacher> values = new ArrayList<Teacher>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Teacher(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        Teacher[] result = new Teacher[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Teacher select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Teacher(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    public Teacher[] selectByKafedraId(Integer id_kafedra) {
        ArrayList<Teacher> values = new ArrayList<Teacher>();
        Object[] params = new Object[] { id_kafedra };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByKafedraIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Teacher(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        Teacher[] result = new Teacher[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Teacher[] selectByDolzhId(Integer id_dolzh) {
        ArrayList<Teacher> values = new ArrayList<Teacher>();
        Object[] params = new Object[] { id_dolzh };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByDolzhIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Teacher(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4)
            ));
        }
        Teacher[] result = new Teacher[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Teacher insert(Teacher entity) {
        Object[] params = new Object[] { entity.getFio_teacher(), entity.getId_dolzh(), entity.getId_kafedra() };
        int[] types = new int[] {  Types.VARCHAR ,Types.INTEGER ,Types.INTEGER   };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Teacher(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public Teacher update(Integer id, Teacher entity) {
        Object[] params = new Object[] { entity.getFio_teacher(), entity.getId_dolzh(), entity.getId_kafedra(), id };
        int[] types = new int[] { Types.VARCHAR ,Types.INTEGER ,Types.INTEGER , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Teacher(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }

    @Override
    public Teacher delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Teacher(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4)
        );
    }
}

