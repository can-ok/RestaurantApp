package com.resturant.restapi.service;

import com.resturant.restapi.consumesoap.SoapClient;
import com.resturant.restapi.consumesoap.SoapConverter;
import com.resturant.restapi.dto.CustomerDto;
import com.resturant.restapi.stub.Customer;
import com.resturant.restapi.stub.GetAllCustomerResponse;
import com.resturant.restapi.stub.ObjectFactory;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;



@Service
public class CustomerService {

//    @Autowired
//    CustomerRepository customerRepository;
//
//    @Autowired
//    CustomerMapper customerMapper;





    @Autowired
    private SoapClient soapClient;


//    public Customer getCustomer(int id){
//
//        ObjectFactory objectFactory=new ObjectFactory();
//        Customer value=new Customer();
//        value.setId(id);
//        value.setFirstName(objectFactory.createCustomerFirstName(""));
//        value.setLastName(objectFactory.createCustomerLastName(""));
//        value.setAddress(objectFactory.createCustomerAddress("x"));
//        value.setCity(objectFactory.createCustomerAddress("x"));
//        value.setPhoneNumber(objectFactory.createCustomerPhoneNumber("53"));
//
//        GetCustomerResponse response=soapClient.getCustomer("http://localhost:8080/CustomerSrv/services/CustomerService",
//                objectFactory.createGetCustomerCustomer(value));
//        return response.getReturn().getValue();
//
//    }





    public List<Customer> getAllCustomers(int pageCount, int pageSize){

        ObjectFactory objectFactory=new ObjectFactory();

        GetAllCustomerResponse response=soapClient.getAllCustomer2("http://localhost:8086/CustomerSrv/services/CustomerService",
                objectFactory.createGetAllCustomer());
        return response.getReturn();

    }

    public Customer getCustomer(int id){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(id);

        try {
            RequestBody body = RequestBody.create(mediaType,SoapConverter.dtoToXMLGET(customerDto));
            Request request = new Request.Builder()
                    .url("http://localhost:8086/CustomerSrv/services/CustomerService?wsdl/getCustomer")
                    .method("POST", body)
                    .addHeader("Content-Type", "text/plain")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            return SoapConverter.xmlToGetCustomer(responseBody).getReturn().getValue();

        } catch (JAXBException | SOAPException | IOException e) {
            e.printStackTrace();
            return null;
        }


    }


    public String addCustomer(CustomerDto customerDto){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        try {
            RequestBody body = RequestBody.create(mediaType, SoapConverter.dtoToXMLADD(customerDto));
            Request request = new Request.Builder()
                    .url("http://localhost:8086/CustomerSrv/services/CustomerService?wsdl/addCustomer")
                    .method("POST", body)
                    .addHeader("Content-Type", "text/plain")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
            return responseBody;

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }









}
