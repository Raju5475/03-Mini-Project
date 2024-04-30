package com.raju.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.Dto.LoginDto;
import com.raju.Dto.QuoteDto;
import com.raju.Dto.RegisterDto;
import com.raju.Dto.ResetDto;
import com.raju.Dto.UserDto;
import com.raju.entity.CityEntity;
import com.raju.entity.CountryEntity;
import com.raju.entity.StateEntity;
import com.raju.entity.UserEntity;
import com.raju.repo.Cityrepo;
import com.raju.repo.CountryRepo;
import com.raju.repo.StateRepo;
import com.raju.repo.UserRepo;
import com.raju.uitls.EmailUtils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private CountryRepo cRepo;
	
	@Autowired
	private StateRepo srepo;
	
	@Autowired
	private Cityrepo ctrepo;
	
	@Autowired
	private EmailUtils emailutils;
	
		@Override
		public Map<Integer, String> getCities(Integer sid) {
			Map<Integer,String> citymap=new HashMap();
			List<CityEntity> cities = ctrepo.getCities(sid);
			
			cities.forEach(c ->{
				citymap.put(c.getCityid(),c.getCityName());
				
			});
			
			return citymap;
		}
		
		@Override
		public Map<Integer, String> getCountries() {
		Map<Integer,String> countrymap=new HashMap();
		
		List<CountryEntity> list = cRepo.findAll();
		
		list.forEach(
				
				c ->{
					countrymap.put(c.getCountryid(),c.getCName());
				}
				);
		
		return countrymap;
		}
		
		@Override
		public String getQuotes() {
			
			QuoteDto[] quotes=null;
			String url="https://type.fit/api/quotes";
			
			//Web Service call
			RestTemplate template=new RestTemplate();
			 ResponseEntity<String> forEntity 
			 = template.getForEntity(url, String.class);
			String resbody = forEntity.getBody();
			
			//converting response body to java object by using object mapper
			
			ObjectMapper mapper=new ObjectMapper();
			try {
				 quotes = mapper.readValue(resbody,QuoteDto[].class);
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
			Random r=new Random();
			
			int index = r.nextInt(quotes.length-1);
		return quotes[index].getText();
		}
		
		@Override				
		public Map<Integer, String> getStates(Integer cid) {
			
			Map<Integer,String> statemap=new HashMap();
			List<StateEntity> states = srepo.getStates(cid);
			
			states.forEach(s ->{
				statemap.put(s.getStateid(), s.getSName());
			});
			
			
			
		return statemap;
		} 
		
		
		@Override
		public UserDto getuser(LoginDto login) {
		
			UserEntity entity = 
					uRepo.findByEmailAndPassword(login.getEmail(),login.getPassword());
			
			if(entity==null) {
				return null;
			}
			ModelMapper mapper=new ModelMapper();
			return mapper.map(entity,UserDto.class);
			
		}
		
		@Override
		public UserDto getUser(String email) {
			
			UserEntity entity = uRepo.findByEmail(email);
			
			UserDto dto=new UserDto();
			BeanUtils.copyProperties(entity, dto);
			
		return dto;
		}
		
		
		@Override
		public boolean registerUser(RegisterDto regdto) {
		
			ModelMapper mapper= new ModelMapper();
			
			UserEntity entity = mapper.map(regdto,UserEntity.class);
			
			CountryEntity Ctr = 
					cRepo.findById(regdto.getCountryid()).orElseThrow();
			
			StateEntity state = 
					srepo.findById(regdto.getStateid()).orElseThrow();
			
			CityEntity city = 
					ctrepo.findById(regdto.getCityid()).orElseThrow();
			
			entity.setCountryid(Ctr);
			entity.setStateid(state);
			entity.setCityid(city);
			entity.setPassword(generateRandom());
			entity.setUpdatedPwd("No");
			
			UserEntity save = uRepo.save(entity);
			String sub="Your registration Status";
			String body="Your temporary password "+entity.getPassword();
			
			emailutils.sendmail(regdto.getEmail(),sub, body);
			
		return save.getUid()!=null;
		}
		
		@Override
		public boolean resetPwd(ResetDto reset) {
		
			UserEntity entity = 
					uRepo.findByEmailAndPassword(reset.getEmail(),reset.getOldpwd());
			
			if(entity!=null) {
				
				entity.setPassword(getQuotes());
				entity.setUpdatedPwd("Yes");
				uRepo.save(entity);
				return true;
			}
			
		return false;
		}
		
		
		private static String generateRandom() {
			
			 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

		        Random random = new Random();

		   
		        StringBuilder password = new StringBuilder();

		      
		        for (int i = 0; i < 5; i++) {
		            // Select a random index from the characters string
		            int randomIndex = random.nextInt(characters.length());

		            // Append the character at the random index to the password
		            password.append(characters.charAt(randomIndex));
		        }

		        // Convert StringBuilder to String and return the password
		        return password.toString();
		}
		

}
