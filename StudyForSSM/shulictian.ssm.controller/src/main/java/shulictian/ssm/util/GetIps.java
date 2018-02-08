package shulictian.ssm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GetIps {

	public static void main(String[] args) {
		System.out.println(GetIps.getIps());
	}

	public static String getIps() {

		File file = new File("src/main/webapp/ips.json");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String str = "";
		StringBuffer sb = new StringBuffer();
		try {
			while ((str = br.readLine()) != null) {
				sb.append(str.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JSONObject.toJSONString(sb.toString(),SerializerFeature.DisableCircularReferenceDetect);
	}

}
