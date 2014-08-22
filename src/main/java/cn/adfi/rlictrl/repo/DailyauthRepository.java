package cn.adfi.rlictrl.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import cn.adfi.rlictrl.model.Client;
import cn.adfi.rlictrl.model.Dailyauth;

public interface DailyauthRepository extends PagingAndSortingRepository<Dailyauth, Long> {
	@Query("select a from Dailyauth a where a.uuid like :uuid%") 
	public Page<Dailyauth> findDailyauthByUUID(@Param("uuid")String uuid,Pageable pageable);
	
	@Query("select a from Dailyauth a where a.localkey like %:localkey%") 
	public Page<Dailyauth> findDailyauthByLocalkey(@Param("localkey")String localkey,Pageable pageable);

	@Query("select a from Dailyauth a where a.localkey like %:requireip%") 
	public Page<Dailyauth> findDailyauthByRequireip(@Param("requireip")String requireip,Pageable pageable);

}
