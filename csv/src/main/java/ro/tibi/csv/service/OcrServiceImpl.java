package ro.tibi.csv.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import ro.tibi.csv.repository.Client;
import ro.tibi.csv.util.Constants;
import ro.tibi.csv.util.Constants.Sex;

@Service
public class OcrServiceImpl implements OcrService,InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(OcrServiceImpl.class);
	private ITesseract tessInstance;
	
	@Value("${tesseract.folder.location}")
	public String tessDataLocation;

	@Transactional
	public void fillClientDataFromCIScan(Client client, byte[] ocrArea) throws IOException, TesseractException {

		String ocrResult = extractOcrData(ocrArea);

		// Split the two lines
		String[] bottomOcrData = ocrResult.split("\n");
		// both lines have 36 chars each
		String[] firstLine = bottomOcrData[0].split("<");

		// first is the name preceded by IDROU or IDROM
		String lastName = firstLine[0].replaceAll("IDROU", "");
		lastName = firstLine[0].replaceAll("IDROM", "");

		client.setLastName(lastName);

		// this line contains many < chars, so we need to only apply logic to
		// non empty strings and keep track of their order
		int validElementCounter = 1;
		for (int i = 1; i < firstLine.length; i++) {
			if (!firstLine[i].isEmpty()) {
				validElementCounter++;
				if (validElementCounter == 2) {
					client.setFirstName1(firstLine[i]);
				} else if (validElementCounter == 3) {
					client.setFirstName2(firstLine[i]);
				}
			}

		}

		// second line logic
		String[] secondLine = bottomOcrData[1].split("<");
		// first two characters of the first String are the serial code and the
		// rest are the serial number
		client.setSerialCode(secondLine[0].substring(0, 2));
		client.setSerialNumber(Integer.parseInt(secondLine[0].substring(2)));

		// the second String need to be split by indexes
		// date of birth starts at char 4 and is 6 chars long
		String dateOfBirthString = secondLine[1].substring(4, 10);
		try {
			client.setDateOfBirth(Constants.CI_DATEFORMAT.parse(dateOfBirthString));
		} catch (ParseException e1) {
			logger.error("Error parsing OCR dateOfBirth: " + e1);
		}

		// the sex can be found at char 11
		String sexString = secondLine[1].substring(11, 12);

		try {
			client.setSex(Sex.valueOf(sexString));
		} catch (Exception e1) {
			logger.error("Error casting OCR sex to enum: " + e1);
		}

		// expire date is found at char 12 and is 6 chars long
		String expireDateString = secondLine[1].substring(12, 18);
		try {
			client.setExpireDate(Constants.CI_DATEFORMAT.parse(expireDateString));
		} catch (ParseException e) {
			logger.error("Error parsing OCR expirationDate: " + e);
		}

		// the last 6 chars of the security are located at the end of String
		// prelast position
		String securityCodeWithoutDoB = secondLine[1].substring(secondLine[1].length() - 8, secondLine[1].length() - 1);
		client.setSecurityCode(Long.parseLong(
				securityCodeWithoutDoB.substring(0, 1) + dateOfBirthString + securityCodeWithoutDoB.substring(1)));

	}

	private String extractOcrData(byte[] ocrArea) throws IOException, TesseractException {
		InputStream inputStream = new ByteArrayInputStream(ocrArea);
		BufferedImage ocrAreaBufferedImage = ImageIO.read(inputStream);

		String ocrResult = tessInstance.doOCR(ocrAreaBufferedImage);


		return ocrResult;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//initialize tesseract OCR
		tessInstance = new Tesseract(); // JNA Interface Mapping
		
		tessInstance.setDatapath(tessDataLocation);
		tessInstance.setLanguage("eng");
	}

}
