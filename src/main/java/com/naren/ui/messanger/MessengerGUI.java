/**
 * @(#)MessanderGUI.java
 * @author Narender
 * @version 1.00 2010/10/1
 */
package com.naren.ui.messanger;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import shine.client.logic.ClientChat;
public class MessengerGUI extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -457828923666595717L;
	DefaultListModel listModel;
	JTextArea jta,jta2;
	JList jl;
	JButton jb,jb2;
	JLabel jl4,lol;
	String uname;
	JPanel home,logout;
	Thread t;
	Container c;
	CardLayout cl;
	ClientChat cc;
	public MessengerGUI(String uname) {
    	this.uname=uname;
    	setTitle("ClientChat");
       	setIconImage(new ImageIcon("shine/client/img/MDI_ico.gif").getImage());
       	cc=new ClientChat("hello");
    }
    public static void main(String...a)
    {
    	//MessengerGUI cl=new MessengerGUI("Narender");
    //	cl.run();
    }
    public void run()
    {
    	String lols="<html><b><u><h1><font color='green' face='Monotype Corsiva'>You Have Successfully Logout.</font></h1></u></b></html>";
    	JLabel SplashLabel = new JLabel(new ImageIcon("shine/client/img/Splash.jpg"));
    	lol=new JLabel(lols);
    	jl4=new JLabel("Hello : "+uname);
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
    	//--------------set frame properties------------
    	setResizable(false);
    	addWindowListener(new WindowAdapter(){
      	public void windowClosing(WindowEvent e){UnloadWindow();}});
    	setSize(300,550);
    	setVisible(true);
    }
     public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand().equals("Post"))
    	{
    		//cc.getPostedData(uname+":"+jta2.getText());
    		jta2.setText("");
    	}
    	if(e.getActionCommand().equals("Logout"))
    	{
    		//cc.getPostedData(uname+" : Logout");
    	}
	
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
	    	// cc.getPostedData(uname+" : Logout");
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
	public void message(String msg)
	{
		jta.append(msg+"\n");
	}
}