/**
 * @(#)Register.java
 *
 *
 * @author 
 * @version 1.00 2011/4/21
 */
 package shine.client.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import shine.client.logic.*;
import shine.client.control.*;
public class Register extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5151495912787726423L;
	JLabel reg,welcome;
	public JTextField fname2,lname2,email2,uid2;
	public JPasswordField pass2,cpass2;
	JButton submit,reset,cont;
	JTextArea test;
	JPanel jp,jp2;
	Clients cl;
	public CardLayout c;
	public Container conn;
    public Register() {
    	super("Register");
    	setIconImage(new ImageIcon("shine/client/img/MDI_ico.gif").getImage());
    	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	jp=new JPanel();
    	jp2=new JPanel();
    	jp2.setLayout(new BorderLayout());
    	//jp.setBackground(Color.cyan);
    	jp.setLayout(new FlowLayout());
    	jp2.setLayout(new FlowLayout());
    	String labs="<html><b><font color='green' face='Monotype Corsiva'>You Hava Successfully register on Shine Messanger Click on Continue Button.</font></b></html>";
    	String wel="<html><b><u><h1><font color='green' face='Monotype Corsiva'>Welcome</font></h1></u></b></html>";
    	String con="<html><b><font color='green' face='Monotype Corsiva'>Continue</font></b></html>";
    	String sub="<html><b><font color='green' face='Monotype Corsiva'>Submit</font></b></html>";
    	String re="<html><b><font color='green' face='Monotype Corsiva'>Reset</font></b></html>";
    	String label="<html><b><h1><u><font color='green'size=5 face='Monotype Corsiva'>Registration</font></u></h1></b></html>";
    	welcome=new JLabel(wel,JLabel.CENTER);
    	test=new JTextArea(4,10);test.setEditable(false);
    	reg=new JLabel(label,JLabel.CENTER);
    	lname2=new JTextField(15);
    	lname2.setBorder(BorderFactory.createTitledBorder("Last Name"));
    	fname2=new JTextField(15);
    	fname2.setBorder(BorderFactory.createTitledBorder("First Name"));
    	email2=new JTextField(15);
    	email2.setBorder(BorderFactory.createTitledBorder("E-Mail"));
    	uid2=new JTextField(15);
    	uid2.setBorder(BorderFactory.createTitledBorder("User ID"));
 		pass2=new JPasswordField(15);
 		pass2.setBorder(BorderFactory.createTitledBorder("Password"));
 		cpass2=new JPasswordField(15);
 		cpass2.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
    	submit=new JButton(sub);
    	submit.addActionListener(this);
    	reset=new JButton(re);
    	reset.addActionListener(this);
    	cont=new JButton(con);
    	cont.addActionListener(this);
    	c=new CardLayout();
    	conn=getContentPane();
    	jp.add(reg);
    	jp.add(fname2);
    	jp.add(lname2);
    	jp.add(email2);
    	jp.add(uid2);
    	jp.add(pass2);
    	jp.add(cpass2);
    	jp.add(submit);
    	jp.add(reset);
    	test.setText(labs);
    	jp2.add(welcome,BorderLayout.NORTH);
    	jp2.add(test,BorderLayout.CENTER);
    	jp2.add(cont,BorderLayout.CENTER);
    	//add(jp);
    	conn.setLayout(c);
    	conn.add(jp,"register");
    	conn.add(jp2,"welcome");
    	setResizable(false);
    	setSize(230,420);
    	setVisible(true);
    }
    
    public static void main(String args[]) {
    	new Register();
}
	public void actionPerformed(ActionEvent e)
	{
		cl=new Clients();
		if(e.getSource()==submit)
		{
			new Validate().chekUser(this);		
		}
		if(e.getSource()==cont)
		{
				this.hide();
    			ClientChat cl=new ClientChat(uid2.getText());
	    		cl.run();
		}
		if(e.getSource()==reset)
		{
			fname2.setText("");
			lname2.setText("");
			email2.setText("");
			uid2.setText("");
			pass2.setText("");
			cpass2.setText("");
		}
	}
}