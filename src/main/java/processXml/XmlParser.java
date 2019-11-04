package processXml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class XmlParser {
    private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);

    public List<Long> parse(String filename) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        XmlHandler xmlHandler = new XmlHandler();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(filename, xmlHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Parse error.. ", e);
        }
        return xmlHandler.getData();
    }
}
