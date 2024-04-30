package com.raju.Dto;

import lombok.Data;

@Data

public class UserDto {

	
	private Integer uid;
	private String name;
	private String email;
	private String phn;
	private String password;
	private String updatedPwd;
	private Integer countryid;
	private Integer stateid;
	private Integer cityid;
	
	private String oldpwd;
	private String newpwd;
	private String confrimpwd;
	
}
