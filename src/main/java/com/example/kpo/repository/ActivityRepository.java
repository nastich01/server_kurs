package com.example.kpo.repository;

import com.example.kpo.entity.Activity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class ActivityRepository implements IRestRepository<com.example.kpo.entity.Activity>{
    protected final JdbcOperations jdbcOperations;
    private static String selectQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "WHERE \"id\" = ?";

    private static String selectByTipIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "WHERE \"id_tip\" = ?";

    private static String selectByDirectionIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\"" +
            "FROM \"activity\" " +
            "WHERE \"id_direction\" = ?";

    private static String selectByEventIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "WHERE \"id_event\" = ?";

    private static String selectByStudentIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "WHERE \"id_student\" = ?";

    private static String selectByTeacherIdQuery = "SELECT \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" " +
            "FROM \"activity\" " +
            "WHERE \"id_teacher\" = ?";

    private static String insertQuery = "INSERT INTO \"activity\"(\"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\") " +
            "VALUES (?,?,?,?,?,?,?,?,?) " +
            "RETURNING \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\" ";

    private static String updateQuery = "UPDATE \"activity\" " +
            "SET \"id_event\" = ?, \"id_tip\"= ?, \"id_direction\"= ?, \"work_name\"= ?, \"annotation\"= ?, \"id_student\"= ?, \"id_teacher\"= ?,\"event_name\"= ?, \"event_date\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\"";

    private static String deleteQuery = "DELETE FROM \"activity\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"id_event\", \"id_tip\", \"id_direction\", \"work_name\", \"annotation\", \"id_student\", \"id_teacher\", \"event_name\", \"event_date\"";

    public ActivityRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Activity[] select() {
        ArrayList<Activity> values = new ArrayList<Activity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Activity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Activity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getInt(7),
                rowSet.getInt(8),
                rowSet.getString(9),
                rowSet.getDate(10)
        );
    }

    public Activity[] selectByTipId(Integer id_teacher) {
        ArrayList<Activity> values = new ArrayList<Activity>();
        Object[] params = new Object[] { id_teacher };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByTipIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Activity[] selectByDirectionId(Integer id_direction) {
        ArrayList<Activity> values = new ArrayList<Activity>();
        Object[] params = new Object[] { id_direction };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByDirectionIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Activity[] selectByEventId(Integer id_event) {
        ArrayList<Activity> values = new ArrayList<Activity>();
        Object[] params = new Object[] { id_event };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByEventIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Activity[] selectByStudentId(Integer id_student) {
        ArrayList<Activity> values = new ArrayList<Activity>();
        Object[] params = new Object[] { id_student };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByStudentIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Activity[] selectByTeacherId(Integer id_teacher) {
        ArrayList<Activity> values = new ArrayList<Activity>();
        Object[] params = new Object[] { id_teacher };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByTeacherIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Activity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getInt(7),
                    rowSet.getInt(8),
                    rowSet.getString(9),
                    rowSet.getDate(10)
            ));
        }
        Activity[] result = new Activity[values.size()];
        result = values.toArray(result);
        return result;
    }


    @Override
    public Activity insert(Activity entity) {
        Object[] params = new Object[] { entity.getId_event(), entity.getId_tip(), entity.getId_direction(), entity.getWork_name(), entity.getAnnotation(),entity.getId_student(), entity.getId_teacher(),entity.getEvent_name(), entity.getEvent_date() };
        int[] types = new int[] {  Types.INTEGER,Types.INTEGER ,Types.INTEGER ,Types.VARCHAR ,Types.VARCHAR,Types.INTEGER ,Types.INTEGER,Types.VARCHAR,Types.DATE  };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Activity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getInt(7),
                rowSet.getInt(8),
                rowSet.getString(9),
                rowSet.getDate(10)
        );
    }

    @Override
    public Activity update(Integer id, Activity entity) {
        Object[] params = new Object[] { entity.getId_event(), entity.getId_tip(), entity.getId_direction(), entity.getWork_name(), entity.getAnnotation(), entity.getId_student(), entity.getId_teacher(),entity.getEvent_name(), entity.getEvent_date(), id };
        int[] types = new int[] { Types.INTEGER,Types.INTEGER ,Types.INTEGER ,Types.VARCHAR ,Types.VARCHAR ,Types.INTEGER ,Types.INTEGER,Types.VARCHAR,Types.DATE , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Activity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getInt(7),
                rowSet.getInt(8),
                rowSet.getString(9),
                rowSet.getDate(10)
        );
    }

    @Override
    public Activity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Activity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getInt(7),
                rowSet.getInt(8),
                rowSet.getString(9),
                rowSet.getDate(10)
        );
    }
}
