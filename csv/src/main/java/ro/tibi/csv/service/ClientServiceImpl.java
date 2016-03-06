package ro.tibi.csv.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import net.sourceforge.tess4j.TesseractException;
import ro.tibi.csv.dao.ClientDAO;
import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.dto.IdentityCardScanDTO;
import ro.tibi.csv.repository.Client;
import ro.tibi.csv.util.DaoSpecifications;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDAO clientDAO;
	
	@Autowired 
	private OcrService ocrService;

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
	public Client updateClient(Client client) throws NotFoundException {
		Client databaseClient = clientDAO.findOne(client.getId());
		if (databaseClient == null) {
			throw new NotFoundException("Client with id: "+ client.getId() + "does not exist.");
		}
		BeanUtils.copyProperties(client, databaseClient);
		return clientDAO.save(databaseClient);
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

	@Override
	public List<Client> searchClients(ClientSearchDTO search) {
		return clientDAO.findAll(DaoSpecifications.findByCriteria(search));
	}

	@Override
	public Client createFromOcr(IdentityCardScanDTO identityCardScanDTO) throws IOException, TesseractException {
		Client client = new Client();
		ocrService.fillClientDataFromCIScan(client, identityCardScanDTO.getIdentityCardOcrAreaScan());
		return clientDAO.save(client);
	}
}
