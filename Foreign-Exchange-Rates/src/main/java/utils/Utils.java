package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqspec;
	public static ResponseSpecification resspec;
	public static String env = getGlobalValue("env");
	
	public String getCurrrentTimeStamp()
	{

	    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
	    
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		return sdf.format(timestamp);	
	}
	
	public String getyesterdayDate()
	{
		 Instant now = Instant.now();
	     Instant yesterdayTS = now.minus(1, ChronoUnit.DAYS);
	     String yesterday = String.valueOf(yesterdayTS);
	     String[] strArray = yesterday.split("T");
			
		 return strArray[0];
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
	
	public String getJsonResponSeValue(Response response, String jpath)
	{
		  String resp=response.asString();
		  
		  JsonPath js = new JsonPath(resp);
		  
		  return js.get(jpath).toString();
	}

	public String getJsonResponSeValuesCount(Response response, String jpath)
	{
		  String resp=response.asString();
		  
		  JsonPath js = new JsonPath(resp);

		  int key = js.getInt(jpath);
				
		  return String.valueOf(key);	
	}
	
	
	public RequestSpecification requestSpecification()throws IOException
	{				
		String logsDir = "Application-Logs";
		new File(System.getProperty("user.dir")+"/target/"+logsDir).mkdir();
		String loggerFile = "Logs_"+getCurrrentTimeStamp()+".txt";
		
		PrintStream psLog =new PrintStream(new FileOutputStream("target/Application-Logs/"+loggerFile));
		
			reqspec = new RequestSpecBuilder().
					setBaseUri(getGlobalValue("baseuri")).
					addFilter(RequestLoggingFilter.logRequestTo(psLog)).
					addFilter(ResponseLoggingFilter.logResponseTo(psLog)).
					build();
			
		return reqspec;	
	}
	
	public ResponseSpecification responseSpecification(int code)
	{
		 
		resspec = new ResponseSpecBuilder().
				expectStatusCode(code).
				expectContentType(ContentType.JSON).build();
		
		 return resspec;	
	}

	

}
