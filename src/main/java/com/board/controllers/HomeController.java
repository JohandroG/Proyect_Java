package com.board.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.models.Notice;
import com.board.models.User;
import com.board.services.AppService;

@Controller
public class HomeController {

	private final AppService as;

	public HomeController(AppService as) {
		this.as = as;
	}
	
//========================================Load main page	
    @RequestMapping("/")
    public String home(HttpSession session, Model model) {
    	
    	Long user_id =  (Long) session.getAttribute("user_id");
    	User current = as.findUsingID(user_id);

    	
    	List<Notice> allnotices = as.findOrdered(); 
    	
    		if(current != null) {
    			model.addAttribute("userInfo", current );
    		}
    		model.addAttribute("noticesInfo", allnotices );
    		
    		
    		return "index.jsp";
    		
    	
    }
	
}
