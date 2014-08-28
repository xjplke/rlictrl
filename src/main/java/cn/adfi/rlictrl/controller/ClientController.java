package cn.adfi.rlictrl.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.adfi.rlictrl.exception.ClientNotFoundException;
import cn.adfi.rlictrl.model.AuthPK;
import cn.adfi.rlictrl.model.Authorization;
import cn.adfi.rlictrl.model.Client;
import cn.adfi.rlictrl.model.ClientStatus;
import cn.adfi.rlictrl.repo.AuthorizationRepository;
import cn.adfi.rlictrl.repo.ClientRepository;
import cn.adfi.rlictrl.repo.DailyauthRepository;

@Configuration
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@Transactional
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AuthorizationRepository authRepo;
	
	@Autowired
	private DailyauthRepository dailyauthRepo;
	
	//called by client。
	@RequestMapping(method=RequestMethod.POST)
	public Client addClient(@RequestBody Client client){
		Client c = null;
		client.setCreateAt(new Date());
		c = clientRepo.save(client);
		c.setStatus(ClientStatus.TRIAL);//just can be set as TRIAL， can be change by special interface which is for admin only。
		
		setClientAuthrizationTest(c.getUuid());
		return c;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Client> getClient(@RequestParam("page")int page,
			@RequestParam("size") int size) {
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "createAt");
		return clientRepo.findAll(pageable);
	}
	
	@RequestMapping(value="/{uuid}",method=RequestMethod.GET)
	public Client getClient(@PathVariable("uuid") String uuid) throws ClientNotFoundException {
		Client find = clientRepo.findOne(uuid);
		if(null == find){
			throw new ClientNotFoundException("Client not find for uuid:"+uuid);
		}
		return find;
	}
	
	
	@RequestMapping(value="/uuid/{key}",method=RequestMethod.GET)
	public Page<Client> findClientByUUID(@RequestParam("page")int page,
			@RequestParam("size")int size,
			@PathVariable("key") String uuid)
	{
		return clientRepo.findClientByUUID(uuid, new PageRequest(page, size, Direction.DESC, "createAt"));
	}
	
	@RequestMapping(value="/company/{key}",method=RequestMethod.GET)
	public Page<Client> findClientByCompany(@RequestParam("page")int page,
			@RequestParam("size")int size,
			@PathVariable("key") String company)
	{
		return clientRepo.findClientByCompany(company, new PageRequest(page, size, Direction.DESC, "createAt"));
	}
	
	@RequestMapping(value="/{uuid}/auth",method=RequestMethod.GET)
	public List<Authorization> getClientAuthrization(@PathVariable("uuid")String uuid)
	{
		
		return authRepo.findAuthorizationByUUID(uuid);
	}
	
	//TODO:default Authrization should define a temp!! 
	public void setClientAuthrizationTest(String uuid){
		Map<String,String> auth = new HashMap<String,String>();
		auth.put("auth1", "123");
		auth.put("auth2", "value2");
		auth.put("auth4", "value3");
		
		for(Entry<String, String> e:auth.entrySet()){
			Authorization nauth = new Authorization();
			AuthPK pk = new AuthPK();
			
			pk.setUuid(uuid);
			pk.setAuthkey(e.getKey());
			nauth.setId(pk);
			nauth.setAuthvalue(e.getValue());
			
			nauth.setCreate(new Date());
			nauth.setLastUpdate(new Date());
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DATE,30);
			nauth.setExpire(ca.getTime());
			authRepo.save(nauth);
		}
	}
	
	@RequestMapping(value="/status",method=RequestMethod.GET)
	public List<String> getClientStatusList(){
		List<String> l = new ArrayList<String>();
		for(ClientStatus cs:ClientStatus.values()){
			l.add(cs.getName().getClass().getSimpleName());
		}
		return l;
	}
	
	@RequestMapping(value="/{uuid}/trial",method=RequestMethod.POST)
	public void setClientTrial(@PathVariable("uuid")String uuid,
			@RequestParam("expire")Date expire){
		Client client = clientRepo.findOne(uuid);
		client.setStatus(ClientStatus.TRIAL);
		client.setExpire(expire);
		clientRepo.save(client);
	}
	
	@RequestMapping(value="/{uuid}/contract",method=RequestMethod.POST)
	public void setClientContract(@PathVariable("uuid")String uuid,Date expire){
		Client client = clientRepo.findOne(uuid);
		client.setStatus(ClientStatus.CONTRACT);
		clientRepo.save(client);
	}
	
	@RequestMapping(value="/{uuid}/ban",method=RequestMethod.POST)
	public void setClientStatus(@PathVariable("uuid")String uuid){
		Client client = clientRepo.findOne(uuid);
		client.setStatus(ClientStatus.CONTRACT);
		clientRepo.save(client);
	}
	
	@RequestMapping(value="/{uuid}/auth",method=RequestMethod.POST)
	public void setClientAuthrization(@PathVariable("uuid")String uuid,
			@RequestBody Authorization authx)
	{
		List<Authorization> listauth = authRepo.findAuthorizationByUUID(uuid);
		for(Authorization auth : listauth){
			if(auth.getId().getAuthkey().equals(authx.getId().getAuthkey())){
				if(authx.getAuthvalue()!=null&&!authx.getAuthvalue().equals("")){
					auth.setAuthvalue(authx.getAuthvalue());
				}
				if(authx.getExpire()!=null){
					auth.setExpire(authx.getExpire());
				}
				auth.setLastUpdate(new Date());
				authRepo.save(auth);
				return;
			}
		}
		
		Authorization nauth = new Authorization();
		AuthPK pk = new AuthPK();
		pk.setUuid(uuid);
		pk.setAuthkey(authx.getId().getAuthkey());
		
		nauth.setId(pk);
		nauth.setAuthvalue(authx.getAuthvalue());
		nauth.setExpire(authx.getExpire());
		nauth.setCreate(new Date());
		nauth.setLastUpdate(new Date());
		
		authRepo.save(nauth);
		return;
	}
}

