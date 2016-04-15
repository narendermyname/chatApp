/**
 * @(#)Server.java
 *
 *
 * @author 
 * @version 1.00 2011/8/5
 */
package shineserver.server.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import shineserver.server.logic.ServerChat;

public class ServerUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JTextArea ta;
	JButton jb; 
	JPanel jpanel;
	JMenuBar jbar;
	JMenu jmenu1,jmenu2;
	JMenuItem jitem11,jitem12,jitem13,jitem21;
	Container c;
	CardLayout cc;
	AboutUs au;
	ServerChat sc;
	String message="This is Shine Messenger Server\n version 1.0\n";
    public ServerUI() {
    	setTitle("Shine Messanger Server");
    	setIconImage(new ImageIcon("shineserver/server/img/MDI_ico.gif").getImage());
    	au=new AboutUs();
    	sc=new ServerChat();
    	c=getContentPane();
    	cc=new CardLayout();
    	jpanel=new JPanel();
    	jbar=new JMenuBar();
    	jmenu1=new JMenu("Server");
    	jmenu2=new JMenu("About Us");
    	jitem11=new JMenuItem("Stop Server");
    	jitem13=new JMenuItem("Start Server");
    	jitem12=new JMenuItem("Exit");
    	jitem21=new JMenuItem("About Author");
    	label=new JLabel("<html><b><h1><font color='green' face='Monotype Corsiva'>Shine Messenger Server</h1></font></b></html>",JLabel.CENTER);
    	ta=new JTextArea(20,20);
    	ta.setText(message);
    	ta.setEditable(false);
    	jb=new JButton("Stop Server");
    	jmenu1.add(jitem11);jmenu1.add(jitem13);
    	jmenu1.add(jitem12);
    	jmenu2.add(jitem21);
    	jbar.add(jmenu1);
    	jbar.add(jmenu2);
    	setJMenuBar(jbar);
    	jpanel.setLayout(new BorderLayout());
    	jpanel.add(label,BorderLayout.NORTH);
    	jpanel.add(ta,BorderLayout.CENTER);
    	jpanel.add(jb,BorderLayout.SOUTH);
    	jitem11.addActionListener(this);
    	jitem12.addActionListener(this);
    	jitem13.addActionListener(this);
    	jitem21.addActionListener(this);
    	jb.addActionListener(this);
    	c.setLayout(cc);
    	c.add(jpanel,"main");
    	c.add(au,"aboutus");
    	
    	addWindowListener(new WindowAdapter(){
      	public void windowClosing(WindowEvent e){UnloadWindow();}});
    	setSize(700,560);
    	setResizable(false);
    	setVisible(true);
    	sc.run();
    }
    public static void main(String args[]) 
    {
 		new ServerUI();   
	}
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand().equals("Exit"))
    	{
    		setVisible(false); // hide the Frame
		   	dispose();         // free the system resources
		   	System.exit(0);    // close the application
    	}
    	if(e.getActionCommand().equals("About Author"))
    		cc.show(c,"aboutus")    		;
    	if(e.getActionCommand().equals("Start Server"))
    	{
    		cc.show(c,"main");
    		ta.setText(message+"Server Started");
    		new ServerChat().run();
    		
    	}    		
    	if(e.getSource()==jb||e.getActionCommand().equals("Stop Server"))
    	{
    		cc.show(c,"main");
    		sc=null;
    		System.gc();
    		ta.setText(message+"Server Stoped");
    	}
    }
    public void go()
    {
    }
    protected void UnloadWindow()
	{
		try 
		{
	    	int reply = JOptionPane.showConfirmDialog(this,
	    	                                          "Are you sure you want to exit?",
	    	                                          "Client Chat" ,
	    	                                          JOptionPane.YES_NO_OPTION,
	    	                                          JOptionPane.WARNING_MESSAGE);
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