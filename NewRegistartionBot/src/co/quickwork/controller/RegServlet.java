package co.quickwork.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuickWorkProperties.QuickWorkPropertyGiver;
import co.quickwork.database.Database;
import co.quickwork.database.ProductionDao;
import co.quickwork.service.Extra;
import co.quickwork.service.Validation;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	Database database= new Database();
    Validation validation=new Validation();
    Extra extra= new Extra();
    ProductionDao dao= new ProductionDao();
  //  private QuickWorkPropertyGiver qwPropGiver = null;
    
    public RegServlet() {
        super();
      
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		PrintWriter out= response.getWriter();
		String context=request.getParameter("context");
		String message =request.getParameter("message");
		String userMail=null;
		try{
		 userMail= request.getParameter("userMail");
		}
		catch(NullPointerException e)
		{
			out.println("message came from bot not user");
		}
	
		boolean var=database.checkUserExist(context);
		if(var==false)
		{
			database.insertData(context);
			database.insertEditedNumber(context);
		}
		else
		{
			database.updateData(context);
		}
		
		int count=database.getCount(context);
		String lineBreak ="                                                                 ";
    	String data;
		switch(count)
		{

		case 0: 
			
			   String mob=dao.getMobileUsingUserMail(userMail);
		       String lid=dao.getLIDUsingMobile(mob);
		       String did=dao.getUserDIDUsingMobile(mob);
		        	 data= "Hello user_name, this is Meena Mehta. I am a BOT not human."+lineBreak
		        		   +"I need your help to verify your profile."+lineBreak
		        		   +"Please answer my questions, so that I can build your correct profile."+lineBreak
		        		   +"Your profile with us is as below -"+lineBreak
		        		   +"0. My profile is correct. No change required."+lineBreak
		        		   +"1. Name: "+" "+ dao.getUserNameUsingMobile(mob)+lineBreak
		        		   +"2. Location: "+" "+ dao.getLocationUsingLID(lid)+lineBreak
		        		   +"3. Degree: "+" "+ dao.getUserDegreeUsingDID(did)+lineBreak
		        		   +"4. Year of passing: "+" "+ dao.getUserYOPUsingMobile(mob)+"."+lineBreak
		        		   +"5. Gender: "+" "+ dao.getUserGenderUsingMobile(mob)+lineBreak
		        		   +"If your profile is correct, please reply with 0, if you wish to you wish to change or update, please reply with 1 or 2 or 3 or 4 or 5.";	        		  
		        	out.println(data);
		        	break;
		       
		        
		case 1: if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("0"))
				{
			    database.updateCheckFlag(2, context);
			    database.updateCounter(context, 2);
				}
		
		        else if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("1"))
		        {
		        	data="Enter your name"+lineBreak;
		        	out.println(data);
		        	database.updateCounter(context, 94);
		        	database.updateEditedNumber(context, 1);
		        	break;
		        }
		        else if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("2"))
		        {
		        	data="For editing of Location, please reply with 1 or 2 or 3"+lineBreak
		        		  +"1. Mumbai"+lineBreak
		        		  +"2. Navi Mumbai"+lineBreak
		        	      +"3. Pune"+lineBreak;
		        	out.println(data);
		        	database.updateCounter(context, 93);
		        	database.updateEditedNumber(context, 2);
		        	break;
		        }
		        else if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("3"))
		        {
		        	data="For editing of degree,please reply with 1 or 2 or.....upto 12"+lineBreak
		        			  +"1. B.Tech"+lineBreak
			        		  +"2. B.A"+lineBreak
			        	      +"3. B.Com"+lineBreak
			        	      +"4. B.E"+lineBreak
			        		  +"5. B.Sc"+lineBreak
			        	      +"6. BCA"+lineBreak
			        	      +"7. BBA"+lineBreak
			        		  +"8. BAF"+lineBreak
			        	      +"9. BMS"+lineBreak
			        	      +"10. MCA"+lineBreak
			        	      +"11. M.Tech"+lineBreak
			        	      +"12. HSC"+lineBreak;

		        	out.println(data);
		        	database.updateCounter(context, 92);
		        	database.updateEditedNumber(context, 3);
		        	break;
		        }
		        else if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("4"))
		        {
		        	data="For editing of year of passing, please reply with 1 or 2 or 3 or 4"+lineBreak
		        		  +"1. 2013"+lineBreak
		        		  +"2. 2014"+lineBreak
		        		  +"3. 2015"+lineBreak
		        		  +"4. 2016"+lineBreak;
		        	out.println(data);
		        	database.updateCounter(context, 90);
		        	database.updateEditedNumber(context, 4);
		        	break;
		        }
		        else if(validation.FiveDigitValidation(message)==true && message.equalsIgnoreCase("5"))
		        {
		        	data="For editing of Gender, please reply with 1 or 2 or 3"+lineBreak
		        		  +"1.Male"+lineBreak
		        		  +"2.Female"+lineBreak
		        		  +"3.Other"+lineBreak;
		        	out.println(data);
		        	database.updateCounter(context, 91);
		        	database.updateEditedNumber(context, 5);
		        	break;	
		        }	
		        else
		        {
		        	data="Invalid entry. Please enter number between 0 to 5"+lineBreak;
		        	out.println(data);
		        	database.decreaseCounter(context);
		        	break;
		        }
		
		case 2: 
			     if(validation.isValidEmail(message)==true)
			     {
			    	 database.emailID(message,context);
			    	 database.updateCounter(context, 3);
			     }
			     else if((database.getEmail(context).equals("4"))&&(database.getCheckFlag(context)==2))
			     {
			    	    data="Thank you. "+lineBreak+"Can you please provide your email id?"+lineBreak ;
		    	        out.println(data);
		    	        out.flush();
		    	        database.updateCheckFlag(3, context);
		    	        database.decreaseCounter(context);
		    	        break;
			     }
			     else
			     {
			    	  data="Invalid email address. Please enter a valid address.";
	    	    	  out.println(data);
	    	    	  out.flush();
	    	    	  database.decreaseCounter(context);
	    	    	  break;
			     }
			     
		case 3: if(validation.phoneValidation(message)==true)
			   {
			     database.addAlternate(message, context);
			     database.updateCounter(context, 4);
			   }
	
		       else if((database.getAltNo(context).equals("3"))&&(database.getCheckFlag(context)==3))
		       {
		    	  data="We also need an alternate phone number so that we can reach you."+lineBreak;
		    	  out.println(data);
		    	  database.updateCheckFlag(4, context);
	    	      database.decreaseCounter(context);
	    	      break;
		       }
		       else
		       {
		    	      data="Invalid phone number. Please enter a valid phone number.";
	    	    	  out.println(data);
	    	    	  out.flush();
	    	    	  database.decreaseCounter(context);
	    	    	  break;
		       }
			 
		case 4: 
				if(validation.FiveDigitValidation(message)==true)
				{
					database.careerInterest(message,context);
					database.updateCounter(context, 5);
				}
				else if((database.getCareerInterest(context).equals("7"))&&(database.getCheckFlag(context)==4))
				{
					data="Can you please tell us your Career Field of Interest? Please reply with 1 or 2 or 3 or 4 or 5 or 6."+lineBreak
			                +"1. Sales, Business Development & Marketing"+lineBreak
							+"2. Operations - Back office, Front office"+lineBreak 
							+"3. Digital & Social Media Marketing"+lineBreak
							+"4. Software Development - Web/Mobile"+lineBreak
							+"5. Graphic Designer"+lineBreak
							+"6. Do Not Know Yet"+lineBreak;
							out.println(data);
						   database.updateCheckFlag(5, context);
				    	   database.decreaseCounter(context);
				    	   break;
				}
				else
				{
					data="Invalid  entry. Please enter 1 or 2 or 3 or 4 or 5 or 6";
					out.println(data);
					database.decreaseCounter(context);
					break;
				}
				
		case 5:
		          if(validation.isValidAvaliabilitity(message)==true)
					{
					if(message.equalsIgnoreCase("Y")||message.equalsIgnoreCase("yes"))
					  {
					  database.availability(message, context);
					  database.updateCounter(context, 6);
					  }
				else 
					  {	  
				     	data="Thank you. You can also reach out to us by calling at 7045607365/66 or sending email to hr@quickwork.co";
						 database.availability(message, context);								
						 out.println(data);
						 database.updateCounter(context,98);	
					  }
				   }
					
					else if((database.getAvailability(context).equals("8"))&&(database.getCheckFlag(context)==5))
					{	   
						data="Now, I need to know your availability. Are you available to do 6-months full-time internship through us?"+lineBreak
								+"Please enter Y or yes if available, else N or no.";
						out.println(data);
						database.updateCheckFlag(6, context);
						database.decreaseCounter(context);
						break;
					}
					else
					{
					 out.println("Invalid input, Please type Y or N.");
					 database.decreaseCounter(context);
					 break;
					}

		case 6: 
			  if((database.getStartInternshipDate(context).equals("9"))&&(database.getCheckFlag(context)==6)) 
   			  {
              out.println("From what date are you available full-time. Enter in dd/mm/yyyy format.");
              database.updateCheckFlag(7, context);
   	          database.decreaseCounter(context);
   	          break; 
   			 }
   			else if(validation.isValidDate(message)==true)
		        {
   				 data="Thank you. Quickwork Team will reach out to you soon. You can also reach out to us by calling at 7045607365/66 or sending email to hr@quickwork.co"
   						 +lineBreak;
                 out.println(data);
   				 database.startInternshipDate(message, context);
    	         database.updateCounter(context, 98); 
		        }
		   	    else
	 	        {
	 	        data="Invalid date. Please enter a valid date in dd/mm/yyyy format.";
	 	        out.println(data);
	 	    	database.decreaseCounter(context);
	 	    	break; 
	 	        }
            
		case 91:  if(validation.FourDigitValidation(message)==true)	
					{
			          String mobile=dao.getMobileUsingUserMail(userMail); 
				        dao.updateUserYOP(mobile,message);	
				        database.updateCounter(context, 96);
					}
			       else if(database.getEditedNumber(context)==4)
			       {
			    	     data="Invalid entry. Please enter 1 or 2 or 3 or 4.";
					      out.println(data);
					      out.flush();
			    	      database.decreaseCounter(context);	    			
						  break;
			       }
		case 92: 
					if(validation.ThreeDigitValidation(message)==true)	
					{
						String mobile=dao.getMobileUsingUserMail(userMail); 
				        dao.updateGender(mobile, message);
				        database.updateCounter(context, 96);
					}
			       else if(database.getEditedNumber(context)==5)
			       {
			    	      data="Invalid entry. Please enter 1 or 2 or 3.";
					      out.println(data);
					      out.flush();
			    	      database.decreaseCounter(context);	    			
						  break;
			       }
		case 93:  if(validation.degreeValidation(message)==true)	
					{
		            	String mobile=dao.getMobileUsingUserMail(userMail); 
				        dao.updateUserDegree(mobile, message);
				        database.updateCounter(context, 96);
					}
			       else if(database.getEditedNumber(context)==3)
			       {
			    	     data="Invalid entry. Please enter number from 1 to 12.";
					      out.println(data);
					      out.flush();
			    	      database.decreaseCounter(context);	    			
						  break;
			       }
			
		case 94:  if(validation.ThreeDigitValidation(message)) 
			       {
			           String mobile=dao.getMobileUsingUserMail(userMail); 
				       dao.updateUserLocation(mobile, message);	
				       database.updateCounter(context, 98);
			       }
		    	  else if(database.getEditedNumber(context)==2)
			      {
				      data="Invalid entry. Please enter 1 or 2 or 3.";
				      out.println(data);
				      out.flush();
		    	      database.decreaseCounter(context);	    			
					  break;
			      }
		
			   
		case 95: 
			  if(validation.validateName(message)==true)
			  {
				String mobile=dao.getMobileUsingUserMail(userMail); 
				dao.updateUserName(mobile, message);
			    database.updateCounter(context, 96);
		    	 }
		      else if(database.getEditedNumber(context)==1)
		     {
			     data="Please enter a valid name";
    	         out.println(data);
    	         out.flush();
    	         database.decreaseCounter(context);	    			
			    break;	    			
		      }
			  
			  
			  
		case 96: 
			    String mob1=dao.getMobileUsingUserMail(userMail);
		        String lid1=dao.getLIDUsingMobile(mob1);
		        String did1=dao.getUserDIDUsingMobile(mob1);
			     data="Your updated profile is as below -"+lineBreak
			     +"0. My profile is correct. No change required."+lineBreak
      		     +"1. Name: "+" "+ dao.getUserNameUsingMobile(mob1)+lineBreak
      		      +"2. Location: "+" "+ dao.getLocationUsingLID(lid1)+lineBreak
      		     +"3. Degree: "+" "+ dao.getUserDegreeUsingDID(did1)+lineBreak
      		      +"4. Year of passing: "+" "+ dao.getUserYOPUsingMobile(mob1)+"."+lineBreak
      		     +"5. Gender: "+" "+ dao.getUserGenderUsingMobile(mob1)+lineBreak
      		     +"If your profile is correct, please reply with 0, if you wish to you wish to change or update, please reply with 1 or 2 or 3 or 4 or 5.";
			     out.println(data);
			     database.updateCounter(context, 0);
			     break;
		  
		  case 98:	
			   data="The end";
		  }
	}

}
