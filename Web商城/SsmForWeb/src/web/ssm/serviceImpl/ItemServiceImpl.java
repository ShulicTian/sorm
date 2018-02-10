package web.ssm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.ssm.mapper.ItemMapper;
import web.ssm.po.item.Item;
import web.ssm.po.user.User;
import web.ssm.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper im;

	@Override
	public List<Item> findItems(String name) {
		return im.findItems(name);
	}

	@Override
	public Item getItem(int id) {
		
		return im.getItem(id);
	}
	
	@Override
	public void addItem(User user,String name,double much,String photo) {
		Item item = new Item();
		item.setName(name);
		item.setMuch(much);
		item.setPhoto(photo);
		item.setUser(user);
		
		im.addItem(item);
	}

}
