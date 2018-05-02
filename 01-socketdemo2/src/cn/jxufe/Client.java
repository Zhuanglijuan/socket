package cn.jxufe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
			System.out.print("客户端输入:");
			Scanner input = new Scanner(System.in);
			String name = input.nextLine();
			pw.write(name);
			pw.flush();// 刷新缓存
			socket.shutdownOutput();// 关闭输出流
			// 3. 获取输入流，并读取服务端的响应信息
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			while ((info = br.readLine()) != null) {// 循环读取客户端的信息
				System.out.println("我是客户端，服务端说  : " + info);
			}

			// 4. 关闭资源
			br.close();
			isr.close();
			is.close();
			input.close();
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
