package com.board.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.models.Code;
import com.board.models.User;
import com.board.services.AppService;

@Controller
public class UsersController {

private final AppService as;

public UsersController(AppService as) {
	this.as = as;
}

//----------------------------------------LOAD SITE----------------------------------------------------



//========================================Load register
	@RequestMapping(value="/registrarse", method=RequestMethod.GET)
    public String Register(HttpSession session, Model model) {
    	
    	Long user_id =  (Long) session.getAttribute("user_id");
    	User current = as.findUsingID(user_id);

    		if(current != null) {
    			model.addAttribute("userInfo", current );
    			return "redirect:/";
    		}
    		

    		
        return "register.jsp";
    }

//========================================Load login
	@RequestMapping(value="/iniciar/sesion", method=RequestMethod.GET)
    public String Login(HttpSession session, Model model) {
    	
    	Long user_id =  (Long) session.getAttribute("user_id");
    	User current = as.findUsingID(user_id);

    		if(current != null) {
    			model.addAttribute("userInfo", current );
    			return "redirect:/";
    		}
        return "login.jsp";
    }

//==================================Load Update	
	@RequestMapping(value="/editar/admin", method=RequestMethod.GET)
	public String Code(HttpSession session, Model model) {
		
		Long user_id =  (Long) session.getAttribute("user_id");
		User current = as.findUsingID(user_id);

		if(current == null) {
			return "redirect:/";
		}
		
		if(current != null) {
			model.addAttribute("userInfo", current );
		}
		
		model.addAttribute("adminInfo", current );
		
	    return "updateadmin.jsp";
	}

	
//-------------------------------------POST AND FORMS-----------------------------------------------
	
    
    
//================================ REGISTER POST
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String addAdmin(@RequestParam (value = "name") String name,
    		@RequestParam (value = "username") String username,
    		@RequestParam (value = "password") String password,
    		@RequestParam (value = "confpassword") String conf,
    		@RequestParam (value = "code") String code,
    		HttpSession session,RedirectAttributes redirectAttributes) {
	
    	List<User> match = as.getlistofUsersByusername(username);
    	boolean isValid = true;
    	boolean isValid2 = true;
    	List<Code> matchCode = as.seeMatch(code);
    	
//<Validations>
    	if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
    		redirectAttributes.addFlashAttribute("rerrorMessage2", "📃 Uno de los campos está incompleto");
    		isValid = false;
    		isValid2 = false;
    	}
    	if( match.size() > 0 ) {
    		redirectAttributes.addFlashAttribute("rerrorMessage1", "😥 Alguien mas ya tiene este usuario");
    		isValid = false;
    	}
    	if (name.length() < 5 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage3", "🧧 El nombre completo debe ser de al menos 5 caracteres");
    		isValid = false;
    	}
    	if (username.length() < 5 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage4", "🧧 El usuario debe ser de al menos 5 caracteres");
    		isValid = false;
    	}
    	if (password.length() < 8 && isValid2 ) {
    		redirectAttributes.addFlashAttribute("rerrorMessage5", "🧧 La contraseña debe ser de al menos 8 caracteres");
    		isValid = false;
    	}
	    if (!password.equals( conf )) {
	    	redirectAttributes.addFlashAttribute("rerrorMessage6","🔑 Las contraseñas no coinciden");
	    	isValid = false;
	    }
	    if( matchCode.size() == 0 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage7", "🔎 El codigo no coincide con nuestros registros");
    		isValid = false;
    	}
	    
//<Validations>
//<Insert>		    
	    if (isValid) {
	    	as.registerUser(name, username, password);
	    	
	    	User unew = as.getUserByusername(username);
	    	session.setAttribute("user_id", unew.getUser_id());
	    	redirectAttributes.addFlashAttribute("indexmessage1", "✅😀 Registro completado con exito");
	    	
	    	return "redirect:/";
	    }
	    else {
	    	return "redirect:/registrarse";
	    }
//<Insert>
}

	
//================================= LOGIN POST
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("username") String lusername, 
    		@RequestParam("password") String lpassword, 
    		HttpSession session, RedirectAttributes redirectAttributes) {
        	    	
    	User current = as.getUserByusername(lusername);
    	boolean isValid = true;
    	
//<Validations>
    	if (lusername.isEmpty() || lpassword.isEmpty()) {
    		redirectAttributes.addFlashAttribute("lerrorMessage1", "📃 Uno de los campos está incompleto");
    		isValid = false;
    	}
    	
    	if( current == null && isValid) {
    		redirectAttributes.addFlashAttribute("lerrorMessage2", "🧧 No existe ningun usuario con estos datos");
    		isValid = false;
    	}
    	
//<Validations>
    	
//<Insert>
    	if(isValid) {
    		if( as.validateUser(current, lpassword) ) {
    			User u = as.getUserByusername(lusername);
		    	session.setAttribute("user_id", u.getUser_id());
		    	redirectAttributes.addFlashAttribute("indexmessage2", "✔🎫 Iniciaste sesión exitosamente");
		    	return "redirect:/";
			}
			else {
				
		    	redirectAttributes.addFlashAttribute("lerrorMessage3", "🔑 La contraseña es incorrecta");
		    	return "redirect:/iniciar/sesion";
    	}
    	}
    	else {
    		return "redirect:/iniciar/sesion";
    	}
//<Insert>
    }		
	
	
	@RequestMapping(value="/update/admin", method=RequestMethod.POST)
	public String updateAdmin(@RequestParam (value = "name") String name,
    		@RequestParam (value = "username") String username,
    		@RequestParam (value = "password") String password,
    		@RequestParam (value = "confpassword") String conf,
    		@RequestParam (value = "code") String code,
    		@RequestParam (value = "user_id") Long user_id,
    		HttpSession session,RedirectAttributes redirectAttributes) {
	
    	List<User> match = as.getlistofUsersByusername(username);
    	boolean isValid = true;
    	boolean isValid2 = true;
    	List<Code> matchCode = as.seeMatch(code);
    	
		Long user_id2 =  (Long) session.getAttribute("user_id");
		User current = as.findUsingID(user_id2);

    	
//<Validations>
    	if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
    		redirectAttributes.addFlashAttribute("rerrorMessage2", "📃 Uno de los campos está incompleto");
    		isValid = false;
    		isValid2 = false;
    	}
    	if( !current.getUsername().equals(username) && match.size() > 0) {
    		redirectAttributes.addFlashAttribute("rerrorMessage1", "😥 Alguien mas ya tiene este usuario");
    		isValid = false;
    	}
    	if (name.length() < 5 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage3", "🧧 El nombre completo debe ser de al menos 5 caracteres");
    		isValid = false;
    	}
    	if (username.length() < 5 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage4", "🧧 El usuario debe ser de al menos 5 caracteres");
    		isValid = false;
    	}
    	if (password.length() < 8 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage5", "🧧 La contraseña debe ser de al menos 8 caracteres");
    		isValid = false;
    	}
	    if (!password.equals( conf )) {
	    	redirectAttributes.addFlashAttribute("rerrorMessage6","🔑 Las contraseñas no coinciden");
	    	isValid = false;
	    }
	    if( matchCode.size() == 0 && isValid2) {
    		redirectAttributes.addFlashAttribute("rerrorMessage7", "🔎 El codigo no coincide con nuestros registros");
    		isValid = false;
    	}
	    
