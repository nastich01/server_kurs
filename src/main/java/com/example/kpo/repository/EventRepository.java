package com.example.kpo.repository;

import com.example.kpo.entity.Event;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Types;
import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:9000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT }, maxAge = 3600)
@Repository
public class EventRepository implements IRestRepository<com.example.kpo.entity.Event> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"event_name\", \"place\", \"date\", \"time\", \"link\" " +
            "FROM \"event\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"event_name\", \"place\", \"date\", \"time\", \"link\"" +
            "FROM \"event\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"event\"(\"event_name\", \"place\", \"date\", \"time\", \"link\") " +
            "VALUES (?,?,?,?,?) " +
            "RETURNING \"id\", \"event_name\", \"place\", \"date\", \"time\", \"link\"";

    private static String updateQuery = "UPDATE \"event\" " +
            "SET \"event_name\" = ?, \"place\"= ?, \"date\"= ?, \"time\"= ?, \"link\"= ?" +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"event_name\", \"place\", \"date\", \"time\", \"link\"";

    private static String deleteQuery = "DELETE FROM \"event\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"event_name\", \"place\", \"date\", \"time\", \"link\"";

    public EventRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Event[] select() {
        ArrayList<Event> values = new ArrayList<Event>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Event(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getString(6)
            ));
        }
        Event[] result = new Event[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Event select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Event(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }


    @Override
    public Event insert(Event entity) {
        Object[] params = new Object[] { entity.getEvent_name(), entity.getPlace(), entity.getDate(), entity.getTime(), entity.getLink() };
        int[] types = new int[] {  Types.VARCHAR,Types.VARCHAR ,Types.VARCHAR ,Types.VARCHAR ,Types.VARCHAR  };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Event(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }

    @Override
    public Event update(Integer id, Event entity) {
        Object[] params = new Object[] { entity.getEvent_name(), entity.getPlace(), entity.getDate(), entity.getTime(), entity.getLink(), id };
        int[] types = new int[] { Types.VARCHAR,Types.VARCHAR ,Types.VARCHAR ,Types.VARCHAR ,Types.VARCHAR , Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Event(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }

    @Override
    public Event delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Event(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }
}
