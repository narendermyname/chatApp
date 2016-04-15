/**
 * @(#)PrivateMsg.java
 *
 *
 * @author Narender
 * @version 1.00 2011/8/12
 */
package shine.client.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import shine.client.logic.ClientChat;
public class PrivateMsg extends JFrame implements ActionListener {
	JTextArea msgfield;
	JButton post;
	JScrollPane jsp;
	String to;
	ClientChat cc;
    public PrivateMsg(String to,ClientChat cc) {
    	this.to=to;
    	this.cc=cc;
    }
    public void go()
    {
    	setTitle("To "+to);
       	setIconImage(new ImageIcon("shine/client/img/MDI_ico.gif").getImage());
    	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    	msgfield=new JTextArea(4,15);
    	jsp=new JScrollPane(msgfield);
    	post=new JButton("Post");
    	add(jsp);
    	post.addActionListener(this);
    	add(post);
    	setLayout(new FlowLayout());
    	setLocation(400,200);
    	setResizable(false);
    	setVisible(true);
    	setSize(200,140);
    }
   public void actionPerformed(ActionEvent e)
   {
   		if(e.getSource()==post)
   		{
   			cc.privateMsg(msgfield.getText()+"|"+to);
   			msgfield.setText("");
   		}
   } 
    public static void main(String args[]) {
	//new PrivateMsg("Nisha");    
}
}