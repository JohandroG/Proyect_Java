package com.board.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.board.models.Code;
import com.board.models.Notice;
import com.board.models.User;

public interface CodeRepository extends CrudRepository <Code,Long> {

	
//-----------------------------------------JAVA----------------------------------

	List<Code> findAll();
	
	
	
//----------------------------------------QUERY----------------------------------

	@Query(value = "SELECT * FROM hostcode WHERE hcode = ?1", nativeQuery=true)
	List<Code> matchcode( String code );
	
	@Query(value = "SELECT * FROM hostcode WHERE code_id = 1", nativeQuery=true)
	List<Code> exists();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO hostcode(code_id,hcode) " +
	"VALUES(1,'123456')", nativeQuery=true)
	void defaultCode();
	

}
