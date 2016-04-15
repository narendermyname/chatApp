/**
 * @(#)ChatConfigReader.java
 *
 *
 * @author 
 * @version 1.00 2011/8/7
 */
package shine.client.logic;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
public class ChatConfigReader {
	ArrayList<String> al;
    public ChatConfigReader() {
    	al=new ArrayList<String>();
    }
    public ArrayList<String> clientConfig()
    {
    	try{
    	
    	DocumentBuilderFactory fact=DocumentBuilderFactory.newInstance();
    	fact.setValidating(true);
    	fact.setIgnoringElementContentWhitespace(true);
    	DocumentBuilder bld=fact.newDocumentBuilder();
    	String clients="/office/jsdn-data/feeds/client.xml";
    	Document clientdoc=bld.parse(new File(clients));
    	Element client=clientdoc.getDocumentElement();
    	NodeList clientnode=client.getChildNodes();
    	for(int i=0;i<clientnode.getLength();i++)
    	{
    		Element node=(Element) clientnode.item(i);
    		NodeList child=node.getChildNodes();
    		if(child.getLength()>1)
    			for( int j=0;j<child.getLength();j++)
    			{
    				Text data =(Text) child.item(j).getFirstChild();
    				al.add(data.getData());
    			}
    	}
    	}catch(Exception e){System.out.println("Exception Caught : "+e); e.printStackTrace();}
    	return al;
	}
}