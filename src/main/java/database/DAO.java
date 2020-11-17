package database;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

public interface DAO <Entity> {
    boolean create(Entity model) throws SQLException;
    boolean read(Entity model) throws SQLException;
    boolean update(Entity model) throws SQLException;
    boolean delete(List<UserPoint> userPoints) throws SQLException;
    void clear() throws SQLException;
    List<UserPoint> readAll() throws SQLException;
}

