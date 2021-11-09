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
	
	
	@Query(value = "SELECT * FROM notices ORDER BY created_at DESC", nativeQuery=true)
	List<Notice> findOrder();


	@Query( value = "SELECT * FROM notices WHERE title LIKE %?1% ORDER BY created_at DESC", nativeQuery=true )
	List<Notice> findNbyWord( String word);

	@Query( value = "SELECT * FROM notices WHERE title LIKE %?1% and importance = 'on' ORDER BY created_at DESC ", nativeQuery=true )
	List<Notice> findNbyWordImp( String word);

}

