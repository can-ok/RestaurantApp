package com.resturant.restapi.consumesoap;

import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.stub.GetCustomerResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class SoapConverter {

    public static String dtoToXMLADD(CustomerDto customerDto) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDto.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty("jaxb.fragment", Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(customerDto, stringWriter);

        String soapEnvelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://model.ba.com/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:addCustomer>\n" +
                stringWriter.toString() +
                "      </ser:addCustomer>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return soapEnvelope;
    }


    public static String dtoToXMLGET(CustomerDto customerDto) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDto.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty("jaxb.fragment", Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(customerDto, stringWriter);

        String soapEnvelope = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://model.ba.com/xsd\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:getCustomer>\n" +
                stringWriter.toString() +
                "      </ser:getCustomer>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return soapEnvelope;
    }



    public static CustomerDto xmlToDTO(String xml) throws JAXBException, SOAPException, IOException {

        final InputStream inputStream = new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));
        final SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);
        final SOAPPart sp = message.getSOAPPart();
        final SOAPEnvelope env = sp.getEnvelope();
        final SOAPBody body = env.getBody();
        final JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDto.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (CustomerDto) jaxbUnmarshaller.unmarshal(body.extractContentAsDocument());
    }



    public static GetCustomerResponse xmlToGetCustomer(String xml) throws JAXBException, SOAPException, IOException {

        final InputStream inputStream = new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8")));
        final SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);
        final SOAPPart sp = message.getSOAPPart();
        final SOAPEnvelope env = sp.getEnvelope();
        final SOAPBody body = env.getBody();
        final JAXBContext jaxbContext = JAXBContext.newInstance(GetCustomerResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (GetCustomerResponse) jaxbUnmarshaller.unmarshal(body.extractContentAsDocument());
    }

}
