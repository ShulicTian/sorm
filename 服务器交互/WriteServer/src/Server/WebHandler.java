package Server;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler {
	private List<Entity> enList;
	private List<Mapping> mappList;
	private Entity en;
	private Mapping mapp;
	private String BeginTag;//标签
	private boolean flag;
	
	@Override
	public void startDocument() throws SAXException {
//		文档解析开始
		enList = new ArrayList<Entity>();
		mappList = new ArrayList<Mapping>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//		开始读取元素
		if(null!=qName){
			BeginTag = qName;
			if(qName.equals("servlet")){
				flag = false;
				en = new Entity();
			}else if(qName.equals("servlet-mapping")){
				flag = true;
				mapp = new Mapping();
			}
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
//		处理内容
		String str = new String(ch,start,length);
		if(null!=BeginTag){
			if(flag){
				if(BeginTag.equals("servlet-name")){
					mapp.setName(str);
				}else if(BeginTag.equals("url-pattern")){
					mapp.getUrlPatern().add(str);
				}
			}else{
				if(BeginTag.equals("servlet-name")){
					en.setName(str);
				}else if(BeginTag.equals("servlet-class")){
					en.setClz(str);
				}
			}
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
	//	读取元素结束
		if(null!=qName){
			if(qName.equals("servlet")){
				enList.add(en);
			}else if(qName.equals("servlet-mapping")){
				mappList.add(mapp);
			}
		}
		BeginTag = null;
	}

	@Override
	public void endDocument() throws SAXException {
//		文档解析结束
		
	}
	
	public List<Entity> getEnList() {
		return enList;
	}

	public void setEnList(List<Entity> enList) {
		this.enList = enList;
	}

	public List<Mapping> getMappList() {
		return mappList;
	}

	public void setMappList(List<Mapping> mappList) {
		this.mappList = mappList;
	}



}
