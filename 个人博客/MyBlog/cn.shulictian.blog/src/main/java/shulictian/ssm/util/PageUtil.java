package shulictian.ssm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import shulictian.ssm.po.Pages;

public class PageUtil {

	public static Pages getPage(int nowPage, String data, int num) {
		Pages pages = new Pages(nowPage, num, JSONArray.parseArray(data).size());
		int start = nowPage * num - num;
		int end = nowPage * num;
		if(end>JSONArray.parseArray(data).size()) {
			end = JSONArray.parseArray(data).size();
		}
		pages.setPageJSON(JSONArray.toJSONString(JSONArray.parseArray(data).subList(start, end),
				SerializerFeature.DisableCircularReferenceDetect));
		return pages;
	}

}
