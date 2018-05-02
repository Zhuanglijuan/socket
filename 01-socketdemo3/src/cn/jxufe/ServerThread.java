package cn.jxufe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean isRunning = true;

	public ServerThread(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dos, dis);
		}
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				String msg = dis.readUTF();
				System.out.println(msg);
				dos.writeUTF("欢迎" + msg);
				dos.flush();
			}
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dos, dis);
		}
	}

}
