package com.resturant.restapi.consumesoap;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BeanConfig {

    @Bean
    public Jaxb2Marshaller marshaller()
    {
        Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
        marshaller.setContextPath("com.resturant.restapi.stub");
        return marshaller;
    }

    @Bean
    public SoapClient soapConnector(Jaxb2Marshaller marshaller){
        SoapClient client=new SoapClient();
        client.setDefaultUri("http://localhost:8086/CustomerSrv/services/CustomerService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
