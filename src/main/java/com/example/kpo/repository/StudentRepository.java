package com.example.kpo.repository;



import com.example.kpo.entity.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class StudentRepository implements IRestRepository<com.example.kpo.entity.Student>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\" " +
            "FROM \"student\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\"" +
            "FROM \"student\" " +
            "WHERE \"id\" = ?";

    private static String selectByFacultyIdQuery = "SELECT \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\" " +
            "FROM \"student\" " +
            "WHERE \"id_faculty\" = ?";

    private static String selectByKursQuery = "SELECT \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\" " +
            "FROM \"student\" " +
            "WHERE \"kurs\" = ?";

    private static String selectByGroupQuery = "SELECT \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\" " +
            "FROM \"student\" " +
            "WHERE \"group\" = ?";

    private static String insertQuery = "INSERT INTO \"student\"(\"fio_stud\", \"kurs\", \"group\", \"id_faculty\") " +
            "VALUES (?,?,?,?) " +
            "RETURNING \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\"";

    private static String updateQuery = "UPDATE \"student\" " +
            "SET \"fio_stud\" = ?, \"kurs\"= ?, \"group\"= ?, \"id_faculty\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\"";

    private static String deleteQuery = "DELETE FROM \"student\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"fio_stud\", \"kurs\", \"group\", \"id_faculty\"";

    public StudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Student[] select() {
        ArrayList<Student> values = new ArrayList<Student>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Student(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Student[] result = new Student[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Student select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Student(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    public Student[] selectByFacultyId(Integer id_faculty) {
        ArrayList<Student> values = new ArrayList<Student>();
        Object[] params = new Object[] { id_faculty };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByFacultyIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Student(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Student[] result = new Student[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Student[] selectByKurs(Integer kurs) {
        ArrayList<Student> values = new ArrayList<Student>();
        Object[] params = new Object[] { kurs };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByKursQuery, params, types);
        while (rowSet.next()) {
            values.add(new Student(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Student[] result = new Student[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Student[] selectByGroup(String group) {
        ArrayList<Student> values = new ArrayList<Student>();
        Object[] params = new Object[] { group };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByGroupQuery, params, types);
        while (rowSet.next()) {
            values.add(new Student(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Student[] result = new Student[values.size()];
        result = values.toArray(result);
        return result;
    }


    @Override
    public Student insert(Student entity) {
        Object[] params = new Object[] { entity.getFio_stud(), entity.getKurs(), entity.getGroup(), entity.getId_faculty() };
        int[] types = new int[] {  Types.VARCHAR,Types.INTEGER ,Types.VARCHAR ,Types.INTEGER   };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Student(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Student update(Integer id, Student entity) {
        Object[] params = new Object[] { entity.getFio_stud(), entity.getKurs(), entity.getGroup(), entity.getId_faculty(), id };
        int[] types = new int[] { Types.VARCHAR ,Types.INTEGER ,Types.VARCHAR ,Types.INTEGER , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Student(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Student delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Student(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }
}
