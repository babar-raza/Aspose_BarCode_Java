package com.aspose.barcode.demos;

//==========================================================================================================//
//This demo explains the steps to read barcode from TIFF document
//This file requires reference to JAI jar files as well
//==========================================================================================================//

import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.TIFFDecodeParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * 
 * @author Administrator
 */
public class ReadBarcodeFromTiff {
	// Base folder to load and save files used in this demo
	private static String strBaseFolder = ".\\Resources";

	public static void main(String[] args) throws Exception {
		String fileName = strBaseFolder + "\\barcode.tif";
		
		Iterator readers = javax.imageio.ImageIO.getImageReadersBySuffix("tiff");
		if (readers.hasNext()) {
			File fi = new File(fileName);
			ImageInputStream iis = javax.imageio.ImageIO
					.createImageInputStream(fi);
			TIFFDecodeParam param = null;
			ImageDecoder dec = ImageCodec.createImageDecoder("tiff", fi, param);
			// Get the page count of the tiff image
			int pageCount = dec.getNumPages();
			ImageReader _imageReader = (ImageReader) (readers.next());
			if (_imageReader != null) {
				_imageReader.setInput(iis, true);
				// Feed each page to the BarCodeReader
				int count = 1;
				for (int i = 0; i < pageCount; i++) {
					BufferedImage _bufferedImage = _imageReader.read(i);
					BarCodeReader reader = new BarCodeReader(_bufferedImage,
							BarCodeReadType.getCode39Standard());
					// Read the barcodes in a single page
					while (reader.read()) {
						System.out.println("Page: " + (i + 1) + "  Codetext: "
								+ reader.getCodeText());
						count++;
					}
					reader.close();
				}
			}
		}
	}
}
