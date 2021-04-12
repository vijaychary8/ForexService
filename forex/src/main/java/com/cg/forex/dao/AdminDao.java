package com.cg.forex.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.forex.dto.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin, Long>{
	@Query("select a from Admin a where email=?1 and password=?2")
	Admin validateEmail( String email, String password);
	
	

}
