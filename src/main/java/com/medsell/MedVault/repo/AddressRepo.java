/**
 * 
 */
package com.medsell.MedVault.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medsell.MedVault.entity.Address;


/**
 * 
 */
public interface AddressRepo extends JpaRepository<Address, Long> {


	@Modifying
	@Query("update Address u set u.isActive = :status where u.id = :addressId")
	Address updatestatus(@Param("addressId") Long addressId, @Param("status")String status);
	
}
