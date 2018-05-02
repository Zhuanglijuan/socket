package cn.jxufe;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author Administrator
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", 8888);
		new Thread(new Send(client)).start();
		new Thread(new Receive(client)).start();
	}
}
