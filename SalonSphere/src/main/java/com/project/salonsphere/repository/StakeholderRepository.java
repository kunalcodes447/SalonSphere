package com.project.salonsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.salonsphere.entity.Stakeholders;

public interface StakeholderRepository extends JpaRepository<Stakeholders, Long>{
	
}
