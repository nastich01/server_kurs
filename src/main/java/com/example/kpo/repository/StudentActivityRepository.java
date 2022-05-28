package com.example.kpo.repository;



import com.example.kpo.entity.StudentActivity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class StudentActivityRepository implements IRestRepository<com.example.kpo.entity.StudentActivity>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"id_activity\", \"id_student\" " +
            "FROM \"student_activity\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"id_activity\", \"id_student\"" +
            "FROM \"student_activity\" " +
            "WHERE \"id\" = ?";

    private static String selectByActivityIdQuery = "SELECT \"id\", \"id_activity\", \"id_student\" " +
            "FROM \"student_activity\" " +
            "WHERE \"id_activity\" = ?";

    private static String selectByStudentIdQuery = "SELECT \"id\", \"id_activity\", \"id_student\" " +
            "FROM \"student_activity\" " +
            "WHERE \"id_student\" = ?";

    private static String insertQuery = "INSERT INTO \"student_activity\"(\"id_activity\", \"id_student\") " +
            "VALUES (?,?) " +
            "RETURNING \"id\", \"id_activity\", \"id_student\"";

    private static String updateQuery = "UPDATE \"student_activity\" " +
            "SET \"id_activity\" = ?, \"id_student\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_activity\", \"id_student\"";

    private static String deleteQuery = "DELETE FROM \"student_activity\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_activity\", \"id_student\"";

    public StudentActivityRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public StudentActivity[] select() {
        ArrayList<StudentActivity> values = new ArrayList<StudentActivity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new StudentActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        StudentActivity[] result = new StudentActivity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public StudentActivity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    public StudentActivity[] selectByActivityId(Integer id_activity) {
        ArrayList<StudentActivity> values = new ArrayList<StudentActivity>();
        Object[] params = new Object[] { id_activity };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByActivityIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new StudentActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        StudentActivity[] result = new StudentActivity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public StudentActivity[] selectByStudentId(Integer id_student) {
        ArrayList<StudentActivity> values = new ArrayList<StudentActivity>();
        Object[] params = new Object[] { id_student };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByStudentIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new StudentActivity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        StudentActivity[] result = new StudentActivity[values.size()];
        result = values.toArray(result);
        return result;
    }


    @Override
    public StudentActivity insert(StudentActivity entity) {
        Object[] params = new Object[] { entity.getId_activity(), entity.getId_student() };
        int[] types = new int[] {  Types.INTEGER ,Types.INTEGER   };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public StudentActivity update(Integer id, StudentActivity entity) {
        Object[] params = new Object[] { entity.getId_activity(), entity.getId_student(), id };
        int[] types = new int[] { Types.INTEGER ,Types.INTEGER , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public StudentActivity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentActivity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }
}


