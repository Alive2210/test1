package dao;

import java.sql.Connection;

public interface ClearDataFromTable {
    boolean clear(Connection con);
}
