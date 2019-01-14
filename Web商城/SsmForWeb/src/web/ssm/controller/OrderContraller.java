package web.ssm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.ssm.po.item.Item;
import web.ssm.po.order.Order;
import web.ssm.po.ordermsg.Ordermsg;
import web.ssm.po.user.User;
import web.ssm.service.ItemService;
import web.ssm.service.OrderService;

@Controller
@Scope("prototype")
public class OrderContraller {
	
	@Autowired
	@Qualifier("itemService")
	private ItemService is;
	@Autowired
	@Qualifier("orderService")
	private OrderService os;
	
	@RequestMapping("/buy")
	public ModelAndView buy(@RequestParam(value="id",required=true) int id
			,HttpServletRequest request) {
		
		Ordermsg om = os.createOrder("0100"+id, new Date(), (User)request.getSession().getAttribute("user"),1, is.getItem(id));
		
		ModelAndView mav=null;
		mav=new ModelAndView("buy");
		mav.addObject("order", om);
		return mav;
	}
	
	@RequestMapping("/addcart")
	public ModelAndView addCart(@RequestParam(value="id",required=true) int id,HttpServletRequest request) {
		
		os.addItem((User)request.getSession().getAttribute("user"),is.getItem(id));
		ModelAndView mav=null;
		mav=new ModelAndView("item");
		mav.addObject("item", is.getItem(id));
		return mav;
	}
	
	@RequestMapping("/cart")
	public ModelAndView getCart(HttpServletRequest request) {
		List<Integer> itemids = os.getCart((User)request.getSession().getAttribute("user"));
		List<Item> items=new ArrayList<Item>();
		for(int i:itemids) {
			
			items.add(is.getItem(i));
			
		}
		ModelAndView mav=null;
		mav=new ModelAndView("cart");
		mav.addObject("items", items);
		return mav;
	}
	
	@RequestMapping("/buyall")
	public ModelAndView buyAll(@RequestParam(value="id",required=true) String id,
			@RequestParam(value="number",required=true) String number,HttpServletRequest request) {
		Order o = new Order();
		o.setCreateTime(new Date());
		o.setUser((User)request.getSession().getAttribute("user"));
		o.setOrderNum("001"+id);
		
		String[] ids=id.split(",");
		String[] numbers=number.split(",");
		
		List<Ordermsg> ordermsgs = new ArrayList<Ordermsg>();
		for(int i=0;i<ids.length;i++) {
			System.out.println(Integer.parseInt(numbers[i]));
			ordermsgs.add(os.createOrdermsg(o, Integer.parseInt(numbers[i]), is.getItem(Integer.parseInt(ids[i]))));
		}
		
		ModelAndView mav=null;
		mav=new ModelAndView("buys");
		mav.addObject("ordermsgs",ordermsgs);
		return mav;
	}

}
