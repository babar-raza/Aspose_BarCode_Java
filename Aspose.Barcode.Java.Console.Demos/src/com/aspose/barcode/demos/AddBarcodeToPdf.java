package com.aspose.barcode.demos;

//==========================================================================================================//
//This demo explains the steps to create the barcode and add it to PDF in a Java console application
//==========================================================================================================//

import com.aspose.barcode.*;
import java.io.File;
import java.io.FileOutputStream;

import aspose.pdf.*;

public class AddBarcodeToPdf {
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
			System.out.println("Adding barcode to PDF ....");

			Pdf pdf = new Pdf();

			// add a section
			Section sec1 = pdf.getSections().add();

			Text text1 = new Text(sec1,
					"Barcode Image\n");
			sec1.getParagraphs().add(text1);

			Image img1 = new Image(sec1);
			img1.getImageInfo().setFile(strBarcodeImage);
			sec1.getParagraphs().add(img1);

			pdf.save(new FileOutputStream(new File(
					strBaseFolder + "\\Barcode.pdf")));
			
			System.out.println("PDF created and saved at "
					+ strBaseFolder + "\\Barcode.pdf");
		} catch (Exception ex) {
			System.err.println("Error in adding barcode to PDF: " + ex.getMessage());
		}
	}
}
