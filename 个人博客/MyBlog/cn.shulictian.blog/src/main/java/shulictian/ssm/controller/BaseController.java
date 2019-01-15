package shulictian.ssm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import shulictian.ssm.vo.ResponseEntity;

/**
 * @Desc 父类Controller
 * @author ShulicTian
 * @Date 2018/3/10
 */
public class BaseController {
	
	/**
	 * @Desc 异常捕获
	 * @param e
	 */
	@ResponseBody
	@ExceptionHandler(Throwable.class)
	public void handlerError(Throwable e) {
		printJSON(buildResponseEntity(e));
	}
	
	/**
	 * @Desc 输出JSON
	 * @param responseEntity
	 */
	private void printJSON(ResponseEntity responseEntity) {
		
		String json = JSONObject.toJSONString(responseEntity);
		HttpServletResponse response = getResponse();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void printJSON() {
		printJSON(buildResponseEntity(new HashMap<String,Object>()));
	}
	
	protected void printJSON(String key,Object value) {
		Map<String,Object> data = new HashMap<String,Object>();
		data.put(key, value);
		printJSON(buildResponseEntity(data));
	}
	
	protected void printJSON(Map<String,Object> data) {
		printJSON(buildResponseEntity(data));
	}
	
	/**
	 * 构建ResponseEntity
	 * @param code
	 * @param desc
	 * @param map
	 * @return
	 */
	private ResponseEntity buildResponseEntity(int code,String desc,Map<String,Object> data) {
		
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.setCode(code);
		responseEntity.setDesc(desc);
		responseEntity.setData(data);
		
		return responseEntity;
	} 
	
	/**
	 * 构建异常时的ResponseEntity
	 * @param e
	 * @return
	 */
	private ResponseEntity buildResponseEntity(Throwable e) {
		
		String message = "服务器繁忙,请稍后再试...";
		if(true) {
			message = e.getMessage();
		}
		return buildResponseEntity(ResponseEntity.FAILURE_CODE,message,null);
	}
	
	/**
	 * 构建成功时的ResponseEntity
	 * @param data
	 * @return
	 */
	private ResponseEntity buildResponseEntity(Map<String,Object> data) {
		return buildResponseEntity(ResponseEntity.SUCCESS_CODE,null,data);
	}
	
	
	/**
	 * 获取request
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ( (ServletRequestAttributes)RequestContextHolder.getRequestAttributes() ).getRequest();
	}
	
	/**
	 * 获取response
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ( (ServletRequestAttributes)RequestContextHolder.getRequestAttributes() ).getResponse();
	}

}
