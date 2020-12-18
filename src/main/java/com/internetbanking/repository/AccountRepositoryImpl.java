package com.internetbanking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.model.Account;
import com.internetbanking.model.MonthlyAverageBalance;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository{
	@Autowired
	private HibernateTemplate template;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveAccount(Account account) {
		template.saveOrUpdate(account);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveMontlyAverageBalance(MonthlyAverageBalance balance) {
		template.saveOrUpdate(balance);
		
	}

}
