package com.board.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.models.Code;
import com.board.models.User;
import com.board.services.AppService;

@Controller
public class CodeController {

	private final AppService as;

	public CodeController(AppService as) {
		this.as = as;
	}

//----------------------------------------LOAD SITE----------------------------------------------------
	
	@RequestMapping(value="/cambiar/codigo", method=RequestMethod.GET)
	public String Code(HttpSession session,Model model) {
		
		Long user_id =  (Long) session.getAttribute("user_id");
		User current = as.findUsingID(user_id);

		if(current == null) {
			return "redirect:/";
		}
		if(current != null) {
			model.addAttribute("userInfo", current );
		}
		
		
		
	    return "code.jsp";
	}
	
	
//-------------------------------------POST AND FORMS-----------------------------------------------	

	@RequestMapping(value="/new/code", method=RequestMethod.POST)
	public String addAdmin(@RequestParam (value = "last") String last_code,
    		@RequestParam (value = "new") String new_code,
    		@RequestParam (value = "code_id") Long code_id,
    		HttpSession session,RedirectAttributes redirectAttributes) {
		
		boolean isValid = true;
		List<Code> match = as.seeMatch(last_code);
		
		if (new_code.isEmpty() || last_code.isEmpty()) {
    		redirectAttributes.addFlashAttribute("cerrorMessage1", "📃 Uno de los campos está incompleto");
    		isValid = false;
    	}
		if( match.size() == 0 && isValid) {
    		redirectAttributes.addFlashAttribute("cerrorMessage2", "🔎 El codigo anterior no coincide con nuestros registros");
    		isValid = false;
    	}
		if (new_code.length() < 5 && isValid ) {
    		redirectAttributes.addFlashAttribute("cerrorMessage3", "🧧 El nuevo codigo debe ser de al menos 5 caracteres");
    		isValid = false;
    	}
		
		if(isValid) {
			
			Code updatedCode = new Code(code_id,new_code);
			as.updateCode(updatedCode);
			redirectAttributes.addFlashAttribute("cerrorMessage4", "🎉 El codigo se actualizó con exito");
		}
		
		
		return "redirect:/cambiar/codigo";
	}
	
	
	
	
//-------------------------------Un solo uso	
	@RequestMapping(value="/default/code", method=RequestMethod.GET)
	public String createCode() {
		
		List<Code> match = as.exists();
		
		if (match.size() == 0 ) {
			as.defultCode();
		}
		
		return "redirect:/";
	}
	
	
	
}//End Controller
