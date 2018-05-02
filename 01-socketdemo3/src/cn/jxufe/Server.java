package cn.jxufe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信 服务器端
 * 
 * @author Administrator
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket client = null;
		while(true) {
			client = serverSocket.accept();
			new Thread(new ServerThread(client)).start();
		}
	}

}
