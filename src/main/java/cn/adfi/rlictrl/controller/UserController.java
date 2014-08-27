package cn.adfi.rlictrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.adfi.rlictrl.model.User;
import cn.adfi.rlictrl.repo.UserRepository;

@Configuration
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@Transactional
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public User addUser(@RequestBody User user){
		return userRepo.save(user);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public User getUser(@PathVariable("id") Long id){
		return userRepo.findOne(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
		return userRepo.save(user);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id")  Long id){
		userRepo.delete(id);
	}
}
