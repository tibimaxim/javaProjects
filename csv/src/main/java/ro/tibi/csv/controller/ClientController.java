package ro.tibi.csv.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javassist.NotFoundException;
import net.sourceforge.tess4j.TesseractException;
import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.dto.IdentityCardScanDTO;
import ro.tibi.csv.repository.Client;
import ro.tibi.csv.service.ClientService;

@RestController
@RequestMapping(value = "/client", produces = "application/json")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/getClient", method = RequestMethod.GET)
	public Client getClient(@RequestParam(required = true) Integer id) {
		return clientService.getClient(id);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/getClientBySecurityCode", method = RequestMethod.GET)
	public Client getClientBySecurityCode(@RequestParam(required = true) Integer securityCode) {
		return clientService.getClientBySecurityCode(securityCode);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/searchClients", method = RequestMethod.POST, consumes = "application/json")
	public List<Client> searchClients(@RequestBody ClientSearchDTO clientSearchDTO) {
		return clientService.searchClients(clientSearchDTO);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/getClients", method = RequestMethod.GET)
	public List<Client> getClients() {
		return clientService.getClients();
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST, consumes = "application/json")
	public Client getClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/updateClient", method = RequestMethod.POST, consumes = "application/json")
	public Client updateClient(@RequestBody Client client) throws NotFoundException{
		return clientService.updateClient(client);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
	public boolean deleteClient(@RequestParam(required = true) Integer id) {
		return clientService.deleteClient(id);
	}
	

	
	@Secured({"ROLE_USER"})
	@RequestMapping(value = "/saveIdentityCardData", method = RequestMethod.POST, consumes = "application/json")
	public Client saveIdentityCardData(@RequestBody IdentityCardScanDTO identityCardScanDTO) throws IOException, TesseractException {
		return clientService.createFromOcr(identityCardScanDTO);
	}

}
