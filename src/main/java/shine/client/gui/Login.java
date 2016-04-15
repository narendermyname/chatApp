/**
 * @(#)Login.java
 *
 *
 * @author Narender
 * @version 1.00 2010/10/1
 */
 package shine.client.gui;
  import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import shine.client.control.Validate;
import shine.client.logic.XPStyleTheme;
import shine.client.logic.frmSplash;
public class Login extends JFrame implements ActionListener{
	JTextField uname;
	JPasswordField pass;
	JButton jb ,jb2;
	JLabel jl,jl1,jl2;
	JPanel jp;
	//-------------flash screen-------------------
	frmSplash FormSplash= new frmSplash();
	Thread ThFormSplash = new Thread(FormSplash);
	//-------------------ur consctor----------
    public Login() {
    	setTitle("login");
    	setIconImage(new ImageIcon("shine/client/img/MDI_ico.gif").getImage());
    	loadSplashScreen();
		FormSplash.dispose();
		String log="<html><b><font color='green' face='Monotype Corsiva'>Login</font></b></html>";
    	String reg="<html><b><font color='green' face='Monotype Corsiva'>Register</font></b></html>";
		//-------------set look and feel--------------
		setDefaultLookAndFeelDecorated(true); 
		JDialog.setDefaultLookAndFeelDecorated(true);
    	MetalTheme myXPStyleTheme = new XPStyleTheme();
    	MetalLookAndFeel.setCurrentTheme(myXPStyleTheme);
    	try 
    	{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception err) 
    	{
        	System.out.println("Error loading myXPStyleTheme");
			System.out.println(err);
   		}
		jp=new JPanel();
    	jp.setLayout(new FlowLayout());
    	jl=new JLabel("User ID :   ");
    	jl1=new JLabel("Password : ");
    	uname=new JTextField(10);
    	uname.setBorder(BorderFactory.createTitledBorder("Usre ID"));
    	pass=new JPasswordField(10);
    	pass.setBorder(BorderFactory.createTitledBorder("Password"));
    	jb=new JButton(log);jb.addActionListener(this);
    	jb2=new JButton(reg);jb2.addActionListener(this);
    	//jp.add(jl);
    	jp.add(uname);
    	//jp.add(jl1);
    	jp.add(pass);
    	jp.add(jb);
    	jp.add(jb2);
   		setResizable(false);
    	add(jp);
    	setLocation(400,200);
    	setSize(190,160);
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==jb)
    	{
    		new Validate().checkUser(uname.getText(),pass.getText(),this);
    	}
    	if(e.getSource()==jb2)
    	{
    		this.hide();
    		new Register();
    	}
    }
    public static void main(String args[]) {
    	new Login();
    }
    //------------this method call on closing the window and show a confirm dialog box--------------
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
	//--------------------------this method call on loading the messanger and show a flash screen------------
	protected void loadSplashScreen()
	{
		//Start the thread
		ThFormSplash.start();
		while(!FormSplash.isShowing())
		{
			try
			{
				//Display the FormSplash for 10 seconds
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
		}
	}
	
}