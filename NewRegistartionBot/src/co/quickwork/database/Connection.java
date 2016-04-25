package co.quickwork.database;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import QuickWorkProperties.QuickWorkPropertyGiver;

public class Connection {
	DB dBase;
	public MongoClient CLIENT ;
	public DB conn()
	{
		try{
	    CLIENT = new MongoClient("52.33.177.69",27017) ;
        dBase = CLIENT.getDB("OmniChannelDatabase"); 
        }catch(Exception E){
	      E.printStackTrace();
        }
        return dBase;  	
	}
	
	public DB productionConn()
	{
		try{
		CLIENT = new MongoClient("52.33.177.69",27017) ;
        dBase = CLIENT.getDB("qw_v21"); 
        }catch(Exception E){
	      E.printStackTrace();
        }
        return dBase;  	
	}

}
