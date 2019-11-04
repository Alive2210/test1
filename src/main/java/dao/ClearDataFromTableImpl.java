package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClearDataFromTableImpl implements ClearDataFromTable {

    private static final Logger logger = LoggerFactory.getLogger(ClearDataFromTable.class);
    private PreparedStatement stmt;

    @Override
    public boolean clear(Connection con) {
        boolean flag = false;
        try {
            String CLEAR_DB = "TRUNCATE TEST";
            stmt = con.prepareStatement(CLEAR_DB);
            stmt.executeUpdate();
            logger.info("table is cleared... ");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("error closing session... ", e);
            }
        }
        return flag;
    }
}
