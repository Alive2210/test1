package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SendDataToDBImpl implements SendDataToDB {
    private static final Logger logger = LoggerFactory.getLogger(ReadDataFromDB.class);
    private PreparedStatement stmt;

    @Override
    public void loadDataToDb(Connection con, long n, int batchSize) {
        try {
            String LOAD_DATA = "INSERT INTO TEST (FIELD) VALUES (?)";
            con.setAutoCommit(false);
            stmt = con.prepareStatement(LOAD_DATA);
            LongStream.range(1, n+1).forEach(i -> {
                try {
                    stmt.setLong(1, i);
                    stmt.executeUpdate();
                    logger.debug(String.valueOf(i) + " - field was write... ");
                } catch (SQLException e) {
                    logger.error("error with insert.. ", e);
                }
            });
            IntStream.range(1, batchSize+1).filter(i->i<=batchSize).forEach(i -> {
                try {
                    stmt.addBatch(LOAD_DATA);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            stmt.executeBatch();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("error closing session... ", e);
            }
        }
    }
}
