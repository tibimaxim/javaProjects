package ro.tibi.csv.dto;

public class IdentityCardScanDTO {
	
	private byte[] identityCardScan;
	private byte[] identityCardOcrAreaScan;
	
	
	public byte[] getIdentityCardScan() {
		return identityCardScan;
	}
	public void setIdentityCardScan(byte[] identityCardScan) {
		this.identityCardScan = identityCardScan;
	}
	public byte[] getIdentityCardOcrAreaScan() {
		return identityCardOcrAreaScan;
	}
	public void setIdentityCardOcrAreaScan(byte[] identityCardOcrAreaScan) {
		this.identityCardOcrAreaScan = identityCardOcrAreaScan;
	}
	
	
}
