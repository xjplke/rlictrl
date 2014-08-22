package cn.adfi.rlictrl.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import cn.adfi.rlictrl.model.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, String> {
	@Query("select a from Client a where a.uuid like :uuid%") 
	public Page<Client> findClientByUUID(@Param("uuid")String uuid,Pageable pageable);
	
	@Query("select a from Client a where a.company like %:company%") 
	public Page<Client> findClientByCompany(@Param("company")String company,Pageable pageable);
}
