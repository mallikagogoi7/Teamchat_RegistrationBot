package co.quickwork.service;

import org.json.JSONException;
import org.json.JSONObject;

public class Extra {
	
	//checked
	public String getChannelFromContext(String context) throws JSONException
	   {	
		JSONObject jsonObj = new JSONObject(context);
	     String name = jsonObj.getString("channel");
	     System.out.println(name);
	     return name;
		}
	
	public String getRoomIdFromContext(String context) throws JSONException
	   {	
		JSONObject jsonObj = new JSONObject(context);
	     String name = jsonObj.getString("contextid");
	     System.out.println(name);
	     return name;
		}

}
