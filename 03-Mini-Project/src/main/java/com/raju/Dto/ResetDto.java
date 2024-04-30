package com.raju.Dto;

import lombok.Data;

@Data
public class ResetDto {

	private Integer uid;
	private String email;
	private String oldpwd;
	private String newpwd;
	private String confrimpwd;
	
	
}
