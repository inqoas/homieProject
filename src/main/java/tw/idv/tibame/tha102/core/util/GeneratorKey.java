package tw.idv.tibame.tha102.core.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

public class GeneratorKey {
	 public static void main(String[] args) {
	        try {
	            // 創建 AES 密鑰生成器
	            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

	            // 設置密鑰位元數（256 位）
	            keyGenerator.init(256);

	            // 生成隨機密鑰
	            SecretKey secretKey = keyGenerator.generateKey();

	            // 將密鑰轉換為 Base64 字串，便於存儲和傳遞
	            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

	            // 輸出密鑰
//	            System.out.println("隨機生成的密鑰：" + encodedKey);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	    }	
}
