package com.bazaarstores.utilities;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiUtilities {


    public static RequestSpecification spec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Bearer " + getToken())
                .build();
    }

    private static String getToken(){
        Map payload = new HashMap();
        payload.put("email" , ConfigReader.getAdminEmail());
        payload.put("password", ConfigReader.getDefaultPassword());

        Response response= RestAssured.given().body(payload).contentType(ContentType.JSON).post(ConfigReader.getApiBaseUrl()+"/login");
        return response.jsonPath().getString("authorisation.token");
    }

    public static String getCustomerToken(){
        Map payload = new HashMap();
        payload.put("email" , ConfigReader.getCustomerEmail());
        payload.put("password", ConfigReader.getDefaultPassword());

        Response response= RestAssured.given().body(payload).contentType(ContentType.JSON).post(ConfigReader.getApiBaseUrl()+"/login");
        return response.jsonPath().getString("authorisation.token");
    }

    public static String getStoreMangerToken(){
        Map payload = new HashMap();
        payload.put("email" , ConfigReader.getStoreManagerEmail());
        payload.put("password", ConfigReader.getDefaultPassword());

        Response response= RestAssured.given().body(payload).contentType(ContentType.JSON).post(ConfigReader.getApiBaseUrl()+"/login");
        return response.jsonPath().getString("authorisation.token");
    }

    public static RequestSpecification customerSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Bearer " + getCustomerToken())
                .build();
    }

    public static RequestSpecification managerSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept","application/json")
                .addHeader("Authorization","Bearer " + getStoreMangerToken())
                .build();
    }

}
