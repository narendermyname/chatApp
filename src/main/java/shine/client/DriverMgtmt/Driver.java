package shine.client.DriverMgtmt;
import java.sql.*;
import java.util.ArrayList;
import shine.client.logic.ChatConfigReader;
public class Driver {
	Connection c;
	String driver;
	String url;
	String user;
	String password;
	public Connection Connection()
	{
		try{
			Class.forName(driver);
			c=DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return c;
	}
	public Driver()
	{
		this.config();
	}
	public void config()
    {
    	ChatConfigReader ccr=new ChatConfigReader();    
		ArrayList<String> al2=ccr.clientConfig();
		int ii=al2.size();
		String arr[]=new String[ii];
		arr=al2.toArray(arr);
		driver=arr[2];
		url=arr[3];
		user=arr[4];
		password=arr[5];
    }
	public static void main()
	{
		
	}
}
