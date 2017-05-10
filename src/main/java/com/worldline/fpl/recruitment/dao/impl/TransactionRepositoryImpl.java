package com.worldline.fpl.recruitment.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository,
		InitializingBean {

	private List<Transaction> transactions;

	@Override
	public void afterPropertiesSet() throws Exception {
		transactions = new ArrayList<>();
		{
			//Create a transaction
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(42.12));
			transaction.setId("1");
			transaction.setNumber("12151885120");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(456.00));
			transaction.setId("2");
			transaction.setNumber("12151885121");
			transactions.add(transaction);
		}
		{
			Transaction transaction = new Transaction();
			transaction.setAccountId("1");
			transaction.setBalance(BigDecimal.valueOf(-12.12));
			transaction.setId("3");
			transaction.setNumber("12151885122");
			transactions.add(transaction);
		}
	}

	
	@Override
	/**
	 * find transation by id
	 * @param id
	 * 			the transaction id to update
	 * 
	 * @return
	 * 			the transaction founded
	 */
	public Transaction findById(String id) {
		return transactions.stream()
				.filter(transaction -> transaction.getId().equals(id))
				.findFirst().orElse(null);
	}

	
	/**
	 * get list transaction by account
	 * @param accountID
	 * 			the account id
	 * 
	 * @param p
	 * 			the pageable object
	 * 
	 */
	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		return new PageImpl<Transaction>(transactions.stream()
				.filter(t -> t.getAccountId().equals(accountId))
				.collect(Collectors.toList()));
	}

	
	/**
	 * Delete transaction from account
	 * @param accountid
	 * 			the account related to transaction
	 * 
	 * @param transactionId
	 * 			the id of transaction to delete
	 */
	@Override
	public void deleteTransactionFromAccount(String accountId,
			String transactionId) {
		transactions.removeIf(t -> t.getAccountId().equals(accountId)
			&& t.getId().equals(transactionId));
		
	}

	/**
	 * check if transaction exists
	 * 
	 * @param transactionId
	 * 			the id of transaction 
	 * return true if exists
	 */
	@Override
	public boolean transactionExists(String transactionId) {
		return transactions.stream().anyMatch(t -> t.getId().equals(transactionId));
	}
	
	/**
	 * check if transaction belong to account
	 * 
	 * @param accountId
	 * 			the account id
	 * @param transactionId
	 * 			the id of transaction 
	 * return true if transaction belong to account
	 */
	@Override
	 public boolean transactionBelongToAccount(String accountId,
	 			String transactionId) {
	 		return transactions.stream().anyMatch(t ->  t.getAccountId().equals(accountId)
	 				&& t.getId().equals(transactionId));
	 	}

	/**
	 * add a new transaction
	 * 
	 * @param accountId
	 * 			the account id
	 * @param transaction
	 * 			the transaction to add
	 * return the added transaction
	 */
	@Override
	public Transaction addTransaction(String accountId,Transaction transaction) {
		transaction.setAccountId(accountId);
		transaction.setId(getNextTransactionId());
		transactions.add(transaction);
		return transaction;
	}

	/**
	 * update a transaction
	 * 
	 * @param transactionId
	 * 			the id of transaction to update
	 * @param newTransaction
	 * 			the new transaction
	 */
	@Override
	public void updateTransaction(String transactionId,Transaction newTransaction) {
		transactions.stream().filter(t -> t.getId().equals(transactionId)).findFirst().ifPresent(
					t -> {
				 				t.setBalance(newTransaction.getBalance());
				 				t.setNumber(newTransaction.getNumber());
				 			}
				 		)
				 		;

		
	}
	
	/**
	 * get the newt new transaction id
	 * 
	 *@return the new id
	 */
	private String getNextTransactionId(){
		return Integer.toString(transactions.size()+1);
	}

}
