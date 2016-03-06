package ro.tibi.csv.service;

import java.io.IOException;
import java.text.ParseException;

import net.sourceforge.tess4j.TesseractException;
import ro.tibi.csv.repository.Client;

public interface OcrService {
	
	public void fillClientDataFromCIScan(Client client, byte[] ocrArea) throws IOException, TesseractException;
}
