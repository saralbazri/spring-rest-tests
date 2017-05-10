package com.worldline.fpl.recruitment.service;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction service
 * 
 * @author A525125
 *
 */
@Slf4j
@Service
public class TransactionService {

	private AccountService accountService;

	private TransactionRepository transactionRepository;

	@Autowired
	public TransactionService(AccountService accountService,
			TransactionRepository transactionRepository) {
		this.accountService = accountService;
		this.transactionRepository = transactionRepository;
	}

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable object
	 * @return
	 */
	public Page<TransactionResponse> getTransactionsByAccount(String accountId,
			Pageable p) {
		if (!accountService.isAccountExist(accountId)) {
			log.error("Account doesn't exixt");
			throw new ServiceException(ErrorCode.NOT_FOUND_ACCOUNT,
					"Account doesn't exist");
		}
		return new PageImpl<TransactionResponse>(transactionRepository
				.getTransactionsByAccount(accountId, p).getContent().stream()
				.map(this::map).collect(Collectors.toList()));
	}

	/**
	 * Map {@link Transaction} to {@link TransactionResponse}
	 * 
	 * @param transaction
	 * @return
	 */
	private TransactionResponse map(Transaction transaction) {
		TransactionResponse result = new TransactionResponse();
		result.setBalance(transaction.getBalance());
		result.setId(transaction.getId());
		result.setNumber(transaction.getNumber());
		return result;
	}
	/**
	 * Remove transaction from account
	 * 
	 * @param accountId
	 * 			The account id
	 * @param transactionId
	 * 			The transaction id
	 */
	public void deleteTransactionByAccount(String accountId,String transactionId) {
		checkIfAccountExist(accountId);
		checkIfTransactionExist(transactionId);
		checkIfTransactionBelongToAccount(accountId, transactionId);
		transactionRepository.deleteTransactionFromAccount(accountId, transactionId);
		log.debug("transaction "+transactionId+ "deleted");
	}


	/**
	 * create a new transaction
	 * @param accountId
	 * 			The account id
	 * @param transaction
	 * 			the transaction to add
	 * @return the transaction response
	 */
	public TransactionResponse createTransaction(String accountId,
			Transaction transaction) {

		log.debug("Create a new transaction "+transaction + "in"+accountId);
		
		/**check if the new information of transaction are valid **/
		checkIfTransactionIsValid(transaction);
		
		return  map(transactionRepository.addTransaction(accountId, transaction));
	}


	/**
	 * Update a transaction
	 * @param accountId
	 * 			The account id
	 * @param transactionId
	 * 			The id of transaction to update
	 * @param transaction
	 * 			The new transaction information
	 */
	public void updateTransaction(String accountId,String transactionId,
			Transaction transaction) {
		//check if account exist
		checkIfAccountExist(accountId);
		
		//check if transaction exist
		checkIfTransactionExist(transactionId);
		
		//check if transaction belong to account
		checkIfTransactionBelongToAccount(accountId,transactionId);
		
		//check if informations of transaction are valid
		checkIfTransactionIsValid(transaction);
		
		//update transaction
		transactionRepository.updateTransaction(transactionId, transaction);
	}
	
	
	/**
	 * check if account exist
	 * @param accountId
	 * 		the account id
	 */
	private void checkIfAccountExist(String accountId) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.NOT_FOUND_ACCOUNT,
					"Account doesn't exist");
		}
	}
	
	
	/**
	 * check if transaction belong to account
	 * @param accountId
	 * 			the account id
	 * @param transactionId
	 * 			The transaction id
	 */
	private void checkIfTransactionBelongToAccount(String accountId,
			String transactionId) {
		if (!transactionRepository.transactionBelongToAccount(accountId,transactionId)) {
			log.error("transaction not belong to the account"); 
			throw new ServiceException(ErrorCode.FORBIDDEN_TRANSACTION,
					"transaction not belong to the account");
		}
	}

	
	/**
	 * check if transaction exist
	 * @param transactionId
	 * 			The transaction id
	 */
	private void checkIfTransactionExist(String transactionId) {
		if (!transactionRepository.transactionExists(transactionId)) {
			log.error("Transaction doesn't exixt");
			throw new ServiceException(ErrorCode.NOT_FOUND_TRANSACTION,
					"Transaction doesn't exist");
		}
	}
	
	
	/**
	 * check if the informations of transaction are valid
	 * @param transaction
	 */
	private void checkIfTransactionIsValid(Transaction transaction) {
		if ( transaction.getBalance() == null
				|| transaction.getNumber() == null) {
			log.error("Invalid transaction"+transaction);
			throw new ServiceException(ErrorCode.INVALID_REQUEST,
					"Invalid transaction");
		}
	}
}
