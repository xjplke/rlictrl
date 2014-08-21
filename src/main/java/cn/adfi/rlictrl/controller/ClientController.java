package cn.adfi.rlictrl.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import cn.adfi.rlictrl.exception.ClientNotFoundException;
import cn.adfi.rlictrl.model.AuthPK;
import cn.adfi.rlictrl.model.Authorization;
import cn.adfi.rlictrl.model.Client;
import cn.adfi.rlictrl.repo.AuthorizationRepository;
import cn.adfi.rlictrl.repo.ClientRepository;

@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AuthorizationRepository authRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public Client addClient(@RequestBody Client client){
		client.setCreate(new Date());
		return clientRepo.save(client);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Page<Client> getClient(@RequestParam("page")int page,
			@RequestParam("size") int size) {
		Pageable pageable = new PageRequest(page, size, Direction.DESC, "id");
		return clientRepo.findAll(pageable);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Client getClient(@PathVariable("id") UUID id) throws ClientNotFoundException {
		Client find = clientRepo.findOne(id);
		if(null == find){
			throw new ClientNotFoundException("Client not find for uuid:"+id);
		}
		return find;
	}
	
	@RequestMapping(value="/uuid/{key}",method=RequestMethod.GET)
	public Page<Client> findClientByUUID(@RequestParam("page")int page,
			@RequestParam("size")int size,
			@PathVariable("key") UUID uuid)
	{
		return clientRepo.findClientByUUID(uuid, new PageRequest(page, size, Direction.DESC, "id"));
	}
	
	@RequestMapping(value="/company/{key}",method=RequestMethod.GET)
	public Page<Client> findClientByCompany(@RequestParam("page")int page,
			@RequestParam("size")int size,
			@PathVariable("key") String company)
	{
		return clientRepo.findClientByCompany(company, new PageRequest(page, size, Direction.DESC, "id"));
	}
	
	@RequestMapping(value="/{uuid}/auth",method=RequestMethod.GET)
	public List<Authorization> getClientAuthrization(@PathVariable("uuid")UUID uuid)
	{
		return authRepo.findAuthorizationByUUID(uuid);
	}
	
	@RequestMapping(value="/{uuid}/auth",method=RequestMethod.POST)
	public void setClientAuthrization(@PathVariable("uuid")UUID uuid,
			@RequestParam("authkey")String authkey,
			@RequestParam("authvalue")String authvalue,
			@RequestParam("expire")Date expire)
	{
		List<Authorization> listauth = authRepo.findAuthorizationByUUID(uuid);
		for(Authorization auth : listauth){
			if(auth.getId().getAuthkey().equals(authkey)){
				auth.setAuthvalue(authvalue);
				auth.setLastUpdate(new Date());
				authRepo.save(auth);
				return;
			}
		}
		
		Authorization nauth = new Authorization();
		AuthPK pk = new AuthPK();
		pk.setUuid(uuid);
		pk.setAuthkey(authkey);
		
		nauth.setId(pk);
		nauth.setAuthvalue(authvalue);
		nauth.setExpire(expire);
		nauth.setCreate(new Date());
		nauth.setLastUpdate(new Date());
		
		authRepo.save(nauth);
		return;
	}
}

