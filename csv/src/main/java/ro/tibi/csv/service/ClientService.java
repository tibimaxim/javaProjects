package ro.tibi.csv.service;

import java.util.List;

import ro.tibi.csv.repository.Client;

public interface ClientService {
	
	public Client getClient(Integer id);
	public Client saveClient(Client client);
	public Client updateClinet(Client client);
	public boolean deleteClient(Integer id);
	public List<Client> getClients();
	public Client getClientBySecurityCode(Integer securityCode);

}