//<Validations>
//<Insert>		    
	    if (isValid) {
	    	as.updateAdmin(user_id, name, username, password);
	    	redirectAttributes.addFlashAttribute("indexmessage8", "📋 Tus datos han sido actualizados");
			return "redirect:/";
	    }
	    
	    return "redirect:/editar/admin";
//<Insert>
}	

    

    
//=============================== LOGOUT
    @RequestMapping("/logout")
    public String logout( HttpSession session, RedirectAttributes redirectAttributes ) {
		session.removeAttribute("user_id");
		redirectAttributes.addFlashAttribute("indexmessage3", "🔐 Cerraste la sesión");
		return "redirect:/";
	}
    
    
    
    @RequestMapping( value = "/delete/myuser", method = RequestMethod.GET )
	public String delete(RedirectAttributes redirectAttributes,HttpSession session){
		
		
    	Long user_id =  (Long) session.getAttribute("user_id");
    	User current = as.findUsingID(user_id);

    		if(current != null) {
    			as.deleteuser(current);
    			
    			redirectAttributes.addFlashAttribute("indexmessage9", "👔❌ Tu Usuario ha sido eliminado!");
    			return "redirect:/";
    		}
		
		
		//--------------
		return "redirect:/";
	}
	
	
	
	
	

}//Controller end

