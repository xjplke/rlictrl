package cn.adfi.rlictrl.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.adfi.rlictrl.model.Dailyauth;
import cn.adfi.rlictrl.repo.AuthorizationRepository;
import cn.adfi.rlictrl.repo.ClientRepository;
import cn.adfi.rlictrl.repo.DailyauthRepository;

@Configuration
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@Transactional
@RequestMapping("/auth")
public class DailyauthController {
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AuthorizationRepository authRepo;
	
	@Autowired
	private DailyauthRepository dailyauthRepo;
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Dailyauth> getDailyauth(@RequestParam("page")int page,
			@RequestParam("size") int size){
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return dailyauthRepo.findAll(pageable);
	}
	
	@RequestMapping(value="/license/{key}",method=RequestMethod.GET)
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
