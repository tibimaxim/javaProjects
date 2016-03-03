package ro.tibi.csv.service;

import java.io.IOException;
import java.text.ParseException;

import ro.tibi.csv.repository.Client;

public interface OcrService {
	
	public void fillClientDataFromCIScan(Client client, byte[] ocrArea) throws IOException;
}
