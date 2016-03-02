package ro.tibi.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.tibi.csv.repository.Account;
import ro.tibi.csv.service.AccountService;
import ro.tibi.csv.service.SecurityService;

@RestController
@RequestMapping(value = "/account",produces="application/json")

public class AccountController {
	
		
		@Autowired
		private AccountService accountService;
		
		@Autowired
		private SecurityService securityService;
		
		@Secured({"ROLE_USER","ROLE_ADMIN"})
		@RequestMapping(value = "/getCurrentAccount", method=RequestMethod.GET)
		public Account getAccount() {
			User user=securityService.getLoggedUser();
		return accountService.getAccount(user.getUsername());
		}
		
		@Secured({"ROLE_USER","ROLE_ADMIN"})
		@RequestMapping(value = "/getAccountById", method=RequestMethod.GET)
		public Account getAccountById(@RequestParam(required = true) Integer id) {
		return accountService.getAccount(id);
		}
		
		@Secured({"ROLE_USER","ROLE_ADMIN"})
		@RequestMapping(value = "/getAccountByUsername", method=RequestMethod.GET)
		public Account getAccount(@RequestParam(required = true) String username) {
		return accountService.getAccount(username);
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping(value = "/getAccounts", method=RequestMethod.GET)
		public List<Account> getAccounts() {
		return accountService.getAccounts();
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping(value = "/saveAccount", method=RequestMethod.POST, consumes="application/json")
		public Account getAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
		}
		
		@Secured({"ROLE_USER","ROLE_ADMIN"})
		@RequestMapping(value = "/updateAccount", method=RequestMethod.POST, consumes="application/json")
		public Account updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
		}
		
		@Secured({"ROLE_ADMIN"})
		@RequestMapping(value = "/deleteAccount", method=RequestMethod.POST)
		public boolean deleteAccount(@RequestParam(required = true) Integer id) {
		return accountService.deleteAccount(id);
		}

	
}
