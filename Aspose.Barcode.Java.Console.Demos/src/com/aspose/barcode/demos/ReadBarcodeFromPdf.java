package com.aspose.barcode.demos;

//==========================================================================================================//
//This demo explains the steps to read barcode from PDF document
//==========================================================================================================//

import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;
import com.aspose.pdf.kit.ImageType;
import com.aspose.pdf.kit.PdfExtractor;

public class ReadBarcodeFromPdf {
	// Base folder to load and save files used in this demo
	private static String strBaseFolder = ".\\Resources";

	public static void main(String[] args) {
		try {
			String strBarCodeImage = "";
			String strPdfDoc = strBaseFolder + "\\barcode.pdf";

			// Instantiate PdfExtractor object
			PdfExtractor extractor = new PdfExtractor();

			// Bind the input PDF document to extractor
			extractor.bindPdf(strPdfDoc);
			// extractor.setStartPage(1);
			// extractor.setEndPage(1);

			// Extract images from the input PDF document
			extractor.extractImage();
			String suffix = ".png";
			int imageCount = 1;
			while (extractor.hasNextImage()) {
				System.out.println("Extracting image " + imageCount);
				strBarCodeImage = strBaseFolder + "\\tmpbarcode" + imageCount
						+ suffix;
				System.out.println("Image saved: " + strBarCodeImage);
				extractor.getNextImage(strBarCodeImage, ImageType.PNG);

				// recognize barcode from image
				BarCodeReader reader = new BarCodeReader(strBarCodeImage,
						BarCodeReadType.getCode39Standard());
				// reader.SetHints(RecognitionHints.ThresholdHints.AutoCalculateByImage());
				while (reader.read()) {
					System.out.println("codetext: " + reader.getCodeText());
				}
				reader.close();

				imageCount++;
			}
			extractor.close();
			System.out.println("Program finished.");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}