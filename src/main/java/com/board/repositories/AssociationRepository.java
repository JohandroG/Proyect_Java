package com.board.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.board.models.Association;

public interface AssociationRepository extends CrudRepository <Association,Long> {

	
	//-----------------------------------------JAVA----------------------------------

		List<Association> findAll();
		
	//----------------------------------------QUERY----------------------------------


}
