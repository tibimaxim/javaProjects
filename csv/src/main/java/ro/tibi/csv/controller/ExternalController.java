package ro.tibi.csv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ro.tibi.csv.dto.IdentityCardDataDTO;
import ro.tibi.csv.service.ClientService;

@RestController
@RequestMapping(value = "/external",produces="application/json")
public class ExternalController {
	
	@Autowired
	private ClientService clientservice;
	
	@RequestMapping(value = "/saveIdentityCardData", method=RequestMethod.POST, consumes="application/json")
	public boolean saveIdentityCardData(@RequestBody IdentityCardDataDTO identityCardDataDTO) {
	System.out.println(identityCardDataDTO.getSecurityCode());	
	return true;
	}

}
