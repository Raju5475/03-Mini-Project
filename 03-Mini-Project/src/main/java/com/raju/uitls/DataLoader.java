package com.raju.uitls;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.raju.entity.CountryEntity;
import com.raju.entity.StateEntity;
import com.raju.repo.CountryRepo;


@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	CountryRepo crepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//Insert the data into the country,state,city
		
		
		
	}

}
