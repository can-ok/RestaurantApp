package com.resturant.restapi.service;

import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoService {

    @Value("${spring.message}")
    private String message;

    @Value("${spring.h2.console.enabled}")
    private Boolean hConsole;

    @Value("${spring.datasource.url}")
    private String dataSource;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String jpaInfo;


    @Value("${logging.level.org.hibernate.type}")
    private String logginType;

    @Value("${spring.datasource.username}")
    private String dataSourceName;


    public Map<String,String> getConfig(){

        HashMap<String, String> map = new HashMap<>();
        map.put("message",message);
        map.put("hConsole",hConsole.toString());
        map.put("dataSource",dataSource);
        map.put("Jpa",jpaInfo);
        map.put("loggingType",logginType);
        map.put("dataSourceName",dataSource);
        return map;
    }


    @Autowired
    ApplicationContext context;

    public List<String> getBeans() {
        List<String> stringList=new ArrayList<>();
        String[] allBeans=context.getBeanDefinitionNames();

        for(String bean:allBeans){
           stringList.add(bean);
        }
        return stringList;
    }

}
