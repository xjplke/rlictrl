package cn.adfi.rlictrl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.adfi.rlictrl.model.Authorization;
import cn.adfi.rlictrl.model.Client;
import cn.adfi.rlictrl.model.Dailyauth;
import cn.adfi.rlictrl.repo.AuthorizationRepository;
import cn.adfi.rlictrl.repo.ClientRepository;
import cn.adfi.rlictrl.repo.DailyauthRepository;

@Configuration
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@RequestMapping("/auth")
public class DailyauthController {
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AuthorizationRepository authRepo;
	
	@Autowired
	private DailyauthRepository dailyauthRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public List<Authorization> addDailyauth(@RequestBody Dailyauth dauth,HttpServletRequest request){
		List<Authorization> lauth = new ArrayList<Authorization>();
		dauth.setRequireip(request.getRemoteAddr());
		dauth.setTime(new Date());
		dauth.setRequest(dauth.toString());
		
		//check uuid & localkey
		String uuid = dauth.getUuid();
		if (null == uuid) {
			dauth.setResponse("error:uuid is null!");
		}else{
			Client c = clientRepo.findOne(uuid);
			if (null == c) {
				dauth.setResponse("error:uuid for client not find!");
			}else{				
				lauth = authRepo.findAuthorizationByUUID(uuid);
				if (null == lauth || lauth.size() == 0) {
					dauth.setResponse("error:Authorization list is null! inner error!");
				}else{
					dauth.setResponse(lauth.toString());
				}
			}	
		}
		dailyauthRepo.save(dauth);
		return lauth;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Dailyauth> getDailyauth(@RequestParam("page")int page,
			@RequestParam("size") int size){
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return dailyauthRepo.findAll(pageable);
	}
	
	@RequestMapping(value="/uuid/{key}",method=RequestMethod.GET)
	public Page<Dailyauth> findDailyauthByUUID(@PathVariable("key")String uuid, 
			@RequestParam("page")int page,
			@RequestParam("size") int size){
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return dailyauthRepo.findDailyauthByUUID(uuid,pageable);
	}
	
	@RequestMapping(value="/requireip/{key}",method=RequestMethod.GET)
	public Page<Dailyauth> findDailyauthByRequireip(@PathVariable("key")String requireip, 
			@RequestParam("page")int page,
			@RequestParam("size") int size){
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return dailyauthRepo.findDailyauthByRequireip(requireip,pageable);
	}
	
	@RequestMapping(value="/localkey/{key}",method=RequestMethod.GET)
	public Page<Dailyauth> findDailyauthByLocalkey(@PathVariable("key")String localkey, 
			@RequestParam("page")int page,
			@RequestParam("size") int size){
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return dailyauthRepo.findDailyauthByLocalkey(localkey,pageable);
	}
}
