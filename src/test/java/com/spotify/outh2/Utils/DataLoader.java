package com.spotify.outh2.Utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;
    private DataLoader()
    {
        properties=PropertyUtil.propertyLoader("src/test/resources/Data.properties");
    }
    public static DataLoader getInstance()
    {
        if(dataLoader==null)
        {
             dataLoader=new DataLoader();
        }
        return dataLoader;
    }
    public String getUserId()
    {
        String prop=properties.getProperty("get_user_id");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("get_user_id property is not available !!");
        }
    }
    public String updateUserId()
    {
        String prop=properties.getProperty("update_user_id");
        if(prop!=null)
        {
            return prop;
        }
        else
        {
            throw new RuntimeException("update_user_id property is not available !!");
        }
    }
}
