package ro.tibi.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import ro.tibi.csv.dto.AccountCreationDTO;
import ro.tibi.csv.repository.Account;
import ro.tibi.csv.repository.Role;
import ro.tibi.csv.service.AccountService;

@RestController
@RequestMapping(value = "/account", produces = "application/json")

public class AccountController {

	@Autowired
	private AccountService accountService;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/getCurrentAccount", method = RequestMethod.GET)
	public Account getCurrentAccount() {
		return accountService.getAccount();
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/getAccount", method = RequestMethod.GET)
	public Account getAccount(@RequestParam(required = true) Integer id) {
		return accountService.getAccount(id);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/searchAccounts", method = RequestMethod.GET)
	public List<Account> searchAccounts(@RequestParam(required = true) String username) {
		return accountService.searchAccounts(username);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public void changePassword(@RequestParam(required = true) String password) throws NotFoundException {
		accountService.changePassword(password);
	}
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/changePasswordForUser", method = RequestMethod.POST)
	public void changePasswordForUser(@RequestParam(required = true) String password, @RequestParam(required = true) Integer id) throws NotFoundException {
		accountService.changePasswordForUser(password,id);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/updateRoles", method = RequestMethod.POST)
	public void updateRoles(@RequestParam(required = true) Integer id, @RequestBody(required = true) List<Role> roles) throws NotFoundException {
		Account account = accountService.getAccount(id);
		account.setRoles(roles);
		accountService.updateAccount(account);
	}
	

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/getAccounts", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}

//	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/saveAccount", method = RequestMethod.POST, consumes = "application/json")
	public Account saveAccount(@RequestBody AccountCreationDTO accountCreationDTO) {
		return accountService.createAccount(accountCreationDTO);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST, consumes = "application/json")
	public Account updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public boolean deleteAccount(@RequestParam(required = true) Integer id) {
		return accountService.deleteAccount(id);
	}

}
