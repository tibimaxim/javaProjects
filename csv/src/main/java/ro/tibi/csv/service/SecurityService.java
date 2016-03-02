package ro.tibi.csv.service;


import org.springframework.security.core.userdetails.User;

public interface SecurityService {
	
	 public User getLoggedUser();
}
