package co.quickwork.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;

import co.quickwork.database.Database;
import co.quickwork.database.ProductionDao;

public class Validation {

	Database database=new Database();
	ProductionDao dao= new ProductionDao();
	// changes Done
		public boolean FiveDigitValidation(String message) {
			boolean validate = false;
			if((message.equals("0"))||(message.equals("1"))||(message.equals("2"))||(message.equals("3"))||(message.equals("4"))||(message.equals("5")))
			{
				validate=true;
			}
			return validate;
		}
		
		public boolean ThreeDigitValidation(String message) {
			boolean validate = false;
			if((message.equals("1"))||(message.equals("2"))||(message.equals("3")))
			{
				validate=true;
			}
			return validate;
		}
		
		public boolean FourDigitValidation(String message) {
			boolean validate = false;
			if((message.equals("1"))||(message.equals("2"))||(message.equals("3"))||(message.equals("4")))
			{
				validate=true;
			}
			return validate;
		}
		
		public boolean SixDigitValidation(String message)
		{
			boolean valid=false;
			  for(int i=1;i<=6;i++)
			  {
				  if(Integer.parseInt(message)==i)
				  {
					  valid=true;
					  break;
				  }
				  else
				  {
					  valid=false;
				  }
			  }
			  return valid;  
		}
		//checked
	 public  boolean isValidDate(String date)
	 {
		 boolean validity=false;
		 Calendar current = Calendar.getInstance();
		 current.add(Calendar.DATE, -1);
		
		    String myFormatString = "dd/MM/yyyy";
		    SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		    Date givenDate;
			try {
				givenDate = df.parse(date);
				 Long l = givenDate.getTime();
				 Date next = new Date(l);
				 if(next.after(current.getTime()) || (next.equals(current.getTime()))){
				        validity=true;
				    } else {
				        validity=false;
				    }
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(validity);
		return validity;	 
	 }
	 
	 //checked
	 public boolean isValidEmail(String mail)
	 {
		 String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	     Boolean b = mail.matches(EMAIL_REGEX);
	     System.out.println(b);
	     return b;
	 }
	 
	 public boolean isValidAvaliabilitity(String message)
	 {
		 boolean valid=false;
		 if(message.equalsIgnoreCase("y")||message.equalsIgnoreCase("n")||message.equalsIgnoreCase("yes")||message.equalsIgnoreCase("no"))
		 {
			 valid=true;
		 }
		 else
		 {
			 valid=false;
		 }
		 System.out.println(valid);
		 return valid;
	 }
	 
	 //checked
	 public boolean phoneValidation(String moNo){
			
			String regex ="^(?:(\\d{0,9})[\\s-]?)?(\\d{6})$";
			
			long checkNo = 0;
			long validNo = 0;
			boolean isMobileNoValid = false;
		    Validation objPhoneValidation = new Validation();	
		
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(moNo);
			if (m.matches())
			{
			   checkNo = Integer.parseInt(m.group(1));;
			   for(int i = 7000 ; i<=9999 ;++i){
					  
					if(checkNo == i){
					  
					System.out.println("No Is Valid."+i);
						isMobileNoValid = true;
						break;
					}
					
					else{
				       isMobileNoValid = false;
						}					
			     }			   
		     }
			System.out.println(isMobileNoValid);
			return isMobileNoValid;
			}
	 
	 public int getAltNumberValidation(String message,String userMail)
	 {
		 String llRegexwith91 ="^(?:(\\d{0,9})[\\s-]?)?(\\d{10})$";
		 String llRegexwith0 ="^(?:(\\d{0,9})[\\s-]?)?(\\d{9})$";
		 String mobRegex ="^(?:(\\d{0,9})[\\s-]?)?(\\d{6})$";
		 String firstDigit = ((message).substring(0, 1));	
		 int numberLength=message.length();
		 long checkNo = 0;
	     int flag=0;
	     if(message.matches(".*\\d.*")){
	    	 
	     
		 if(Integer.parseInt(firstDigit)==9&& numberLength==12)
		 {
			 System.out.println("llRegexwith91");
			 Pattern p = Pattern.compile(llRegexwith91);
				Matcher m = p.matcher(message);
				if (m.matches())
				{	
					 checkNo = Integer.parseInt(m.group(1));;
					 int thirdDigit = Integer.parseInt((message).substring(2,3));	
					 System.out.println("thirdDigit"+thirdDigit);
					 if(checkNo==91&&thirdDigit!=0)
					 {
						 flag=2;    //matched with regex	
					 }
					 else{
						 flag=3;  // invalid number
					     }
				}
				else
				{
					flag=3;  // invalid number
				}
		 }
		 
		 else if(((Integer.parseInt(firstDigit)==9)||(Integer.parseInt(firstDigit)==8)||(Integer.parseInt(firstDigit)==7))&& numberLength==10)
		 {
			 System.out.println("mobRegex");
			 Pattern p = Pattern.compile(mobRegex);
				Matcher m = p.matcher(message);
				if (m.matches())
				{	
					
					System.out.println(m);
					//String mail=dao.g
					if(message.equals(dao.getMobileUsingUserMail(userMail)))
					{
					  flag=1;	// same with MOB
					}
					else
					{
						 checkNo = Integer.parseInt(m.group(1));;
						 System.out.println("checkNo"+checkNo);
						  for(int i = 7000 ; i<=9999 ;++i)
						  {
							  
							 if(checkNo == i){
						
					            flag=2;    //matched with regex
					            break;
					            }
							 else
							   {
								 System.out.println("*");
								 flag=3;    // invalid number
							   }
					        }		
				     }
				}
				else
				{
					flag=3;  // invalid number
				}
		 }
		 else if(Integer.parseInt(firstDigit)==0 && numberLength==11)
		 {
			 System.out.println("llRegexwith0");
			 Pattern p = Pattern.compile(llRegexwith0);
				Matcher m = p.matcher(message);
				if (m.matches())
				{				
				    checkNo = Integer.parseInt(m.group(1));;
				    if(checkNo==00)
				    {
				    	flag=3;  // invalid number
				    }
				    else
				    {
				    	flag=2;    //matched with regex	
				    }
									
				}
				else
				{
					flag=3;  // invalid number
				}
		 }
		 else
		 {
			 flag=3;
		 }
			
	     }
	     else
	     {
	    	 flag=3;
	     }
	     System.out.println(flag);
		return flag;
	 }
			
	  public String yearOfPassingValidation(String year){				
			String isValidYOP = null;
			int currentYear =Calendar.getInstance().get(Calendar.YEAR);
			try{
				int y=Integer.parseInt(year);
				System.out.println(y);
			 if((y<=(currentYear+3))&&(y>=(currentYear-1)))
			  {
				isValidYOP="yes";
			  }
			 else
			 {
				 isValidYOP="no";
			 }
			}
			catch(NumberFormatException e)
			{
				isValidYOP="Invalid Format";
			}

			System.out.println(isValidYOP);
			return isValidYOP;
			}
	  
	  //checked
	  public boolean validateName(String message)
	  {
		  String tokens[] = null;
		    String splitPattern = " ";
		    int flag1=0;
		    int flag2=0;
		    
		    boolean validate=false;
		    Pattern p = Pattern.compile(splitPattern);
		    tokens = p.split(message);
		    for (int i = 0; i <tokens.length; i++) {	    	
		    	String word=tokens[i];
		    	if(i==0)
		    	{
		    		if(word.length()>=2)
		    		{
		    			flag1=1;
		    		}	
		    	}
		    	else if(i==(tokens.length-1))
		    	{
		    		if(word.length()>=3)
		    		{
		    			flag2=1;
		    		}
		    	}
		    }
		    
		   if(flag1==1&&flag2==1)
		   {
			   validate=true;
		   }
		   System.out.println(validate);
		return validate;   
	  }
	  
	  public int getPreviousYearDate()
	  {
		
		  int previousYear=Calendar.getInstance().get(Calendar.YEAR)-1;
		  return previousYear;
	  }
	  
	  public int getNextYear()
	  {
		  int nextYear=Calendar.getInstance().get(Calendar.YEAR)+3;
		  return nextYear; 
	  }
	  
	  public List<String> getFieldNumbers(String message)
	  {
		    List<String> list= new ArrayList<String>();
	        StringTokenizer st = new StringTokenizer(message,",");
	        while(st.hasMoreTokens()){
	           list.add(st.nextToken());
	        }
	        System.out.println(list);
		    return list;
	  }	 
	  
	  public boolean validateEdit(String message)
	  {
		  boolean valid=false;
		  String inputs[]={"1","2","3","4","5","6","7","8","9"};
		  int counter=0;
		  int counter1=0;
		        StringTokenizer st = new StringTokenizer(message,",");
		        while(st.hasMoreTokens()){
		        	String data=st.nextToken();
		        	counter++;
		        	for(int i=0;i<inputs.length;i++)
		        	{
		        		if(data.equals(inputs[i]))
		        		{
		        			counter1++;
		        		}
		        	}		      
		        }
		        if(counter==counter1)
		        {
		        	valid=true;
		        }
		        else
		        {
		        	valid=false;
		        }
		   return valid;
		}
	  
	  public boolean degreeValidation(String msg)
	  {
		  boolean valid=false;
		  for(int i=1;i<=12;i++)
		  {
			  if(Integer.parseInt(msg)==i)
			  {
				  valid=true;
				  break;
			  }
			  else
			  {
				  valid=false;
			  }
		  }
		  return valid;  
	  }
	}