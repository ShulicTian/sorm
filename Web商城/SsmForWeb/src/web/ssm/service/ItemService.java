package web.ssm.service;

import java.util.List;

import web.ssm.po.item.Item;
import web.ssm.po.user.User;

public interface ItemService {
	
	public List<Item> findItems(String name);
	
	public Item getItem(int id);

	void addItem(User user, String name, double much, String photo);

}
