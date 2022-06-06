package main.java;

import main.java.Barcode_Versions.EAN13;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EAN13 barcode = new EAN13("9312345678907");	//example EAN-Number
		barcode.printBinary();
		barcode.saveAs("barcode_Test");
	}
}
