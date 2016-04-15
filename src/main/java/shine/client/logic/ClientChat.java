/**
 * @(#)ClientChat.java
 *
 *
 * @author Narender
 * @version 1.00 2010/10/1
 */
package shine.client.logic;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import shine.client.gui.*;
public class ClientChat extends JFrame implements ActionListener,ListSelectionListener
{
	DefaultListModel listModel;
	JTextArea jta,jta2;
	JList jl;
	JButton jb,jb2;
	JLabel jl4,lol;
	BufferedReader br;
	PrintWriter pw;
	Socket s;
	String str,host;
	JPanel home,logout;
	Thread t;
	Container c;
	CardLayout cl;
	ArrayList al;
	int port;
	public ClientChat(String str) {
    	this.str=str;
    	setTitle("Shine Messenger");
       	setIconImage(new ImageIcon("shine/client/img/MDI_ico.gif").getImage());
       	al=new ArrayList();
    }
    public static void main(String...a)
    {
    	//ClientChat cl=new ClientChat("Narender");
    	//cl.run();
    }
    public void run()
    {
    	String lols="<html><b><u><h1><font color='green' face='Monotype Corsiva'>You Have Successfully Logout Thanks for using Shine Messanger.</font></h1></u></b></html>";
    	//String post="<html><b><font color='green' face='Monotype Corsiva'>Post</font></b></html>";
    	//String log="<html><b><font color='green' face='Monotype Corsiva'>Logout</font></b></html>";
    	String hello="<html><b><font color='green' face='Monotype Corsiva'>Hello : </font></b></html>"+str;
    	JLabel SplashLabel = new JLabel(new ImageIcon("shine/client/img/Splash.jpg"));
    	lol=new JLabel(lols);
    	jl4=new JLabel("Hello : "+str);
    	listModel = new DefaultListModel();
    	//-----------panel-------
    	home=new JPanel();
    	home.setLayout(new FlowLayout());
    	logout=new JPanel();
    	logout.setLayout(new BorderLayout());
        
        //------------user msg area------------  
    	jta=new JTextArea(10,22);
    	jta.setLineWrap(true);
    	jta.setEditable(false);
    	jta.setWrapStyleWord(true);
    	JScrollPane jsp1=new JScrollPane(jta);
    	//------------current user msg----------
    	jta2=new JTextArea(4,22);
    	JScrollPane jsp2=new JScrollPane(jta2);
    	jta.setAutoscrolls(true);
    	jta2.setAutoscrolls(true);
    	//--------------user list-----------
       	jl=new JList(listModel);
    	jl.resize(0,0);
    	jl.setFixedCellWidth(245);
    	jl.setAutoscrolls(true);
   		jl.addListSelectionListener(this); 			
    	JScrollPane jsp3=new JScrollPane(jl);
    	//--------------setting border to jta1 ,jta2, list-----------
    	jsp1.setBorder(BorderFactory.createTitledBorder("Message From Users"));
    	jsp2.setBorder(BorderFactory.createTitledBorder("Your  Message"));
    	jsp3.setBorder(BorderFactory.createTitledBorder("Online Users"));
    	//--------- buttons--------------
    	jb=new JButton("Post");
    	jb.addActionListener(this);
    	jb2=new JButton("Logout");
    	jb2.addActionListener(this);
    	c=getContentPane();
    	cl=new CardLayout();
    	//------------adding component to container--------
    	home.add(jl4);
       	home.add(jsp1);
       	home.add(jsp3);
     	home.add(jsp2);
    	home.add(jb);
    	home.add(jb2);
     	logout.add(lol ,BorderLayout.NORTH);
    	logout.add(SplashLabel,BorderLayout.CENTER);
    	c.setLayout(cl);
    	c.add(home,"home");
    	c.add(logout,"logout");
    	
    	//add(p);
    	//------------make connection with server-----------
    	this.config();  
      	setNetwork();
    	this.loginCall();
    	t=new Thread(new InComming());
    	t.start();
    	//--------------set frame properties------------
    	setResizable(false);
    	addWindowListener(new WindowAdapter(){
      	public void windowClosing(WindowEvent e){UnloadWindow();}});
    	setSize(300,550);
    	setVisible(true);
    }
    public void config()
    {
    	ChatConfigReader ccr=new ChatConfigReader();    
		ArrayList<String> al2=ccr.clientConfig();
		int ii=al2.size();
		String arr[]=new String[ii];
		arr=al2.toArray(arr);
		host=arr[0];
		port=Integer.valueOf(arr[1]).intValue();
    }
    //------this method tell server about online users
    public void loginCall()
    {
    	try
    		{   		
    				pw=new PrintWriter(s.getOutputStream());
    				pw.println(str+" : Online");
    				pw.flush();
    		}
    		catch(Exception e1){e1.printStackTrace();}
    
    }
    public void valueChanged(ListSelectionEvent e)
    {
    	String item=(String)jl.getSelectedValue();
    	new PrivateMsg(item,this).go();
    }
     public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand().equals("Post"))
    	{
    		try
    		{   		
    				pw=new PrintWriter(s.getOutputStream());
    				pw.println(str+":"+jta2.getText());
    				pw.flush();
    		}
    		catch(Exception e1){}
    		jta2.setText("");
    	}
    	if(e.getActionCommand().equals("Logout"))
    	{
    		try
    		{
    		
    			pw=new PrintWriter(s.getOutputStream());
    			pw.println(str+" : Logout");
    			pw.flush();
      		}catch(Exception e1){}
    		cl.show(c,"logout");
    	}
	
    }
    //------------set conn with server----------
    public void setNetwork()
    {
    	try{
			s=new Socket(host,port);System.out.println(s);
    		InputStreamReader in=new InputStreamReader(s.getInputStream());
    		br=new BufferedReader(in);
    	}catch(Exception e){JOptionPane.showMessageDialog(this,"ServerNot Found Error Reconnect  Again.");System.exit(0);	 }
    }
    // -------------------class read data from comming from server----------------
    public class InComming implements Runnable
	{
		public void run()
		{
			
			String msg="";
			try
				{
					br=new BufferedReader(new InputStreamReader(s.getInputStream()));
					while((msg=br.readLine())!=null)
					{
						if(msg.startsWith("["))
							this.getUserList(msg);	
						else
							jta.append(msg+"\n");
					}
				}catch(Exception e){}
		}
		
		public void getUserList(String msg) 
		{
			int i=0;
			listModel.removeAllElements();
			StringTokenizer st=new StringTokenizer(msg,"[,'']");
			while(st.hasMoreTokens())
			{
			        listModel.add(i++,st.nextToken().trim());
			}		
					
		}
	}
		//-------------------- this method show list of users------------------
		public void privateMsg(String msg)
		{
			try
    		{   	pw=new PrintWriter(s.getOutputStream());
    				pw.println(str+"|"+msg+"| private");
    				pw.flush();
    		}
    		catch(Exception e1){e1.printStackTrace();}
		}
		
	// -----------------------call on window closing ----------------
	protected void UnloadWindow()
	{
		try 
		{
	    	int reply = JOptionPane.showConfirmDialog(this,
	    	                                          "Are you sure you want to exit?",
	    	                                          "Client Chat" ,
	    	                                          JOptionPane.YES_NO_OPTION,
	    	                                          JOptionPane.WARNING_MESSAGE);
	    	try
    		{
    		
    			pw=new PrintWriter(s.getOutputStream());
    			pw.println(str+" : Logout");
    			pw.flush();
      		}catch(Exception e1){}
			// If the confirmation was affirmative, handle exiting.
			if (reply == JOptionPane.YES_OPTION) 
			{
			   	setVisible(false); // hide the Frame
		    	dispose();         // free the system resources
		    	System.exit(0);    // close the application
			}
		}
		catch(Exception e) {}
	}
	
}