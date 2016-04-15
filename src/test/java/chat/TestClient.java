/**
 * 
 */
package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author narender
 *
 */
public class TestClient {

	/**
	 * 
	 */
	public TestClient() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket client=null;
		BufferedWriter dataOut=null;
		try {
			client=new Socket("127.0.0.1", 9017);
			//sending data to server
			dataOut=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			dataOut.write("Hi from Neeru");
			dataOut.flush();
			//reading data coming from server
			Thread thread=new Thread(new ClientHandler(client));
			thread.start();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {
			try {
				client.close();
				dataOut.close();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}
}

class ClientHandler implements Runnable{
	Socket client;
	BufferedReader reader;
	public ClientHandler(Socket client) throws IOException{
		this.client=client;
		reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

	public void run() {
		String msg="";
		try
		{
			while((msg=reader.readLine())!=null)
			{
				System.out.println("Client Message : "+msg);
			}
		}catch(Exception e){}
	}

}
