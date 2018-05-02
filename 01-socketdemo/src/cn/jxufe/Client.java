package cn.jxufe;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author Administrator
 */
public class Client {
	public static void main(String[] args) {
		try {
			// 1. 创建Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 8888);
			// 2. 获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 字节输出流
			PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流
			pw.write("admin");
			pw.flush();// 刷新缓存
			socket.shutdownOutput();// 关闭输出流

			// 3. 关闭资源
			pw.close();
			os.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
