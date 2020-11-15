package com.nsi.qrcodegen;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class QRCodeGen {

	public static void main(String[] args) throws Exception {
		String text = args[0];
		String type = args[1];
		int taille = Integer.valueOf(args[2]);
		System.out.println(generateQRCodeImage(text, type, taille));
	}

	public static String generateQRCodeImage(String barcodeText, String type, int taille) throws Exception {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, taille, taille);

		return encodeToString(MatrixToImageWriter.toBufferedImage(bitMatrix), type);
	}

	public static String encodeToString(BufferedImage image, String type) throws IOException {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ImageIO.write(image, type, bos);
		byte[] imageBytes = bos.toByteArray();

		BASE64Encoder encoder = new BASE64Encoder();
		imageString = encoder.encode(imageBytes);

		bos.close();

		return imageString;
	}

}
