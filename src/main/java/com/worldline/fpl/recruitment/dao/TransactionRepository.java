package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {


	/**
	 * Get transaction by Id
	 *
	 * @param id
	 *            id of the transaction to get
	 * @return the transaction corresponding to the given id or null
	 */
	Transaction findById(String id);

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);
	/**
	 * Delete transaction from account
	 * @param accountId
	 * 			the account Id
	 * @param transactionId
	 * 			The transaction Id
	 */
	void deleteTransactionFromAccount(String accountId, String transactionId);
	
	
	/**
	 * check if transaction exist
	 * @param transactionId
	 * 			transaction ID
	 * @return
	 * 			true if transaction exist
	 */
	boolean transactionExists(String transactionId);
	
	
	/**
	 * Check if transaction belong to acount
	 * @param accountId
	 * 			The account ID
	 * @param transactionId
	 * 			The transaction ID
	 * @return
	 * 			Return true if transaction belong to account
	 */
	boolean transactionBelongToAccount(String accountId,String transactionId);
	
}
