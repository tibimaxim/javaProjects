package ro.tibi.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.tibi.csv.repository.Account;
import ro.tibi.csv.service.AccountService;

@RestController
@RequestMapping(value = "/account",produces="application/json")

public class AccountController {
	
		
		@Autowired
		private AccountService accountService;
		
		@RequestMapping(value = "/getAccount", method=RequestMethod.GET)
		public Account getAccount(@RequestParam(required = true) Integer id) {
		return accountService.getAccount(id);
		}
		
		@RequestMapping(value = "/getAccountByUsername", method=RequestMethod.GET)
		public Account getAccount(@RequestParam(required = true) String username) {
		return accountService.getAccount(username);
		}
		

		
		@RequestMapping(value = "/getAccounts", method=RequestMethod.GET)
		public List<Account> getAccounts() {
		return accountService.getAccounts();
		}
		
		@RequestMapping(value = "/saveAccount", method=RequestMethod.POST, consumes="application/json")
		public Account getAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
		}
		
		@RequestMapping(value = "/updateAccount", method=RequestMethod.POST, consumes="application/json")
		public Account updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
		}
		
		@RequestMapping(value = "/deleteAccount", method=RequestMethod.POST)
		public boolean deleteAccount(@RequestParam(required = true) Integer id) {
		return accountService.deleteAccount(id);
		}

	
}
