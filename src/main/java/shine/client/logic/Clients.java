/**
 * @(#)Clients.java
 *
 *
 * @author 
 * @version 1.00 2011/8/5
 */
package shine.client.logic;
import java.util.*;
import java.sql.*;
import shine.client.DriverMgtmt.Driver;
public class Clients {

	Connection c=null;
	 PreparedStatement ps=null;
	 Statement s=null;
	 ResultSet rs=null;
	 String str="";
    public Clients() {
    	 c=new Driver().Connection();
    }
    //-------this method chech availability og client in data base-------- 
    public boolean availableClient(String name,String pass)
	 {
		 
		 try
			{
			 	ps=c.prepareStatement("select * from USER where EMAIL=? AND PASSWORD=?");
				ps.setString(1, name);
				ps.setString(2, pass);
				rs=ps.executeQuery();
				while(rs.next())
				{
					str+=rs.getString(1)+" 12"+rs.getString(2)+" 21"+rs.getString(3)+" 12"+rs.getString(4)+" "+rs.getString(5);
					//System.out.println(s);
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
		if(str.equals(""))
		 	return false;
		 else
		 	 return true;
	 }
	 public boolean availableClient(String name)
	 {
		 
		 try
			{
			 	ps=c.prepareStatement("select * from USER where email=?");
				ps.setString(1, name);
				rs=ps.executeQuery();
				while(rs.next())
				{
					str+=rs.getString(1)+" 12"+rs.getString(2)+" 21"+rs.getString(3)+" 12"+rs.getString(4)+" "+rs.getString(5);
					//System.out.println(s);
				}
				}catch(Exception e){
				e.printStackTrace();
			}
		if(str.equals(""))
		 	return false;
		 else
		 	 return true;
	 }
    public static void main(String args[]) {
    //Clients cl=	new Clients();System.out.println(cl.availableClient("narender","1234"));
   
    //cl.registerClient("nirmal","singh","nirmal@gmail.com","nirmal","nirmal","nirmal");
	}
	//---------------this method call when anew client register on our messanger--------
	//--------------and enter detail of client in database------------------------------
	public void registerClient(String firstName,String lastName,String email,String userID,String pass,String confirmPass)
	 {
		 try
			{
				String insertq="insert into USER values(?,?,?,?,?,?)";
			  	PreparedStatement ps=c.prepareStatement(insertq);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, email);
				ps.setString(4, userID);
				ps.setString(5, pass);
				ps.setString(6, confirmPass);
				ps.executeUpdate();
				ps.close();
				c.close();
			}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
			}
	 }
}