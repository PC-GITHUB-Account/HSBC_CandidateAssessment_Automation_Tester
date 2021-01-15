package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Utils {
	
	public String getCurrrentTimeStamp()
	{

	    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
	    
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return sdf.format(timestamp);	
	}

	public static String getGlobalValue(String key)
	{	
		Properties prop = null;
		
		try
		{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalProperties.properties");
		prop.load(fis);	
		}
		catch (IOException e){	
		e.printStackTrace();
		System.out.println("Unable to read property file");
		}
		return prop.getProperty(key);
	}
}
