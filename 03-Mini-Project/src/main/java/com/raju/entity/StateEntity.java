package com.raju.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class StateEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stateid;
	
	private String sName;
	
	@ManyToOne
	@JoinColumn(name="countryid")
	private CountryEntity country;
	
	
}
