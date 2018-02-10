package Servlet;

import Server.Request;
import Server.Response;

public abstract class Servlet {

	
	public void service(Request req,Response res)throws Exception{
		try {
			this.doGet(req,res);
			this.doPost(req,res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected abstract void doGet(Request req,Response res)throws Exception;
	protected abstract void doPost(Request req,Response res)throws Exception;
	
	
}
