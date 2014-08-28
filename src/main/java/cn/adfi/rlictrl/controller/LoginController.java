package cn.adfi.rlictrl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cn.adfi.rlictrl.exception.UsernameOrPassowordError;
import cn.adfi.rlictrl.model.User;
import cn.adfi.rlictrl.repo.UserRepository;

@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public User login(@RequestBody User user) throws UsernameOrPassowordError{
		List<User> l = userRepo.findUserByUsername(user.getUsername());
		if(null==l ||  l.size() != 1){
			throw new UsernameOrPassowordError("Username or Passoword Error!"); 
		}
		User find = l.get(0);
		if(!find.getPassword().equals(user.getPassword())){
			throw new UsernameOrPassowordError("Username or Passoword Error!"); 
		}
		
		find.setPassword("");
		find.setId(0L);
		
		return find;
	}


}
