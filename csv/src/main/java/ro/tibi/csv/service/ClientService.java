package ro.tibi.csv.service;

import java.io.IOException;
import java.util.List;

import javassist.NotFoundException;
import net.sourceforge.tess4j.TesseractException;
import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.dto.IdentityCardScanDTO;
import ro.tibi.csv.repository.Client;

public interface ClientService {
	
	public Client getClient(Integer id);
	public Client saveClient(Client client);
	public Client updateClient(Client client) throws NotFoundException;
	public boolean deleteClient(Integer id);
	public List<Client> getClients();
	public Client getClientBySecurityCode(Integer securityCode);
	public List<Client> searchClients(ClientSearchDTO search);
	public Client createFromOcr(IdentityCardScanDTO identityCardScanDTO) throws IOException, TesseractException;

}
