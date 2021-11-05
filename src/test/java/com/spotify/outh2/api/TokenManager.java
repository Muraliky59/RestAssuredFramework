package com.spotify.outh2.api;

import com.spotify.outh2.Utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.outh2.api.RestResource.POSTAccount;

public class TokenManager {
    static String accesstoken;
    static Instant expiryTime;
    static int expiryLimit;
    public synchronized static String getToken()
    {
        try
        {
            if(accesstoken==null||Instant.now().isAfter(expiryTime))
            {

                Response response=renewToken();
                System.out.println("Token is renewed");
                expiryLimit=response.path("expires_in");
                expiryTime=Instant.now().plusSeconds(expiryLimit);
                accesstoken=response.path("access_token");
            }
            else
            {
                System.out.println("Token is good to go!!");
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException("ABORT!! token renewal failed.");
        }

        return accesstoken;
    }
    private static Response renewToken()
    {
        HashMap<String,String> formParams=new HashMap<>();
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id",ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        Response response= POSTAccount(formParams);
        if(response.statusCode()!=200)
        {
            throw new RuntimeException("ABORT!! token renewal failed.");
        }
        return response;
    }
}
