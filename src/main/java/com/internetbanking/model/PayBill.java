package com.internetbanking.model;

import java.util.Date;

public class PayBill {
	private String paybillId;
	private Double balance;
	private String pin;
	private Person customer;
	private Date startdate;
	private Date expiredate;
	private BillStatus billstatus;
	private Double amount;

}
