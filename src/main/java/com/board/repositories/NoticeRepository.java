package com.board.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.board.models.Code;
import com.board.models.Notice;
import com.board.models.User;

public interface NoticeRepository extends CrudRepository <Notice,Long> {

	
//-----------------------------------------JAVA----------------------------------

	List<Notice> findAll();
	
//----------------------------------------QUERY----------------------------------
	@Query(value = "SELECT * FROM notices WHERE notice_id = ?1", nativeQuery=true)
	Notice findNbyID( Long notice_id );
		
}
