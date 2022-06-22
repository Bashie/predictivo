package predictivo.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptor {

	private static final String key = "A009C1A485912C6AE630D3E744240B04";
	public static final String pText = "Con esto encripto!!!!";
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
	private static final int IV_LENGTH_BYTE = 12;
	public static final int ITERATION_COUNT = 1000;
	public static final int KEY_LENGTH = 256;
	private static final int TAG_LENGTH_BIT = 128;
	private static final int SALT_LENGTH_BYTE = 16;

	public static String cryptWithCipher(String pass) {
		try {
			byte[] salt = decodeHexString(key);
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

	        SecretKey aesKeyFromPassword = getAESKeyFromPassword(pass.toCharArray(), salt);
	        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
	        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

	        byte[] cipherText = cipher.doFinal(pText.getBytes(StandardCharsets.UTF_8));

	        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
	            .put(iv)
	            .put(salt)
	            .put(cipherText)
	            .array();
	        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static int toDigit(char hexChar) {
		int digit = Character.digit(hexChar, 16);
		if (digit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
		}
		return digit;
	}

	public static byte hexToByte(String hexString) {
		int firstDigit = toDigit(hexString.charAt(0));
		int secondDigit = toDigit(hexString.charAt(1));
		return (byte) ((firstDigit << 4) + secondDigit);
	}

	public static byte[] decodeHexString(String hexString) {
		if (hexString.length() % 2 == 1) {
			throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
		}

		byte[] bytes = new byte[hexString.length() / 2];
		for (int i = 0; i < hexString.length(); i += 2) {
			bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
		}
		return bytes;
	}

	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static String hex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte b : bytes) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}

	public static String decryptWithCipher(String pass, String encryptedPass) {
		try {
			byte[] decode = Base64.getDecoder().decode(encryptedPass.getBytes(StandardCharsets.UTF_8));
	        ByteBuffer bb = ByteBuffer.wrap(decode);

	        byte[] iv = new byte[IV_LENGTH_BYTE];
	        bb.get(iv);

	        byte[] salt = new byte[SALT_LENGTH_BYTE];
	        bb.get(salt);

	        byte[] cipherText = new byte[bb.remaining()];
	        bb.get(cipherText);
	        SecretKey aesKeyFromPassword = getAESKeyFromPassword(pass.toCharArray(), salt);

	        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

	        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

	        byte[] plainText = cipher.doFinal(cipherText);

	        return new String(plainText, StandardCharsets.UTF_8);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
