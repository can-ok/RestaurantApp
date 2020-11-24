package com.resturant.restapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoService {



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

        map.put("hConsole",hConsole.toString());
        map.put("dataSource",dataSource);
        map.put("Jpa",jpaInfo);
        map.put("loggingType",logginType);
        map.put("dataSourceName",dataSource);
        return map;
    }

}
