package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	private ServerSocket ss;
	public static final String CRLF = "\r\n";
	public static final String BLANK = " ";
	private boolean flag = false;
	
	public static void main(String[] args) {
		Server s = new Server();
		s.start();
		
		
	}
	
	public void start(){
		start(8888);
	}
	
	public void start(int port){
		try {
			ss = new ServerSocket(port);
			this.acceptC();
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
		
	}
	
	private void acceptC(){
		try {
			while(!flag){
				new Thread(new Dispatcher(ss.accept())).start();
			}
		} catch (IOException e) {
			stop();
		}
	}
	
	public void stop(){
		flag = true;
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
