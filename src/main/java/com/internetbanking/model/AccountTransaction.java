package com.internetbanking.model;

import java.util.Date;

public class AccountTransaction {
	private Long accountTransId;
	private Double balance;
	private String pin;
	private Person customer;
	private Date startdate;
	private Date expiredate;
	private BillStatus billstatus;
	private Double amount;
	private AccountType accountType;
	private TransactionType transactionType;

}
