package ro.tibi.csv.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.tibi.csv.repository.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account,Integer> {

}
