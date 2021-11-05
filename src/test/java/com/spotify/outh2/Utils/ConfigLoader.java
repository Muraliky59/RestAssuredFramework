package com.spotify.outh2.Utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static  ConfigLoader configLoader;
    private ConfigLoader()
    {
        properties=PropertyUtil.propertyLoader("src/test/resources/Config.properties");
    }
    public static ConfigLoader getInstance()
    {
        if(configLoader==null)
        {
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }
    public String getClientId()
    {
        String prop=properties.getProperty("client_id");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("client_id property is not available !!");
        }
    }
    public String getClientSecret()
    {
        String prop=properties.getProperty("client_secret");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("client_secret property is not available !!");
        }
    }
    public String getRefreshToken()
    {
        String prop=properties.getProperty("refresh_token");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("refresh_token property is not available !!");
        }
    }
    public String getGrantType()
    {
        String prop=properties.getProperty("grant_type");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("grant_type property is not available !!");
        }
    }
    public String getUserId()
    {
        String prop=properties.getProperty("user_id");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("user_id property is not available !!");
        }
    }
}
