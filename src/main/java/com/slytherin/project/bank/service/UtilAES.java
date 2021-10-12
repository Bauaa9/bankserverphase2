package com.slytherin.project.bank.service;

//Java program to demonstrate the creation
//of Encryption and Decryption with Java AES
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class UtilAES {
	// Class private variables
	private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";

	private static final String SALT = "ssshhhhhhhhhhh!!!!";

	// This method use to encrypt to string
	public static String encrypt(String strToEncrypt) {
		try {

			// Create default byte array
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			// Create SecretKeyFactory object
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			// Create KeySpec object and assign with
			// constructor
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			// Return encrypted string
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	// This method use to decrypt to string
	public static String decrypt(String strToDecrypt) {
		try {

			// Default byte array
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			// Create IvParameterSpec object and assign with
			// constructor
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			// Create SecretKeyFactory Object
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			// Create KeySpec object and assign with
			// constructor
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			// Return decrypted string
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
	
	public static void main(String[] args) {
		// Create String variables
		String originalString = "5678 9012 3456 7898";
		String originalString1 = "2021-12";
		String originalString2 = "123";
		String originalString3 = "rohit ghodke";
		String originalString4 = "credit";
		// Call encryption method
		String encryptedString = encrypt(originalString);
		String encryptedString1 = encrypt(originalString1);
		String encryptedString2 = encrypt(originalString2);
		String encryptedString3 = encrypt(originalString3);
		String encryptedString4 = encrypt(originalString4);
		// Call decryption method
		String decryptedString = decrypt(encryptedString);

		// Print all strings
		System.out.println(originalString);
		System.out.println(encryptedString);
		System.out.println(decryptedString);
		
		System.out.println(encryptedString1);
		System.out.println(encryptedString2);
		System.out.println(encryptedString3);
		System.out.println(encryptedString4);
	}
}
