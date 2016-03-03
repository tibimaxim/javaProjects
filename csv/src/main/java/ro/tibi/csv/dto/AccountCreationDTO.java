package ro.tibi.csv.dto;

import java.util.List;

import ro.tibi.csv.repository.Role;
import ro.tibi.csv.util.Constants.AccountStatus;

public class AccountCreationDTO {

	private String username;

	private String password;

	private AccountStatus status;

	public List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
