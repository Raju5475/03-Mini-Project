package com.raju.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raju.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer>{

	
	
	@Query(value="select * from StateEntity where countryid=:cid",nativeQuery = true)
	public List<StateEntity> getStates(Integer cid);
}
