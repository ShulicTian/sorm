package shulictian.ssm.vo;

import java.io.Serializable;
import java.util.Map;

public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int FAILURE_CODE=0;
	public static final int SUCCESS_CODE=1;
	
	private int code;
	private String desc;
	private Map<String,Object> data;
	
	public ResponseEntity() {}

	public ResponseEntity(int code, String desc, Map<String, Object> data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
