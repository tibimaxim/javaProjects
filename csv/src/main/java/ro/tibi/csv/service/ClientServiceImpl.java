package ro.tibi.csv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.tibi.csv.dao.ClientDAO;
import ro.tibi.csv.repository.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDAO clientDAO;

	@Override
	public Client getClient(Integer id) {
		return clientDAO.findOne(id);
	}

	@Override
	public Client saveClient(Client client) {
		client.setId(null);
		return clientDAO.save(client);
	}

	@Override
	public Client updateClinet(Client client) {
		return clientDAO.save(client);
	}

	@Override
	public boolean deleteClient(Integer id) {
		clientDAO.delete(id);
		return true;
	}

	@Override
	public List<Client> getClients() {
		return clientDAO.findAll();
	}

	@Override
	public Client getClientBySecurityCode(Integer securityCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
