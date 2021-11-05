package com.spotify.outh2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.outh2.api.Route.API;
import static com.spotify.outh2.api.Route.TOKEN;
import static com.spotify.outh2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response POST(String path,String token,Object payLoad)
    {

        return given().spec(getRequestSpec()).
                auth().oauth2(token).
                body(payLoad).
                when().
                post(path).
                then().spec(getResponseSpec()).
                log().all().extract().response();

    }
    public static Response POSTAccount(HashMap<String,String> formParams)
    {
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().
                post(API+TOKEN).
                then().
                log().all().extract().response();
    }
    public static Response GET(String path,String token)
    {

        return given().spec(getRequestSpec()).
                auth().oauth2(token).
                when().
                get(path).
                then().spec(getResponseSpec()).
                log().all().
                extract().response();

    }
    public static Response PUT(String path, String token,Object payLoad)
    {
        return given().spec(getRequestSpec()).
                auth().oauth2(token).
                body(payLoad).
                when().
                put(path).
                then().spec(getResponseSpec()).
                log().all().extract().response();
    }
}
