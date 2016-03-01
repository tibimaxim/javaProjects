package ro.tibi.csv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.tibi.csv.repository.Client;

public interface ClientDAO extends JpaRepository<Client, Integer> {

}
