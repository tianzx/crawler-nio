package org.crawler.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {

	public void server(int port) throws IOException {
		final ServerSocket socket = new ServerSocket(port);
		for (;;) {
			final Socket clientSocket = socket.accept();
			System.err.println("Accepted connection from" + clientSocket);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						OutputStream os = clientSocket.getOutputStream();
						os.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
						os.flush();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
						try {
							clientSocket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {
		PlainOioServer pos = new PlainOioServer();
		try {
			pos.server(9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
