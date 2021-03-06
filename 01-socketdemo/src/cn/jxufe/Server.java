package cn.jxufe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信
 * 服务器端
 * @author Administrator
 */
public class Server {
	public static void main(String[] args) {
		try {
			//1. 创建一个服务器端的Socket,即ServerSocket,指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			//2. 调用accept()方法开始监听，等待客户端的连接
			System.out.println("服务期即将启动，等待客户端的连接:");
			Socket socket = serverSocket.accept();//处于阻塞状态
			//3. 获取输入流，用来读取客户端信息
			InputStream is = socket.getInputStream();//字节输入流
			InputStreamReader isr = new InputStreamReader(is);//将字节流转换成字符流
			BufferedReader bufferedReader = new BufferedReader(isr);//为输入流添加缓冲
			String info = null;
			while((info = bufferedReader.readLine()) != null) {//循环读取客户端的信息
				System.out.println("我是服务器，客户端说  : " + info);
			}
			socket.shutdownInput();//关闭输入流
			
			//4. 关闭相关的资源
			bufferedReader.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
