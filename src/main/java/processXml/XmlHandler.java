package processXml;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

class XmlHandler extends DefaultHandler {
    private List<Long> data = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String atr = attributes.getValue("field");
        if (atr != null) {
            data.add(Long.valueOf(atr.substring(0, (atr.indexOf(" ")))));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
    }

    List<Long> getData() {
        return data;
    }
}
