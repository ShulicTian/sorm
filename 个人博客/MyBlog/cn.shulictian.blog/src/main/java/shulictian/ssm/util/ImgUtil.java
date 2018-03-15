package shulictian.ssm.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class ImgUtil {
	
	public static void saveImg(MultipartFile file,HttpServletRequest request,int id) {
		if(!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/img/topic/");
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            try {
				file.transferTo(new File(path + File.separator + id + ".jpg"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	public static void saveHeadImg(MultipartFile file,HttpServletRequest request,int id) {
		if(!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/img/user/");
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            if (!filepath.getParentFile().exists()) { 
                filepath.getParentFile().mkdirs();
            }
            try {
				file.transferTo(new File(path + File.separator + id + ".jpg"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
