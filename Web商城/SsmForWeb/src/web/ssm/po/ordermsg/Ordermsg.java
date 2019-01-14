package web.ssm.po.ordermsg;

import web.ssm.po.item.Item;
import web.ssm.po.order.Order;

public class Ordermsg {
	
	private int id;
	private int number;
	private Order order;
	private Item item;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
