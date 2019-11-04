package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromDBImpl implements ReadDataFromDB {
    private static final Logger logger = LoggerFactory.getLogger(ReadDataFromDB.class);
    private PreparedStatement stmt;

    @Override
    public List<Long> readDataFromDB(Connection con) {
        ResultSet resultSet;
        String READ_FIELD_FROM_TEST = "SELECT FIELD FROM TEST";
        List<Long> listFields = new ArrayList<>();
        try {
            stmt = con.prepareStatement(READ_FIELD_FROM_TEST);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                listFields.add(resultSet.getLong("FIELD"));
                logger.debug(String.valueOf(resultSet.getLong("FIELD") + " - field was read..."));
            }
        } catch (SQLException e) {
            logger.error("error select data.. ", e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("error with closing session... ", e);
            }
        }
        return listFields;
    }

}
