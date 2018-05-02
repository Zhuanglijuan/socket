package cn.jxufe;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	public static void closeAll(Closeable... io) {
		for(Closeable temp : io) {
				try {
					if(temp != null)
					temp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
