package main.java;

import main.java.Barcode_Versions.EAN13;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EAN13 barcode = new EAN13();	//example EAN-Number
		
		for(int i = 1; i <= 10; i++) {							//generates and saves 10 barcodes
			String ean13 = barcode.generateRandomEAN13Number();
			barcode.setEAN13(ean13);
			barcode.saveAs("barcode(" + i + ")");		
		}
		
//		System.out.println(barcode.getEAN13());					//other methods you can use
//		System.out.println(barcode.getBinary());
//		barcode.calculateBinary();
//		barcode.calculateCheckingDigit("400123400001");
//		barcode.generateBarcodeImage();
//		barcode.generateRandomEAN13Number("400", "1234", "00001");
	}
}
