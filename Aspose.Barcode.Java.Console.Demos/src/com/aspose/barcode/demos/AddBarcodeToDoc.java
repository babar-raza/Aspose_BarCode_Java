package com.aspose.barcode.demos;

import com.aspose.barcode.*;
import com.aspose.barcoderecognition.imaging.*;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.barcode.BarCodeBuilder;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * Creates and inserts a barcode with random settings into a document using
 * Aspose.Words and Aspose.Barcode.
 */
public class AddBarcodeToDoc {

	public static final void main(String args[]) throws Exception {
		try {
			// Open the template document
			Document doc = new Document(".\\Resources\\BarcodeDemo.docx");

			// Create a barcode with random properties
			BarCodeBuilder barCode = createBarCode();

			// Fill merge fields in the document to show barcode properties
			fillProperties(doc, barCode);
			// Insert the barcode image
			insertBarcodeImage(doc, barCode);
			doc.save(".\\Output\\OutputBarcodeDoc.doc");
			System.out.println("Barcode DOC created and saved at .\\Output\\OutputBarcodeDoc.doc");
		} catch (Exception ex) {
			System.err.println("Error in barcode creation: " + ex.getMessage());
		}
	}

	/**
	 * Called for every merge field encountered in the document. We can either
	 * return some data to the mail merge engine or do something else with the
	 * document. In this case we choose to modify cell formatting.
	 */
	private static void fillProperties(Document doc, BarCodeBuilder barCode)
			throws Exception {
		String[] fieldNames = new String[] { "SymbologyType", "CodeText",
				"SupplementData", "RotationAngle", "ImageQuality" };
		Object[] fieldValues = new Object[] {
				getNameOfEnumValue(Symbology.class, barCode.getSymbologyType()),
				barCode.getCodeText(),
				barCode.getSupplementData(),
				barCode.getRotationAngleF(),
				getNameOfEnumValue(ImageQualityMode.class,
						barCode.getImageQuality()) };

		// Execute mail merge
		doc.getMailMerge().execute(fieldNames, fieldValues);
	}

	/**
	 * Inserts the barcode into the document at the specified bookmark.
	 */
	private static void insertBarcodeImage(Document doc, BarCodeBuilder barCode)
			throws Exception {
		DocumentBuilder builder = new DocumentBuilder(doc);

		// Move to the appropriate table cell
		builder.moveToBookmark("BarcodeImage");
		// Insert the barcode image
		BufferedImage image = (BufferedImage) barCode.generateBarCodeImage();
		builder.insertImage(image);
	}

	/**
	 * Generates a barcode with random settings.
	 */
	private static BarCodeBuilder createBarCode() {
		// Instantiate a LinearBarCode object
		BarCodeBuilder barCode = new BarCodeBuilder();

		// Call necessary methods each of which returns a random value for the
		// corresponding barcode property
		barCode.setSymbologyType(getRandomSymbology());
		barCode.setCodeText(getRandomCodeText(barCode));
		barCode.setSupplementData(getRandomSupplementData());
		barCode.setImageQuality((int) getRandomImageQualityMode());
		barCode.setRotationAngleF(getRandomRotationAngle());

		return barCode;
	}

	/**
	 * Returns a random Symbology enumeration.
	 */
	private static int getRandomSymbology() {
		return getRandomEnumValue(Symbology.class);
	}

	/**
	 * Returns a random code text string.
	 */
	private static String getRandomCodeText(BarCodeBuilder barCode) {
		// Some barcode standard specifications have certain restrictions of the
		// number of digits in the code
		switch ((int) barCode.getSymbologyType()) {
		case (int) Symbology.EAN13:
			return getRandomDigits(12);
		case (int) Symbology.EAN8:
			return getRandomDigits(7);
		case (int) Symbology.UPCA:
		case (int) Symbology.UPCE:
			return getRandomDigits(10);
		default:
			return getRandomDigits(12);
		}
	}

	/**
	 * Returns a random supplement data string.
	 */
	private static String getRandomSupplementData() {
		// Random number between 10,000 and 100,000
		return String.valueOf((rnd.nextInt(90000) + 10000));
	}

	/**
	 * Returns a random angle between 0 and 359 degrees.
	 */
	private static int getRandomRotationAngle() {
		// Find next random angle.
		return rnd.nextInt(360);
	}

	/**
	 * Gets a random ImageQuality enumeration.
	 */
	private static long getRandomImageQualityMode() {
		return getRandomEnumValue(ImageQualityMode.class);
	}

	/**
	 * Aspose.Barcode for Java stores enumerations in classes. This method
	 * random enumeration value stored within the specified class. Not all of
	 * these classes have built-in methods to retrieve the name and value of the
	 * internal enumerations so we will extract them using reflection instead.
	 */
	private static int getRandomEnumValue(Class enumType) {
		try {
			// Get all fields of this class.
			Field[] fields = enumType.getFields();

			// Java implements nextInt similarly to .NET, the upper value is
			// exclusive so the result will always be one less
			// than the passed length.
			int num = rnd.nextInt(fields.length);

			// Get a random enumeration.
			Field field = fields[num];

			// Retrieve the value from a new instance of this class.
			Object enumObject = enumType.newInstance();

			// Return the value.
			return field.getInt(enumObject);
		}

		catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get the name of the enumeration from the specified class. The name to
	 * retrieve is which enum matches the passed value.
	 */
	private static String getNameOfEnumValue(Class enumType, long value) {
		try {
			// Get all fields in the class.
			Field[] fields = enumType.getFields();

			// / Create a new instance of the enumeration class.
			Object enumObject = enumType.newInstance();

			// Loop through all fields and find the enumeration whose value
			// matches.
			for (Field field : fields) {
				if (field.getLong(enumObject) == value)
					return field.getName();
			}

			// Couldn't find value, return empty string.
			return "";
		}

		catch (Exception e) {
			return "";
		}
	}

	/**
	 * Gets a random string digits of specified length.
	 */
	private static String getRandomDigits(int digits) {
		StringBuffer builder = new StringBuffer();

		for (int i = 0; i < digits; i++)
			builder.append(rnd.nextInt(10));

		return builder.toString();
	}

	// Used to create random settings for the barcode.
	private static Random rnd = new Random();
}
