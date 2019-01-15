package Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装响应信息
 *
 */
public class Response {
	private StringBuilder content;
	private StringBuilder headInfo;
	private int len = 0;
	public static final String CRLF = "\r\n";
	public static final String BLANK = " ";
	private BufferedWriter bw;
	
	public Response(){
		headInfo = new StringBuilder();
		content = new StringBuilder();
		len = 0;
	}
	
	public Response(Socket s){
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Response(OutputStream os){
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}
/**
 * 构建正文
 */
	public Response print(String info){
		content.append(info);
		len+=info.getBytes().length;
		return this;
	}
	
	public Response println(String info){
		content.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	
/**
 * 创建头
 */
	private void createHeadInfo(int code){
//		HTTP版本  状态代码  描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code){
		case 200:
			headInfo.append("OK");
			break;
		case 500:
			headInfo.append("false");
			break;
		}
		headInfo.append(CRLF);
//		响应头
		headInfo.append("Server:ShulicTIan Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
//		正文长度
		headInfo.append("Content-Length:").append(len).append(CRLF);
		headInfo.append(CRLF);
	}
//	发送给客户端
	void toClient(int code) throws IOException{
		createHeadInfo(code);
		bw.append(headInfo.toString());
		bw.append(content.toString());
		bw.flush();
	}
	
	public void close(){
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
