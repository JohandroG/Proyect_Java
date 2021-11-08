package com.board.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.board.models.Notice;
import com.board.models.User;

public interface NoticeRepository extends CrudRepository <Notice,Long> {

	
//-----------------------------------------JAVA----------------------------------

	List<Notice> findAll();
	
//----------------------------------------QUERY----------------------------------
		
		
}
