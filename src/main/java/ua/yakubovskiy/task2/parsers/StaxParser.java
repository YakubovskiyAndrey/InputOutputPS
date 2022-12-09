package ua.yakubovskiy.task2.parsers;

import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.entity.TypeViolation;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {

    private final XMLInputFactory factory = XMLInputFactory.newInstance();

    public List<TrafficViolation> parse(String urlInput){
        TrafficViolation currViolation = null;
        List<TrafficViolation> trafficViolationList = null;
        String tagContent = null;
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

        try {
            XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream(urlInput));
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("violations".equals(reader.getLocalName())) {
                            trafficViolationList = new ArrayList<>();
                        }
                        if ("violation".equals(reader.getLocalName())) {
                            currViolation = new TrafficViolation();
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "violation":
                                if (trafficViolationList != null) {
                                    trafficViolationList.add(currViolation);
                                }
                                break;
                            case "fine_amount":
                                if (currViolation != null && tagContent != null) {
                                    currViolation.setFineAmount(Double.parseDouble(tagContent));
                                }
                                break;
                            case "type":
                                if (currViolation != null && tagContent != null) {
                                    currViolation.setType(TypeViolation.valueOf(tagContent));
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLStreamConstants.START_DOCUMENT:
                        trafficViolationList = new ArrayList<>();
                        break;
                    default:
                        break;
                }
            }
        } catch (XMLStreamException ex){
            throw new RuntimeException(ex.getMessage());
        }
        return trafficViolationList;
    }
}
