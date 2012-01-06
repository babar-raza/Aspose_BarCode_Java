package com.aspose.barcode.demos;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;

import com.aspose.words.*;

public class ReadBarcodeFromDoc {
	// Base folder to load and save files used in this demo
	private static String strBaseFolder = ".\\Resources";

	public static final void main(String args[]) throws Exception {
		try {
			Document doc = new Document(strBaseFolder + "\\Barcode.doc");

			NodeCollection shapes = doc.getChildNodes(NodeType.SHAPE, true,
					false);

			String suffix = ".png";
			int imageCount = 1;
			int imageIndex = 0;
			for (Shape shape : (Iterable<Shape>) shapes) {
				if (shape.hasImage()) {
					System.out.println("Extracting image " + imageCount);
					String imageFileName = strBaseFolder + "\\tmpbarcode" + imageCount
							+ suffix;
					shape.getImageData().save(imageFileName);
					System.out.println("Image saved: " + imageFileName);

					// Initialize the BarCodeReader class
					BarCodeReader reader = new BarCodeReader(imageFileName,
							BarCodeReadType.getCode39Standard());
					while (reader.read() == true) {
						// Barcode found. Print result on screen
						System.out.println("Barcode found. Codetext: "
								+ reader.getCodeText());
					}
					// Close the reader
					reader.close();
					
					imageCount++;
					imageIndex++;
				}
			}
		} catch (Exception ex) {
			System.err.println("Error in barcode reading: " + ex.getMessage());
		}
	}
}
