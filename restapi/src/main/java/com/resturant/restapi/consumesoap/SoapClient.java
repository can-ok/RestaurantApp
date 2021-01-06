package com.resturant.restapi.consumesoap;

import com.resturant.restapi.stub.AddCustomerResponse;
import com.resturant.restapi.stub.GetAllCustomer;
import com.resturant.restapi.stub.GetAllCustomerResponse;
import com.resturant.restapi.stub.GetCustomerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class SoapClient extends WebServiceGatewaySupport{

    public GetCustomerResponse getCustomer(String url, Object request){
        JAXBElement res=(JAXBElement)getWebServiceTemplate().marshalSendAndReceive(url,request);
        return (GetCustomerResponse) res.getValue();
    }


    public GetAllCustomerResponse getAllCustomer(String url,Object request){
        JAXBElement res=(JAXBElement)getWebServiceTemplate().marshalSendAndReceive(url,request);
        return (GetAllCustomerResponse) res.getValue();
    }


    public GetAllCustomerResponse getAllCustomer2(String url, GetAllCustomer request){
        GetAllCustomerResponse getAllCustomer= (GetAllCustomerResponse) getWebServiceTemplate().marshalSendAndReceive(url,request);
        return getAllCustomer;
    }


    public AddCustomerResponse addCustomer(String url,Object request){
        JAXBElement res=(JAXBElement)getWebServiceTemplate().marshalSendAndReceive(url,request);
        return  (AddCustomerResponse)res.getValue();
    }
}
