package com.worldline.fpl.recruitment.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.worldline.fpl.recruitment.entity.Account;
import org.springframework.stereotype.Repository;
/**
 * Account repository
 * 
 * @author A525125
 *
 */
@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {

	
}
