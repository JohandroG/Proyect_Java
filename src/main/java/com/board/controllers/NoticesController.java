package com.board.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
 
//================================================
@RequestMapping(value="/publicar", method=RequestMethod.GET)
public String Register(HttpSession session, Model model) {
	
	Long user_id =  (Long) session.getAttribute("user_id");
	User current = as.findUsingID(user_id);

		if(current != null) {
			model.addAttribute("userInfo", current );
		}
	
	
	if(current == null) {
		return "redirect:/";
	}
	
	
	
    return "publish.jsp";
}

//================================================
@RequestMapping(value="/editar/{id}", method=RequestMethod.GET)
public String update(@PathVariable("id") Long id,HttpSession session, Model model) {
	
	Long user_id =  (Long) session.getAttribute("user_id");
	User current = as.findUsingID(user_id);
	if(current == null) {
		return "redirect:/";
	}
	if(current != null) {
		model.addAttribute("userInfo", current );
	}
	
	
	Notice currentN = as.findNUsingID(id);

	model.addAttribute("noticeInfo", currentN );
	model.addAttribute("UserInfo", current );

    return "updatepublished.jsp";
}



@RequestMapping( value = "/search", method = RequestMethod.POST )
public String home(@RequestParam("search") String word,
		@RequestParam(value = "important", defaultValue = "off") String important,
		HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	
	Long user_id =  (Long) session.getAttribute("user_id");
	User current = as.findUsingID(user_id);
	
	
	if (word.isEmpty() && important.equalsIgnoreCase("off")) {
		redirectAttributes.addFlashAttribute("indexmessage4", "❓ El campo de busqueda está vacío, Ingresa algo para buscar");
		return "redirect:/";
	}
	
	if (word.isEmpty() && important.equalsIgnoreCase("on")) {
		List<Notice> allnotices = as.searchImp(); 
		model.addAttribute("noticesInfo", allnotices );
	}
	
	if (important.equalsIgnoreCase("off")) {
		List<Notice> allnotices = as.searchResults(word); 
		model.addAttribute("noticesInfo", allnotices );
	}
	if (important.equalsIgnoreCase("on")) {
		List<Notice> allnotices = as.searchResultsImp(word); 
		model.addAttribute("noticesInfo", allnotices );
	}
	
	
		if(current != null) {
			model.addAttribute("userInfo", current );
		}

		model.addAttribute("word", "filter" );
		return "index.jsp";
		
	
}




//-------------------------------------POST AND FORMS-----------------------------------------------


//================================================
@RequestMapping( value = "/publish", method = RequestMethod.POST )
public String publish(@RequestParam("topic") String topic,
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
		redirectAttributes.addFlashAttribute("indexmessage5", "📃✔ Anuncio publicado con exito!");
		return "redirect:/";
	}
	
	
    if (!fileName2.equals("2" + currentU.getUser_id()) && !fileName1.equals("1" + currentU.getUser_id())) {
	Notice newnotice = new Notice(topic,desc,"/images/" + fileName1,"/images/" + fileName2,link,important,current);
	as.publishNotice(newnotice);
	String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
	FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);
	FileUploadUtil.saveFile(fileLocation, fileName2, multipartFile2);
	redirectAttributes.addFlashAttribute("indexmessage5", "📃✔ Anuncio publicado con exito!");
	return "redirect:/";
    }
	
    if (!fileName1.equals("1" + currentU.getUser_id())) {
    	Notice newnotice = new Notice(topic,desc,"/images/" + fileName1, link,important,current);
    	as.publishNotice(newnotice);
    	String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
        FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);
        redirectAttributes.addFlashAttribute("indexmessage5", "📃✔ Anuncio publicado con exito!");
		return "redirect:/";
    }
    
    if (!fileName2.equals("2" + currentU.getUser_id()) && fileName1.equals("1" + currentU.getUser_id())) {
		redirectAttributes.addFlashAttribute("errorMessage1", "🧧 Error: Utiliza el primer espacio para subir una sola imagen");
    }
    
	}
    
	return "redirect:/publicar";
	
}



