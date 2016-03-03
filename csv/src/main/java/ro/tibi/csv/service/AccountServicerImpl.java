package ro.tibi.csv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;
import ro.tibi.csv.dao.AccountDAO;
import ro.tibi.csv.dto.AccountCreationDTO;
import ro.tibi.csv.repository.Account;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServicerImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private SecurityService securityService;



	@Override
	@Transactional(readOnly = true)
	public Account getAccount() {
		User user = securityService.getLoggedUser();
		return accountDAO.findByUsername(user.getUsername());
	}

	@Override
	@Transactional
	public Account createAccount(AccountCreationDTO accountCreationDTO) {
		Account account = new Account();
		account.setUsername(accountCreationDTO.getUsername());
		account.setPassword(passwordEncoder.encode(accountCreationDTO.getPassword()));
		account.setStatus(accountCreationDTO.getStatus());
		account.setRoles(accountCreationDTO.getRoles());
		return accountDAO.saveAndFlush(account);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> searchAccounts(String username) {
		return accountDAO.findAllByUsername(username);
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(readOnly = true)
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

	@Override
	@Transactional
	public void changePassword(String password) throws NotFoundException {
		Account account = getAccount();
		if (account == null) {
			throw new NotFoundException("Your Account was not found.");
		}
		//encode new password and save it
		account.setPassword(passwordEncoder.encode(password));
		accountDAO.save(account);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Account getAccount(Integer id) {
		return accountDAO.findOne(id);
	}

	@Override
	@Transactional
	public void changePasswordForUser(String password, Integer id) throws NotFoundException {
		Account account = accountDAO.findOne(id);
		if (account == null) {
			throw new NotFoundException("Your Account was not found.");
		}
		//encode new password and save it
		account.setPassword(passwordEncoder.encode(password));
		accountDAO.save(account);
	}
}
