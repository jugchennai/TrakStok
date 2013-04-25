package in.jugchennai.javamoney.jpa.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public List<Cube> readConfig(String configFile) {
		List<Cube> cubes = new ArrayList<Cube>();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(configFile);
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
										.equals(CURRENCY)) {
									cube.setCurrency(attribute.getValue());
								}

							}
						}
					}

					
				}else{
					if (event.isEndElement()) {
						EndElement endElement = event.asEndElement();
						if (endElement.getName().getLocalPart() == (CUBE)) {
							cubes.add(cube);
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return cubes;
	}

}