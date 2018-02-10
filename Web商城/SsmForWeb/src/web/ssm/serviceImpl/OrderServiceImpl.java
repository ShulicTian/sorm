package web.ssm.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import web.ssm.mapper.OrderMapper;
import web.ssm.po.item.Item;
import web.ssm.po.order.Order;
import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.Cart;
import web.ssm.po.user.User;
import web.ssm.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	@Qualifier("orderMapper")
	private OrderMapper oma;

	@Override
	public Ordermsg createOrder(String orderNum,Date createTime,User user,int number, Item item) {
		
		Order o = new Order();
		o.setCreateTime(createTime);
		o.setUser(user);
		o.setOrderNum(orderNum);
		
		Ordermsg om = new Ordermsg();
		om.setNumber(number);
		om.setItem(item);
		om.setOrder(o);
		
		oma.addOrder(o);
		
		oma.addOrdermsg(om);
		
		return om;
	}

	@Override
	public Ordermsg createOrdermsg(Order o,int number, Item item) {
		
		Ordermsg om = new Ordermsg();
		om.setNumber(number);
		om.setItem(item);
		om.setOrder(o);
		if(o.getId()==0) {
			
			oma.addOrder(o);
			
		}
		oma.addOrdermsgs(om);
		return om;
	}

	@Override
	public void addItem(User user, Item item) {
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setItem(item);
		
		oma.addItem(cart);
	}

	@Override
	public List<Integer> getCart(User attribute) {
		return oma.getCarts(attribute.getId());
	}

}
