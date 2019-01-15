package Server;

import java.util.ArrayList;
import java.util.List;

/*
  	<servlet-mapping>
		<servlet-name>login</servlet-name>
		<url-pattern>/login</url-pattern>
		<url-pattern>/log</url-pattern>
	</servlet-mapping>
 */
public class Mapping {
	private String name;
	private List<String> urlPatern;

	public Mapping() {
		urlPatern = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrlPatern() {
		return urlPatern;
	}

	public void setUrlPatern(List<String> urlPatern) {
		this.urlPatern = urlPatern;
	}
	
	
}
