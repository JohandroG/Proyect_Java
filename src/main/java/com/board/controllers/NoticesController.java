package com.board.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.models.User;
import com.board.services.AppService;

@Controller
public class NoticesController {

private final AppService as;

public NoticesController(AppService as) {
	this.as = as;
}

//----------------------------------------LOAD SITE----------------------------------------------------

@RequestMapping(value="/publicar", method=RequestMethod.GET)
public String Register(HttpSession session) {
	
	Long user_id =  (Long) session.getAttribute("user_id");
	User current = as.findUsingID(user_id);

	if(current == null) {
		return "redirect:/";
	}
	
    return "publish.jsp";
}











}//End Controller

