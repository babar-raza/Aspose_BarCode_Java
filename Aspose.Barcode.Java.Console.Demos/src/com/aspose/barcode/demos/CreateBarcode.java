package com.aspose.barcode.demos;

//==========================================================================================================//
//This demo explains the steps to create the barcode and save it to disk in a Java console application
//==========================================================================================================//

import com.aspose.barcode.*;

public class CreateBarcode {
	// Base folder to load and save files used in this demo
	private static String strBaseFolder = ".\\Output";

	public static final void main(String args[]) throws Exception {
		try {
			// Create a barcode and save the barcode image to disk
			BarCodeBuilder b = new BarCodeBuilder();
			b.setAutoSize(true);
			b.setSymbologyType(Symbology.Code39Standard);
			b.setCodeText("12345678");
			b.save(strBaseFolder + "\\barcode.png");
			System.out.println("Barcode created and saved at " + strBaseFolder
					+ "\\barcode.png");
		} catch (Exception ex) {
			System.err.println("Error in barcode creation: " + ex.getMessage());
		}
	}
}
