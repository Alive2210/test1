package dao;

import java.sql.Connection;

public interface SendDataToDB {
    void loadDataToDb(Connection con, long n, int batchSize);
}
