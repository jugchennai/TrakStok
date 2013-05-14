package in.jugchennai.javamoney.jpa.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaXParser {

    static final String CUBE = "Cube";
    static final String DATE = "time";
    static final String RATE = "rate";
    static final String CURRENCY = "currency";

    @SuppressWarnings("unchecked")
    public List<Cube> readConfig(String configFile) throws MalformedURLException, IOException {
        List<Cube> cubes = new ArrayList<>();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            URL url = new URL(configFile);
            InputStream in = url.openStream();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Cube cube = null;
            String tempDate = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                //System.out.println(event);
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals(CUBE)) {

                        Iterator<Attribute> attributes = startElement
                                .getAttributes();

                        while (attributes.hasNext()) {

                            Attribute attribute = attributes.next();

                            if (attribute.getName().toString().equals(DATE)) {

                                tempDate = attribute.getValue();

                            } else {

                                if (attribute.getName().toString().equals(RATE)) {
                                    cube = new Cube();
                                    cube.setDate(tempDate);
                                    cube.setRate(attribute.getValue());
                                } else if (attribute.getName().toString()
                                        .equals(CURRENCY) && attribute.getValue() != null) {
                                    cube.setCurrency(attribute.getValue());
                                }

                            }
                        }
                    }


                } else {
                    if (event.isEndElement()) {
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equals(CUBE)) {
                            cubes.add(cube);
                        }
                    }
                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            Logger.getLogger(StaXParser.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return cubes;
    }
}