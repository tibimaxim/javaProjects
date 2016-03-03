package ro.tibi.csv.service;

import java.util.List;

import javassist.NotFoundException;
import ro.tibi.csv.dto.AccountCreationDTO;
import ro.tibi.csv.repository.Account;

public interface AccountService {
	
	public Account getAccount();
	public Account getAccount(Integer id);
	public void changePassword(String password) throws NotFoundException;
	public void changePasswordForUser(String password,Integer id) throws NotFoundException;
	public Account createAccount(AccountCreationDTO accountCreationDTO);
	public Account updateAccount(Account account);
	public List<Account> searchAccounts(String username);
	public boolean login(String username,String password);
	public List<Account> getAccounts();
	public boolean deleteAccount(Integer id);
	
}
