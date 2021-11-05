package com.spotify.outh2.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertyUtil {
    public static Properties propertyLoader(String filePath)
    {
        Properties properties=new Properties();
        BufferedReader reader;
        try
        {
            reader=new BufferedReader(new FileReader(filePath));
            try
            {
                properties.load(reader);
                reader.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file "+filePath);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file "+filePath);
        }
        return properties;
    }
}
