package com.board.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.board.models.Code;
import com.board.models.User;
import com.board.repositories.AssociationRepository;
import com.board.repositories.CodeRepository;
import com.board.repositories.NoticeRepository;
import com.board.repositories.UsersRepository;

@Service
public class AppService {

private final UsersRepository ur;
private final CodeRepository cr;
private final NoticeRepository nr;
private final AssociationRepository ar;

public AppService(UsersRepository ur, CodeRepository cr, NoticeRepository nr, AssociationRepository ar) {
	this.ur = ur;
	this.cr = cr;
	this.nr = nr;
	this.ar = ar;
}
	
//=========================================================================================

//-----------------------------------------------User--------------------------------------

public List<User> findAllU(){
	return ur.findAll();
}

public User findUsingID(Long user_id) {
	return ur.findbyId(user_id);
}

public User getUserByusername( String username ){
	return ur.UserByusername(username);
}

public List<User> getlistofUsersByusername( String username ){
	return ur.AllUsersByusername(username);
}

public void  registerUser( String name,String username, String password) {
	String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	ur.insertNewUser(name,username,encryptedPassword);
}

public Boolean validateUser (User currentUser, String password){
	return BCrypt.checkpw( password, currentUser.getPassword() );
}


//-----------------------------------------------Notice--------------------------------------



//-----------------------------------------------Code--------------------------------------

public List<Code> seeMatch( String code ){
	return cr.matchcode(code);
}


//-----------------------------------------------Association--------------------------------------







}
