package co.quickwork.database;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mysql.fabric.xmlrpc.base.Data;

import QuickWorkProperties.QuickWorkPropertyGiver;

public class Database {

	Connection Mcon = new Connection();
	protected DB dBase = Mcon.conn();
	
	DBCollection userDetails = dBase.getCollection("UserDetails");
	DBCollection edit = dBase.getCollection("EditNumbers");
	
	//checked
	public void insertData(String context)
	{
		BasicDBObject basicDBObject= new BasicDBObject();
		basicDBObject.put("context", context);
		basicDBObject.put("counter", 0);
		basicDBObject.put("checkFlag", 1);
		userDetails.insert(basicDBObject);
	}
	//checked
	public void insertEditedNumber(String context)
	{
		BasicDBObject basicDBObject= new BasicDBObject();
		basicDBObject.put("context", context);
		basicDBObject.put("num", 0);
		edit.insert(basicDBObject);
	}
	
	//checked
	public void  updateData(String context)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", getCount(context)+1);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public void updateCounter(String context,int no)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", no);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);		
	}
	
	//checked
	public void  decreaseCounter(String context)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("counter", getCount(context)-1);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public int getCount(String context)
	{
		return (int) userDetails.findOne(new BasicDBObject("context", context)).get("counter");
	}
	
	//checked
	public boolean checkUserExist(String context)
	{
	 try{
		    int value=(int) userDetails.findOne(new BasicDBObject("context", context)).get("counter");
			return true;
			
		}catch(NullPointerException e){
			return false;
		}		
	}
	
	//checked
	public void addName(String name,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Name", name);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
		
	}

	
	//checked
	public void addAlternate(String altmob,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("AltMOB", altmob);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public void emailID(String email,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Email", email);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public void updateEditedNumber(String context,int number)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("num", number);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		edit.update(objDb1, updateDBobj);
	}
	
	public int getEditedNumber(String context)
	{
		return (int) edit.findOne(new BasicDBObject("context", context)).get("num");
	}

	
	//checked
	public void careerInterest(String interest,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		if(interest.equals("1"))
		{
		objDb2.put("careerInterest", "Sales, Business Development & Marketing");
		}
		if(interest.equals("2"))
		{
			objDb2.put("careerInterest", "Operations - Back office, Front office");
		}
		if(interest.equals("3"))
		{
			objDb2.put("careerInterest", "Digital & Social Media Marketing");
		}
		if(interest.equals("4"))
		{
			objDb2.put("careerInterest", "Software Development - Web/Mobile");
		}
		if(interest.equals("5"))
		{
		objDb2.put("careerInterest", "Graphic Designer");
		}
		if(interest.equals("6"))
		{
		 objDb2.put("careerInterest", "Do Not Know Yet");
		}
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public void availability(String available,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("Availability", available);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	//checked
	public void updateCheckFlag(int flag,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("checkFlag", flag);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	
	//checked
	public void startInternshipDate(String date,String context)
	{
		BasicDBObject objDb1= new BasicDBObject();
		objDb1.put("context", context);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("AvailabilityDate", date);
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		userDetails.update(objDb1, updateDBobj);
	}
	
	
	public void dropTable() throws UnknownHostException
	{
		MongoClient mongoClient = new MongoClient("52.33.177.69",27017) ;
		DB db=mongoClient.getDB("dummy7");
		db.dropDatabase();
		System.out.println("db dropped");
	}
	
	//checked
	public String getAltNo(String context){
		String name;
		try{
			name= userDetails.findOne(new BasicDBObject("context",context)).get("AltMOB").toString();
		}
		catch(NullPointerException e)
		{
			name="3";
		}
		return name; 
			
	}
	
	//checked
	public String getEmail(String context){
		
		String name; 
		try{
			name= userDetails.findOne(new BasicDBObject("context",context)).get("Email").toString();
		}
		catch(NullPointerException e)
		{
			name="4";
		}
		return name; 
			
	}

	//checked
	public String getCareerInterest(String context){
		
		String name;
		try{
			name= userDetails.findOne(new BasicDBObject("context",context)).get("careerInterest").toString();
		}
		catch(NullPointerException e)
		{
			name="7";
		}
		return name; 
			
	}
	
	//checked
	public String getAvailability(String context){
		String name;
		try{
			name= userDetails.findOne(new BasicDBObject("context",context)).get("Availability").toString();
		}
		catch(NullPointerException e)
		{
			name="8";
		}
		return name; 
			
	}
	
	//checked
	public String getStartInternshipDate(String context){
		String date=null;
		try{
		date = userDetails.findOne(new BasicDBObject("context",context)).get("AvailabilityDate").toString();
		
		}
		catch(NullPointerException e)
		{
			date= "9";
		}
		return date;	
	}
	
	//checked
	public int getCheckFlag(String context)
	{
		int flag = (int) userDetails.findOne(new BasicDBObject("context",context)).get("checkFlag");
		return flag; 
	}

}