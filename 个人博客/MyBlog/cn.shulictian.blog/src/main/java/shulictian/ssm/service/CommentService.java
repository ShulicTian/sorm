package shulictian.ssm.service;

import java.util.Map;

public interface CommentService {
	
	public void sendComment(Map<String,Object> map);
	
	public void remComment(Map<String, Object> map);
	
	public String getComment(int topId);

}