//================================================
@RequestMapping( value = "/update", method = RequestMethod.POST )
public String edit(@RequestParam("topic") String topic,
		@RequestParam("desc") String desc,
		@RequestParam("newImage1") MultipartFile multipartFile1,
		@RequestParam("newImage2") MultipartFile multipartFile2,
		@RequestParam("link") String link,
		@RequestParam("notice_id") Long notice_id,
		@RequestParam(value = "important", defaultValue = "off") String important ,
		@RequestParam("img1") String img1,
		@RequestParam("img2") String img2,
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
			
			
			if (!img1.isEmpty()) {
	    		String img1name = img1.substring(8,img1.length());
	    		File img1File = new File (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img1name );
	    		img1File.delete();
	    		}
			
			if (!img2.isEmpty()) {
	    		String img2name = img2.substring(8,img2.length());
	    		File img2File =  new File  (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img2name );
	    		img2File.delete();
	    		}
			
			
			Notice newnotice = new Notice(notice_id,topic,desc,link,important,current);
			as.publishNotice(newnotice);
			
			redirectAttributes.addFlashAttribute("indexmessage6", "📃✔ Anuncio actualizado con exito!");
			return "redirect:/";
		}
		
		
	    if (!fileName2.equals("2" + currentU.getUser_id()) && !fileName1.equals("1" + currentU.getUser_id())) {
		
	    	if (!img1.isEmpty()) {
	    		String img1name = img1.substring(8,img1.length());
	    		File img1File = new File (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img1name );
	    		img1File.delete();
	    		}
			
			if (!img2.isEmpty()) {
	    		String img2name = img2.substring(8,img2.length());
	    		File img2File =  new File  (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img2name );
	    		img2File.delete();
	    		}	
	    	
	    Notice newnotice = new Notice(notice_id,topic,desc,"/images/" + fileName1,"/images/" + fileName2,link,important,current);
		as.publishNotice(newnotice);
		String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
		FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);
		FileUploadUtil.saveFile(fileLocation, fileName2, multipartFile2);
		redirectAttributes.addFlashAttribute("indexmessage6", "📃✔ Anuncio actualizado con exito!");
		return "redirect:/";
	    }
		
	    if (!fileName1.equals("1" + currentU.getUser_id())) {
	    	
	    	if (!img1.isEmpty()) {
	    		String img1name = img1.substring(8,img1.length());
	    		File img1File = new File (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img1name );
	    		img1File.delete();
	    		}
			
			if (!img2.isEmpty()) {
	    		String img2name = img2.substring(8,img2.length());
	    		File img2File =  new File  (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img2name );
	    		img2File.delete();
	    		}

	    	Notice newnotice = new Notice(notice_id,topic,desc,"/images/" + fileName1, link,important,current);
	    	as.publishNotice(newnotice);
	    	String fileLocation = new File("src/main/resources/static/images").getAbsolutePath();
	        FileUploadUtil.saveFile(fileLocation, fileName1, multipartFile1);

	        redirectAttributes.addFlashAttribute("indexmessage6", "📃✔ Anuncio actualizado con exito!");
			return "redirect:/";
	    }
	    
	    if (!fileName2.equals("2" + currentU.getUser_id()) && fileName1.equals("1" + currentU.getUser_id())) {
			redirectAttributes.addFlashAttribute("errorMessage1", "🧧 Error: Utiliza el primer espacio para subir una sola imagen");
	    }

	    
		}
	    
		return "redirect:/editar/" + notice_id;
		
	}

//================================================	
	@RequestMapping( value = "/delete", method = RequestMethod.POST )
	public String delete(@RequestParam("notice_id") Long notice_id,
			@RequestParam("img2") String img2,
			@RequestParam("img1") String img1,
			RedirectAttributes redirectAttributes
			){
		
		if (!img1.isEmpty()) {
		String img1name = img1.substring(8,img1.length());
		File img1File = new File (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img1name );
		img1File.delete();
		}

		if (!img2.isEmpty()) {
		String img2name = img2.substring(8,img2.length());
		File img2File =  new File  (new File("src/main/resources/static/images").getAbsoluteFile() + "/" + img2name );
		img2File.delete();
		}
		
		
		as.deleteNotice(notice_id);
		//--------------
		
		
		redirectAttributes.addFlashAttribute("indexmessage7", "📃❌ Anuncio eliminado con exito!");
		return "redirect:/";
	}





}//End Controller

