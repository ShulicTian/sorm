package web.ssm.mapper;

import java.util.List;

import web.ssm.po.order.Order;
import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.Cart;

public interface OrderMapper {

	public void addOrdermsg(Ordermsg om);
	
	public void addOrdermsgs(Ordermsg om);
	
	public void addOrder(Order om);
	
	public void addItem(Cart cart);

	public List<Integer> getCarts(Integer id);
}
