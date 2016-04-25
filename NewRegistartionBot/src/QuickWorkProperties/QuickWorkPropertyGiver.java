package QuickWorkProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QuickWorkPropertyGiver {

 public Properties prop;
 
 public QuickWorkPropertyGiver(){
	 setPropValues();
 }
	
	public void setPropValues() {
	
		prop = new Properties();
		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		InputStream propertiesStream = contextClassLoader.getResourceAsStream("QuickWorkProperties/QuickWork.properties");
		if (propertiesStream != null) {
		  try {
			  prop.load(propertiesStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		  
		} else {
			
			System.out.println("Properties file not found!");
		  
		}
	}
	
	public String getPropValue(String key)
	{
		return prop.getProperty(key);
	}

}
