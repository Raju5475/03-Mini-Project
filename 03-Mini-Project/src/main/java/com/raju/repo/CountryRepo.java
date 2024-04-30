package com.raju.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raju.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {

}
