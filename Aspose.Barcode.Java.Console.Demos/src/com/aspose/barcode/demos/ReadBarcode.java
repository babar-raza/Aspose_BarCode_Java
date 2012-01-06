package com.aspose.barcode.demos;

//==========================================================================================================//
//This demo explains the steps to create and recognize the barcode in a Java console application
//==========================================================================================================//

import com.aspose.barcode.*;
import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;

public class ReadBarcode {
	// Base folder to load and save files used in this demo
	private static String strBaseFolder = ".\\Output";

	public static final void main(String args[]) throws Exception {
		String strBarcodeImage = "";
		try {
			System.out.println("Creating barcode ....");
			// Create a barcode and save the barcode image to disk
			BarCodeBuilder b = new BarCodeBuilder();
			b.setAutoSize(true);
			b.setSymbologyType(Symbology.Code39Standard);
			b.setCodeText("12345678");
			strBarcodeImage = strBaseFolder + "\\barcode.png";
			b.save(strBarcodeImage);

			System.out.println("Barcode created and saved at "
					+ strBarcodeImage);
		} catch (Exception ex) {
			System.err.println("Error in barcode creation: " + ex.getMessage());
		}

		try {
			System.out
					.println("--------------------------------------------------");
			System.out.println("Reading Code39Standard barcode from image "
					+ strBarcodeImage + " ....");
			// Initialize the BarCodeReader class
			BarCodeReader reader = new BarCodeReader(strBarcodeImage,
					BarCodeReadType.getCode39Standard());
			while (reader.read() == true) {
				// Barcode found. Print result on screen
				System.out.println("Barcode found. Codetext: "
						+ reader.getCodeText());
			}
			// Close the reader
			reader.close();
		} catch (Exception ex) {
			System.err.println("Error in reading barcode: " + ex.getMessage());
		}
	}
}
