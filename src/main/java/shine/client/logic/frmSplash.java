/*
 ****************************************************************
 ****************************************************************
 ****************************************************************
 ****************************************************************
 */
package shine.client.logic;
import javax.swing.*;
import java.awt.*;

public class frmSplash extends JWindow implements Runnable{
	public void run(){
		JLabel SplashLabel = new JLabel(new ImageIcon("shine/client/img/Splash.jpg"));
		Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
		
		getContentPane().add(SplashLabel,BorderLayout.CENTER);
		
		setSize(490,300);
		setLocation((screen.width - 550)/2,((screen.height-300)/2));
		show();
	}
}