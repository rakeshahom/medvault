/**
 * 
 */
package com.medsell.MedVault.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsell.MedVault.entity.Role;


/**
 * 
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(String role);

	
	

//	@Modifying
//	@Query("update User u set u.isActive = :status where u.id = :userId")
//	boolean updatestatus(@Param("status")String status,@Param("userId") Integer userId);
//	
	
//	   @Query("SELECT u FROM User u WHERE u.id = :userId")
//	    User findUserById(@Param("userId") Long userId);
}
