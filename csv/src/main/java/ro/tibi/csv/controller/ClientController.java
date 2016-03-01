package ro.tibi.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.repository.Client;
import ro.tibi.csv.service.ClientService;

@RestController
@RequestMapping(value = "/client", produces = "application/json")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/getClient", method = RequestMethod.GET)
	public Client getClient(@RequestParam(required = true) Integer id) {
		return clientService.getClient(id);
	}

	@RequestMapping(value = "/getClientBySecurityCode", method = RequestMethod.GET)
	public Client getClientBySecurityCode(@RequestParam(required = true) Integer securityCode) {
		return clientService.getClientBySecurityCode(securityCode);
	}

	@RequestMapping(value = "/searchClients", method = RequestMethod.POST, consumes = "application/json")
	public List<Client> searchClients(@RequestBody ClientSearchDTO clientSearchDTO) {
		return clientService.getClients();
	}

	@RequestMapping(value = "/getClients", method = RequestMethod.GET)
	public List<Client> getClients() {
		return clientService.getClients();
	}

	@RequestMapping(value = "/saveClient", method = RequestMethod.POST, consumes = "application/json")
	public Client getClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST, consumes = "application/json")
	public Client updateClient(@RequestBody Client client) {
		return clientService.updateClinet(client);
	}

	@RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
	public boolean deleteClient(@RequestParam(required = true) Integer id) {
		return clientService.deleteClient(id);
	}

}
