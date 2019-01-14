package web.ssm.controller;

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
import web.ssm.po.user.User;
import web.ssm.service.ItemService;

@Controller
@Scope(value="prototype")
public class ItemController {
	
	@Autowired
	@Qualifier("itemService")
	private ItemService is;
	
	@RequestMapping("/finditems")
	public ModelAndView findItems(@RequestParam(value="name", required=true) String name) {
		if(name!=null && !name.equals("")) {
			name="%"+name+"%";
		}
		List<Item> items =is.findItems(name);
		ModelAndView mav=null;
		mav=new ModelAndView("finditems");
		mav.addObject("items", items);
		
		return mav;
	}
	
	@RequestMapping("/item")
	public ModelAndView itemMsg(@RequestParam(value="id", required=true) int id,
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="much", required=true) double much,
			@RequestParam(value="photo", required=true) String photo) {
		
		Item i = new Item();
		i.setId(id);
		i.setName(name);
		i.setMuch(much);
		i.setPhoto(photo);
		
		ModelAndView mav=null;
		mav=new ModelAndView("item");
		mav.addObject("item", i);
		return mav;
	}

	@RequestMapping("/toadd")
	public ModelAndView toAdd() {
		
		ModelAndView mav=null;
		mav=new ModelAndView("addfrom");
		return mav;
	}
	
	@RequestMapping("/additem")
	public ModelAndView addItem(HttpServletRequest request,
			@RequestParam(value="name") String name,
			@RequestParam(value="much") double much,
			@RequestParam(value="photo") String photo) {
		User user = (User) request.getSession().getAttribute("user");
		
		is.addItem(user, name, much, photo);
		ModelAndView mav=null;
		mav=new ModelAndView("additem");
		return mav;
	}

}
