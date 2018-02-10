package shulictian.ssm.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import shulictian.ssm.po.Pages;
import shulictian.ssm.po.Topic;
import shulictian.ssm.po.TopicCustom;
import shulictian.ssm.po.User;
import shulictian.ssm.po.UserPages;

public class PageUtil {
	public static Pages getPage(int nowPage,List<TopicCustom> tops) {
		Pages pages = new Pages(nowPage,10,tops.size());
		List<TopicCustom> list = new ArrayList<>();
		for(int i=pages.getStartIndex();i<pages.getStartIndex()+10;i++) {
			if(i==(tops.size())) {
				break;
			}
			list.add(tops.get(i));
		}
		JSONArray.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
		pages.setPageTops(JSONArray.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat));
		return pages;
	}
	
	public static Pages getAdminPage(int nowPage,List<Topic> tops) {
		Pages pages = new Pages(nowPage,10,tops.size());
		List<Topic> list = new ArrayList<>();
		for(int i=pages.getStartIndex();i<pages.getStartIndex()+10;i++) {
			if(i==(tops.size())) {
				break;
			}
			list.add(tops.get(i));
		}
		JSONArray.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
		pages.setPageTops(JSONArray.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat));
		return pages;
	}
	
	public static UserPages getUserPage(int nowPage,List<User> tops) {
		
		UserPages pages = new UserPages(nowPage,10,tops.size());
		List<User> list = new ArrayList<>();
		for(int i=pages.getStartIndex();i<pages.getStartIndex()+10;i++) {
			if(i==(tops.size())) {
				break;
			}
			list.add(tops.get(i));
		}
		pages.setPageTops(JSONArray.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat));
		return pages;
	}

}
