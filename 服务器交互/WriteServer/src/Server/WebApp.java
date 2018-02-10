package Server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import Servlet.Servlet;

public class WebApp {
	private static ServletContext context;
	static{
		try {
//		获取解析工厂
			SAXParserFactory spf = SAXParserFactory.newInstance();
//		获取解析器
			SAXParser sax = spf.newSAXParser();
			WebHandler ph = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("WEB_INFO/web.xml"),ph);
			
//			将List转Map
			context = new ServletContext();
			
			Map<String,String> servlet = context.getServlet();
			for(Entity en:ph.getEnList()){
				servlet.put(en.getName(), en.getClz());
			}
			
			Map<String,String> mapping = context.getMapping();
			for(Mapping mapp:ph.getMappList()){
				List<String> urls = mapp.getUrlPatern();
				for(String url:urls){
					mapping.put(url, mapp.getName());
				}
			}
			
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(null==url || (url.trim()).equals("")){
			return null;
		}
//		return context.getServlet().get(context.getMapping().get(url));
		String name = context.getServlet().get(context.getMapping().get(url));
		System.out.println("URL:"+name);
		return (Servlet)Class.forName(name).newInstance();
		
	}
	
}
