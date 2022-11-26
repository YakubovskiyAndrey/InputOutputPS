package ua.yakubovskiy.task2.parsers;

import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.entity.TypeViolation;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class StaxParser {

    public void parse(String urlInput, List<TrafficViolation> trafficViolationList) throws XMLStreamException {
        TrafficViolation currViolation = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        XMLStreamReader reader =
                factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream(urlInput));

        while(reader.hasNext()){
            int event = reader.next();
            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("violation".equals(reader.getLocalName())){
                        currViolation = new TrafficViolation();
                        currViolation.setId(Integer.parseInt(reader.getAttributeValue(0)));
                    }
                    /*if("violations".equals(reader.getLocalName())){
                        trafficViolationList = new ArrayList<>();
                    }*/
                    break;

                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch(reader.getLocalName()){
                        case "violation":
                                trafficViolationList.add(currViolation);
                            break;
                        case "date_time":
                            if (currViolation != null && tagContent != null) {
                                currViolation.setDateTime(LocalDateTime.parse(tagContent));
                            }
                            break;
                        case "first_name":
                            if (currViolation != null && tagContent != null) {
                                currViolation.setFirstName(tagContent);
                            }
                            break;
                        case "last_name":
                            if (currViolation != null && tagContent != null) {
                                currViolation.setLastName(tagContent);
                            }
                            break;
                        case "fine_amount":
                            if (currViolation != null && tagContent != null) {
                                currViolation.setFineAmount(Double.parseDouble(tagContent));
                            }
                            break;
                        case "type":
                            if(currViolation != null && tagContent != null) {
                                currViolation.setType(TypeViolation.valueOf(tagContent));
                            }
                            break;
                        default:
                            break;
                    }
                    break;

               /* case XMLStreamConstants.START_DOCUMENT:
                    trafficViolationList = new ArrayList<>();
                    break;*/
                default:
                    break;
            }
        }
    }

}
