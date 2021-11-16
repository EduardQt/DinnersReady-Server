package se.hkr.freechoice.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Model {
    void serialize(ResultSet resultSet) throws SQLException;
}
