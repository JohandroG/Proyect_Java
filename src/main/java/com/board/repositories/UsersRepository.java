package com.board.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.board.models.User;


public interface UsersRepository extends CrudRepository <User,Long> {

	
//-----------------------------------------JAVA----------------------------------

	List<User> findAll();
	
//----------------------------------------QUERY----------------------------------
	
	@Query( "SELECT u FROM User u WHERE id = ?1" )
	User findbyId( Long user_id );
	
	@Query( "SELECT u FROM User u WHERE username = ?1" )
	List<User> AllUsersByusername( String username );
	
	@Query( "SELECT u FROM User u WHERE username = ?1" )
	User UserByusername( String username );
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO users(fullname,username,password) " +
	"VALUES(?1, ?2, ?3)", nativeQuery=true)
	void insertNewUser( String name,String username, String encryptedpassword);
}
