package processXml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class TransformXml {
    private static final Logger logger = LoggerFactory.getLogger(TransformXml.class);

     public boolean transform(String inFilename, String outFilename, String trasformFilename){
        boolean flag = false;
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(trasformFilename)));
            StreamSource streamSource = new StreamSource(new File(inFilename));
            StreamResult streamResult = new StreamResult(new File(outFilename));
            transformer.transform(streamSource,streamResult);
            flag = true;
            logger.info("Transform is done..");
        } catch (TransformerException e) {
            logger.error("Transform error.. ", e);
        }
        return flag;
    }
}
