package ro.tibi.csv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ro.tibi.csv.repository.Account;

public interface AccountDAO extends JpaRepository<Account,Integer> {
	
	@Query("select acc from Account acc where username= :username")
	public Account findByUsername(@Param("username") String username);
	
	@Query("select acc from Account acc where username like CONCAT('%', :username, '%') ")
	public List<Account> findAllByUsername(@Param("username") String username);

}
