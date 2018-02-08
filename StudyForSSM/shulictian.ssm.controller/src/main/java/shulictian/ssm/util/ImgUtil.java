package shulictian.ssm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ImgUtil {
	
	public static void getImg(String url,HttpServletResponse response) {
		url = "src/main/webapp/img/"+url;
		FileInputStream fis = null;
		ServletOutputStream sos = null;
		try {
			fis = new FileInputStream(new File(url));
			
			int len=0;
			byte[] data = new byte[1024];
			while(-1 != (len=fis.read(data))) {
				sos=response.getOutputStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				sos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
