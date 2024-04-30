package com.raju.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raju.entity.CityEntity;

public interface Cityrepo extends JpaRepository<CityEntity, Integer>{
	
	@Query(value="select * from CityEntity where stateid=:stateId",nativeQuery = true)
	public List<CityEntity> getCities(Integer stateId);	

}
