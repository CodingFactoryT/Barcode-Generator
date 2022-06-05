package main.java.Barcode_Versions;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;

public class EAN13 {
	
	String EAN13_Number = "";
	
	String beginning_sequence = "101";
	String ending_sequence = "101";
	String middle_sequence = "01010";
	
	String[] letter_codes_A = {"0001101","0011001","0010011","0111101","0100011","0110001","0101111","0111011","0110111","0001011"};	//before middle
	String[] letter_codes_B = {"0100111","0110011","0011011","0100001","0011101","0111001","0000101","0010001","0001001","0010111"};	//before middle
	String[] letter_codes_C = {"1110010","1100110","1101100","1000010","1011100","1001110","1010000","1000100","1001000","1110100"}; //after middle
	String[] first_digit_sequences = {"AAAAAA","AABABB","AABBAB","AABBBA","ABAABB","ABBAAB","ABBBAA","ABABAB","ABABBA","ABBABA"};
	
	
	public EAN13(String EAN13_Number) throws Exception {
		if(EAN13_Number.length() != 13) throw new Exception("EAN13-Number has not a lenght of 13!: Lenght: " + EAN13_Number.length());
		if(!EAN13_Number.matches("[0-9]+")) throw new Exception("EAN13-Number is not only contained of digits!");
		
		this.EAN13_Number = EAN13_Number;
	}
	
	private String calculateBinary() {
		String binary = beginning_sequence;
		String first_digit_sequence = first_digit_sequences[Character.getNumericValue(EAN13_Number.charAt(0))];
		
		for(int i = 1; i <= 6; i++) {
			if(first_digit_sequence.charAt(i-1) == 'A') {
				binary += letter_codes_A[Character.getNumericValue(EAN13_Number.charAt(i))];
			} else {
				binary += letter_codes_B[Character.getNumericValue(EAN13_Number.charAt(i))];			
			}
		}
		
		binary += middle_sequence;
		
		for(int i = 7; i <= 12; i++) {
				binary += letter_codes_C[Character.getNumericValue(EAN13_Number.charAt(i))];	
		}
		
		binary += ending_sequence;
		return binary;
	}

	private BufferedImage generateBarcodeImage() {
		int width = 5;
		int height = 150;
		int delimiter_height = 180;
		int fontSize = 20;
		
		BufferedImage bi = new BufferedImage(width*95, delimiter_height, BufferedImage.TYPE_INT_RGB);
		String binary = calculateBinary();
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,width*95, delimiter_height);
		
		for(int i = 0; i < 95; i++) {
			if(binary.charAt(i) == '0') {
				g2d.setColor(Color.white);
			}else {
				g2d.setColor(Color.black);
			}
			
			if((i > 2 && i < 46) || (i > 48 && i < 92)) {
				g2d.fillRect(i*width, 0, width, height);
			} else {
				g2d.fillRect(i*width, 0, width, delimiter_height);
			}
		}
		return bi;
	}
	
	public void saveAs(String fileName) {
		try {
			ImageIO.write(generateBarcodeImage(), "PNG", new File(fileName + ".PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printBinary() {
		System.out.println(calculateBinary());
	}	
}
