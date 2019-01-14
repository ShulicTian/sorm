package web.ssm.mapper;

import java.util.List;

import web.ssm.po.item.Item;

public interface ItemMapper {
	
	public List<Item> findItems(String name);
	
	public Item getItem(int id);

	public void addItem(Item item);
}
