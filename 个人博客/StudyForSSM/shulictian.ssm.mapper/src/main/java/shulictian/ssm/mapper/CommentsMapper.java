package shulictian.ssm.mapper;

import java.util.List;
import java.util.Map;

import shulictian.ssm.po.Comments;

public interface CommentsMapper {
	
	public List<Comments> getComments(int id);
	
	public void saveComment(Map<String,Object> map);
	
	public void delComment(Map<String,Object> map);

}
