/**
 * 
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author narender
 *
 */
public class TestServer {

	/**
	 * 
	 */
	public TestServer() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		Socket client=null;
		try {
			serverSocket=new ServerSocket(9017);
			while(true){
				client=serverSocket.accept();
				System.out.println(client);
				Thread thread=new Thread(new ServerHandler(client));
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
class ServerHandler implements Runnable{
	Socket client;
	
	public ServerHandler(Socket client) throws IOException{
		this.client=client;
	}

	public void run() {
		String msg="";
		BufferedReader reader=null;
		PrintWriter writter=null;
		try
		{
			writter=new PrintWriter(client.getOutputStream());
			reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
			while((msg=reader.readLine())!=null)
			{
				System.out.println(msg);
				writter.println("Message from Client :"+client+" Message : "+msg);
			}
			writter.println("Hwllo");
			writter.flush();
		}catch(Exception e){
			try {
				client.close();
				reader.close();
				writter.close();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
	}

}

