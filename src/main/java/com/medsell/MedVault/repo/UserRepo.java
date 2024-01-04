/**
 * 
 */
package com.medsell.MedVault.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsell.MedVault.entity.User;


/**
 * 
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	long countByIsActive(int isActive);

//	@Modifying
//	@Query("update User u set u.isActive = :status where u.id = :userId")
//	boolean updatestatus(@Param("status")String status,@Param("userId") Integer userId);
//	
	
//	   @Query("SELECT u FROM User u WHERE u.id = :userId")
//	    User findUserById(@Param("userId") Long userId);
}
