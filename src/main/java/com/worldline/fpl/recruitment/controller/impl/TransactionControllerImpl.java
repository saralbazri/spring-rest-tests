package com.worldline.fpl.recruitment.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.fpl.recruitment.controller.TransactionController;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.json.TransactionResponse;
import com.worldline.fpl.recruitment.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of {@link TransactionController}
 * 
 * @author A525125
 *
 */
@Slf4j
@RestController
public class TransactionControllerImpl implements TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionControllerImpl(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * get list transactions by account
	 * @param accountId
	 * 			The account ID
	 * @param p
	 * 			The pageableObject
	 * @return
	 * 			The response entity
	 */
	@Override
	public ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@PathVariable("accountId") String accountId,
			@PageableDefault Pageable p) {
		Page<TransactionResponse> page = transactionService
				.getTransactionsByAccount(accountId, p);
		if (null == page || page.getTotalElements() == 0) {
			log.debug("Cannot find transaction for account {}", accountId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok().body(page);
	}

	/**
	 * Remove transaction from account
	 * @param accountId
	 * 			The account ID
	 * @param transactionId
	 * 			The transaction id
	 * @return
	 * 			The response entity
	 */
	@Override
	public ResponseEntity<Void> deleteTransactionsByAccount(@PathVariable("accountId") String accountId,
			@PathVariable("transactionId") String transactionId) {
		log.debug("Deleting transaction:"+transactionId+" From account: "+accountId);
		this.transactionService.deleteTransactionByAccount(accountId, transactionId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	/**
	 * add transaction to account
	 * @param accountId
	 * 			The account ID
	 * @param transaction
	 * 			The transaction to add
	 * @return
	 * 			The response entity
	 */
	@Override
	public ResponseEntity<TransactionResponse> addTransaction(@PathVariable("accountId") String accountId,
			@RequestBody Transaction transaction) {
		TransactionResponse transactionCreated = transactionService.createTransaction(accountId, transaction);
		 return ResponseEntity.status(HttpStatus.CREATED).body(transactionCreated);
	}

	/**
	 * update a transaction
	 * @param accountId
	 * 			The account ID
	 * @param transactionId
	 * 			The id of transaction to update
	 * @param transaction
	 * 			The new transaction
	 * @return
	 * 			The response entity
	 */
	@Override
	public ResponseEntity<TransactionResponse> updateTransaction(
			@PathVariable("accountId") String accountId,@PathVariable("transactionId") String transactionId, @RequestBody Transaction transaction) {
		transactionService.updateTransaction(accountId,transactionId, transaction);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}

