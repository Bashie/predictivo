package predictivo.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptor {

	private static final String key = "Bar12345Bar12345Bar12345Bar12345"; 

	public static String cryptWithCipher(String pass) {
		try {
			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(pass.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
            
		} catch (Exception  ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String decryptWithCipher(String pass) {
		try {
			SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] encrypted = Base64.getDecoder().decode(pass);
            return new String(cipher.doFinal(encrypted)); 
		} catch (Exception  ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
