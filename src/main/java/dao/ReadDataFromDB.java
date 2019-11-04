package dao;

import java.sql.Connection;
import java.util.List;

public interface ReadDataFromDB {
    List<Long> readDataFromDB(Connection con);
}
