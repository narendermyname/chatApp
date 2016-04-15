/**
 * @(#)ServerChat.java
 *
 *
 * @author Narender
 * @version 1.00 2010/10/1
 */
 package shineserver.server.logic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import shine.client.logic.ChatConfigReader;
public class ServerChat {
	ArrayList al;
	ArrayList<String> al4;
	ArrayList<String> al2=new ArrayList<String>();
	ArrayList<Socket> al3;
	int port;
	String arr[];
    public ServerChat() {
    	this.config();
    	al4=new ArrayList<String>();
    }
    public void config()
    {
    	ChatConfigReader ccr=new ChatConfigReader();    
		ArrayList<String> al2=ccr.clientConfig();
		int ii=al2.size();
		String arr[]=new String[ii];
		arr=al2.toArray(arr);
		port=Integer.valueOf(arr[1]).intValue();
    }
    //---------------------------------this class read data comming from various clients and pass this data to tell every method that sent it to alll logined user----
    public class ClientHandler implements Runnable
    {
		BufferedReader br;
		Socket s;
		public ClientHandler(Socket s)
		{
			try
			{
				this.s=s;
				InputStreamReader in=new InputStreamReader(s.getInputStream());
				br=new BufferedReader(in);
			}catch(Exception e){}
		}
		public void run()
		{
			String msg;
			try
			{
					while((msg=br.readLine())!=null)
					{
						if(msg.endsWith("| private"))
							getUser(msg);
						else
							tellEveryOne(msg);
						if(msg.endsWith("Online"))
							getUserList(msg);
						if(msg.endsWith("Logout"))
							removeUser(msg);
						System.out.println(msg);
						
					}
			}catch(Exception e){}
		}    	
    }
    public static void main(String...a)
    {
    	new ServerChat().run();
    }
    //------------this method hold the comming clients to connect to server--------
    public void run()
    {
    	al=new ArrayList();
    	al3=new ArrayList<Socket>();
    	try{
    		ServerSocket ss=new ServerSocket(port);
    		while(true)
    		{
    			Socket s=ss.accept();
    			al3.add(s);
    			System.out.println("Client : "+s);
    			PrintWriter pw1=new PrintWriter(s.getOutputStream());
    			al.add(pw1);
    			Thread t=new Thread(new ClientHandler(s));
    			t.start();
    			System.out.println("get Connected ");
    			
    		}
    	}catch(Exception e){}
    	
    }
    //--------------------------this will get logiuned user list user -------------------
    public void getUserList(String msg) 
		{
			try
				{
						char ch1[]=msg.toCharArray();
    					char ch[]=new char[20];
						for(int i=0;i<ch1.length;i++)
							{
								if(msg.charAt(i)==' '||msg.charAt(i)==':' ||msg.charAt(i+1)==':')
								{
									break;
								}
								else
								{
									ch[i]=ch1[i];
								}
							}
						String name=new String(ch);
						al2.add(name.trim());
						tellEveryOne(al2.toString());
						System.out.println(al2.toString());
			}catch(Exception e)
			{
			}
		}
		public void getUser(String msg)
		{
			al4.clear();
			int i=0;
			StringTokenizer st=new StringTokenizer(msg,"|");
			while(st.hasMoreTokens())
			{
			        al4.add(st.nextToken());
			}
			tellSomeOne();
		}
		public void removeUser(String msg) 
		{
			try
				{
						char ch1[]=msg.toCharArray();
    					char ch[]=new char[20];
						for(int i=0;i<ch1.length;i++)
							{
								if(msg.charAt(i)==' '||msg.charAt(i)==':' ||msg.charAt(i+1)==':')
								{
									break;
								}
								else
								{
									ch[i]=ch1[i];
								}
							}
						String name=new String(ch);
						al2.remove(name.trim());
						tellEveryOne(al2.toString());
						System.out.println(al2.toString());
			}catch(Exception e)
			{
			}
		}
		//-----------------this method sent message to each logined user-----------------
    public void tellEveryOne(String msg)
    {
    	Iterator i=al.iterator();
    	while(i.hasNext())
    	{
    		try
    		{
    			PrintWriter pw=(PrintWriter)i.next();
    			pw.println(msg);
    			pw.flush();
    		}catch(Exception e){e.printStackTrace();}
    	}
    }
    public void tellSomeOne()
    {
    	int x=al2.indexOf(al4.get(2));
	   	System.out.println("Index og arr[2]"+x);
    	try
    	{
    	    Socket soc=al3.get(x);
    	   	PrintWriter pw1=new PrintWriter(soc.getOutputStream());
    	   	String msgr=al4.get(0)+":"+al4.get(1);
    		pw1.println(msgr);
    		pw1.flush();
    	}
    	catch(Exception e){System.out.println(e);e.printStackTrace();}
    	
    }
}