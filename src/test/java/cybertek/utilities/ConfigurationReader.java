package cybertek.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {

    private ConfigurationReader(){}

    private static Properties properties;


    static{

        try {

            // getting the file name to read
            String path = "configuration.properties";

            //storing the file into Java memory
            FileInputStream input = new FileInputStream(path);

            //creating an object of properties class
            properties = new Properties();

            //load the information from configuration.properties file into properties object
            //properties object stores the elements is key=value format
            properties.load(input);

            //closing the file from Java memory
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //creating a method to get values from properties object by their key

    public static String get(String key){
        return properties.getProperty(key);
    }




}
