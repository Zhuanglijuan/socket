package cn.jxufe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据线程
 * 
 * @author Administrator
 *
 */
public class Send implements Runnable {
	private BufferedReader console;
	private DataOutputStream dos;
	private boolean isRunning = true;

	public Send() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public Send(Socket client) {
		this();
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// e.printStackTrace();
			isRunning = false;
			CloseUtil.closeAll(dos, console);
		}
	}

	private String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void send() {
		String msg = getMsgFromConsole();
		try {
			if (msg != null && !msg.equals("")) {
				dos.writeUTF(msg);
				dos.flush();
			}
		} catch (IOException e) {
			// e.printStackTrace();
			isRunning = false;
			CloseUtil.closeAll(dos, console);
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			send();
		}
	}

}
