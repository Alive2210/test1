package processXml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class CreateXml {
    private static final Logger logger = LoggerFactory.getLogger(CreateXml.class);

    public void creatXmlDoc(List<Long> data,String filename) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("entries");
            doc.appendChild(root);
            if (!data.isEmpty()) {
                data.forEach(n -> {
                    Element element = doc.createElement("entry");
                    Element child = doc.createElement("field");
                    root.appendChild(element);
                    element.appendChild(child);
                    child.setTextContent(n + " field");
                });
            } else {
                logger.info("data is empty");
            }
            assert false;
            File file = new File(filename);
            try {
                Transformer transform = TransformerFactory.newInstance().newTransformer();
                transform.setOutputProperty("indent", "yes");
                transform.transform(new DOMSource(doc), new StreamResult(file));
                logger.info("xml file is build");
            } catch (TransformerException e) {
                logger.error("Error occurred during the transformation process.. ", e);
            }
        } catch (ParserConfigurationException e) {
            logger.error("Serious configuration error.. ", e);
        }

    }
}
