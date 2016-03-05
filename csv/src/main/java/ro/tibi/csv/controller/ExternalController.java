package ro.tibi.csv.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.tibi.csv.dto.IdentityCardScanDTO;
import ro.tibi.csv.repository.Client;
import ro.tibi.csv.service.ClientService;

@RestController
@RequestMapping(value = "/external", produces = "application/json")
public class ExternalController {

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/saveIdentityCardData", method = RequestMethod.POST, consumes = "application/json")
	public Client saveIdentityCardData(@RequestBody IdentityCardScanDTO identityCardScanDTO) throws IOException {
		return clientService.createFromOcr(identityCardScanDTO);
	}

	// TODO: remove this. Added for saveIdentityCardData testing purposes
	@RequestMapping(value = "/getBytes", method = RequestMethod.GET)
	public IdentityCardScanDTO getBytes() {
		File input = new File("E:\\temp\\bottom.jpg");
		// load image
		BufferedImage sourceImg = getBufferedImage(input, false);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageInByte = null;
		try {
			ImageIO.write(sourceImg, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IdentityCardScanDTO identityCardScanDTO = new IdentityCardScanDTO();
		identityCardScanDTO.setIdentityCardOcrAreaScan(imageInByte);
		return identityCardScanDTO;
	}

	// TODO: remove this. Added for saveIdentityCardData testing purposes
	public static BufferedImage getBufferedImage(File imageFile, boolean makeGrayScale) {
		BufferedImage sourceImg = null;
		try {
			sourceImg = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage image = null;
		if (!makeGrayScale) {
			image = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), BufferedImage.TYPE_INT_RGB);
		} else {
			image = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		}

		Graphics g = image.getGraphics();
		g.drawImage(sourceImg, 0, 0, null);

		g.dispose();

		return image;
	}

}
