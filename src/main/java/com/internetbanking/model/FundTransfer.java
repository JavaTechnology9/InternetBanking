package com.internetbanking.model;

import java.util.Date;

public class FundTransfer {
	private Long fundTransferId;
	private Double balance;
	private String pin;
	private Person customer;
	private Date startdate;
	private Date expiredate;
	private BillStatus billstatus;
	private Double transferFunds;
	private boolean ownaccount;
	private boolean thirdpartyAccount;
	
	

}
