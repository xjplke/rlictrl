package cn.adfi.rlictrl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import cn.adfi.rlictrl.model.AuthPK;
import cn.adfi.rlictrl.model.Authorization;


public interface AuthorizationRepository extends CrudRepository<Authorization, AuthPK> {
	@Query("select a from Authorization a where a.id.uuid = :uuid") 
	public List<Authorization> findAuthorizationByUUID(@Param("uuid")String uuid);
}
