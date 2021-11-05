package com.spotify.outh2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.outh2.api.Route.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
                setBasePath(BASE_PATH).
                addFilter(new AllureRestAssured()).
                setContentType("application/json").log(LogDetail.ALL).build();

    }
    public static RequestSpecification getAccountRequestSpec()
    {
        return new RequestSpecBuilder().
                //setBaseUri("https://accounts.spotify.com").
                setBaseUri(System.getProperty("ACCOUNTS_BASE_URI")).
                addFilter(new AllureRestAssured()).
                setContentType(ContentType.URLENC).log(LogDetail.ALL).build();

    }
    public static ResponseSpecification getResponseSpec()
    {
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }
}
