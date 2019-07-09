package CharThree;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private ServerSocket ss;
	private ArrayList<Socket> list;

	public	Server() {
		try {
			ss = new ServerSocket(7019);
			list = new ArrayList<Socket>();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startServer() {
		while (true) {
			try {
				Socket s = ss.accept();
				list.add(s);
				ServerThread st = new ServerThread(s, this);
				Thread thread = new Thread(st);
				thread.start();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void broadcast(String message) {
		for (int i = 0; i < list.size(); i++) {
			Socket s = list.get(i);

			try {
				OutputStream os = s.getOutputStream();
				PrintWriter out = new PrintWriter(os, true);
				out.println(message);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server().startServer();

	}

}
