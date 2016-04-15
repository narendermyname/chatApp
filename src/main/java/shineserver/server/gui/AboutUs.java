/**
 * @(#)AboutUs.java
 *
 *
 * @author 
 * @version 1.00 2011/8/15
 */

package shineserver.server.gui;
import javax.swing.*;
public class AboutUs extends JPanel {
	JLabel label;
	JLabel image;
	public AboutUs() {
      	image=new JLabel(new ImageIcon("shineserver/server/img/Splash.jpg"));
    	label=new JLabel(new ImageIcon("char/shineserver/server/img/image004.png"));
    	add(label);
    	add(image);
    	setVisible(true);
    }
}