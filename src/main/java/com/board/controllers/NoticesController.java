package com.board.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.models.Notice;
import com.board.models.User;
import com.board.services.AppService;
import com.board.util.FileUploadUtil;

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


//-------------------------------------POST AND FORMS-----------------------------------------------


@RequestMapping( value = "/publish", method = RequestMethod.POST )
public String addNewImage(@RequestParam("topic") String topic,
		@RequestParam("desc") String desc,
		@RequestParam("newImage1") MultipartFile multipartFile1,
		@RequestParam("newImage2") MultipartFile multipartFile2,
		@RequestParam("link") String link,
		@RequestParam(value = "important", defaultValue = "off") String important , 
		HttpSession session, RedirectAttributes redirectAttributes
		) throws IOException {

	
	
//<Validations>
	boolean isValid = true;
	
	if (topic.isEmpty() || desc.isEmpty() ) {
		redirectAttributes.addFlashAttribute("errorMessage2", "📃 El tema o la descripción no pueden estar vacíos");
		isValid = false;
	}
	if (topic.length() < 5) {
		redirectAttributes.addFlashAttribute("errorMessage3", "🧧 El tema debe ser de al menos 5 caracteres");
		isValid = false;
	}
	if (desc.length() < 5) {
		redirectAttributes.addFlashAttribute("errorMessage4", "🧧 La descripcion debe ser de al menos 5 caracteres");
		isValid = false;
	}
//<Validations>

	if(isValid) {
	
	Long user_id =  (Long) session.getAttribute("user_id");
	List<User> current = as.findUsersUsingID(user_id);
	User currentU = as.findUsingID(user_id);

	
	String fileName1 = "1" + currentU.getUser_id() + StringUtils.cleanPath(multipartFile1.getOriginalFilename());
	String fileName2 = "2" + currentU.getUser_id() + StringUtils.cleanPath(multipartFile2.getOriginalFilename());
	
	
	if (fileName2.equals("2" + currentU.getUser_id()) && fileName1.equals("1" + currentU.getUser_id())) {
		Notice newnotice = new Notice(topic,desc,link,important,current);
		as.publishNotice(newnotice);
		return "redirect:/publicar";
	}
	
	
    if (!fileName2.equals("2" + currentU.getUser_id()) && !fileName1.equals("1" + currentU.getUser_id())) {
	Notice newnotice = new Notice(topic,desc,"/images/" + fileName1,"/images/" + fileName2,link,important,current);
	as.publishNotice(newnotice);
	String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
	FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);
	FileUploadUtil.saveFile(fileLocation, fileName2, multipartFile2);
	return "redirect:/publicar";
    }
	
    if (!fileName1.equals("1" + currentU.getUser_id())) {
    	Notice newnotice = new Notice(topic,desc,"/images/" + fileName1, link,important,current);
    	as.publishNotice(newnotice);
    	String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
        FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);
    	return "redirect:/publicar";
    }
    
    if (!fileName2.equals("2" + currentU.getUser_id()) && fileName1.equals("1" + currentU.getUser_id())) {
		redirectAttributes.addFlashAttribute("errorMessage1", "🧧 Error: Utiliza el primer espacio para subir una sola imagen");
    }
    
	}
    
	return "redirect:/publicar";
	
}










}//End Controller

