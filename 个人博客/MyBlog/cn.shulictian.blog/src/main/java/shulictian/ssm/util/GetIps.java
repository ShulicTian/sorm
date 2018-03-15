package shulictian.ssm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GetIps {
	
	public static String getIps() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(GetIps.class.getClassLoader().getResourceAsStream("ips.json"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
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
