package main.java;

import main.java.Barcode_Versions.EAN13;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EAN13 barcode = new EAN13("4002846034504");	//example EAN-Number
		barcode.printBinary();
		barcode.saveAs("barcode_Test");
	}
}
