package com.board.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.board.models.Code;
import com.board.models.Notice;
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

public List<User> findUsersUsingID(Long user_id) {
	return ur.findUsersbyId(user_id);
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

public void updateAdmin(Long id, String name,String username, String password) {
	String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	ur.updateUser(id, name, username, encryptedPassword);
}

public void deleteuser(User user) {
	ur.delete(user);
}


//-----------------------------------------------Notice--------------------------------------

public void publishNotice(Notice notice) {
	nr.save(notice);
}

public List<Notice> findOrdered(){
	return nr.findOrder();
}

//-----------
public List<Notice> searchResults(String word){
	return nr.findNbyWord(word);
}

public List<Notice> searchResultsImp(String word){
	return nr.findNbyWordImp(word);
}

public List<Notice> searchImp(){
	return nr.findIMP();
}

//--------------


public List<Notice> findAllN(){
	return nr.findAll();
}

public Notice findNUsingID(Long notice_id) {
	return nr.findNbyID(notice_id);
}

public void deleteNotice(Long id) {
	nr.deleteById(id);
}

//-----------------------------------------------Code--------------------------------------

public List<Code> seeMatch( String code ){
	return cr.matchcode(code);
}

public void updateCode(Code code) {
	cr.save(code);
}

//-----------------------------------------------Association--------------------------------------







}
