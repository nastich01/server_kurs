package com.example.kpo.repository;


import com.example.kpo.entity.Student;
import com.example.kpo.entity.TeacherActivity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class TeacherActivityRepository implements IRestRepository<com.example.kpo.entity.TeacherActivity>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"id_activity\", \"id_teacher\" " +
            "FROM \"teacher_activity\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"id_activity\", \"id_teacher\"" +
            "FROM \"teacher_activity\" " +
            "WHERE \"id\" = ?";

    private static String selectByActivityIdQuery = "SELECT \"id\", \"id_activity\", \"id_teacher\"" +
            "FROM \"teacher_activity\" " +
            "WHERE \"id_activity\" = ?";

    private static String selectByTeacherIdQuery = "SELECT \"id\", \"id_activity\", \"id_teacher\"" +
            "FROM \"teacher_activity\" " +
            "WHERE \"id_teacher\" = ?";

    private static String insertQuery = "INSERT INTO \"teacher_activity\"(\"id_activity\", \"id_teacher\") " +
            "VALUES (?,?) " +
            "RETURNING \"id\", \"id_activity\", \"id_teacher\"";

    private static String updateQuery = "UPDATE \"teacher_activity\" " +
            "SET \"id_activity\" = ?, \"id_teacher\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_activity\", \"id_teacher\"";

    private static String deleteQuery = "DELETE FROM \"teacher_activity\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_activity\", \"id_teacher\"";

    public TeacherActivityRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public TeacherActivity[] select() {
        ArrayList<TeacherActivity> values = new ArrayList<TeacherActivity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new TeacherActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        TeacherActivity[] result = new TeacherActivity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public TeacherActivity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    public TeacherActivity[] selectByActivityId(Integer id_activity) {
        ArrayList<TeacherActivity> values = new ArrayList<TeacherActivity>();
        Object[] params = new Object[] { id_activity };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByActivityIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new TeacherActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        TeacherActivity[] result = new TeacherActivity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public TeacherActivity[] selectByTeacherId(Integer id_teacher) {
        ArrayList<TeacherActivity> values = new ArrayList<TeacherActivity>();
        Object[] params = new Object[] { id_teacher };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByTeacherIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new TeacherActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        TeacherActivity[] result = new TeacherActivity[values.size()];
        result = values.toArray(result);
        return result;
    }


    @Override
    public TeacherActivity insert(TeacherActivity entity) {
        Object[] params = new Object[] { entity.getId_activity(), entity.getId_teacher() };
        int[] types = new int[] {  Types.INTEGER ,Types.INTEGER   };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public TeacherActivity update(Integer id, TeacherActivity entity) {
        Object[] params = new Object[] { entity.getId_activity(), entity.getId_teacher(), id };
        int[] types = new int[] { Types.INTEGER ,Types.INTEGER , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public TeacherActivity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }
}



