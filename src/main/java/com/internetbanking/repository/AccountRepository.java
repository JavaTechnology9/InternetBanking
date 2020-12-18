package com.internetbanking.repository;

import com.internetbanking.model.Account;
import com.internetbanking.model.MonthlyAverageBalance;

public interface AccountRepository {
	public void saveAccount(Account account);
	public void saveMontlyAverageBalance(MonthlyAverageBalance balance);

}
