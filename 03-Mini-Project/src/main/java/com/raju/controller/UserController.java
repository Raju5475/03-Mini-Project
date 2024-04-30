package com.raju.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raju.Dto.LoginDto;
import com.raju.Dto.RegisterDto;
import com.raju.Dto.ResetDto;
import com.raju.Dto.UserDto;
import com.raju.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
		
	//To load RegisterPage
	@GetMapping("/register")
	 public String registerPage(Model model) {
		 	
		model.addAttribute("reg",new RegisterDto());
		model.addAttribute("country",service.getCountries());
		
		
		 return "registerView";
	 }
	 
	 //To insert data into registerpage
	//Handle the Registration page
	@PostMapping("/register")
	 public String register(@ModelAttribute("regDto") RegisterDto regDto,Model model) {
		 //Checking wheather the user is already registered or not
		//System.out.println(regDto.toString());
		UserDto user = service.getUser(regDto.getEmail());
		
		if(user!=null) {
			model.addAttribute("errmsg","Duplicate email");
			return "registerView";
		}
	
		boolean registerUser = service.registerUser(regDto);
		System.out.println("down");
		if(registerUser) {
			model.addAttribute("smsg","Registration SucessFull..!");
		}
		else {
			model.addAttribute("emsg","Registration Failed..!");
			
		}
		
		 return "registerView";
	 }
	 
	@GetMapping("/states/{cid}")
	@ResponseBody
	 public Map<Integer,String> getStates(@PathVariable("cid") Integer cid){
		 
		 return service.getStates(cid);
	 }
	
	@GetMapping("/cities/{sid}")
	@ResponseBody
    public Map<Integer,String> getCities(@PathVariable("sid") Integer sid){
		 
		 return service.getCities(sid);
	 }
    
    
	//To load the index page
	@GetMapping("/")
	 // Assuming this is the endpoint for displaying the login form
	public String showLoginForm(Model model) {
	    model.addAttribute("login", new LoginDto()); // Add a new instance of LoginDto to the model
	    return "index"; // Return the view name for the login form
	}

    //To perform Successful login
	@PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDto loginDto,Model model) {
		 UserDto getuser = service.getuser(loginDto);
		 
		 if(getuser==null) {
			 model.addAttribute("emsg","Invalid Credintials..!");
			 return "index";
		 }
		 if("YES".equals(getuser.getUpdatedPwd())) {
			 return "redirect:dashboard";
		 }else {
			 ResetDto dto=new ResetDto();
			 dto.setEmail(getuser.getEmail());
			 
			 model.addAttribute("reset",  dto);
			 return "resetView";
		 }
		
    
    }
	
	@PostMapping("/reset")
    public String resetPwd(ResetDto resetdto,Model model) {
		
		UserDto user = service.getUser(resetdto.getEmail());
		
		if((resetdto.getNewpwd().equals(resetdto.getOldpwd()))) {
			model.addAttribute("errmsg","Old password is incorrect");
			return "resetView";
		}
		
		if(user.getPassword().equals(resetdto.getOldpwd())) {
			//Setting the given password with 
			boolean resetPwd = service.resetPwd(resetdto);
			if(resetPwd) {
				return "redirect:dashboard";
			}else {
				model.addAttribute("emsg","password update failed");
			}
			
		}else {
			model.addAttribute("errmsg","Old password is incorrect");
			return "resetView";
		}
		
    	return"resetView";
    }
    
	@GetMapping("/dashBoard")
    public String DashBoard(Model model) {
		model.addAttribute("dashboard",service.getQuotes());
    	return "dashboard";
    }
    
	@GetMapping("/logout")
    public String logOut() {
    	return "redirect:/";
    }
    
	
}
