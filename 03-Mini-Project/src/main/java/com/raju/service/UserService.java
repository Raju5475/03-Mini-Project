package com.raju.service;

import java.util.Map;

import com.raju.Dto.LoginDto;
import com.raju.Dto.RegisterDto;
import com.raju.Dto.ResetDto;
import com.raju.Dto.UserDto;

public interface UserService {
	
	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer cid);
	public Map<Integer,String> getCities(Integer sid);
	
	public UserDto getUser(String email);
	public boolean registerUser(RegisterDto regdto);
	public UserDto getuser(LoginDto login);
	
	public boolean resetPwd(ResetDto reset);
	
	public String getQuotes();//api call

}
