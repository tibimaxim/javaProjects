package ro.tibi.csv.service;

import java.util.List;

import ro.tibi.csv.repository.Account;

public interface AccountService {
	
	public Account getAccount(Integer id);
	public Account saveAccount(Account account);
	public Account updateAccount(Account account);
	public Account getAccount(String username);
	public boolean login(String username,String password);
	public List<Account> getAccounts();
	public boolean deleteAccount(Integer id);
	
}
