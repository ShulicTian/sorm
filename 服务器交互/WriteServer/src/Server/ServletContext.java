package Server;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 上下文
 * @author TIan
 *
 */
public class ServletContext {
//	为每一个 Servlet 定义名字
//	login --> LoginServlet
	private Map<String, String> servlet;
//	url --> login
	private Map<String,String> mapping;
	
	
	public ServletContext() {
		servlet = new HashMap<String,String>();
		mapping = new HashMap<String,String>();
	}
	
	public Map<String, String> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}
