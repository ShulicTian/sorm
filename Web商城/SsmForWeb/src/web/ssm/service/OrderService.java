package web.ssm.service;

import java.util.Date;
import java.util.List;

import web.ssm.po.item.Item;
import web.ssm.po.order.Order;
import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.User;

public interface OrderService {
	
	public Ordermsg createOrder(String orderNum,Date createTime,User user,int number, Item item);

	public Ordermsg createOrdermsg(Order o,int number, Item item);

	public void addItem(User user, Item item);

	public List<Integer> getCart(User attribute);
}
