package rout;

import dao.*;
import entityData.FieldsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processXml.CreateXml;
import processXml.TransformXml;
import processXml.XmlParser;

import java.sql.Connection;
import java.sql.SQLException;

public class StartTest {
    private static final Logger logger = LoggerFactory.getLogger(StartTest.class);

    private FieldsBean fieldsBean = new FieldsBean();
    private ClearDataFromTable clearDataFromTable = new ClearDataFromTableImpl();
    private SendDataToDB sendDataToDB = new SendDataToDBImpl();
    private ReadDataFromDB readDataFromDB = new ReadDataFromDBImpl();
    private ConnectToDB connectToDB = new ConnectToDB();
    private CreateXml createXml = new CreateXml();
    private TransformXml transformXml = new TransformXml();
    private XmlParser parser = new XmlParser();




    public void start() {
        long startTime = System.currentTimeMillis();

        fieldsBean.setIp("127.0.0.1");
        fieldsBean.setLogin("root");
        fieldsBean.setPass("vertrigo");
        fieldsBean.setNameDb("test");
        fieldsBean.setPort("3306");
        fieldsBean.setDriverName("com.mysql.jdbc.Driver");
        fieldsBean.setN(1000000);
        String fileNameCreateXml = "src/main/resources/xml/first.xml";
        String fileNameTransformedXml = "src/main/resources/xml/final.xml";
        String fileNameTransformXsl = "src/main/resources/xslt/transform.xsl";

        String url = String.valueOf(new StringBuilder()
                .append("jdbc:mysql://")
                .append(fieldsBean.getIp())
                .append(":")
                .append(fieldsBean.getPort())
                .append("/")
                .append(fieldsBean.getNameDb())
        );

        Connection connect = connectToDB.getConnection(fieldsBean.getDriverName(), url, fieldsBean.getLogin(), fieldsBean.getPass());
        if (connect != null) {
            clearDataFromTable.clear(connect);
            sendDataToDB.loadDataToDb(connect, fieldsBean.getN(), 500);
            createXml.creatXmlDoc(readDataFromDB.readDataFromDB(connect), fileNameCreateXml);

            try {
                connect.close();
                logger.info("Connection is closed..");
            } catch (SQLException e) {
                logger.error("Error close connection ", e);
            }
        }
        transformXml.transform(fileNameCreateXml, fileNameTransformedXml, fileNameTransformXsl);
        long sum = parser.parse(fileNameTransformedXml).parallelStream().reduce((l, p) -> l + p).orElse((long) 0);
        long resultTime = System.currentTimeMillis()-startTime;
        logger.info("Sum of all element is - " + sum);
        logger.info("Time of work program " + resultTime + "ms");
    }


}
