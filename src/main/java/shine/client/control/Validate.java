/**
 * @(#)Validate.java
 *
 *
 * @author 
 * @version 1.00 2011/8/7
 */
package shine.client.control;
import javax.swing.JOptionPane;

import shine.client.gui.Login;
import shine.client.gui.Register;
import shine.client.logic.ClientChat;
import shine.client.logic.Clients;
public class Validate {
	private String fname;
	private String lname;
	private String email;
	private String uid;
	private String pass;
	private String cpass;
	Clients cl;
	public Validate() {
		cl=new Clients();
	}

	//-----------------this method validate ur user on login------------------
	public void checkUser(String name,String pass,Login login)
	{
		if(name.equals("")&&pass.equals(""))
			JOptionPane.showMessageDialog(login,"User Id and Password Is invalid.");
		else
		{
			if(new Clients().availableClient(name,pass))
			{
				login.hide();
				ClientChat cl=new ClientChat(name);
				cl.run();
			}
			else
				JOptionPane.showMessageDialog(login,"User Id and Password Is invalid.");	
		}
	}
	public void getUserDetail(Register reg)
	{
		fname=reg.fname2.getText();
		lname=reg.lname2.getText();
		email=reg.email2.getText();
		uid=reg.uid2.getText();
		pass=reg.pass2.getText();
		cpass=reg.cpass2.getText();
	}
	public void chekUser(Register reg)
	{
		this.getUserDetail(reg);
		if(fname.equals("")||lname.equals("")||email.equals("")||uid.equals("")||pass.equals("")||cpass.equals(""))
			JOptionPane.showMessageDialog(reg,"Please do'nt Leave Blank any field.");
		else
			if(pass.trim().equals(cpass.trim()))
			{
				if(cl.availableClient(uid.trim()))
					JOptionPane.showMessageDialog(reg,"User already exist choose another User Id.");
				else
				{
					cl.registerClient(fname.trim(),lname.trim(),email.trim(),uid.trim(),pass.trim(),cpass.trim());	
					reg.c.show(reg.conn,"welcome");;		
				}
			}
			else
				JOptionPane.showMessageDialog(reg,"Password and Confirm password does'nt match..");	
	}
}