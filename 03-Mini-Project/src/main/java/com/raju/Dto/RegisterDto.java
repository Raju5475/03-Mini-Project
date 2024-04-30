package com.raju.Dto;

import lombok.Data;

@Data
public class RegisterDto {
	
	private String name;
	private String email;
	private String phn;
	
	private Integer countryid;
	private Integer stateid;
	private Integer cityid;
	
	@Override
	public String toString() {
		return "RegisterDto [name=" + name + ", email=" + email + ", phn=" + phn + ", countryid=" + countryid
				+ ", stateid=" + stateid + ", cityid=" + cityid + "]";
	}

}
