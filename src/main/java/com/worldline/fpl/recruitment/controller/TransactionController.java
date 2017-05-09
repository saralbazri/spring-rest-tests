package com.worldline.fpl.recruitment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction controller
 * 
 * @author A525125
 *
 */
@RequestMapping(value = "/accounts/{accountId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TransactionController {

	/**
	 * Get transaction list by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return the transaction list
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	ResponseEntity<Page<TransactionResponse>> getTransactionsByAccount(
			@PathVariable("accountId") String accountId,
			@PageableDefault Pageable p);
	
	/**
	 * Remove transaction from account
	 * @param accountId
	 * 			The account Id
	 * @param transactionId
	 * 			The transaction Id
	 * @return
	 * 			The response entity
	 */
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteTransactionsByAccount(
	@PathVariable("accountId") String accountId,
	@PathVariable("transactionId") String transactionId);
}

