package com.htc.spring4.service;


import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.htc.spring4.dao.AccountDAO;

public class AccountService2 {

	AccountDAO accountDAO;
	TransactionTemplate transactionTemplate;
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public boolean deposit(int accno, double amount) {	
		return accountDAO.deposit(accno, amount);	
	}
	
	public boolean withdraw(int accno, double amount) {
		return accountDAO.withdraw(accno, amount);
	}
	
	public boolean transferMoney(int fromAccno, int toAccno, double amount) {
		boolean status = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus txStatus) {
				boolean transferStatus = false;
				try {
					transferStatus = accountDAO.transferMoney(fromAccno, toAccno, amount);
				}
				catch(Exception ex) {
					txStatus.setRollbackOnly();
					throw ex;
				}
				return transferStatus;
			}
			
		});
		return status;
	}
}
