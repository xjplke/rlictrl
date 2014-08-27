package cn.adfi.rlictrl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import cn.adfi.rlictrl.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	@Query("select a from User a where a.username = :username") 
	List<User> findUserByUsername(@Param("username")String username);
}
