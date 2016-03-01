package ro.tibi.csv.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.tibi.csv.dao.AccountDAO;
import ro.tibi.csv.repository.Account;

@Service
@Transactional(rollbackFor=Exception.class)
public class AccountServicerImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDAO;

	@Override
	@Transactional(readOnly=true)
	public Account getAccount(Integer id) {
		return accountDAO.findOne(id);
	}

	@Override
	@Transactional
	public Account saveAccount(Account account) {
		//this is save so nullify id just to be sure
		account.setId(null);
		return accountDAO.saveAndFlush(account);
	}

	@Override
	@Transactional(readOnly=true)
	public Account getAccount(String username) {
		return accountDAO.findByUsername(username);
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Account> getAccounts() {
		return accountDAO.findAll();
	}

	@Override
	public boolean deleteAccount(Integer id) {
		accountDAO.delete(id);
		return true;
	}

	@Override
	public Account updateAccount(Account account) {
		return accountDAO.saveAndFlush(account);
	}
}
