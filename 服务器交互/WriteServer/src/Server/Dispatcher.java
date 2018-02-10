package Server;

import java.io.IOException;
import java.net.Socket;

import Servlet.Servlet;

public class Dispatcher implements Runnable {
	private Socket s;
	private Request req;
	private Response res;
	private int code = 200;

	public Dispatcher(Socket s) {
		this.s = s;
		try {
			req = new Request(s.getInputStream());
			res = new Response(s.getOutputStream());
		} catch (IOException e) {
//			e.printStackTrace();
			code = 500;
			return;
		}
	}

	@Override
	public void run() {
		try {
			Servlet se = WebApp.getServlet(req.getUrl());
			if(null == se){
				this.code = 404;
			}else{
				se.service(req, res);
			}
			res.toClient(code);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			res.toClient(500);
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
