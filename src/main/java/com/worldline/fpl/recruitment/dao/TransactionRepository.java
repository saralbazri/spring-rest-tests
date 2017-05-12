package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.worldline.fpl.recruitment.entity.Transaction;
import org.springframework.stereotype.Repository;
/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long>{


}
