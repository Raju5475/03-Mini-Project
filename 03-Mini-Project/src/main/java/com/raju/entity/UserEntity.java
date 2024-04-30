package com.raju.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	private String name;
	private String email;
	private String phn;
	private String password;
	private String updatedPwd; 
	
    @ManyToOne
	@JoinColumn(name="cid")
	private CountryEntity countryid;
	
    @ManyToOne
	@JoinColumn(name="sid")
	private StateEntity stateid;
	
    @ManyToOne
	@JoinColumn(name="cityid")
	private CityEntity cityid;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate UpdatedDate;
	
	
	
}
