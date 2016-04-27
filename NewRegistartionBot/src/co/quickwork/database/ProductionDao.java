package co.quickwork.database;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

//import com.sun.security.ntlm.Client;

public class ProductionDao {

	Connection Mcon = new Connection();
	protected DB dBase = Mcon.productionConn();
	protected DBCollection userTable = dBase.getCollection("users");
	
	protected DBCollection personalDetailsTable = dBase
			.getCollection("personalDetails");
	
	protected DBCollection vf_goalsTable = dBase.getCollection("vf_goals");
	
	protected DBCollection vf_goal_trackTable = dBase
			.getCollection("vf_goal_track");
	
	protected DBCollection companyTable = dBase.getCollection("company");
	
	protected DBCollection company_teamTable = dBase
			.getCollection("company_team");
	
	protected DBCollection opsUserTable = dBase.getCollection("ops_users");
	
	protected DBCollection degreeTable = dBase.getCollection("degrees");
	
	protected DBCollection locationsTable = dBase.getCollection("locations");
	
	protected DBCollection applicationTable = dBase
			.getCollection("applications");
	
	
	public String getStatisticsTableHeaders() throws UnknownHostException {

	//	DBCollection coll = dBase.getCollection("vf_goal_track");

		BasicDBObject basicDBObject = new BasicDBObject();

		DBCursor cursor = personalDetailsTable.find(basicDBObject);

		String ret = "";
		Set<String> keys = null;
		keys = cursor.next().keySet();
		ret = keys.toString();

		return ret;
	}
	
	
	public String getMobileUsingUserMail(String userMail)
	{
		String mobile = userTable.findOne(new BasicDBObject("membermail", userMail)).get("mobile").toString();
		return mobile;
	}
	
	
	public String getUserNameUsingMobile(String mobile)
	{
		String userName = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("name").toString();
		return userName;
	}
	
	public String getUserYOPUsingMobile(String mobile)
	{
		String userYOP = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("personYOP").toString();
		return userYOP;
	}
	
	public String getUserYOPUsingMobileEdited(String mobile)
	{
		String userYOP = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("yearOfPassing").toString();
		return userYOP;
	}
	
	
	public String getUserGenderUsingMobile(String mobile)
	{
		String gender = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("gender").toString();
		return gender;
	}
	
	public String getUserDIDUsingMobile(String mobile)
	{
		String userDID = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("did").toString();
		return userDID;
	}
	
	public String getUserDegreeUsingDID(String did)
	{
		String userDegree = degreeTable.findOne(new BasicDBObject("_id",new ObjectId(did))).get("degreeName").toString();
		return userDegree;
	}
	
	public String getLIDUsingMobile(String mobile)
	{
		String userLID = personalDetailsTable.findOne(new BasicDBObject("mobile", mobile)).get("lid").toString();
		return userLID;
	}
	
	
	public String getLocationUsingLID(String LId)
	{
		String location = locationsTable.findOne(new BasicDBObject("_id", new ObjectId(LId))).get("Area").toString();
		return location;
	}
	

	public String getLIDUsingLocation(String location)
	{
		String LID = locationsTable.findOne(new BasicDBObject("Area", location)).get("_id").toString();
		return LID;
	}
	
	public void  updateUserLocation(String mobile, String lid, String number)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		if(number.equals("1"))
		{
		objDb2.put("lid", lid);
		}
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}

	public void  updateUserName(String mobile, String userName)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		objDb2.put("name", userName);
		
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}
	
	public void  updateUserLocation(String mobile, String number)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		if(number.equals("1"))
		{
			String lID = getLIDUsingLocation("Mumbai");
		    objDb2.put("lid", lID);
		}
		
		
		if(number.equals("2"))
		{
			String lID = getLIDUsingLocation("Navi Mumbai");
		   objDb2.put("lid", lID);
		}
		
		
		if(number.equals("3"))
		{
			String lID = getLIDUsingLocation("Pune");
		   objDb2.put("lid", lID);
		}
		
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}
	
	public String getDIDUsingDegree(String degree)
	{
		String DID = degreeTable.findOne(new BasicDBObject("degreeName", degree)).get("_id").toString();
		return DID;
	}
	
	public void  updateUserDegree(String mobile , String number)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		try{
			
		
		if(number.equals("1"))
		{
			String DID = getDIDUsingDegree("B.Tech"); // changes required during production
		    objDb2.put("did", DID);
		}
		
		
//		if(number.equals("2"))
//		{
//			String DID = getDIDUsingDegree("B.E");
//		    objDb2.put("did", DID);
//		}
//		
		
		if(number.equals("2"))
		{
		   String lID = getDIDUsingDegree("B.A");
		   objDb2.put("lid", lID);
		}
		
		
		if(number.equals("3"))
		{
			String lID = getDIDUsingDegree("B.Com");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("4"))
		{
			String lID = getDIDUsingDegree("B.E");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("5"))
		{
			String lID = getDIDUsingDegree("B.Sc");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("6"))
		{
			String lID = getDIDUsingDegree("BCA");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("7"))
		{
			String lID = getDIDUsingDegree("BBA");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("8"))
		{
			String lID = getDIDUsingDegree("BAF");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("9"))
		{
			String lID = getDIDUsingDegree("BMS");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("10"))
		{
			String lID = getDIDUsingDegree("MCA");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("11"))
		{
			String lID = getDIDUsingDegree("M.Tech");
		   objDb2.put("lid", lID);
		}
		
		if(number.equals("12"))
		{
			String lID = getDIDUsingDegree("HSC");
		   objDb2.put("lid", lID);
		}
		
		}
		catch(NullPointerException e)
		{
			System.out.println();
		}
		
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}
	
	public void  updateGender(String mobile, String number)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		if(number.equals("1"))
		{
			objDb2.put("gender", "male");
		}
		
		
		if(number.equals("2"))
		{
		    objDb2.put("gender", "female");
		}
		
		
		if(number.equals("3"))
		{
			 objDb2.put("gender", "other");
		}
		
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}

	public void  updateUserYOP(String mobile, String number)
	{
		BasicDBObject objDb1 = new BasicDBObject();
		objDb1.put("mobile", mobile);
		
		BasicDBObject objDb2 = new BasicDBObject();
		if(number.equals("1"))
		{
			objDb2.put("yearOfPassing","2013");
		}
		
		if(number.equals("2"))
		{
		    objDb2.put("yearOfPassing", "2014");
		}
		
		
		if(number.equals("3"))
		{
			objDb2.put("yearOfPassing", "2015");
		}
		
		if(number.equals("4"))
		{
			objDb2.put("yearOfPassing", "2016");
		}
		
		
		BasicDBObject updateDBobj = new BasicDBObject();
		updateDBobj.put("$set", objDb2);
		personalDetailsTable.update(objDb1, updateDBobj);
	}
	
	public static void main(String[] args) throws UnknownHostException {
		ProductionDao objProductionDao = new ProductionDao();
		System.out.println(objProductionDao.getDIDUsingDegree("B. Tech."));
	}

}
