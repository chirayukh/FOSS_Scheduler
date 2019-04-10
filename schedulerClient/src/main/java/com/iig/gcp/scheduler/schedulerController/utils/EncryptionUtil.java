package com.iig.gcp.scheduler.schedulerController.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

	private static String master_key_path;

	/**
	 * @param value
	 */
	@Value("${master.key.path}")
	public void setMasterKeyPath(String value) {
		EncryptionUtil.master_key_path = value;
	}

	/**
	 * @return String
	 */
	public String getMaster_key_path() {
		return master_key_path;
	}

	/**
	 * @param master_key_path
	 */
	public void setMaster_key_path(String master_key_path) {
		EncryptionUtil.master_key_path = master_key_path;
	}

	/**
	 * @param keyStr
	 * @return SecretKey
	 */
	public static SecretKey decodeKeyFromString(String keyStr) {
		/* Decodes a Base64 encoded String into a byte array */
		String keyString = keyStr.trim();
		byte[] decodedKey = Base64.getDecoder().decode(keyString);

		/* Constructs a secret key from the given byte array */
		SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

		return secretKey;
	}

	/**
	 * @param pathname
	 * @return String
	 * @throws IOException
	 */
	public static String readFile(String pathname) throws IOException {
		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
		String lineSeparator = System.getProperty("line.separator");

		try {
			if (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine());
			}
			while (scanner.hasNextLine()) {
				fileContents.append(lineSeparator + scanner.nextLine());
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}

	/**
	 * @param byteCipherText
	 * @param secKey
	 * @return String
	 * @throws Exception
	 */
	public static String decryptText(byte[] byteCipherText, SecretKey secKey) throws Exception {

		// AES defaults to AES/ECB/PKCS5Padding in Java 7

		Cipher aesCipher = Cipher.getInstance("AES");

		aesCipher.init(Cipher.DECRYPT_MODE, secKey);

		byte[] bytePlainText = aesCipher.doFinal(byteCipherText);

		return new String(bytePlainText);

	}

	/**
	 * @param plainText
	 * @param key
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] encryptText(String plainText, String key) throws Exception {

		// AES defaults to AES/ECB/PKCS5Padding in Java 7
		SecretKey secKey = decodeKeyFromString(key);

		Cipher aesCipher = Cipher.getInstance("AES");

		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);

		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());

		return byteCipherText;

	}

	/**
	 * @param encrypted_key
	 * @param encrypted_password
	 * @return String
	 * @throws Exception
	 */
	public static String decyptPassword(byte[] encrypted_key, byte[] encrypted_password) throws Exception {
		String content = readFile(master_key_path);
		SecretKey secKey = decodeKeyFromString(content);
		String decrypted_key = decryptText(encrypted_key, secKey);
		SecretKey secKey2 = decodeKeyFromString(decrypted_key);
		String password = decryptText(encrypted_password, secKey2);
		return password;

	}

}