package cn.adfi.rlictrl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cn.adfi.rlictrl.model.User;
import cn.adfi.rlictrl.repo.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository managerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<User> l = managerRepo.findUserByUsername(username);
		if(l!=null && l.size()>0){
			return l.get(0);
		}
		throw new UsernameNotFoundException(username+" not find!");
	}

}
